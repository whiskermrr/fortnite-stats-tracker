package com.example.mrr.fortnitetracker.view.catalog;

import android.os.CountDownTimer;

import com.example.mrr.fortnitetracker.Utils.Cycler;
import com.example.mrr.fortnitetracker.Utils.FTConstants;
import com.example.mrr.fortnitetracker.Utils.ProjectUtils;
import com.example.mrr.fortnitetracker.models.catalog.CatalogEntryViewModel;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    private void startCountDownTimer(String time) {
        long timeLeft = ProjectUtils.getTimeUntilStringDate(time);

        if(timeLeft < 0)
            return;

        new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                long hours = TimeUnit.MILLISECONDS.toHours(l);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(l - hours * 3600000);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(l - hours * 3600000 - minutes * 60000);
                view.updateTime(String.format(Locale.getDefault(), FTConstants.TIMER_FORMAT, hours, minutes, seconds));
            }

            @Override
            public void onFinish() {
                getCurrentShop();
            }
        }.start();
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
                                    startCountDownTimer(catalog.getExpiration());
                                    view.initWeeklyStorefront(weeklyStorefrontCycler.getSublist());
                                    view.initDailyStorefront(dailyStorefrontCycler.getSublist());
                                },
                                error -> {
                                    view.hideProgress();
                                    view.onFailure(error.getMessage());
                                }
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
