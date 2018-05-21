package com.example.mrr.fortnitetracker.dagger.modules;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;

import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.stats.SharedPreferencesInteractor;
import com.example.mrr.fortnitetracker.view.stats.StatsContracts;
import com.example.mrr.fortnitetracker.view.stats.StatsSearchFragment;
import com.example.mrr.fortnitetracker.view.stats.StatsInteractor;
import com.example.mrr.fortnitetracker.view.stats.StatsPresenter;
import com.example.rxjava_fortnite_api.FortniteApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class StatsSearchFragmentModule {

    public static final String STATS_FRAGMENT_MANAGER = "stats_fragment_manager";

    @Provides
    @PerFragment
    public StatsContracts.View view(StatsSearchFragment fragment) {
        return fragment;
    }

    @Provides
    @PerFragment
    public StatsContracts.Interactor interactor(FortniteApi fortniteApi) {
        return new StatsInteractor(fortniteApi);
    }

    @Provides
    @PerFragment
    public StatsContracts.Presenter presenter(StatsContracts.View view,
                                              StatsContracts.Interactor interactor,
                                              StatsContracts.SPInteractor prefInteractor) {
        return new StatsPresenter(view, interactor, prefInteractor);
    }

    @Provides
    @Named(STATS_FRAGMENT_MANAGER)
    @PerFragment
    public FragmentManager fragmentManager(StatsSearchFragment statsSearchFragment) {
        return statsSearchFragment.getChildFragmentManager();
    }

    @Provides
    @PerFragment
    public StatsContracts.SPInteractor sharedPreferencesInteractor(SharedPreferences preferences) {
        return new SharedPreferencesInteractor(preferences);
    }
}
