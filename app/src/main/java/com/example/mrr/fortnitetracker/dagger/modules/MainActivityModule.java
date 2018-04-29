package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.Network.FortniteApiService;
import com.example.mrr.fortnitetracker.dagger.scopes.PerActivity;
import com.example.mrr.fortnitetracker.view.stats.StatsContracts;
import com.example.mrr.fortnitetracker.view.stats.StatsInteractor;
import com.example.mrr.fortnitetracker.view.stats.StatsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
@PerActivity
public class MainActivityModule {

    private final StatsContracts.View view;


    public MainActivityModule(StatsContracts.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    public StatsContracts.Interactor statsInteractor(FortniteApiService apiService) {
        return new StatsInteractor(apiService);
    }

    @Provides
    @PerActivity
    public StatsContracts.Presenter statsPresenter(StatsContracts.View view, StatsContracts.Interactor interactor) {
        return new StatsPresenter(view, interactor);
    }

    @Provides
    @PerActivity
    public StatsContracts.View view() {
        return view;
    }
}
