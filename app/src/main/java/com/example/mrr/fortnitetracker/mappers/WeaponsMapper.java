package com.example.mrr.fortnitetracker.mappers;

import android.util.Pair;

import com.example.mrr.fortnitetracker.Utils.FTConstants;
import com.example.mrr.fortnitetracker.models.Weapon;
import com.example.mrr.fortnitetracker.models.WeaponsHolder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class WeaponsMapper {

    public static Single<WeaponsHolder> transform(List<Weapon> weapons) {
        WeaponsHolder weaponsHolder = new WeaponsHolder();
        for(Weapon weapon : weapons) {
            switch(weapon.getWeaponType()) {
                case FTConstants.ASSAULT_RIFLE:
                    weaponsHolder.getAssaultRifles().add(weapon);
                    break;
                case FTConstants.MACHINE_GUN:
                    weaponsHolder.getMachineGuns().add(weapon);
                    break;
                case FTConstants.SMG:
                    weaponsHolder.getSmgs().add(weapon);
                    break;
                case FTConstants.PISTOL:
                    weaponsHolder.getPistols().add(weapon);
                    break;
                case FTConstants.SHOTGUN:
                    weaponsHolder.getShotguns().add(weapon);
                    break;
                case FTConstants.SNIPER_RIFLE:
                    weaponsHolder.getSniperRifles().add(weapon);
                    break;
                case FTConstants.EXPLOSIVE_WEAPON:
                    weaponsHolder.getExplosiveWeapons().add(weapon);
            }
        }
        return Single.just(weaponsHolder);
    }

    public static List<String> transformToList(Weapon weapon) {
        List<String> weaponAsList = new LinkedList<>();
        weaponAsList.add(weapon.getDps());
        weaponAsList.add(weapon.getDamage());
        weaponAsList.add(weapon.getFireRate());
        weaponAsList.add(weapon.getBulletType());
        weaponAsList.add(weapon.getMagazineSize());
        weaponAsList.add(weapon.getReloadTime());
        weaponAsList.add(weapon.getStructureDamage());

        return weaponAsList;
    }

    public static List<Pair<String, String>> transformToDictionary(Weapon weapon, String[] weaponDetails) {
        List<String> weaponAsList = transformToList(weapon);
        List<Pair<String, String>> weaponAsDictionary = new LinkedList<>();

        for(int i = 0; i < weaponAsList.size() && i < weaponDetails.length; i++) {
            weaponAsDictionary.add(new Pair<>(weaponDetails[i], weaponAsList.get(i)));
        }

        return weaponAsDictionary;
    }
}
