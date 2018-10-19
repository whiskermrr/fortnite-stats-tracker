package com.example.mrr.fortnitetracker.view.stats;

import android.util.Log;

import com.example.mrr.fortnitetracker.Network.NoConnectivityException;
import com.example.rxjava_fortnite_api.models.stats.BattleRoyaleStats;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class StatsPresenter implements StatsContracts.Presenter {

    private StatsContracts.View view;
    private StatsContracts.Interactor statsInteractor;
    private StatsContracts.SPInteractor sharedPreferencesInteractor;
    private CompositeDisposable disposables;

    public StatsPresenter(StatsContracts.View view,
                          StatsContracts.Interactor statsInteractor,
                          StatsContracts.SPInteractor sharedPreferencesInteractor) {
        this.view = view;
        this.statsInteractor = statsInteractor;
        this.sharedPreferencesInteractor = sharedPreferencesInteractor;
        disposables = new CompositeDisposable();
    }

    @Override
    public void getUserStats(String username) {
        view.showProgress();
        disposables.add(
                statsInteractor.getUserStats(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(StatsPresenter.this::onFinished,
                        StatsPresenter.this::onFailure
                )
        );
    }

    @Override
    public void saveRecentSearches(ArrayList<String> recentSearches) {
        sharedPreferencesInteractor.saveRecentSearchesToSharedPreferences(recentSearches);
    }

    @Override
    public ArrayList<String> getRecentSearches() {
        return sharedPreferencesInteractor.getRecentSearchesFromSharedPreferences();
    }

    @Override
    public void unsubscribe() {
        disposables.clear();
    }


    private void onFinished(BattleRoyaleStats battleRoyaleStats) {
        view.hideProgress();
        view.onSuccess(battleRoyaleStats);
    }

    private void onFailure(Throwable throwable) {
        view.hideProgress();
        view.onFailure(throwable.getMessage());
    }


}
