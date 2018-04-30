package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.Network.FortniteApiService;
import com.example.mrr.fortnitetracker.Network.RxTwitterApiClient;
import com.example.mrr.fortnitetracker.Network.RxTwitterApiService;
import com.example.mrr.fortnitetracker.dagger.components.StatsFragmentComponent;
import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;
import com.example.mrr.fortnitetracker.dagger.scopes.PerActivity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.twitter.sdk.android.core.TwitterCore;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = { StatsFragmentComponent.class })
public class MainActivityModule {

    @Provides
    @PerActivity
    public FortniteApiService fortniteApiService(Retrofit retrofit) {
        return retrofit.create(FortniteApiService.class);
    }

    @Provides
    @PerActivity
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://api.fortnitetracker.com/v1/profile/")
                .build();
    }

    @Provides
    @PerActivity
    public RxTwitterApiClient apiClient(OkHttpClient okHttpClient) {
        RxTwitterApiClient apiClient = new RxTwitterApiClient(okHttpClient);
        TwitterCore.getInstance().addGuestApiClient(apiClient);
        return apiClient;
    }

    @Provides
    @PerActivity
    public RxTwitterApiService apiService(RxTwitterApiClient apiClient) {
        return apiClient.getRxService();
    }
}
