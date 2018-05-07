package com.example.mrr.fortnitetracker.dagger.components;

import com.example.mrr.fortnitetracker.dagger.modules.StatsSearchFragmentModule;
import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.stats.StatsSearchFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = StatsSearchFragmentModule.class)
@PerFragment
public interface StatsFragmentComponent extends AndroidInjector<StatsSearchFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<StatsSearchFragment>{}
}
