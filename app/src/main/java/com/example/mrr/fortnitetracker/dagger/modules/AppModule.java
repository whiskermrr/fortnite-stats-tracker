package com.example.mrr.fortnitetracker.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.example.mrr.fortnitetracker.dagger.components.MainActivityComponent;
import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = MainActivityComponent.class)
public class AppModule {

    @Provides
    @FortniteApplicationScope
    public Context context(Application application) {
        return application;
    }
}
