package com.example.mrr.fortnitetracker.view.stats;

import com.example.mrr.fortnitetracker.Model.UserProfileModel;

import io.reactivex.Observable;

public interface StatsContracts {

    interface View {
        void showProgress();
        void hideProgress();
        void onSuccess(UserProfileModel userProfile);
        void onFailure(String message);
    }

    interface Presenter {
        void getUserStats(String platform, String username);
        void unsubscribe();
    }

    interface Interactor {
        Observable<UserProfileModel> getProfile(String platform, String username);
    }
}
