package com.example.mrr.fortnitetracker.view.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.Utils.FTConstants;
import com.example.mrr.fortnitetracker.models.catalog.CatalogEntryViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class StorefrontAdapter extends RecyclerView.Adapter<StorefrontAdapter.StorefrontViewHolder> {

    private List<CatalogEntryViewModel> entries;
    private Context context;
    private boolean isWeekly;

    public StorefrontAdapter(final Context context, boolean isWeekly) {
        entries = new LinkedList<>();
        this.context = context;
        this.isWeekly = isWeekly;
    }

    public void setCatalogEntries(List<CatalogEntryViewModel> newEntries) {
        entries.clear();
        entries.addAll(newEntries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StorefrontViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.catalog_item, parent, false);
        return new StorefrontViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StorefrontViewHolder holder, int position) {
        CatalogEntryViewModel entry = entries.get(position);
        switch(entry.getRarity()) {
            case FTConstants.RARITY_UNCOMMON:
                holder.itemView.setBackground(context.getDrawable(R.drawable.border_uncommon));
                break;
            case FTConstants.RARITY_RARE:
                holder.itemView.setBackground(context.getDrawable(R.drawable.border_rare));
                break;
            case FTConstants.RARITY_EPIC:
                holder.itemView.setBackground(context.getDrawable(R.drawable.border_epic));
                break;
            case FTConstants.RARITY_LEGENDARY:
                holder.itemView.setBackground(context.getDrawable(R.drawable.border_legendary));
                break;
            default:
                holder.itemView.setBackground(context.getDrawable(R.drawable.border_common));
                break;
        }
        holder.tvCatalogEntry.setText(entry.getName());

        try {
            InputStream inputStream;
            if(isWeekly && entry.getEntryType().equals(FTConstants.SKIN)) {
                inputStream = context.getAssets().open("featured" + "/" + entry.getDisplayAssetPath());
            } else {
                inputStream = context.getAssets().open(entry.getEntryType() + "/" + entry.getDisplayAssetPath());
            }
            Drawable icon = Drawable.createFromStream(inputStream, null);
            holder.ivCatalogEntry.setImageDrawable(icon);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class StorefrontViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCatalogEntry;
        TextView tvCatalogEntry;

        StorefrontViewHolder(View itemView) {
            super(itemView);
            ivCatalogEntry = itemView.findViewById(R.id.ivCatalogEntry);
            tvCatalogEntry = itemView.findViewById(R.id.tvCatalogEntry);
        }
    }
}
