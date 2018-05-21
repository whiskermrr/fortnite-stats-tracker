package com.example.mrr.fortnitetracker.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.mrr.fortnitetracker.dagger.components.MainActivityComponent;
import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;

import dagger.Module;
import dagger.Provides;

import static com.example.mrr.fortnitetracker.FortniteTrackerApplication.APPLICATION_SHARED_PREFERENCES;

@Module(subcomponents = MainActivityComponent.class)
public class AppModule {

    @Provides
    @FortniteApplicationScope
    public Context context(Application application) {
        return application;
    }

    @Provides
    @FortniteApplicationScope
    public SharedPreferences sharedPreferences(Context context) {
        return context.getSharedPreferences(APPLICATION_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }
}
