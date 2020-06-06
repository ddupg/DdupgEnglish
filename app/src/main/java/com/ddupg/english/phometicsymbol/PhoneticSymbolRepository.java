package com.ddupg.english.phometicsymbol;

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

public class PhoneticSymbolRepository {

  private static Gson GSON = new Gson();

  private Map<Integer, String> tags;

  private List<RawPhoneticSymbol> rawPhoneticSymbols;

  private List<ResourcefulPhoneticSymbol> resourcefulPhoneticSymbols;

  private Context context;

  private static PhoneticSymbolRepository INSTANCE;

  private PhoneticSymbolRepository(Context context) {
    this.context = context;
    loadResource();
  }

  public static synchronized PhoneticSymbolRepository getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = new PhoneticSymbolRepository(context);
    }
    return INSTANCE;
  }

  private void loadResource() {
    InputStream is = context.getResources().openRawResource(R.raw.kk_phonetic);
    Resource resource = GSON.fromJson(new InputStreamReader(is), Resource.class);
    rawPhoneticSymbols = resource.getPhonetic();
    for (int i = 0; i < rawPhoneticSymbols.size(); i++) {
      rawPhoneticSymbols.get(i).setId(i);
    }
    tags = resource.getTags();
    // do this last after init
    resourcefulPhoneticSymbols = rawPhoneticSymbols.stream()
        .map(r -> new ResourcefulPhoneticSymbol(r, this)).collect(Collectors.toList());
  }

  public Flowable<List<ResourcefulPhoneticSymbol>> loadResourcefulPhoneticSymbols() {
    return Flowable.fromCallable(() -> resourcefulPhoneticSymbols);
  }

  public List<ResourcefulPhoneticSymbol> filterByTag(String tag) {
    return resourcefulPhoneticSymbols.stream().filter(p -> p.getTags().contains(tag)).collect(Collectors.toList());
  }

  public List<String> convertTags(List<Integer> tagIds) {
    return tagIds.stream().map(tags::get).collect(Collectors.toList());
  }

  @Data
  public static class Resource {

    private Map<Integer, String> tags;

    private List<RawPhoneticSymbol> phonetic;
  }
}
