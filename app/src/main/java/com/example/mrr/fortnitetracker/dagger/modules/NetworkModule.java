package com.example.mrr.fortnitetracker.dagger.modules;

import android.content.Context;
import android.util.Log;

import com.example.mrr.fortnitetracker.Utils.ProjectUtils;
import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;

import java.io.File;
import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @FortniteApplicationScope
    public OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor interceptor, Interceptor networkInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(networkInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @FortniteApplicationScope
    public Interceptor interceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                if(ProjectUtils.isNetworkAvailable(context)) {
                    int age = 180;
                    return response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + age)
                            .build();
                }
                else {
                    int stale = 3600 * 24 * 14;
                    return response.newBuilder()
                            .header("Cache-Control", "only-if-cached, max-stale=" + stale)
                            .build();
                }
            }
        };
    }

    @Provides
    @FortniteApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 2 * 1000 * 1000);
    }

    @Provides
    @FortniteApplicationScope
    public File file(Context context) {
        //return new File(context.getCacheDir(), "okhttp_cache");
        return context.getDir("okhttp_cache", Context.MODE_PRIVATE);
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
