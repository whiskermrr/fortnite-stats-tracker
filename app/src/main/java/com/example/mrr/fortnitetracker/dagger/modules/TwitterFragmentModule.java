package com.example.mrr.fortnitetracker.dagger.modules;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;

import com.example.mrr.fortnitetracker.Network.RxTwitterApiService;
import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.adapters.CustomTwitterAdapter;
import com.example.mrr.fortnitetracker.view.twitter.TwitterContracts;
import com.example.mrr.fortnitetracker.view.twitter.TwitterFragment;
import com.example.mrr.fortnitetracker.view.twitter.TwitterInteractor;
import com.example.mrr.fortnitetracker.view.twitter.TwitterPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TwitterFragmentModule {

    public static final String TWITTER_FRAGMENT_MANAGER = "twitter_fragment_manager";

    @Provides
    @PerFragment
    TwitterContracts.View view(TwitterFragment fragment) {
        return fragment;
    }

    @Provides
    @PerFragment
    TwitterContracts.Presenter presenter(TwitterContracts.View view, TwitterContracts.Interactor interactor) {
        return new TwitterPresenter(view, interactor);
    }

    @Provides
    @PerFragment
    TwitterContracts.Interactor interactor(RxTwitterApiService apiService) {
        return new TwitterInteractor(apiService);
    }

    @Provides
    @Named(TWITTER_FRAGMENT_MANAGER)
    @PerFragment
    FragmentManager fragmentManager(TwitterFragment twitterFragment) {
        return twitterFragment.getChildFragmentManager();
    }

    @Provides
    @PerFragment
    CustomTwitterAdapter adapter(Context context) {
        return new CustomTwitterAdapter(context, R.style.tw__TweetLightStyle);
    }

    @Provides
    @PerFragment
    LinearLayoutManager linearLayoutManager(TwitterFragment twitterFragment) {
        return new LinearLayoutManager(twitterFragment.getActivity());
    }
}

