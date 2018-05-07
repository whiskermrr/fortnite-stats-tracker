package com.example.mrr.fortnitetracker.view.stats;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrr.fortnitetracker.R;
import com.example.rxjava_fortnite_api.models.stats.BattleRoyaleStats;

import butterknife.ButterKnife;

public class StatsFragment extends Fragment {

    BattleRoyaleStats battleRoyaleStats;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_stats, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
