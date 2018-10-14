package com.example.mrr.fortnitetracker.view.catalog;

import com.example.mrr.fortnitetracker.models.catalog.CatalogEntryViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CatalogPresenter implements CatalogContracts.Presenter {

    private CatalogContracts.View view;
    private CatalogContracts.Interactor catalogInteractor;
    private int cycleIndex = 2;
    private List<CatalogEntryViewModel> weeklyStorefront;

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
                                    cycleIndex = 2;
                                    weeklyStorefront = catalog.getWeeklyOffer();
                                    view.hideProgress();
                                    view.initWeeklyStorefront(weeklyStorefront.subList(0, cycleIndex));
                                },
                                error -> view.onFailure(error.getMessage())
                        )
        );
    }

    @Override
    public void cycleWeeklyOffer() {
        cycleIndex++;
        if(cycleIndex > weeklyStorefront.size()) {
            cycleIndex = 2;
        }
        view.initWeeklyStorefront(weeklyStorefront.subList(cycleIndex - 2, cycleIndex));
    }

    @Override
    public void unsubscribe() {
        disposables.dispose();
    }
}
