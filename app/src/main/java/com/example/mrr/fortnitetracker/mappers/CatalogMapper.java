package com.example.mrr.fortnitetracker.mappers;

import com.example.mrr.fortnitetracker.Utils.FTConstants;
import com.example.mrr.fortnitetracker.Utils.ProjectUtils;
import com.example.mrr.fortnitetracker.models.catalog.CatalogEntryViewModel;
import com.example.mrr.fortnitetracker.models.catalog.CatalogViewModel;
import com.example.rxjava_fortnite_api.models.catalog.Catalog;
import com.example.rxjava_fortnite_api.models.catalog.CatalogEntry;
import com.example.rxjava_fortnite_api.models.catalog.Storefront;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class CatalogMapper {

    public static Single<CatalogViewModel> transform(Catalog catalog) {
        CatalogViewModel catalogViewModel = new CatalogViewModel();
        catalogViewModel.setDailyPurchaseHrs(catalog.getDailyPurchaseHrs());
        catalogViewModel.setRefreshIntervalHrs(catalog.getRefreshIntervalHrs());
        catalogViewModel.setExpiration(catalog.getExpiration());

        for(Storefront storefront : catalog.getStorefronts()) {
            if(storefront.getName().equals(FTConstants.BR_DAILY_STOREFRONT)) {
                catalogViewModel.setDailyOffer(transform(storefront));
            } else if(storefront.getName().equals(FTConstants.BR_WEEKLY_STOREFRONT)) {
                catalogViewModel.setWeeklyOffer(transform(storefront));
            }
        }

        return Single.just(catalogViewModel);
    }

    private static List<CatalogEntryViewModel> transform(Storefront storefront) {
        List<CatalogEntryViewModel> newEntries = new ArrayList<>();

        for(final CatalogEntry catalogEntry : storefront.getCatalogEntries()) {
            CatalogEntryViewModel newEntry = new CatalogEntryViewModel();
            newEntry.setName(convertDevNameToName(catalogEntry.getDevName()));
            newEntry.setDisplayAssetPath(
                    newEntry.getName() + ".png"
            );

            if(catalogEntry.getPrices() != null && catalogEntry.getPrices().size() > 0) {
                newEntry.setPrice(catalogEntry.getPrices().get(0).getFinalPrice());
            } else {
                newEntry.setPrice(0);
            }
            newEntry.setEntryType(getEntryTypeByRequirementId(
                    catalogEntry.getRequirements().get(0).getRequiredId()
                    )
            );
            newEntry.setRarity(
                    ProjectUtils.getRarityOfCatalogEntry(
                            newEntry.getEntryType(), newEntry.getPrice())
            );

            newEntries.add(newEntry);
        }

        return newEntries;
    }

    private static String getEntryTypeByRequirementId(String requirementId) {
        if(requirementId.contains(FTConstants.SKIN)) {
            return FTConstants.SKIN;
        } else if(requirementId.contains(FTConstants.GLIDER)) {
            return FTConstants.GLIDER;
        } else if(requirementId.contains(FTConstants.PICKAXE)) {
            return FTConstants.PICKAXE;
        } else if(requirementId.contains(FTConstants.DANCE)) {
            return FTConstants.DANCE;
        } else {
            return null;
        }
    }

    private static String convertDevNameToName(String catalogEntryName) {
        catalogEntryName = catalogEntryName.replace(",", " ,");
        String[] words = catalogEntryName.split("\\s+");
        StringBuilder newName = new StringBuilder();
        boolean append = false;
        for(String word : words) {
            if(word.equals(",") || word.equals("for")) {
                break;
            }
            if(append) {
                newName.append(word).append(" ");
            }
            if(word.equals("x")) {
                append = true;
            }
        }
        return newName.toString().trim();
    }
}
