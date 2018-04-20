package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.Network.RxTwitterApiClient;
import com.example.mrr.fortnitetracker.Network.RxTwitterApiService;
import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;
import com.twitter.sdk.android.core.TwitterCore;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = NetworkModule.class)
public class TwitterApiModule {

    @Provides
    @FortniteApplicationScope
    public RxTwitterApiClient apiClient(OkHttpClient client) {
        RxTwitterApiClient apiClient = new RxTwitterApiClient(client);
        TwitterCore.getInstance().addGuestApiClient(apiClient);
        return  apiClient;
    }

    @Provides
    @FortniteApplicationScope
    public RxTwitterApiService apiService(RxTwitterApiClient apiClient) {
        return apiClient.getRxService();
    }
}
