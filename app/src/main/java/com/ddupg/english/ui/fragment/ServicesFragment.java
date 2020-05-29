package com.ddupg.english.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddupg.english.R;
import com.ddupg.english.ui.ServicesAdapter;
import com.ddupg.english.ui.TopbarListener;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.function.Consumer;

public class ServicesFragment extends Fragment {

  public final static String TAG = ServicesFragment.class.getSimpleName();

  private ServicesAdapter servicesAdapter;

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_services, container, false);
    RecyclerView serviceList = root.findViewById(R.id.serviceList);
    servicesAdapter = new ServicesAdapter();
    serviceList.setAdapter(servicesAdapter);
    serviceList.setLayoutManager(new LinearLayoutManager(getContext()));
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    changeTopbar();
    servicesAdapter.setActivity(getActivity());
  }

  private void changeTopbar() {
    FragmentActivity activity = getActivity();
    if (activity instanceof TopbarListener) {
      ((TopbarListener) activity).onTopbarChange(new Consumer<QMUITopBarLayout>() {
        @Override
        public void accept(QMUITopBarLayout topbar) {
          topbar.setTitle("Ddupg English");
        }
      });
    }
  }
}
