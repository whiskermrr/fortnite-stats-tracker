package com.example.mrr.fortnitetracker.view.news;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter implements NewsContracts.Presenter {

    private NewsContracts.View view;
    private NewsContracts.Interactor interactor;
    private CompositeDisposable disposables;

    public NewsPresenter(NewsContracts.View view, NewsContracts.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
        disposables = new CompositeDisposable();
    }

    @Override
    public void getNews() {
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

    private void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }

    public class NewsObserver extends DisposableObserver<List<Tweet>> {

        @Override
        public void onNext(List<Tweet> tweets) {
            NewsPresenter.this.onFinished(tweets);
        }

        @Override
        public void onError(Throwable e) {
            NewsPresenter.this.onFailure(e.getMessage());
        }

        @Override
        public void onComplete() {
            view.hideProgress();
        }
    }
}
