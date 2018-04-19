package com.example.mrr.fortnitetracker;

import android.app.Activity;
import android.app.Application;

import com.example.mrr.fortnitetracker.dagger.components.DaggerFortniteApplicationComponent;
import com.example.mrr.fortnitetracker.dagger.components.FortniteApplicationComponent;
import com.example.mrr.fortnitetracker.dagger.modules.ContextModule;
import com.twitter.sdk.android.core.Twitter;

public class FortniteTrackerApplication extends Application {

    FortniteApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        Twitter.initialize(this);

        component = DaggerFortniteApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public FortniteApplicationComponent getComponent() {
        return component;
    }

    public static FortniteTrackerApplication get(Activity activity) {
        return (FortniteTrackerApplication) activity.getApplication();
    }
}
