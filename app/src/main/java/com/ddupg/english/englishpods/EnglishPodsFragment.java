package com.ddupg.english.englishpods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.ddupg.english.R;
import com.ddupg.english.main.TopbarListener;
import com.ddupg.english.common.NameableFragment;

public class EnglishPodsFragment extends NameableFragment {

  private static final String NAME = "EnglishPods";

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_english_pods_service, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    changeTopbar();
    TextView text = view.findViewById(R.id.service_text);
    text.setText(NAME);
  }

  private void changeTopbar() {
    FragmentActivity activity = getActivity();
    if (activity instanceof TopbarListener) {
      ((TopbarListener) activity).onTopbarChange(topbar -> topbar.setTitle(NAME));
    }
  }

  @Override
  public String name() {
    return NAME;
  }
}
