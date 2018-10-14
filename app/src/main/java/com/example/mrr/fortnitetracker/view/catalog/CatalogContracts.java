package com.example.mrr.fortnitetracker.view.catalog;

import com.example.mrr.fortnitetracker.models.catalog.CatalogEntryViewModel;
import com.example.mrr.fortnitetracker.models.catalog.CatalogViewModel;

import java.util.List;

import io.reactivex.Single;

public interface CatalogContracts {

    interface View {
        void onSuccess(CatalogViewModel catalog);
        void onFailure(String message);
        void initWeeklyStorefront(List<CatalogEntryViewModel> entries);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void getCurrentShop();
        void cycleWeeklyOffer();
        void unsubscribe();
    }

    interface Interactor {
        Single<CatalogViewModel> getCatalog();
    }
}
