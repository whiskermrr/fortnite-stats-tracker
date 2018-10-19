package com.example.mrr.fortnitetracker.Network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mrr.fortnitetracker.Utils.ProjectUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {

    private Context context;

    @SuppressLint("CheckResult")
    public ConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        if (!ProjectUtils.isNetworkAvailable(context)) {
            throw new NoConnectivityException();
        }
        else {
            return chain.proceed(chain.request());
        }
    }
}
