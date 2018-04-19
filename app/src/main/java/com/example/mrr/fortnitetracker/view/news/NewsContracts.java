package com.example.mrr.fortnitetracker.view.news;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;

public interface NewsContracts {

    interface View {
        void showProgress();
        void hideProgress();
        void onSuccess(List<Tweet> tweets);
        void onFailure(String message);
    }

    interface Presenter {
        void getNews();
    }

    interface Interactor {

        Observable<List<Tweet>> getTweets();

        interface OnNewsFinishedListener {

        }

    }
}
