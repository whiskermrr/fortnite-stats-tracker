package com.example.mrr.fortnitetracker.dagger.components;

import com.example.mrr.fortnitetracker.dagger.modules.StatsFragmentModule;
import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.stats.StatsFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = StatsFragmentModule.class)
@PerFragment
public interface StatsFragmentComponent extends AndroidInjector<StatsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<StatsFragment>{}
}
