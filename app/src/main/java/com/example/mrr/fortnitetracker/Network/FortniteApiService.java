package com.example.mrr.fortnitetracker.Network;

import com.example.mrr.fortnitetracker.Model.UserProfileModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface FortniteApiService {

    @Headers("TRN-Api-Key: 7a48a79f-5cef-47df-80e3-682afeba6d37")
    @GET("{platform}/{nickname}")
    Observable<UserProfileModel> getProfile(
            @Path("platform") String platform,
            @Path("nickname")String nickname);
}
