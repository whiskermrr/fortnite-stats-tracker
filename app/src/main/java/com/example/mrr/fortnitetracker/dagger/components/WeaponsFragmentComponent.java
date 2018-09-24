package com.example.mrr.fortnitetracker.dagger.components;

import com.example.mrr.fortnitetracker.dagger.modules.WeaponsFragmentModule;
import com.example.mrr.fortnitetracker.dagger.scopes.PerFragment;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = WeaponsFragmentModule.class)
@PerFragment
public interface WeaponsFragmentComponent extends AndroidInjector<WeaponsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WeaponsFragment>{}
}
