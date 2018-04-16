package com.example.mrr.fortnitetracker.dagger.components;

import com.example.mrr.fortnitetracker.Network.FortniteApiService;
import com.example.mrr.fortnitetracker.dagger.modules.FortniteApiServiceModule;
import com.example.mrr.fortnitetracker.dagger.scopes.FortniteApplicationScope;

import dagger.Component;

@FortniteApplicationScope
@Component(modules = FortniteApiServiceModule.class)
public interface FortniteApplicationComponent {

    FortniteApiService getFortniteApiService();
}
