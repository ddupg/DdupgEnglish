package com.ddupg.english.phometicsymbol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ddupg.english.R;
import com.ddupg.english.common.Nameable;
import com.qmuiteam.qmui.widget.textview.QMUISpanTouchFixTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import lombok.Getter;

public class PhoneticSymbolFragment extends Fragment implements Nameable {

  private Unbinder unbinder;

  @Getter
  private PhoneticSymbolAdapter phoneticSymbol;

  @BindView(R.id.phonetic_symbol_forward)
  Button forwardBtn;

  @BindView(R.id.phonetic_symbol_backward)
  Button backBtn;

  @BindView(R.id.phonetic_symbol_text)
  QMUISpanTouchFixTextView textView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_phonetic_symbol, container, false);
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

  public void changePhoneticSymbol(PhoneticSymbolAdapter phoneticSymbol) {
    this.phoneticSymbol = phoneticSymbol;
    refreshView();
  }

  private void refreshView() {
    if (forwardBtn != null) {
      forwardBtn.setEnabled(phoneticSymbol.hasFormer());
    }
    if (backBtn != null) {
      backBtn.setEnabled(phoneticSymbol.hasLatter());
    }
    if (textView != null) {
      textView.setText(phoneticSymbol.getResource().getShow());
    }
  }

  @OnClick(R.id.phonetic_symbol_forward)
  public void onForwardBtnClick() {
    changePhoneticSymbol(phoneticSymbol.getFormer());
  }

  @OnClick(R.id.phonetic_symbol_backward)
  public void onBackBtnClick() {
    changePhoneticSymbol(phoneticSymbol.getLatter());
  }

  @Override
  public String name() {
    return "PhoneticSymbolFragment";
  }
}
