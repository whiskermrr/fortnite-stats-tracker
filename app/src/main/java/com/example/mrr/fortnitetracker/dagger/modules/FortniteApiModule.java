package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;
import com.example.rxjava_fortnite_api.FortniteApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mrr.fortnitetracker.Utils.ApiDictionary.EMAIL;
import static com.example.mrr.fortnitetracker.Utils.ApiDictionary.FORTNITE_TOKEN;
import static com.example.mrr.fortnitetracker.Utils.ApiDictionary.LAUNCHER_TOKEN;
import static com.example.mrr.fortnitetracker.Utils.ApiDictionary.PASSWORD;

@Module(includes = NetworkModule.class)
public class FortniteApiModule {

    @Provides
    @FortniteApplicationScope
    public FortniteApi fortniteApi(Retrofit retrofit) {
        return new FortniteApi(retrofit, EMAIL, PASSWORD, LAUNCHER_TOKEN, FORTNITE_TOKEN);
    }

    @Provides
    @FortniteApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://account-public-service-prod03.ol.epicgames.com/")
                .build();
    }
}
