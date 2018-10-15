package com.example.mrr.fortnitetracker.models.catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogViewModel {
    private int refreshIntervalHrs;
    private int dailyPurchaseHrs;
    private String expiration;
    private List<CatalogEntryViewModel> weeklyOffer;
    private List<CatalogEntryViewModel> dailyOffer;

    public CatalogViewModel() {
        weeklyOffer = new ArrayList<>();
        dailyOffer = new ArrayList<>();
    }

    public int getRefreshIntervalHrs() {
        return refreshIntervalHrs;
    }

    public void setRefreshIntervalHrs(int refreshIntervalHrs) {
        this.refreshIntervalHrs = refreshIntervalHrs;
    }

    public int getDailyPurchaseHrs() {
        return dailyPurchaseHrs;
    }

    public void setDailyPurchaseHrs(int dailyPurchaseHrs) {
        this.dailyPurchaseHrs = dailyPurchaseHrs;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public List<CatalogEntryViewModel> getWeeklyOffer() {
        return weeklyOffer;
    }

    public void setWeeklyOffer(List<CatalogEntryViewModel> weeklyOffer) {
        this.weeklyOffer = weeklyOffer;
    }

    public List<CatalogEntryViewModel> getDailyOffer() {
        return dailyOffer;
    }

    public void setDailyOffer(List<CatalogEntryViewModel> dailyOffer) {
        this.dailyOffer = dailyOffer;
    }
}
