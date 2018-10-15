package com.example.mrr.fortnitetracker.view.catalog;

import com.example.mrr.fortnitetracker.models.catalog.CatalogEntryViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CatalogPresenter implements CatalogContracts.Presenter {

    private CatalogContracts.View view;
    private CatalogContracts.Interactor catalogInteractor;
    private int cycleWeeklyIndex = 2;
    private int cycleDailyInex = 2;
    private List<CatalogEntryViewModel> weeklyStorefront;
    private List<CatalogEntryViewModel> dailyStorefront;

    private CompositeDisposable disposables;

    public CatalogPresenter(CatalogContracts.View view, CatalogContracts.Interactor catalogInteractor) {
        this.view = view;
        this.catalogInteractor = catalogInteractor;
        disposables = new CompositeDisposable();
    }


    @Override
    public void getCurrentShop() {
        view.showProgress();
        disposables.add(
                catalogInteractor.getCatalog()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                catalog -> {
                                    cycleWeeklyIndex = 2;
                                    cycleDailyInex = 4;
                                    weeklyStorefront = catalog.getWeeklyOffer();
                                    dailyStorefront = catalog.getDailyOffer();
                                    view.hideProgress();
                                    view.initWeeklyStorefront(weeklyStorefront.subList(0, cycleWeeklyIndex));
                                    view.initDailyStorefront(dailyStorefront.subList(0, cycleDailyInex));
                                },
                                error -> view.onFailure(error.getMessage())
                        )
        );
    }

    @Override
    public void cycleWeeklyOffer() {
        cycleWeeklyIndex++;
        if(cycleWeeklyIndex > weeklyStorefront.size()) {
            cycleWeeklyIndex = 2;
        }
        view.initWeeklyStorefront(weeklyStorefront.subList(cycleWeeklyIndex - 2, cycleWeeklyIndex));
    }

    @Override
    public void cycleDailyOffer() {
        cycleDailyInex++;
        if(cycleDailyInex > dailyStorefront.size()) {
            cycleDailyInex = 4;
        }
        view.initDailyStorefront(dailyStorefront.subList(cycleDailyInex - 4, cycleDailyInex));
    }

    @Override
    public void unsubscribe() {
        disposables.dispose();
    }
}
