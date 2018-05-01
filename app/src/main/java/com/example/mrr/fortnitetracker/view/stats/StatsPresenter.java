package com.example.mrr.fortnitetracker.view.stats;

import com.example.mrr.fortnitetracker.Model.UserProfileModel;

import io.reactivex.Observable;
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
    public void getUserStats(String platform, String username) {
        view.showProgress();
        final Observable<UserProfileModel> observable = interactor.getProfile(platform, username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposables.add(observable.subscribeWith(new UserProfileObserver()));
    }

    @Override
    public void unsubscribe() {
        disposables.clear();
    }


    private void onFinished(UserProfileModel userProfile) {
        if(userProfile.getAccountId() != null)
            view.onSuccess(userProfile);
        else
            view.onFailure("User not found.");
    }

    private void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }


    private class UserProfileObserver extends DisposableObserver<UserProfileModel> {

        @Override
        public void onNext(UserProfileModel userProfileModel) {
            StatsPresenter.this.onFinished(userProfileModel);
        }

        @Override
        public void onError(Throwable e) {
            StatsPresenter.this.onFailure(e.getMessage());
        }

        @Override
        public void onComplete() {
            view.hideProgress();
        }
    }
}
