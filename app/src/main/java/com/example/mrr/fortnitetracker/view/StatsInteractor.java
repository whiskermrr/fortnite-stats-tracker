package com.example.mrr.fortnitetracker.view;

import android.util.Log;

import com.example.mrr.fortnitetracker.Model.UserProfileModel;
import com.example.mrr.fortnitetracker.Network.FortniteApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatsInteractor implements StatsContracts.Interactor {

    private FortniteApiService apiService;

    StatsInteractor(FortniteApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getUserStats(String platform, String username, final OnStatsFinishedListener listener) {

        apiService.getUserStats(platform, username).enqueue(new Callback<UserProfileModel>() {
            @Override
            public void onResponse(Call<UserProfileModel> call, Response<UserProfileModel> response) {
                if(response.isSuccessful()) {
                    // this api is like an cancer
                    if(response.body() != null) {
                        UserProfileModel model = response.body();
                        if (model.getAccountId() != null)
                            listener.onFinished(response.body());
                        else
                            listener.onFailure("User not found.");
                    }
                }
                else {
                    switch (response.code()) {
                        case 404:
                            listener.onFailure("404");
                            break;
                        case 500:
                            listener.onFailure("Problem with Server.");
                            break;
                        default:
                            listener.onFailure("Unknown error.");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfileModel> call, Throwable t) {
                listener.onFailure("Failed to connect to Internet.");
            }
        });
    }
}
