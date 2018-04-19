package com.example.mrr.fortnitetracker.Network;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RxTwitterApiService {

    @GET("/1.1//statuses/user_timeline.json")
    Call<List<Tweet>> getNews(@Query("screen_name") String username);
}
