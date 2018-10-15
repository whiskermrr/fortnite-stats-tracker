package com.example.mrr.fortnitetracker.view.catalog;

import com.example.mrr.fortnitetracker.Utils.Cycler;
import com.example.mrr.fortnitetracker.models.catalog.CatalogEntryViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CatalogPresenter implements CatalogContracts.Presenter {

    private CatalogContracts.View view;
    private CatalogContracts.Interactor catalogInteractor;
    private Cycler<CatalogEntryViewModel> weeklyStorefrontCycler;
    private Cycler<CatalogEntryViewModel> dailyStorefrontCycler;

    private CompositeDisposable disposables;

    public CatalogPresenter(CatalogContracts.View view, CatalogContracts.Interactor catalogInteractor) {
        this.view = view;
        this.catalogInteractor = catalogInteractor;
        disposables = new CompositeDisposable();
        weeklyStorefrontCycler = new Cycler<>(2);
        dailyStorefrontCycler = new Cycler<>(4);
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
                                    weeklyStorefrontCycler.setList(catalog.getWeeklyOffer());
                                    dailyStorefrontCycler.setList(catalog.getDailyOffer());
                                    view.hideProgress();
                                    view.initWeeklyStorefront(weeklyStorefrontCycler.getSublist());
                                    view.initDailyStorefront(dailyStorefrontCycler.getSublist());
                                },
                                error -> view.onFailure(error.getMessage())
                        )
        );
    }

    @Override
    public void cycleWeeklyOffer() {
        view.initWeeklyStorefront(weeklyStorefrontCycler.cycle());
    }

    @Override
    public void cycleDailyOffer() {
        view.initDailyStorefront(dailyStorefrontCycler.cycle());
    }

    @Override
    public void unsubscribe() {
        disposables.dispose();
    }
}
