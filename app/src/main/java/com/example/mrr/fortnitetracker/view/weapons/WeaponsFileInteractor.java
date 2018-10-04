package com.example.mrr.fortnitetracker.view.weapons;

import android.content.Context;

import com.example.mrr.fortnitetracker.models.weapons.Weapon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Single;

public class WeaponsFileInteractor implements WeaponsContracts.Interactor {

    private Context context;

    public WeaponsFileInteractor(Context context) {
        this.context = context;
    }

    @Override
    public Single<List<Weapon>> getWeaponsFromFile() {
        try {
            InputStream inputStream = context.getAssets().open("json/weapons.json");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            Type category = TypeToken.getParameterized(LinkedList.class, Weapon.class).getType();
            List<Weapon> weapons = new Gson().fromJson(json, category);
            return Single.just(weapons);
        } catch (IOException e) {
            e.printStackTrace();
            return Single.error(e);
        }
    }
}
