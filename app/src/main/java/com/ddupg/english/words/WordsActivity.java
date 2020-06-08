package com.ddupg.english.words;

import android.os.Bundle;

import com.ddupg.english.R;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.ButterKnife;

public class WordsActivity extends QMUIFragmentActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_words);
    ButterKnife.bind(this);
    QMUIStatusBarHelper.translucent(this);

  }
}
