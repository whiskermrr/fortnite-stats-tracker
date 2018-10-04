package com.example.mrr.fortnitetracker.dagger.modules;

import android.content.Context;

import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.models.weapons.Weapon;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsContracts;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsFileInteractor;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsFragment;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsPresenter;

import dagger.Module;
import dagger.Provides;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.reactivex.subjects.PublishSubject;

@Module
public class WeaponsFragmentModule {

    @Provides
    @PerFragment
    public WeaponsContracts.View view(WeaponsFragment fragment) {
        return fragment;
    }

    @Provides
    @PerFragment
    public WeaponsContracts.Interactor weaponInteractor(Context context) {
        return new WeaponsFileInteractor(context);
    }

    @Provides
    @PerFragment
    public WeaponsContracts.Presenter weaponsPresenter(WeaponsContracts.View view, WeaponsContracts.Interactor interactor) {
        return new WeaponsPresenter(view, interactor);
    }

    @Provides
    @PerFragment
    public SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter() {
        return new SectionedRecyclerViewAdapter();
    }

    @Provides
    @PerFragment
    public PublishSubject<Weapon> weaponPublishSubject() {
        return PublishSubject.create();
    }
}
