package com.ddupg.english.phometic;

import java.util.List;

public class PhoneticSignAdapter {

  private List<PhoneticSignAdapter> chain;

  private ResourcefulPhoneticSign resource;

  public PhoneticSignAdapter(ResourcefulPhoneticSign resource) {
    this.resource = resource;
  }

  public PhoneticSignAdapter addToChain(List<PhoneticSignAdapter> chain) {
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

  public PhoneticSignAdapter getFormer() {
    if (!hasFormer()) {
      throw new RuntimeException("no the former");
    }
    return chain.get(resource.getId() - 1);
  }

  public PhoneticSignAdapter getLatter() {
    if (!hasLatter()) {
      throw new RuntimeException("no the latter");
    }
    return chain.get(resource.getId() + 1);
  }

  public ResourcefulPhoneticSign getResource() {
    return resource;
  }
}
