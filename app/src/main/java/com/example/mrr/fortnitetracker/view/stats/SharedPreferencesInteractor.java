package com.example.mrr.fortnitetracker.view.stats;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SharedPreferencesInteractor implements StatsContracts.SPInteractor {

    private final static String BUNDLE_RECENT_SEARCHES = "recent_searches";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesInteractor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveRecentSearchesToSharedPreferences(ArrayList<String> recentSearches) {
        String jsonRecentSearches = new Gson().toJson(recentSearches);
        sharedPreferences.edit()
                .putString(BUNDLE_RECENT_SEARCHES, jsonRecentSearches)
                .apply();
    }

    @Override
    public ArrayList<String> getRecentSearchesFromSharedPreferences() {
        ArrayList<String> recentSearches = new ArrayList<>();
        String jsonRecentSearches = sharedPreferences.getString(BUNDLE_RECENT_SEARCHES, null);
        if(jsonRecentSearches != null) {
            recentSearches = new Gson()
                    .fromJson(jsonRecentSearches, new TypeToken<ArrayList<String>>(){}.getType());
        }
        return recentSearches;
    }
}
