package com.ddupg.english;

import android.app.Application;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

public class DdupgEnglishApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    QMUISwipeBackActivityManager.init(this);
  }
}
