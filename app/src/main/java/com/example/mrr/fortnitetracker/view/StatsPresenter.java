package com.example.mrr.fortnitetracker.view;

import com.example.mrr.fortnitetracker.Model.UserProfileModel;

public class StatsPresenter implements StatsContracts.Presenter, StatsContracts.Interactor.OnStatsFinishedListener {

    private StatsContracts.View view;
    private StatsContracts.Interactor interactor;

    public StatsPresenter(StatsContracts.View view, StatsContracts.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getUserStats(String platform, String username) {
        view.showProgress();
        interactor.getUserStats(platform, username, this);
    }


    @Override
    public void onFinished(UserProfileModel userProfile) {
        view.hideProgress();
        view.onSuccess(userProfile);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }
}
