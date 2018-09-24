package com.example.mrr.fortnitetracker.models;

import java.util.ArrayList;
import java.util.List;

public class WeaponsHolder {
    private List<Weapon> assaultRifles;
    private List<Weapon> machineGuns;
    private List<Weapon> smgs;
    private List<Weapon> pistols;
    private List<Weapon> shotguns;
    private List<Weapon> sniperRifles;
    private List<Weapon> explosiveWeapons;

    public WeaponsHolder() {
        this.assaultRifles = new ArrayList<>();
        this.machineGuns = new ArrayList<>();
        this.smgs = new ArrayList<>();
        this.pistols = new ArrayList<>();
        this.shotguns = new ArrayList<>();
        this.sniperRifles = new ArrayList<>();
        this.explosiveWeapons = new ArrayList<>();
    }

    public List<Weapon> getAssaultRifles() {
        return assaultRifles;
    }

    public List<Weapon> getMachineGuns() {
        return machineGuns;
    }

    public List<Weapon> getSmgs() {
        return smgs;
    }

    public List<Weapon> getPistols() {
        return pistols;
    }

    public List<Weapon> getShotguns() {
        return shotguns;
    }

    public List<Weapon> getSniperRifles() {
        return sniperRifles;
    }

    public List<Weapon> getExplosiveWeapons() {
        return explosiveWeapons;
    }
}
