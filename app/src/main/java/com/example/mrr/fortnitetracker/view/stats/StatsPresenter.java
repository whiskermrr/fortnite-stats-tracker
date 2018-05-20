package com.example.mrr.fortnitetracker.view.stats;

import com.example.rxjava_fortnite_api.models.stats.BattleRoyaleStats;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.SocketTimeoutException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class StatsPresenter implements StatsContracts.Presenter {

    private StatsContracts.View view;
    private StatsContracts.Interactor interactor;
    private CompositeDisposable disposables;

    public StatsPresenter(StatsContracts.View view, StatsContracts.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
        disposables = new CompositeDisposable();
    }

    @Override
    public void getUserStats(String username) {
        view.showProgress();
        disposables.add(
                interactor.getUserStats(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(StatsPresenter.this::onFinished,
                        StatsPresenter.this::onFailure
                )
        );
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
        String errorMessage = throwable.getMessage();
        view.hideProgress();
        if(throwable instanceof HttpException) {
            if(((HttpException) throwable).response().code() == 404) {
                errorMessage = "User not found.";
            }
        } else if(throwable instanceof SocketTimeoutException) {
            errorMessage = "Connection problem. Please try again.";
        }
        view.onFailure(errorMessage);
    }


    private class UserProfileObserver extends DisposableObserver<BattleRoyaleStats> {

        @Override
        public void onNext(BattleRoyaleStats battleRoyaleStats) {
            StatsPresenter.this.onFinished(battleRoyaleStats);
        }

        @Override
        public void onError(Throwable throwable) {
            StatsPresenter.this.onFailure(throwable);
        }

        @Override
        public void onComplete() {
            view.hideProgress();
        }
    }
}
