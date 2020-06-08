package com.ddupg.english.phometic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ddupg.english.R;
import com.qmuiteam.qmui.widget.textview.QMUISpanTouchFixTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import lombok.Getter;

public class PhoneticSignFragment extends Fragment implements PhoneticSignContract.View {

  private Unbinder unbinder;

  private PhoneticSignContract.Presenter presenter;

  @Getter
  private PhoneticSign phoneticSign;

  @BindView(R.id.phonetic_sign_forward)
  Button forwardBtn;

  @BindView(R.id.phonetic_sign_backward)
  Button backBtn;

  @BindView(R.id.phonetic_sign_text)
  QMUISpanTouchFixTextView textView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_phonetic_sign, container, false);
    unbinder = ButterKnife.bind(this, root);
    refreshView();
    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    refreshView();
  }

  public void changePhoneticSign(PhoneticSign phoneticSign) {
    this.phoneticSign = phoneticSign;
    refreshView();
  }

  private void refreshView() {
    if (forwardBtn != null) {
      forwardBtn.setEnabled(phoneticSign.hasFormer());
    }
    if (backBtn != null) {
      backBtn.setEnabled(phoneticSign.hasLatter());
    }
    if (textView != null) {
      textView.setText(phoneticSign.getResource().getShow());
    }
  }

  @OnClick(R.id.phonetic_sign_forward)
  public void onForwardBtnClick() {
    changePhoneticSign(phoneticSign.getFormer());
  }

  @OnClick(R.id.phonetic_sign_backward)
  public void onBackBtnClick() {
    changePhoneticSign(phoneticSign.getLatter());
  }

  @Override
  public void show(PhoneticSign sign) {

  }

  @Override
  public void setPresenter(PhoneticSignContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
