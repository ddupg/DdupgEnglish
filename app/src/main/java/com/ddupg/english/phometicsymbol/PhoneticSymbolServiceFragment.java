package com.ddupg.english.phometicsymbol;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddupg.english.R;
import com.ddupg.english.common.NameableFragment;
import com.ddupg.english.main.TopbarListener;
import com.ddupg.english.util.SchedulerProvider;
import com.kongzue.stacklabelview.StackLabel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhoneticSymbolServiceFragment extends NameableFragment implements PhoneticContract.View {

  private static final String NAME = "Phonetic Symbol";

  private Unbinder unbinder;

  PhoneticSymbolFragment phoneticSymbolFragment;

  @BindView(R.id.phonetic_list)
  RecyclerView phoneticListView;

  PhoneticContract.Presenter presenter;

  private ListAdapter listAdapter;

  private List<PhoneticSymbolAdapter> resourceChain = Collections.EMPTY_LIST;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_phonetic_symbol_service, container, false);
    unbinder = ButterKnife.bind(this, root);
    listAdapter = new ListAdapter();
    phoneticListView.setAdapter(listAdapter);

    new PhoneticSymbolPresenter(getContext(), this, SchedulerProvider.getInstance());

    phoneticSymbolFragment = new PhoneticSymbolFragment();
    initView();
    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    changeTopbar();
    presenter.load();
  }

  private void changeTopbar() {
    FragmentActivity activity = getActivity();
    if (activity instanceof TopbarListener) {
      ((TopbarListener) activity).onTopbarChange(topbar -> topbar.setTitle(NAME));
    }
  }

  private void initView() {

  }

  @Override
  public void show(List<ResourcefulPhoneticSymbol> phoneticSymbols) {
    List<PhoneticSymbolAdapter> chain = new ArrayList<>();
    phoneticSymbols.stream().map(PhoneticSymbolAdapter::new).forEach(a -> a.addToChain(chain));
    this.resourceChain = chain;
    listAdapter.notifyDataSetChanged();
    Log.i(getTag(), "resource size: " + phoneticSymbols.size());
  }

  @Override
  public void showError(String msg) {

  }

  @Override
  public void setPresenter(PhoneticContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public String name() {
    return NAME;
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
      PhoneticSymbolAdapter phonetic = resourceChain.get(position);
      ListItemViewHolder holder = (ListItemViewHolder) h;
      holder.phoneticCardShow.setText(phonetic.getResource().getShow());
      StackLabel tagLabels = holder.itemView.findViewById(R.id.tag_labels);
      tagLabels.setLabels(phonetic.getResource().getTags());
      holder.itemView.setOnClickListener(v -> {
        phoneticSymbolFragment.changePhoneticSymbol(phonetic);
        getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, phoneticSymbolFragment, phoneticSymbolFragment.getTag())
            .addToBackStack(NAME)
            .commit();
      });
    }

    @Override
    public int getItemCount() {
      return resourceChain.size();
    }
  }
}
