package com.example.mrr.fortnitetracker;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.example.mrr.fortnitetracker.Network.FortniteApiService;
import com.example.mrr.fortnitetracker.dagger.components.DaggerFortniteApplicationComponent;
import com.example.mrr.fortnitetracker.dagger.components.FortniteApplicationComponent;
import com.example.mrr.fortnitetracker.dagger.modules.ContextModule;
import com.google.gson.GsonBuilder;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FortniteTrackerApplication extends Application {

    private FortniteApiService fortniteApiService;

    @Override
    public void onCreate() {
        super.onCreate();

        FortniteApplicationComponent component = DaggerFortniteApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        fortniteApiService = component.getFortniteApiService();
    }

    public static FortniteTrackerApplication get(Activity activity) {
        return (FortniteTrackerApplication) activity.getApplication();
    }

    public FortniteApiService getFortniteApiService() {
        return fortniteApiService;
    }
}
