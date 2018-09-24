package com.example.mrr.fortnitetracker.dagger.modules;

import com.example.mrr.fortnitetracker.dagger.components.NewsFragmentComponent;
import com.example.mrr.fortnitetracker.dagger.components.StatsFragmentComponent;
import com.example.mrr.fortnitetracker.dagger.components.TwitterFragmentComponent;
import com.example.mrr.fortnitetracker.dagger.components.WeaponsFragmentComponent;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsFragment;


import dagger.Module;

@Module(subcomponents = {
        StatsFragmentComponent.class,
        TwitterFragmentComponent.class,
        NewsFragmentComponent.class,
        WeaponsFragmentComponent.class
        })
public class MainActivityModule {


}
