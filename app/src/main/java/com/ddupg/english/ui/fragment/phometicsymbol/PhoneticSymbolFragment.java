package com.ddupg.english.ui.fragment.phometicsymbol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ddupg.english.R;
import com.ddupg.english.ui.fragment.Nameable;
import com.qmuiteam.qmui.widget.textview.QMUISpanTouchFixTextView;

import lombok.Getter;

public class PhoneticSymbolFragment extends Fragment implements Nameable {

  @Getter
  private PhoneticSymbolAdapter phoneticSymbol;

  private Button forwardBtn;

  private Button backBtn;

  private QMUISpanTouchFixTextView textView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_phonetic_symbol, container, false);
    forwardBtn = root.findViewById(R.id.phonetic_symbol_forward);
    forwardBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changePhoneticSymbol(phoneticSymbol.getFormer());
      }
    });
    backBtn = root.findViewById(R.id.phonetic_symbol_backward);
    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changePhoneticSymbol(phoneticSymbol.getLatter());
      }
    });
    textView = root.findViewById(R.id.phonetic_symbol_text);
    refreshView();
    return root;
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
      textView.setText(name() + " " + phoneticSymbol.getName() + " " + phoneticSymbol.getDetail());
    }
  }

  @Override
  public String name() {
    return "PhoneticSymbolFragment";
  }
}
