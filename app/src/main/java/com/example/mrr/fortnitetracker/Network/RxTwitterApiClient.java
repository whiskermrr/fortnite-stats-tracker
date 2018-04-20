package com.example.mrr.fortnitetracker.Network;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import okhttp3.OkHttpClient;


public class RxTwitterApiClient extends TwitterApiClient {

    public RxTwitterApiClient() {
        super();
    }

    public RxTwitterApiClient(OkHttpClient client) {
        super(client);
    }

    public RxTwitterApiService getRxService() {
        return getService(RxTwitterApiService.class);
    }

}
