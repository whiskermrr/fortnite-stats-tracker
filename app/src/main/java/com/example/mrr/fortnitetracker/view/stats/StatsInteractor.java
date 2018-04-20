package com.example.mrr.fortnitetracker.view.stats;

import com.example.mrr.fortnitetracker.Model.UserProfileModel;
import com.example.mrr.fortnitetracker.Network.FortniteApiService;

import io.reactivex.Observable;

public class StatsInteractor implements StatsContracts.Interactor {

    private FortniteApiService apiService;

    public StatsInteractor(FortniteApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<UserProfileModel> getProfile(String platform, String username) {
        return apiService.getProfile(platform, username);
    }


}
