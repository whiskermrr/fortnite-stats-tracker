package com.example.mrr.fortnitetracker.view.weapons;

import com.example.mrr.fortnitetracker.mappers.WeaponsMapper;
import com.example.mrr.fortnitetracker.models.weapons.WeaponsHolder;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WeaponsPresenter implements WeaponsContracts.Presenter {

    private WeaponsContracts.View view;
    private WeaponsContracts.Interactor interactor;
    private CompositeDisposable disposables;

    public WeaponsPresenter(WeaponsContracts.View view, WeaponsContracts.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
        disposables = new CompositeDisposable();
    }

    @Override
    public void getWeapons() {
        disposables.add(
                interactor.getWeaponsFromFile()
                        .flatMap(WeaponsMapper::transform)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                WeaponsPresenter.this::onFinished,
                                WeaponsPresenter.this::onFailure
                        )
        );
    }

    private void onFinished(WeaponsHolder weaponHolder) {
        view.hideProgress();
        view.onSuccess(weaponHolder);
    }

    private void onFailure(Throwable throwable) {
        String message = throwable.getMessage();
        view.hideProgress();
        view.onFailure(message);
    }

    @Override
    public void unsubscribe() {
        disposables.clear();
    }
}
