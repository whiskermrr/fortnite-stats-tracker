package com.example.mrr.fortnitetracker.Network;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;


public class RxTwitterApiClient extends TwitterApiClient {

    public RxTwitterApiClient() {
        super();
    }

    public RxTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public RxTwitterApiService getRxService() {
        return getService(RxTwitterApiService.class);
    }

}
