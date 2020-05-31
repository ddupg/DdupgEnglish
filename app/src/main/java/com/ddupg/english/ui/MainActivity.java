package com.ddupg.english.ui;

import android.os.Bundle;
import android.view.View;

import com.ddupg.english.R;
import com.ddupg.english.ui.fragment.ServicesFragment;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends QMUIFragmentActivity implements TopbarListener {

  @BindView(R.id.topbar)
  QMUITopBarLayout topbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    QMUIStatusBarHelper.translucent(this);

    QMUIAlphaImageButton button = topbar.addLeftBackImageButton();
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    getSupportFragmentManager().beginTransaction()
        .add(R.id.container, new ServicesFragment(), ServicesFragment.TAG)
        .commit();
  }

  @Override
  public void onTopbarChange(Consumer<QMUITopBarLayout> consumer) {
    consumer.accept(topbar);
  }
}
