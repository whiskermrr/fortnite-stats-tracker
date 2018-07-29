package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsContracts;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsFileInteractor;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsFragment;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class WeaponsFragmentModule {

    @Provides
    @PerFragment
    public WeaponsContracts.View view(WeaponsFragment fragment) {
        return fragment;
    }

    @Provides
    @PerFragment
    public WeaponsContracts.Interactor weaponInteractor() {
        return new WeaponsFileInteractor();
    }

    @Provides
    @PerFragment
    public WeaponsContracts.Presenter weaponsPresenter(WeaponsContracts.View view, WeaponsContracts.Interactor interactor) {
        return new WeaponsPresenter(view, interactor);
    }
}
