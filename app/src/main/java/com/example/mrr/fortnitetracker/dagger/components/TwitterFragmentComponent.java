package com.example.mrr.fortnitetracker.dagger.components;

import com.example.mrr.fortnitetracker.dagger.modules.TwitterFragmentModule;
import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.twitter.TwitterFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = TwitterFragmentModule.class)
@PerFragment
public interface TwitterFragmentComponent extends AndroidInjector<TwitterFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TwitterFragment>{}
}
