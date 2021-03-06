package com.ddupg.english.phometic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ddupg.english.R;
import com.ddupg.english.util.SchedulerProvider;
import com.kongzue.stacklabelview.StackLabel;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhoneticSignListFragment extends Fragment implements PhoneticSignContract.ListView {

  private static final String NAME = "Phonetic Sign";

  private Unbinder unbinder;

  PhoneticSignFragment phoneticSignFragment;

  @BindView(R.id.phonetic_list)
  RecyclerView phoneticListView;

  PhoneticSignContract.Presenter presenter;

  private ListAdapter listAdapter;

  private List<PhoneticSign> chain = Collections.EMPTY_LIST;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_phonetic_sign_service, container, false);
    unbinder = ButterKnife.bind(this, root);
    listAdapter = new ListAdapter();
    phoneticListView.setAdapter(listAdapter);

    new PhoneticPresenter(getContext(), this, SchedulerProvider.getInstance());

    phoneticSignFragment = new PhoneticSignFragment();
    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    presenter.load();
  }

  @Override
  public void show(List<PhoneticSign> chain) {
    this.chain = chain;
    listAdapter.notifyDataSetChanged();
    Log.i(getTag(), "resource size: " + chain.size());
  }

  @Override
  public void showError(String msg) {

  }

  @Override
  public void setPresenter(PhoneticSignContract.Presenter presenter) {
    this.presenter = presenter;
  }

  public class ListItemViewHolder extends RecyclerView.ViewHolder {

    private TextView phoneticCardShow;

    public ListItemViewHolder(@NonNull View view) {
      super(view);
      phoneticCardShow = view.findViewById(R.id.phonetic_card_show);
    }
  }

  public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_phonetic_list_item, parent, false);
      ListItemViewHolder holder = new ListItemViewHolder(view);
      return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int position) {
      PhoneticSign phonetic = chain.get(position);
      ListItemViewHolder holder = (ListItemViewHolder) h;
      holder.phoneticCardShow.setText(phonetic.getResource().getShow());
      StackLabel tagLabels = holder.itemView.findViewById(R.id.tag_labels);
      tagLabels.setLabels(phonetic.getResource().getTags());
      holder.itemView.setOnClickListener(v -> {
        phoneticSignFragment.changePhoneticSign(phonetic);
        getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.phonetic_sign_container, phoneticSignFragment, phoneticSignFragment.getTag())
            .addToBackStack(NAME)
            .commit();
      });
    }

    @Override
    public int getItemCount() {
      return chain.size();
    }
  }
}
