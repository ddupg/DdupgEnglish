package com.ddupg.english.phometicsymbol;

import java.util.List;

public class PhoneticSymbolAdapter {

  private List<PhoneticSymbolAdapter> chain;

  private ResourcefulPhoneticSymbol resource;

  public PhoneticSymbolAdapter(ResourcefulPhoneticSymbol resource) {
    this.resource = resource;
  }

  public PhoneticSymbolAdapter addToChain(List<PhoneticSymbolAdapter> chain) {
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

  public PhoneticSymbolAdapter getFormer() {
    if (!hasFormer()) {
      throw new RuntimeException("no the former");
    }
    return chain.get(resource.getId() - 1);
  }

  public PhoneticSymbolAdapter getLatter() {
    if (!hasLatter()) {
      throw new RuntimeException("no the latter");
    }
    return chain.get(resource.getId() + 1);
  }

  public ResourcefulPhoneticSymbol getResource() {
    return resource;
  }
}
