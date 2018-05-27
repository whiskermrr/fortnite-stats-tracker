package com.example.mrr.fortnitetracker.dagger.components;

import com.example.mrr.fortnitetracker.dagger.modules.NewsFragmentModule;
import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.news.NewsFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = NewsFragmentModule.class)
@PerFragment
public interface NewsFragmentComponent extends AndroidInjector<NewsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NewsFragment>{}
}
