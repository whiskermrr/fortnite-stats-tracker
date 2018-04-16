package com.example.mrr.fortnitetracker.dagger.modules;

import android.content.Context;
import android.util.Log;

import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @FortniteApplicationScope
    public OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @FortniteApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 2 * 1000 * 1000);
    }

    @Provides
    @FortniteApplicationScope
    public File file(Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    @FortniteApplicationScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.v("LOGGING INTERCEPTOR", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BASIC);
    }
}
