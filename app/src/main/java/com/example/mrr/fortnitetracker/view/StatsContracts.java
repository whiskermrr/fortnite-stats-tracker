package com.example.mrr.fortnitetracker.view;

import com.example.mrr.fortnitetracker.Model.UserProfileModel;

public interface StatsContracts {

    interface View {
        void showProgress();
        void hideProgress();
        void onSuccess(UserProfileModel userProfile);
        void onFailure(String message);
    }

    interface Presenter {
        void getUserStats(String platform, String username);
    }

    interface Interactor {
        void getUserStats(String platform, String username, OnStatsFinishedListener listener);

        interface OnStatsFinishedListener {
            void onFinished(UserProfileModel userProfile);
            void onFailure(String message);
        }
    }
}
