package com.example.mrr.fortnitetracker.dagger.components;

import com.example.mrr.fortnitetracker.dagger.modules.MainActivityModule;
import com.example.mrr.fortnitetracker.dagger.scopes.ActivityScope;
import com.example.mrr.fortnitetracker.view.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = FortniteApplicationComponent.class)
@ActivityScope
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
