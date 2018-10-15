package com.example.mrr.fortnitetracker.view.weapons;

import com.example.mrr.fortnitetracker.models.weapons.Weapon;
import com.example.mrr.fortnitetracker.models.weapons.WeaponsHolder;

import java.util.List;

import io.reactivex.Single;

public interface WeaponsContracts {

    interface View {
        void onSuccess(WeaponsHolder weapons);
        void onFailure(String message);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void getWeapons();
        void unsubscribe();
    }

    interface Interactor {
        Single<List<Weapon>> getWeaponsFromFile();
    }
}
