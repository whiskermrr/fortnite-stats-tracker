package com.example.mrr.fortnitetracker.dagger.modules;


import com.example.mrr.fortnitetracker.Network.RxTwitterApiClient;
import com.example.mrr.fortnitetracker.Network.RxTwitterApiService;
import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;
import com.twitter.sdk.android.core.TwitterCore;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = NetworkModule.class)
public class TwitterModule {

    @Provides
    @FortniteApplicationScope
    public RxTwitterApiClient apiClient(OkHttpClient okHttpClient) {
        RxTwitterApiClient apiClient = new RxTwitterApiClient(okHttpClient);
        TwitterCore.getInstance().addGuestApiClient(apiClient);
        return apiClient;
    }

    @Provides
    @FortniteApplicationScope
    public RxTwitterApiService apiService(RxTwitterApiClient apiClient) {
        return apiClient.getRxService();
    }
}
