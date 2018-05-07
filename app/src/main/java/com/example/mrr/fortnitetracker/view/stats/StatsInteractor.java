package com.example.mrr.fortnitetracker.view.stats;

import com.example.rxjava_fortnite_api.FortniteApi;
import com.example.rxjava_fortnite_api.models.stats.BattleRoyaleStats;

import io.reactivex.Single;

public class StatsInteractor implements StatsContracts.Interactor {

    private FortniteApi fortniteApi;

    public StatsInteractor(FortniteApi fortniteApi) {
        this.fortniteApi = fortniteApi;
    }

    @Override
    public Single<BattleRoyaleStats> getUserStats(String username) {
        return fortniteApi.getUserBattleRoyaleStats(username);
    }
}
