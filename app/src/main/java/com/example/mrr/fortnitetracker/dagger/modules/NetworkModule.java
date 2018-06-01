package com.example.mrr.fortnitetracker.dagger.modules;

import android.content.Context;
import android.util.Log;

import com.example.mrr.fortnitetracker.Utils.ProjectUtils;
import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = AppModule.class)
public class NetworkModule {

    @Provides
    @FortniteApplicationScope
    public OkHttpClient okHttpClient(Cache cache,
                                     HttpLoggingInterceptor interceptor,
                                     @Named("NetworkInterceptor") Interceptor networkInterceptor,
                                     @Named("OfflineCacheInterceptor") Interceptor offlineCacheInterceptor
    ) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(offlineCacheInterceptor)
                .addNetworkInterceptor(networkInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @FortniteApplicationScope
    @Named("NetworkInterceptor")
    public Interceptor networkInterceptor(final Context context) {
        return chain -> {
            Response response = chain.proceed(chain.request());
            CacheControl cacheControl;

            if(ProjectUtils.isNetworkAvailable(context)) {
                cacheControl = new CacheControl.Builder()
                        .maxAge(180, TimeUnit.SECONDS)
                        .build();
            }
            else {
                cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();
            }

            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", cacheControl.toString())
                    .build();
        };
    }

    @Provides
    @FortniteApplicationScope
    @Named("OfflineCacheInterceptor")
    public Interceptor offlineInterceptor(final Context context) {
        return chain -> {
            Request request = chain.request();

            if(!ProjectUtils.isNetworkAvailable(context)) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();

                request = request.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .cacheControl(cacheControl)
                        .build();
            }
            return chain.proceed(request);
        };
    }

    @Provides
    @FortniteApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 20 * 1000 * 1000);
    }

    @Provides
    @FortniteApplicationScope
    public File file(Context context) {
        return new File(context.getCacheDir(), "ok_http_fortnite_cache");
    }

    @Provides
    @FortniteApplicationScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Log.v("LOGGING INTERCEPTOR", message)).setLevel(HttpLoggingInterceptor.Level.BODY);
    }


}
