package com.example.mrr.fortnitetracker.view.news;

public interface NewsContracts {

    interface View {
        void showProgress();
        void hideProgress();
        void onSuccess();
        void onFailure(String message);
    }

    interface Presenter {
        void getNews();
    }

    interface Interactor {

        interface OnNewsFinishedListener {

        }

    }
}
