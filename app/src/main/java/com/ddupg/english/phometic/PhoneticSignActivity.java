package com.ddupg.english.phometic;

import android.os.Bundle;

import com.ddupg.english.R;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneticSignActivity extends QMUIFragmentActivity {

  @BindView(R.id.phonetic_toolbar)
  QMUITopBarLayout toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_phonetic_sign);
    ButterKnife.bind(this);
    QMUIStatusBarHelper.translucent(this);

    toolbar.setTitle("Phonetic Sign");

    getSupportFragmentManager().beginTransaction()
        .replace(R.id.phonetic_sign_container, new PhoneticSignListFragment())
        .commit();
  }
}
