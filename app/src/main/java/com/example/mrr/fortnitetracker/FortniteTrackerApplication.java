package com.example.mrr.fortnitetracker;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mrr.fortnitetracker.Utils.ProjectUtils;
import com.example.mrr.fortnitetracker.dagger.components.DaggerFortniteApplicationComponent;
import com.example.rxjava_fortnite_api.FortniteApi;
import com.example.rxjava_fortnite_api.models.auth.AuthenticationToken;
import com.google.gson.Gson;
import com.twitter.sdk.android.core.Twitter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FortniteTrackerApplication extends Application implements HasActivityInjector {

    public final static String APPLICATION_SHARED_PREFERENCES = "com.example.mrr.FortniteTrackerApplication";
    public final static String PREFERENCES_AUTH_TOKEN = "authToken";

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    FortniteApi fortniteApi;

    @Inject
    SharedPreferences preferences;

    private CompositeDisposable disposables;

    @Override
    public void onCreate() {
        super.onCreate();
        disposables = new CompositeDisposable();
        preferences = getSharedPreferences(APPLICATION_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Twitter.initialize(this);
        DaggerFortniteApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);

        String json = preferences.getString(PREFERENCES_AUTH_TOKEN, null);

        // TODO: internet check

        if(json != null) {
            AuthenticationToken token = new Gson().fromJson(json, AuthenticationToken.class);
            fortniteApi.setAuthenticationToken(token);
            if(ProjectUtils.isNetworkAvailable(this)) {
                fortniteApi.requestRefreshTokenCompletable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new TokenCompletableObserver());
            }
        }
        else {
            fortniteApi.authenticateCompletable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new TokenCompletableObserver());
        }
    }

    private void saveAuthTokenToSharedPreferences(AuthenticationToken token) {
        String jsonToken = new Gson().toJson(token);
        preferences.edit().putString(PREFERENCES_AUTH_TOKEN, jsonToken).apply();
    }

    private class TokenCompletableObserver implements CompletableObserver {

        @Override
        public void onSubscribe(Disposable d) {
            disposables.add(d);
        }

        @Override
        public void onComplete() {
            saveAuthTokenToSharedPreferences(fortniteApi.getAuthenticationToken());
        }

        @Override
        public void onError(Throwable e) {
            Log.v("TokenObserverOnError", e.getMessage());
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
