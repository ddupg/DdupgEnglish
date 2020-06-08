package com.ddupg.english.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddupg.english.R;
import com.ddupg.english.phometic.PhoneticSignActivity;
import com.ddupg.english.pods.PodsActivity;
import com.ddupg.english.words.WordsActivity;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends QMUIFragmentActivity {

  @BindView(R.id.main_topbar)
  QMUITopBarLayout topbar;

  @BindView(R.id.service_list)
  RecyclerView serviceList;

  private ServicesAdapter servicesAdapter;

  @BindString(R.string.app_name)
  String APP_NAME;

  @BindString(R.string.title_activity_phonetic_sign)
  String SERVICE_PHONETIC_SIGN;

  @BindString(R.string.title_activity_words)
  String SERVICE_WORDS;

  @BindString(R.string.title_activity_pods)
  String SERVICE_PODS;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    QMUIStatusBarHelper.translucent(this);

    topbar.setTitle(SERVICE_PHONETIC_SIGN);

    servicesAdapter = new ServicesAdapter();
    serviceList.setAdapter(servicesAdapter);
  }

  public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    private List<ServiceItem> serviceItems;

    public ServicesAdapter() {
      serviceItems = new ArrayList<>();
      serviceItems.add(new ServiceItem(PhoneticSignActivity.class, SERVICE_PHONETIC_SIGN));
      serviceItems.add(new ServiceItem(WordsActivity.class, SERVICE_WORDS));
      serviceItems.add(new ServiceItem(PodsActivity.class, SERVICE_PODS));
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_service_item, parent, false);
      ServiceViewHolder holder = new ServiceViewHolder(view);
      return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ServiceViewHolder holder, int position) {
      ServiceItem service = serviceItems.get(position);
      holder.serviceName.setText(service.activity.getSimpleName());
      holder.itemView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, service.activity)));
    }

    @Override
    public int getItemCount() {
      return serviceItems.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {

      private TextView serviceName;

      public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        serviceName = itemView.findViewById(R.id.service_name);
      }
    }

    class ServiceItem {

      private Class<? extends Activity> activity;

      private String title;

      public ServiceItem(Class<? extends Activity> activity, String title) {
        this.activity = activity;
        this.title = title;
      }
    }
  }


}

