package com.example.mrr.fortnitetracker.view.catalog;

import com.example.rxjava_fortnite_api.models.catalog.Catalog;

import io.reactivex.Single;

public interface CatalogContracts {

    interface View {
        void onSuccess();
        void onFailure(String message);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void getCurrentShop();
        void unsubscribe();
    }

    interface Interactor {
        Single<Catalog> getCatalog();
    }
}
