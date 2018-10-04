package com.example.mrr.fortnitetracker.models.weapons;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weapon implements Serializable {

    @SerializedName("weapon_type")
    private String weaponType;
    @SerializedName("bullet_type")
    private String bulletType;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("image_file_name")
    private String imageFileName;
    @SerializedName("rarity")
    private String rarity;
    @SerializedName("dps")
    private String dps;
    @SerializedName("damage")
    private String damage;
    @SerializedName("fire_rate")
    private String fireRate;
    @SerializedName("magazine_size")
    private String magazineSize;
    @SerializedName("reload_time")
    private String reloadTime;
    @SerializedName("structure_damage")
    private String structureDamage;
    @SerializedName("name")
    private String name;

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getBulletType() {
        return bulletType;
    }

    public void setBulletType(String bulletType) {
        this.bulletType = bulletType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getDps() {
        return dps;
    }

    public void setDps(String dps) {
        this.dps = dps;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getFireRate() {
        return fireRate;
    }

    public void setFireRate(String fireRate) {
        this.fireRate = fireRate;
    }

    public String getMagazineSize() {
        return magazineSize;
    }

    public void setMagazineSize(String magazineSize) {
        this.magazineSize = magazineSize;
    }

    public String getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(String reloadTime) {
        this.reloadTime = reloadTime;
    }

    public String getStructureDamage() {
        return structureDamage;
    }

    public void setStructureDamage(String structureDamage) {
        this.structureDamage = structureDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}

