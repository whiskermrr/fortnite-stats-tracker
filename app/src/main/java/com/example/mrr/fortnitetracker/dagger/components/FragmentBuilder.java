package com.example.mrr.fortnitetracker.dagger.components;

import android.support.v4.app.Fragment;


import com.example.mrr.fortnitetracker.view.catalog.CatalogFragment;
import com.example.mrr.fortnitetracker.view.news.NewsFragment;
import com.example.mrr.fortnitetracker.view.stats.StatsSearchFragment;
import com.example.mrr.fortnitetracker.view.twitter.TwitterFragment;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(StatsSearchFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindStatsFragment(StatsFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(TwitterFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindTwitterFragment(TwitterFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(NewsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindNewsFragment(NewsFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(WeaponsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindWeaponsFragment(WeaponsFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(CatalogFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindCatalogFragment(CatalogFragmentComponent.Builder builder);
}
