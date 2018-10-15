package com.example.mrr.fortnitetracker.Utils;

import java.util.ArrayList;
import java.util.List;

public class Cycler<T> {

    private List<T> list;
    private int[] indexes;

    public Cycler(int counter) {
        indexes = new int[counter];
        for(int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        this.list = new ArrayList<>();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getSublist() {
        if(indexes.length >= list.size()) {
            return this.list;
        }
        List<T> subList = new ArrayList<>();
        for (int i = 0; i < indexes.length; i++) {
            subList.add(list.get(indexes[i]));
        }
        return subList;
    }

    public List<T> cycle() {
        if(indexes.length >= list.size()) {
            return this.list;
        }
        List<T> subList = new ArrayList<>();
        for(int i = 0; i < indexes.length; i++) {
            indexes[i]++;
            if(indexes[i] == list.size()) {
                indexes[i] = 0;
            }
            subList.add(list.get(indexes[i]));
        }
        return subList;
    }
}
