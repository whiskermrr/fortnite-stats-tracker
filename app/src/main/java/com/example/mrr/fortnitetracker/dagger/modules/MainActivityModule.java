package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.dagger.components.CatalogFragmentComponent;
import com.example.mrr.fortnitetracker.dagger.components.NewsFragmentComponent;
import com.example.mrr.fortnitetracker.dagger.components.StatsFragmentComponent;
import com.example.mrr.fortnitetracker.dagger.components.TwitterFragmentComponent;
import com.example.mrr.fortnitetracker.dagger.components.WeaponsFragmentComponent;

import dagger.Module;

@Module(subcomponents = {
        StatsFragmentComponent.class,
        TwitterFragmentComponent.class,
        NewsFragmentComponent.class,
        WeaponsFragmentComponent.class,
        CatalogFragmentComponent.class
        })
public class MainActivityModule {


}
