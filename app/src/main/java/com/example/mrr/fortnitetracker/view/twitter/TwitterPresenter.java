package com.example.mrr.fortnitetracker.view.twitter;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TwitterPresenter implements TwitterContracts.Presenter {

    private TwitterContracts.View view;
    private TwitterContracts.Interactor interactor;
    private CompositeDisposable disposables;

    public TwitterPresenter(TwitterContracts.View view, TwitterContracts.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
        disposables = new CompositeDisposable();
    }

    @Override
    public void getTweets() {
        view.showProgress();
        final Observable<List<Tweet>> observable = interactor.getTweets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposables.add(observable.subscribeWith(new NewsObserver()));
    }

    private void onFinished(List<Tweet> tweets) {
        view.hideProgress();
        view.onSuccess(tweets);
    }

    private void onFailure(Throwable throwable) {
        view.hideProgress();
        view.onFailure(throwable.getMessage());
    }

    public class NewsObserver extends DisposableObserver<List<Tweet>> {

        @Override
        public void onNext(List<Tweet> tweets) {
            TwitterPresenter.this.onFinished(tweets);
        }

        @Override
        public void onError(Throwable throwable) {
            TwitterPresenter.this.onFailure(throwable);
        }

        @Override
        public void onComplete() {
        }
    }
}
