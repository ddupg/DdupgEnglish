package com.ddupg.english.phometic;

import android.content.Context;

import com.ddupg.english.R;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Flowable;
import lombok.Data;

public class PhoneticRepository {

  private static Gson GSON = new Gson();

  private Map<Integer, String> tags;

  private List<RawPhoneticSign> rawPhoneticSigns;

  private List<ResourcefulPhoneticSign> resourcefulPhoneticSigns;

  private Context context;

  private static PhoneticRepository INSTANCE;

  private PhoneticRepository(Context context) {
    this.context = context;
    loadResource();
  }

  public static synchronized PhoneticRepository getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = new PhoneticRepository(context);
    }
    return INSTANCE;
  }

  private void loadResource() {
    InputStream is = context.getResources().openRawResource(R.raw.kk_phonetic);
    Resource resource = GSON.fromJson(new InputStreamReader(is), Resource.class);
    rawPhoneticSigns = resource.getPhonetic();
    for (int i = 0; i < rawPhoneticSigns.size(); i++) {
      rawPhoneticSigns.get(i).setId(i);
    }
    tags = resource.getTags();
    // do this last after init
    resourcefulPhoneticSigns = rawPhoneticSigns.stream()
        .map(r -> new ResourcefulPhoneticSign(r, this)).collect(Collectors.toList());
  }

  public Flowable<List<PhoneticSign>> loadResourcefulPhoneticSigns() {
    return Flowable.fromCallable(() -> {
      List<PhoneticSign> chain = new ArrayList<>();
      resourcefulPhoneticSigns.stream().map(PhoneticSign::new).forEach(p -> p.addToChain(chain));
      return chain;
    });
  }

  public Flowable<List<PhoneticSign>> filterByTag(String tag) {
    return Flowable.fromCallable(() -> {
      List<PhoneticSign> chain = new ArrayList<>();
      resourcefulPhoneticSigns.stream()
          .filter(p -> p.getTags().contains(tag)).map(PhoneticSign::new).forEach(p -> p.addToChain(chain));
      return chain;
    });
  }

  public List<String> convertTags(List<Integer> tagIds) {
    return tagIds.stream().map(tags::get).collect(Collectors.toList());
  }

  @Data
  public static class Resource {

    private Map<Integer, String> tags;

    private List<RawPhoneticSign> phonetic;
  }

  @Data
  public static class RawPhoneticSign {

    private int id;

    private String show;

    private String audio;

    private String pic;

    private String video;

    private List<Integer> tags;
  }

  @Data
  public static class ResourcefulPhoneticSign {

    private RawPhoneticSign raw;

    private PhoneticRepository repository;

    public List<String> tags;

    public ResourcefulPhoneticSign(RawPhoneticSign raw, PhoneticRepository repository) {
      this.raw = raw;
      this.repository = repository;
      tags = repository.convertTags(raw.getTags());
    }

    public int getId() {
      return raw.getId();
    }

    public String getShow() {
      return raw.getShow();
    }

    public List<String> getTags() {
      return tags;
    }
  }
}
