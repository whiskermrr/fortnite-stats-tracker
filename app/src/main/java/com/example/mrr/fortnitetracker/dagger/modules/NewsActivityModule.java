package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.Network.RxTwitterApiService;
import com.example.mrr.fortnitetracker.dagger.scopes.PerActivity;
import com.example.mrr.fortnitetracker.view.news.NewsContracts;
import com.example.mrr.fortnitetracker.view.news.NewsInteractor;
import com.example.mrr.fortnitetracker.view.news.NewsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
@PerActivity
public class NewsActivityModule {

    private NewsContracts.View view;

    public NewsActivityModule(NewsContracts.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    public NewsContracts.Interactor newsInteractor(RxTwitterApiService service) {
        return new NewsInteractor(service);
    }

    @Provides
    @PerActivity
    public NewsContracts.Presenter newsPresenter(NewsContracts.View view, NewsContracts.Interactor interactor) {
        return new NewsPresenter(view, interactor);
    }

    @Provides
    @PerActivity
    public NewsContracts.View newsView() {
        return view;
    }
}
