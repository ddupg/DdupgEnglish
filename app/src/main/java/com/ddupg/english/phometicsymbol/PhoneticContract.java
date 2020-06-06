package com.ddupg.english.phometicsymbol;

import com.ddupg.english.common.BasePresenter;
import com.ddupg.english.common.BaseView;

import java.util.List;

public interface PhoneticContract {

  interface View extends BaseView<Presenter> {
    void show(List<ResourcefulPhoneticSymbol> phoneticSymbols);

    void showError(String msg);
  }

  interface Presenter extends BasePresenter {
    void changeTag(String tag);

    void load();
  }
}
