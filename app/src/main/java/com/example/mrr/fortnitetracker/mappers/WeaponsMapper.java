package com.example.mrr.fortnitetracker.mappers;

import com.example.mrr.fortnitetracker.Utils.FTConstants;
import com.example.mrr.fortnitetracker.models.Weapon;
import com.example.mrr.fortnitetracker.models.WeaponsHolder;

import java.util.List;

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
}
