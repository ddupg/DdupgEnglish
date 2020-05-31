package com.ddupg.english.ui.fragment.phometicsymbol;

import java.util.List;

public class PhoneticSymbolAdapter extends PhoneticSymbol {

  private List<PhoneticSymbolAdapter> chain;

  public PhoneticSymbolAdapter(Integer id, String name, String detail) {
    super(id, name, detail);
  }

  public PhoneticSymbolAdapter addToChain(List<PhoneticSymbolAdapter> chain) {
    this.chain = chain;
    chain.add(this);
    return this;
  }

  public boolean hasFormer() {
    return getId() > 0;
  }

  public boolean hasLatter() {
    return getId() < chain.size() - 1;
  }

  public PhoneticSymbolAdapter getFormer() {
    if (!hasFormer()) {
      throw new RuntimeException("no the former");
    }
    return chain.get(getId() - 1);
  }

  public PhoneticSymbolAdapter getLatter() {
    if (!hasLatter()) {
      throw new RuntimeException("no the latter");
    }
    return chain.get(getId() + 1);
  }
}
