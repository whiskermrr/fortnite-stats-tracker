package com.example.mrr.fortnitetracker.view.catalog;

import com.example.mrr.fortnitetracker.models.catalog.CatalogViewModel;

import io.reactivex.Single;

public interface CatalogContracts {

    interface View {
        void onSuccess(CatalogViewModel catalog);
        void onFailure(String message);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void getCurrentShop();
        void unsubscribe();
    }

    interface Interactor {
        Single<CatalogViewModel> getCatalog();
    }
}
