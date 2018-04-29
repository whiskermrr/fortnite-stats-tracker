package com.example.mrr.fortnitetracker.dagger.components;

import com.example.mrr.fortnitetracker.dagger.modules.MainActivityModule;
import com.example.mrr.fortnitetracker.dagger.scopes.PerActivity;
import com.example.mrr.fortnitetracker.view.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = FortniteApplicationComponent.class)
@PerActivity
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
