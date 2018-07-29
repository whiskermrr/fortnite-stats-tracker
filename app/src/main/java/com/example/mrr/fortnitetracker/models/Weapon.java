package com.example.mrr.fortnitetracker.models;

import com.example.mrr.fortnitetracker.models.enums.BulletType;
import com.example.mrr.fortnitetracker.models.enums.Rarity;
import com.example.mrr.fortnitetracker.models.enums.WeaponType;

public class Weapon {

    private WeaponType weaponType;
    private BulletType bulletType;
    private Rarity rarity;
    private float dps;
    private int damage;
    private float fireRate;
    private int magazineSize;
    private float reloadTime;
    private int structureDamage;
    private String name;

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    public void setBulletType(BulletType bulletType) {
        this.bulletType = bulletType;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public float getDps() {
        return dps;
    }

    public void setDps(float dps) {
        this.dps = dps;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getFireRate() {
        return fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public void setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }

    public int getStructureDamage() {
        return structureDamage;
    }

    public void setStructureDamage(int structureDamage) {
        this.structureDamage = structureDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

