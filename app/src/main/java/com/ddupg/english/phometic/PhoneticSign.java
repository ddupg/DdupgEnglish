package com.ddupg.english.phometic;

import java.util.List;

public class PhoneticSign {

  private List<PhoneticSign> chain;

  private PhoneticRepository.ResourcefulPhoneticSign resource;

  public PhoneticSign(PhoneticRepository.ResourcefulPhoneticSign resource) {
    this.resource = resource;
  }

  public PhoneticSign addToChain(List<PhoneticSign> chain) {
    this.chain = chain;
    chain.add(this);
    return this;
  }

  public boolean hasFormer() {
    return resource.getId() > 0;
  }

  public boolean hasLatter() {
    return resource.getId() < chain.size() - 1;
  }

  public PhoneticSign getFormer() {
    if (!hasFormer()) {
      throw new RuntimeException("no the former");
    }
    return chain.get(resource.getId() - 1);
  }

  public PhoneticSign getLatter() {
    if (!hasLatter()) {
      throw new RuntimeException("no the latter");
    }
    return chain.get(resource.getId() + 1);
  }

  public PhoneticRepository.ResourcefulPhoneticSign getResource() {
    return resource;
  }
}
