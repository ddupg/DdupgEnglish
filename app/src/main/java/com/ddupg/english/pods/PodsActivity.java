package com.ddupg.english.pods;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ddupg.english.R;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.ButterKnife;

public class PodsActivity extends QMUIFragmentActivity {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pods);
    ButterKnife.bind(this);
    QMUIStatusBarHelper.translucent(this);
  }
}
