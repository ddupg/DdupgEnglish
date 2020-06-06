package com.ddupg.english.phometicsymbol;

import java.util.List;

import lombok.Data;

@Data
public class ResourcefulPhoneticSymbol {

  private RawPhoneticSymbol raw;

  private PhoneticSymbolRepository repository;

  public List<String> tags;

  public ResourcefulPhoneticSymbol(RawPhoneticSymbol raw, PhoneticSymbolRepository repository) {
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
