package com.example.mrr.fortnitetracker.models.catalog;

public class CatalogEntryViewModel {
    private String name;
    private String price;
    private String displayAssetPath;
    private String entryType;

    public CatalogEntryViewModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDisplayAssetPath() {
        return displayAssetPath;
    }

    public void setDisplayAssetPath(String displayAssetPath) {
        this.displayAssetPath = displayAssetPath;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }
}
