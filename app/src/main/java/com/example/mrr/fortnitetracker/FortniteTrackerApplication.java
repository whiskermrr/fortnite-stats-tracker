package com.example.mrr.fortnitetracker;

import android.app.Activity;
import android.app.Application;

import com.example.mrr.fortnitetracker.dagger.components.DaggerFortniteApplicationComponent;
import com.example.rxjava_fortnite_api.FortniteApi;
import com.twitter.sdk.android.core.Twitter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class FortniteTrackerApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    FortniteApi fortniteApi;

    @Override
    public void onCreate() {
        super.onCreate();

        Twitter.initialize(this);

        DaggerFortniteApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);

        fortniteApi.authenticate();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
