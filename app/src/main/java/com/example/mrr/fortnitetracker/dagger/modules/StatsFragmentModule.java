package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.Network.FortniteApiService;
import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.stats.StatsContracts;
import com.example.mrr.fortnitetracker.view.stats.StatsFragment;
import com.example.mrr.fortnitetracker.view.stats.StatsInteractor;
import com.example.mrr.fortnitetracker.view.stats.StatsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class StatsFragmentModule {

    @Provides
    @PerFragment
    public StatsContracts.View view(StatsFragment fragment) {
        return fragment;
    }

    @Provides
    @PerFragment
    public StatsContracts.Interactor interactor(FortniteApiService apiService) {
        return new StatsInteractor(apiService);
    }

    @Provides
    @PerFragment
    public StatsContracts.Presenter presenter(StatsContracts.View view, StatsContracts.Interactor interactor) {
        return new StatsPresenter(view, interactor);
    }
}
