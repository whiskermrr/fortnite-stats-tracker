package com.example.mrr.fortnitetracker.view.twitter;

import com.example.mrr.fortnitetracker.Network.RxTwitterApiService;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;

public class TwitterInteractor implements TwitterContracts.Interactor {

    private RxTwitterApiService apiService;

    public TwitterInteractor(RxTwitterApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<Tweet>> getTweets(Long maxId) {
        return Observable.create( subscriber -> {
            apiService.getNews("fortnitegame", true, maxId).enqueue(new Callback<List<Tweet>>() {
                @Override
                public void success(Result<List<Tweet>> result) {
                    subscriber.onNext(result.data);
                }

                @Override
                public void failure(TwitterException exception) {
                    subscriber.onError(exception);
                }
            });
        });
    }
}
