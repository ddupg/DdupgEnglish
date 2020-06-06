package com.ddupg.english.phometicsymbol;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ddupg.english.util.SchedulerProvider;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class PhoneticSymbolPresenter implements PhoneticContract.Presenter {

  private Context context;

  private PhoneticContract.View view;

  private SchedulerProvider schedulerProvider;

  private PhoneticSymbolRepository repository;

  @NonNull
  private CompositeDisposable compositeDisposable;

  public PhoneticSymbolPresenter(Context context, PhoneticContract.View view, SchedulerProvider schedulerProvider) {
    this.context = context;
    this.view = view;
    this.schedulerProvider = schedulerProvider;
    repository = PhoneticSymbolRepository.getInstance(context);
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
    Disposable disposable = repository.loadResourcefulPhoneticSymbols()
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
