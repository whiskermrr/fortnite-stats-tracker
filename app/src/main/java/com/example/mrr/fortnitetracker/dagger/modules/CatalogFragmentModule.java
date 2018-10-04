package com.example.mrr.fortnitetracker.dagger.modules;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.catalog.CatalogContracts;
import com.example.mrr.fortnitetracker.view.catalog.CatalogFragment;
import com.example.mrr.fortnitetracker.view.catalog.CatalogInteractor;
import com.example.mrr.fortnitetracker.view.catalog.CatalogPresenter;
import com.example.rxjava_fortnite_api.FortniteApi;

import dagger.Module;
import dagger.Provides;

@Module
public class CatalogFragmentModule {

    @Provides
    @PerFragment
    public CatalogContracts.View view(CatalogFragment fragment) {
        return fragment;
    }

    @Provides
    @PerFragment
    public CatalogContracts.Presenter presenter(CatalogContracts.View view, CatalogContracts.Interactor interactor) {
        return new CatalogPresenter(view, interactor);
    }

    @Provides
    @PerFragment
    public CatalogContracts.Interactor interactor(FortniteApi fortniteApi) {
        return new CatalogInteractor(fortniteApi);
    }
}
