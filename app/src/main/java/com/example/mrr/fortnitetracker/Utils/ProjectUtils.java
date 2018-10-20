package com.example.mrr.fortnitetracker.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.mrr.fortnitetracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProjectUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null)
                return networkInfo.isConnected();
        }

        return false;
    }

    public static int getDrawableIdByFileName(final Context context, String fileName) {
        return context.getResources()
                .getIdentifier(
                        fileName.replace(".png", ""),
                        "drawable", context.getPackageName()
                );
    }

    public static int getBackgroundResourceIdByRarity(String rarity) {
        int backgroundResourceId;
        switch(rarity) {
            case FTConstants.RARITY_UNCOMMON:
                backgroundResourceId = R.drawable.border_uncommon;
                break;
            case FTConstants.RARITY_RARE:
                backgroundResourceId = R.drawable.border_rare;
                break;
            case FTConstants.RARITY_EPIC:
                backgroundResourceId = R.drawable.border_epic;
                break;
            case FTConstants.RARITY_LEGENDARY:
                backgroundResourceId = R.drawable.border_legendary;
                break;
            default:
                backgroundResourceId = R.drawable.border_common;
                break;
        }
        return backgroundResourceId;
    }

    public static String getRarityOfCatalogEntry(String entryType, int price) {
        switch (entryType) {
            case FTConstants.SKIN:
                if (price >= FTConstants.LEGENDARY_SKIN_PRICE) {
                    return FTConstants.RARITY_LEGENDARY;
                } else if (price >= FTConstants.EPIC_SKIN_PRICE) {
                    return FTConstants.RARITY_EPIC;
                } else if (price >= FTConstants.RARE_SKIN_PRICE) {
                    return FTConstants.RARITY_RARE;
                } else {
                    return FTConstants.RARITY_UNCOMMON;
                }
            case FTConstants.PICKAXE:
                if (price >= FTConstants.EPIC_PICKAXE_PRICE) {
                    return FTConstants.RARITY_EPIC;
                } else if (price >= FTConstants.RARE_PICKAXE_PRICE) {
                    return FTConstants.RARITY_RARE;
                } else {
                    return FTConstants.RARITY_UNCOMMON;
                }
            case FTConstants.GLIDER:
                if (price >= FTConstants.LEGENDARY_GLIDER_PRICE) {
                    return FTConstants.RARITY_LEGENDARY;
                } else if (price >= FTConstants.EPIC_GLIDER_PRICE) {
                    return FTConstants.RARITY_EPIC;
                } else if (price >= FTConstants.RARE_GLIDER_PRICE) {
                    return FTConstants.RARITY_RARE;
                } else {
                    return FTConstants.RARITY_UNCOMMON;
                }
            case FTConstants.DANCE:
                if (price >= FTConstants.EPIC_EMOTE_PRICE) {
                    return FTConstants.RARITY_EPIC;
                } else if (price >= FTConstants.RARE_EMOTE_PRICE) {
                    return FTConstants.RARITY_RARE;
                } else {
                    return FTConstants.RARITY_UNCOMMON;
                }
            default:
                return FTConstants.RARITY_UNCOMMON;
        }
    }

    public static long getTimeUntilStringDate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FTConstants.CATALOG_TIME_FORMAT, Locale.getDefault());
        try {
            Date catalogDate = dateFormat.parse(time);
            Date nowDate = Calendar.getInstance().getTime();
            return catalogDate.getTime() - nowDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
