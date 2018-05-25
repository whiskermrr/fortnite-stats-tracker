package com.example.mrr.fortnitetracker.view.news;

import com.example.rxjava_fortnite_api.models.blogs.Blog;
import com.example.rxjava_fortnite_api.models.blogs.BlogHolder;

import java.util.List;

import io.reactivex.Single;

public interface NewsContracts {

    interface View {
        void showProgress();
        void hideProgress();
        void onSuccess(List<Blog> blogList);
        void onFailure(String message);
    }

    interface Presenter {
        void getNews(String newsType);
        void unsubscribe();
    }

    interface Interactor {
        Single<BlogHolder> getNews(String newsType, int postPerPage, int offset, String locale);
    }
}
