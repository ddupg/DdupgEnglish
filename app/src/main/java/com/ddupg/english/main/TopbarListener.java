package com.ddupg.english.main;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.function.Consumer;

public interface TopbarListener {

  void onTopbarChange(Consumer<QMUITopBarLayout> topbar);
}
