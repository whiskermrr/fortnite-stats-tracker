package com.example.mrr.fortnitetracker.view.catalog;

import android.content.res.AssetManager;

import com.example.rxjava_fortnite_api.FortniteApi;
import com.example.rxjava_fortnite_api.models.catalog.Catalog;

import io.reactivex.Single;

public class CatalogInteractor implements CatalogContracts.Interactor {

    private FortniteApi fortniteApi;
    private AssetManager assetManager;

    public CatalogInteractor(FortniteApi fortniteApi, AssetManager assetManager) {
        this.fortniteApi = fortniteApi;
        this.assetManager = assetManager;
    }


    @Override
    public Single<Catalog> getCatalog() {
        return fortniteApi.getCatalog();
    }
}
