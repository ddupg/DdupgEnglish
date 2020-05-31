package com.ddupg.english.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ddupg.english.R;
import com.ddupg.english.ui.fragment.englishpods.EnglishPodsFragment;
import com.ddupg.english.ui.fragment.phometicsymbol.PhoneticSymbolFragment;
import com.ddupg.english.ui.fragment.ServiceItemFragment;
import com.ddupg.english.ui.fragment.words.WordsFragment;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

  private List<ServiceItem> serviceItems;

  private FragmentActivity activity;

  public ServicesAdapter() {
    serviceItems = new ArrayList<>();
    serviceItems.add(new ServiceItem(new PhoneticSymbolFragment()));
    serviceItems.add(new ServiceItem(new WordsFragment()));
    serviceItems.add(new ServiceItem(new EnglishPodsFragment()));
  }

  @NonNull
  @Override
  public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
    ServiceViewHolder holder = new ServiceViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull final ServiceViewHolder holder, int position) {
    final ServiceItem service = serviceItems.get(position);
    holder.serviceName.setText(service.fragment.getName());
    holder.serviceName.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        activity.getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, service.fragment)
            .addToBackStack(service.fragment.getName())
            .commit();
      }
    });
  }

  public void setActivity(FragmentActivity activity) {
    this.activity = activity;
  }

  @Override
  public int getItemCount() {
    return serviceItems.size();
  }

  public static class ServiceViewHolder extends RecyclerView.ViewHolder {

    private TextView serviceName;

    public ServiceViewHolder(@NonNull View itemView) {
      super(itemView);
      serviceName = itemView.findViewById(R.id.service_name);
    }
  }

  class ServiceItem {

    private ServiceItemFragment fragment;

    public ServiceItem(ServiceItemFragment fragment) {
      this.fragment = fragment;
    }
  }
}
