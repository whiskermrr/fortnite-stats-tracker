package com.example.mrr.fortnitetracker.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.Utils.ProjectUtils;
import com.example.mrr.fortnitetracker.models.weapons.Weapon;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import io.reactivex.subjects.PublishSubject;

public class WeaponTypeSection extends StatelessSection {

    private Context context;
    private List<Weapon> weapons;
    private PublishSubject<Weapon> publishSubject;

    public WeaponTypeSection(Context context, List<Weapon> weapons, PublishSubject<Weapon> publishSubject) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.weapon_body)
                .headerResourceId(R.layout.weapon_header)
                .build()
        );
        this.context = context;
        this.weapons = weapons;
        this.publishSubject = publishSubject;
    }

    @Override
    public int getContentItemsTotal() {
        return weapons.size();
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new WeaponHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        WeaponHeaderViewHolder headerViewHolder = (WeaponHeaderViewHolder) holder;
        headerViewHolder.tvWeaponHeader.setText(weapons.get(0).getWeaponType());
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new WeaponViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        WeaponViewHolder weaponHolder = (WeaponViewHolder) holder;
        final Weapon weapon = weapons.get(position);
        int backgroundResourceId = ProjectUtils.getBackgroundResourceIdByRarity(weapon.getRarity());
        weaponHolder.itemView.setBackground(context.getDrawable(backgroundResourceId));

        weaponHolder.ibWeapon.setOnClickListener(view ->
                    publishSubject.onNext(weapons.get(position))
        );

        int weaponImageId = ProjectUtils.getDrawableIdByFileName(context, weapon.getImageFileName());
        if(weaponImageId > 0) {
            Picasso.with(context)
                    .load(weaponImageId)
                    .fit()
                    .centerInside()
                    .into(weaponHolder.ibWeapon);
        } else {
            Picasso.with(context)
                    .load(weapon.getImageUrl())
                    .fit()
                    .centerInside()
                    .into(weaponHolder.ibWeapon);
        }
    }

    class WeaponHeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvWeaponHeader)
        TextView tvWeaponHeader;

        private WeaponHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class WeaponViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ibWeapon)
        ImageButton ibWeapon;

        private WeaponViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
