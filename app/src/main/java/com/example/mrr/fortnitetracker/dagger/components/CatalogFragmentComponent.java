package com.example.mrr.fortnitetracker.dagger.components;


import com.example.mrr.fortnitetracker.dagger.modules.CatalogFragmentModule;
import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.catalog.CatalogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = CatalogFragmentModule.class)
@PerFragment
public interface CatalogFragmentComponent extends AndroidInjector<CatalogFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CatalogFragment> {}
}
