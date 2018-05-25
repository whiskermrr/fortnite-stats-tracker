package com.example.mrr.fortnitetracker.view.news;

import com.example.rxjava_fortnite_api.models.blogs.BlogHolder;

import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter implements NewsContracts.Presenter {

    private NewsContracts.View view;
    private NewsContracts.Interactor interactor;
    private CompositeDisposable disposables;
    private int offset = 0;
    private int postPerPage = 10;
    private int totalBlogs = 0;

    public NewsPresenter(NewsContracts.View view, NewsContracts.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
        disposables = new CompositeDisposable();
    }

    private void onFinished(BlogHolder blogHolder) {
        view.hideProgress();
        if(blogHolder.getBlogList() != null) {
            totalBlogs = blogHolder.getBlogTotal();
            offset += blogHolder.getBlogList().size();
            view.onSuccess(blogHolder.getBlogList());
        }
    }

    private void onFailure(Throwable throwable) {
        view.onFailure(throwable.getMessage());
    }

    @Override
    public void getNews(String newsType) {
        if(totalBlogs < offset)
            return;

        view.showProgress();
        disposables.add(
                interactor.getNews(newsType, postPerPage, offset, Locale.US.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onFinished, this::onFailure)
        );
    }

    @Override
    public void unsubscribe() {
        disposables.clear();
    }
}
