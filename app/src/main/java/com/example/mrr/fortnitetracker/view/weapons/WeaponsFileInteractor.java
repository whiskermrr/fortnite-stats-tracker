package com.example.mrr.fortnitetracker.view.weapons;

import com.example.mrr.fortnitetracker.models.Weapon;

import java.util.List;

import io.reactivex.Single;

public class WeaponsFileInteractor implements WeaponsContracts.Interactor {

    public WeaponsFileInteractor() {

    }

    @Override
    public Single<List<Weapon>> getWeaponsFromFile() {
        return null;
    }
}
