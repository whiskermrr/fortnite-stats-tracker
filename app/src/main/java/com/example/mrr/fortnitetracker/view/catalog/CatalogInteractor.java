package com.example.mrr.fortnitetracker.view.catalog;

import com.example.rxjava_fortnite_api.FortniteApi;
import com.example.rxjava_fortnite_api.models.catalog.Catalog;

import io.reactivex.Single;

public class CatalogInteractor implements CatalogContracts.Interactor {

    private FortniteApi fortniteApi;

    public CatalogInteractor(FortniteApi fortniteApi) {
        this.fortniteApi = fortniteApi;
    }


    @Override
    public Single<Catalog> getCatalog() {
        return fortniteApi.getCatalog();
    }
}
