package com.example.mrr.fortnitetracker.view.news;

import com.example.rxjava_fortnite_api.FortniteApi;
import com.example.rxjava_fortnite_api.models.blogs.BlogHolder;

import io.reactivex.Single;

public class NewsInteractor implements NewsContracts.Interactor {

    private FortniteApi fortniteApi;

    public NewsInteractor(FortniteApi fortniteApi) {
        this.fortniteApi = fortniteApi;
    }


    @Override
    public Single<BlogHolder> getNews(String newsType, int postPerPage, int offset, String locale) {
        return fortniteApi.getBlogs(newsType, postPerPage, offset, locale);
    }
}
