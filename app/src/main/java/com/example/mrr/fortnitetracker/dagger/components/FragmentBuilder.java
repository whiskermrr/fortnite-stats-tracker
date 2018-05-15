package com.example.mrr.fortnitetracker.dagger.components;

import android.support.v4.app.Fragment;


import com.example.mrr.fortnitetracker.view.stats.StatsSearchFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(StatsSearchFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindStatsFragment(StatsFragmentComponent.Builder builder);

}
