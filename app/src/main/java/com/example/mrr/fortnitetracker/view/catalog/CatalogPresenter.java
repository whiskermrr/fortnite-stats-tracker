package com.example.mrr.fortnitetracker.view.catalog;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CatalogPresenter implements CatalogContracts.Presenter {

    private CatalogContracts.View view;
    private CatalogContracts.Interactor catalogInteractor;

    private CompositeDisposable disposables;

    public CatalogPresenter(CatalogContracts.View view, CatalogContracts.Interactor catalogInteractor) {
        this.view = view;
        this.catalogInteractor = catalogInteractor;
        disposables = new CompositeDisposable();
    }


    @Override
    public void getCurrentShop() {
        disposables.add(
                catalogInteractor.getCatalog()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            view.onSuccess();
                        }, error -> {
                            view.onFailure(error.getMessage());
                        })
        );
    }

    @Override
    public void unsubscribe() {
        disposables.dispose();
    }
}
