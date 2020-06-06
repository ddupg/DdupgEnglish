package com.ddupg.english.phometic;

import java.util.List;

import lombok.Data;

@Data
public class ResourcefulPhoneticSign {

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
