package com.ddupg.english.ui.fragment.phometicsymbol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.ddupg.english.R;
import com.ddupg.english.ui.TopbarListener;
import com.ddupg.english.ui.fragment.NameableFragment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.ddupg.english.ui.fragment.phometicsymbol.PhoneticSymbolFactory.listItemView;
import static com.ddupg.english.ui.fragment.phometicsymbol.PhoneticSymbolFactory.listItemViewListener;

public class PhoneticSymbolServiceFragment extends NameableFragment {

  private static final String NAME = "Phonetic Symbol";

  PhoneticSymbolFragment phoneticSymbolFragment;

  private QMUIGroupListView phoneticSymbolsView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_phonetic_symbol_service, container, false);
    phoneticSymbolFragment = new PhoneticSymbolFragment();
    phoneticSymbolsView = root.findViewById(R.id.phonetic_symbol_list);
    initView();
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    changeTopbar();
  }

  private void changeTopbar() {
    FragmentActivity activity = getActivity();
    if (activity instanceof TopbarListener) {
      ((TopbarListener) activity).onTopbarChange(new Consumer<QMUITopBarLayout>() {
        @Override
        public void accept(QMUITopBarLayout topbar) {
          topbar.setTitle(NAME);
        }
      });
    }
  }

  private void initView() {
    List<PhoneticSymbolAdapter> phoneticSymbolChain = new ArrayList<>();
    FragmentActivity activity = getActivity();
    PhoneticSymbolAdapter p1 = new PhoneticSymbolAdapter(0, "前元音1", "第1个前元音");
    PhoneticSymbolAdapter p2 = new PhoneticSymbolAdapter(1, "前元音2", "第2个前元音");
    QMUIGroupListView.newSection(getContext())
        .setTitle("前元音")
        .addItemView(listItemView(phoneticSymbolsView, p1),
            listItemViewListener(activity, p1, phoneticSymbolFragment))
        .addItemView(listItemView(phoneticSymbolsView, p2),
            listItemViewListener(activity, p2, phoneticSymbolFragment))
        .addTo(phoneticSymbolsView);

    PhoneticSymbolAdapter p3 = new PhoneticSymbolAdapter(2, "辅音1", "第1个辅音");
    PhoneticSymbolAdapter p4 = new PhoneticSymbolAdapter(3, "辅音2", "第2个辅音");
    QMUIGroupListView.newSection(getContext())
        .setTitle("辅音")
        .addItemView(listItemView(phoneticSymbolsView, p3),
            listItemViewListener(activity, p3, phoneticSymbolFragment))
        .addItemView(listItemView(phoneticSymbolsView, p4),
            listItemViewListener(activity, p4, phoneticSymbolFragment))
        .addTo(phoneticSymbolsView);

    p1.addToChain(phoneticSymbolChain);
    p2.addToChain(phoneticSymbolChain);
    p3.addToChain(phoneticSymbolChain);
    p4.addToChain(phoneticSymbolChain);
  }

  @Override
  public String name() {
    return NAME;
  }
}
