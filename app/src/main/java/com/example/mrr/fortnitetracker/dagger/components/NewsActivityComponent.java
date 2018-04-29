package com.example.mrr.fortnitetracker.dagger.components;


import com.example.mrr.fortnitetracker.FortniteTrackerApplication;
import com.example.mrr.fortnitetracker.dagger.modules.NewsActivityModule;
import com.example.mrr.fortnitetracker.dagger.scopes.PerActivity;
import com.example.mrr.fortnitetracker.view.NewsActivity;

import dagger.Component;

@Component(modules = NewsActivityModule.class, dependencies = FortniteTrackerApplication.class)
@PerActivity
public interface NewsActivityComponent {

    void inject(NewsActivity newsActivity);
}
