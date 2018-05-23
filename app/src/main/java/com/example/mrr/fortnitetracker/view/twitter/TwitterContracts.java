package com.example.mrr.fortnitetracker.view.twitter;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;

public interface TwitterContracts {

    interface View {
        void showProgress();
        void hideProgress();
        void onSuccess(List<Tweet> tweets);
        void onFailure(String message);
    }

    interface Presenter {
        void getTweets();
    }

    interface Interactor {

        Observable<List<Tweet>> getTweets(Long maxId);
    }
}
