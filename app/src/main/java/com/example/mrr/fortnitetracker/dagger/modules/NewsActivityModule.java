package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.Network.RxTwitterApiService;
import com.example.mrr.fortnitetracker.dagger.scopes.ActivityScope;
import com.example.mrr.fortnitetracker.view.news.NewsContracts;
import com.example.mrr.fortnitetracker.view.news.NewsInteractor;
import com.example.mrr.fortnitetracker.view.news.NewsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public class NewsActivityModule {

    private NewsContracts.View view;

    public NewsActivityModule(NewsContracts.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public NewsContracts.Interactor newsInteractor(RxTwitterApiService service) {
        return new NewsInteractor(service);
    }

    @Provides
    @ActivityScope
    public NewsContracts.Presenter newsPresenter(NewsContracts.View view, NewsContracts.Interactor interactor) {
        return new NewsPresenter(view, interactor);
    }

    @Provides
    @ActivityScope
    public NewsContracts.View newsView() {
        return view;
    }
}
