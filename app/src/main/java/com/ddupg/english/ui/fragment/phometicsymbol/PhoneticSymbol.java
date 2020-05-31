package com.ddupg.english.ui.fragment.phometicsymbol;

import lombok.Getter;

@Getter
public class PhoneticSymbol {

  private Integer id;

  private String name;

  private String detail;

  public PhoneticSymbol(Integer id, String name, String detail) {
    this.id = id;
    this.name = name;
    this.detail = detail;
  }
}
