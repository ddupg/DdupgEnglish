package com.ddupg.english.phometic;

import com.ddupg.english.common.BasePresenter;
import com.ddupg.english.common.BaseView;

import java.util.List;

public interface PhoneticSignContract {

  interface ListView extends BaseView<Presenter> {
    void show(List<PhoneticSign> phoneticSigns);

    void showError(String msg);
  }

  interface View extends BaseView<Presenter> {
    void show(PhoneticSign sign);
  }

  interface Presenter extends BasePresenter {
    void changeTag(String tag);

    void load();
  }
}
