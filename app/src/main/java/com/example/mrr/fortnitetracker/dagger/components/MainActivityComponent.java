package com.example.mrr.fortnitetracker.dagger.components;

import com.example.mrr.fortnitetracker.dagger.modules.MainActivityModule;
import com.example.mrr.fortnitetracker.dagger.scopes.PerActivity;
import com.example.mrr.fortnitetracker.view.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = { MainActivityModule.class, FragmentBuilder.class })
@PerActivity
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
