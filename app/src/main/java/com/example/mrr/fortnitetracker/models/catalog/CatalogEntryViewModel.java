package com.example.mrr.fortnitetracker.models.catalog;

public class CatalogEntryViewModel {
    private String name;
    private int price;
    private String displayAssetPath;
    private String entryType;
    private String rarity;

    public CatalogEntryViewModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
