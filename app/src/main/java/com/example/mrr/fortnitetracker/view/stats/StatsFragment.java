package com.example.mrr.fortnitetracker.view.stats;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrr.fortnitetracker.R;
import com.example.rxjava_fortnite_api.models.stats.BattleRoyaleStats;
import com.example.rxjava_fortnite_api.models.stats.StatsModel;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatsFragment extends Fragment {

    private BattleRoyaleStats battleRoyaleStats;

    @BindView(R.id.layout_body_solo)
    View layoutSoloBody;

    @BindView(R.id.layout_header_solo)
    View layoutHeaderSolo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        battleRoyaleStats = (BattleRoyaleStats) getArguments().getSerializable("stats");
        if(battleRoyaleStats != null) {
            Toast.makeText(getActivity(), String.valueOf(battleRoyaleStats.getSquad().getKills()), Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        ButterKnife.bind(this, view);
        populateBody(layoutSoloBody, battleRoyaleStats.getSolo());
        return view;
    }

    private void populateBody(View view, StatsModel stats) {
        // TODO: populate include layout
        TextView tMatchesPlayed = view.findViewById(R.id.stats_matches_played);
        TextView tWins = view.findViewById(R.id.stats_wins);
        TextView tPercentage = view.findViewById(R.id.stats_percentage);
        TextView tKills = view.findViewById(R.id.stats_kills);
        TextView tKDRatio = view.findViewById(R.id.stats_ratio);
        TextView tKillsPerMatch = view.findViewById(R.id.stats_kills_per_match);
        TextView tTop10 = view.findViewById(R.id.stats_top_10);
        TextView tTop25 = view.findViewById(R.id.stats_top_25);
        TextView tHours = view.findViewById(R.id.stats_hours_played);

        tMatchesPlayed.setText(String.valueOf(stats.getMatches()));
        tWins.setText(String.valueOf(stats.getWins()));
        String winPercentage = String.format(Locale.US, "%.1f", stats.getWinPercentage()) + "%";
        tPercentage.setText(winPercentage);
        tKills.setText(String.valueOf(stats.getKills()));
        tKDRatio.setText(String.format(Locale.US, "%.2f", stats.getKDRatio()));
        tKillsPerMatch.setText(String.format(Locale.US, "%.2f", stats.getKillsPerMatch()));

        String top10 = "";
        String top25 = "";

        switch (stats.getMode()) {
            case "_p2":
                top10 = String.valueOf(stats.getTop10());
                top25 = String.valueOf(stats.getTop25());
                break;
            case "_p10":
                top10 = String.valueOf(stats.getTop5());
                top25 = String.valueOf(stats.getTop12());
                break;
            case "_p9":
                top10 = String.valueOf(stats.getTop3());
                top25 = String.valueOf(stats.getTop6());
                break;
            case "_p":
                // TODO: calculation for lifeTime stats
                break;
        }

        tTop10.setText(top10);
        tTop25.setText(top25);
        tHours.setText(String.valueOf(stats.getTime()));
    }
}
