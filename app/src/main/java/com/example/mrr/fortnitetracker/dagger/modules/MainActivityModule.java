package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.Network.FortniteApiService;
import com.example.mrr.fortnitetracker.dagger.scopes.MainActivityScope;
import com.example.mrr.fortnitetracker.view.StatsContracts;
import com.example.mrr.fortnitetracker.view.StatsInteractor;
import com.example.mrr.fortnitetracker.view.StatsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
@MainActivityScope
public class MainActivityModule {

    private final StatsContracts.View view;


    public MainActivityModule(StatsContracts.View view) {
        this.view = view;
    }

    @Provides
    @MainActivityScope
    public StatsContracts.Interactor statsInteractor(FortniteApiService apiService) {
        return new StatsInteractor(apiService);
    }

    @Provides
    @MainActivityScope
    public StatsContracts.Presenter statsPresenter(StatsContracts.View view, StatsContracts.Interactor interactor) {
        return new StatsPresenter(view, interactor);
    }

    @Provides
    @MainActivityScope
    public StatsContracts.View view() {
        return view;
    }
}
