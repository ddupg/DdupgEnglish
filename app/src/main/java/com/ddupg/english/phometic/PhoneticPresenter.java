package com.ddupg.english.phometic;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ddupg.english.util.SchedulerProvider;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class PhoneticPresenter implements PhoneticSignContract.Presenter {

  private Context context;

  private PhoneticSignContract.View view;

  private SchedulerProvider schedulerProvider;

  private PhoneticRepository repository;

  @NonNull
  private CompositeDisposable compositeDisposable;

  public PhoneticPresenter(Context context, PhoneticSignContract.View view, SchedulerProvider schedulerProvider) {
    this.context = context;
    this.view = view;
    this.schedulerProvider = schedulerProvider;
    repository = PhoneticRepository.getInstance(context);
    compositeDisposable = new CompositeDisposable();
    view.setPresenter(this);
  }

  @Override
  public void changeTag(String tag) {

  }

  @Override
  public void subscribe() {

  }

  @Override
  public void unsubscribe() {

  }

  @Override
  public void load() {
    compositeDisposable.clear();
    Disposable disposable = repository.loadResourcefulPhoneticSigns()
        .subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
        .subscribe(
            // onNext
            tasks -> view.show(tasks),
            // onError
            throwable -> view.showError("load resource error" + throwable));

    compositeDisposable.add(disposable);
  }
}
