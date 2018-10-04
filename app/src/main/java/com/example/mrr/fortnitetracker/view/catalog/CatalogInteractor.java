package com.example.mrr.fortnitetracker.view.catalog;

import com.example.mrr.fortnitetracker.mappers.CatalogMapper;
import com.example.mrr.fortnitetracker.models.catalog.CatalogViewModel;
import com.example.rxjava_fortnite_api.FortniteApi;

import io.reactivex.Single;

public class CatalogInteractor implements CatalogContracts.Interactor {

    private FortniteApi fortniteApi;

    public CatalogInteractor(FortniteApi fortniteApi) {
        this.fortniteApi = fortniteApi;
    }


    @Override
    public Single<CatalogViewModel> getCatalog() {
        return fortniteApi.getCatalog().flatMap(CatalogMapper::transform);
    }
}
