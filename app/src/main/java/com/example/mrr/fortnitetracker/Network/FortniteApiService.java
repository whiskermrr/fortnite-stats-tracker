package com.example.mrr.fortnitetracker.Network;

import com.example.mrr.fortnitetracker.Model.UserProfileModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FortniteApiService {

    @Headers("TRN-Api-Key: 7a48a79f-5cef-47df-80e3-682afeba6d37")
    @GET("{platform}/{nickname}")
    Call<UserProfileModel> getUserStats(
            @Path("platform") String platform,
            @Path("nickname")String nickname);
}
