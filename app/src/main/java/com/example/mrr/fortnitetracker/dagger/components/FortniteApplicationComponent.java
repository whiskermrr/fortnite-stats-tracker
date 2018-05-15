package com.example.mrr.fortnitetracker.dagger.components;

import android.app.Application;

import com.example.mrr.fortnitetracker.FortniteTrackerApplication;
import com.example.mrr.fortnitetracker.dagger.modules.FortniteApiModule;
import com.example.mrr.fortnitetracker.dagger.modules.TwitterModule;
import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@FortniteApplicationScope
@Component(modules = {AndroidInjectionModule.class, FortniteApiModule.class, TwitterModule.class, ActivityBuilder.class })
public interface FortniteApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        FortniteApplicationComponent build();
    }

    void inject(FortniteTrackerApplication application);
}
