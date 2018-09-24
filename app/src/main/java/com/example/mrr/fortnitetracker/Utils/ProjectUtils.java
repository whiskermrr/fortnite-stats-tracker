package com.example.mrr.fortnitetracker.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.mrr.fortnitetracker.R;

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

    public static int getColorResourceIdByWeaponRarity(String rarity) {
        int colorResourceId;
        switch(rarity) {
            case FTConstants.RARITY_COMMON:
                colorResourceId = R.color.colorRarityCommon;
                break;
            case FTConstants.RARITY_UNCOMMON:
                colorResourceId = R.color.colorRarityUncommon;
                break;
            case FTConstants.RARITY_RARE:
                colorResourceId = R.color.colorRarityRare;
                break;
            case FTConstants.RARITY_EPIC:
                colorResourceId = R.color.colorRarityEpic;
                break;
            case FTConstants.RARITY_LEGENDARY:
                colorResourceId = R.color.colorRarityLegendary;
                break;
            default:
                colorResourceId = R.color.colorRarityCommon;
                break;

        }
        return colorResourceId;
    }
}
