package com.example.mrr.fortnitetracker.dagger.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.adapters.NewsAdapter;
import com.example.mrr.fortnitetracker.view.news.NewsContracts;
import com.example.mrr.fortnitetracker.view.news.NewsFragment;
import com.example.mrr.fortnitetracker.view.news.NewsInteractor;
import com.example.mrr.fortnitetracker.view.news.NewsPresenter;
import com.example.rxjava_fortnite_api.FortniteApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsFragmentModule {

    @Provides
    @PerFragment
    public NewsContracts.View view(NewsFragment newsFragment) {
        return newsFragment;
    }

    @Provides
    @PerFragment
    public NewsContracts.Presenter presenter(NewsContracts.View view, NewsContracts.Interactor interactor) {
        return new NewsPresenter(view, interactor);
    }

    @Provides
    @PerFragment
    public NewsContracts.Interactor interactor(FortniteApi fortniteApi) {
        return new NewsInteractor(fortniteApi);
    }

    @Provides
    @PerFragment
    public NewsAdapter adapter(Context context) {
        return new NewsAdapter(context);
    }
}
