package com.example.mrr.fortnitetracker.view.stats;

import com.example.rxjava_fortnite_api.models.stats.BattleRoyaleStats;

import io.reactivex.Single;

public interface StatsContracts {

    interface View {
        void showProgress();
        void hideProgress();
        void onSuccess(BattleRoyaleStats battleRoyaleStats);
        void onFailure(String message);
    }

    interface Presenter {
        void getUserStats(String username);
        void unsubscribe();
    }

    interface Interactor {
        Single<BattleRoyaleStats> getUserStats(String username);
    }
}
