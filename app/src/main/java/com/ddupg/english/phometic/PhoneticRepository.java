package com.ddupg.english.phometic;

import android.content.Context;

import com.ddupg.english.R;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
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

  public Flowable<List<ResourcefulPhoneticSign>> loadResourcefulPhoneticSigns() {
    return Flowable.fromCallable(() -> resourcefulPhoneticSigns);
  }

  public List<ResourcefulPhoneticSign> filterByTag(String tag) {
    return resourcefulPhoneticSigns.stream().filter(p -> p.getTags().contains(tag)).collect(Collectors.toList());
  }

  public List<String> convertTags(List<Integer> tagIds) {
    return tagIds.stream().map(tags::get).collect(Collectors.toList());
  }

  @Data
  public static class Resource {

    private Map<Integer, String> tags;

    private List<RawPhoneticSign> phonetic;
  }
}
