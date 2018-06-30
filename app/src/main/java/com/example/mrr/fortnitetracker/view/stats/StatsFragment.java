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

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatsFragment extends Fragment {

    private BattleRoyaleStats battleRoyaleStats;

    @BindView(R.id.layout_header_solo)
    View layoutHeaderSolo;

    @BindView(R.id.layout_body_solo)
    View layoutBodySolo;

    @BindView(R.id.layout_header_duo)
    View layoutHeaderDuo;

    @BindView(R.id.layout_body_duo)
    View layoutBodyDuo;

    @BindView(R.id.layout_header_squad)
    View layoutHeaderSquad;

    @BindView(R.id.layout_body_squad)
    View layoutBodySquad;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        battleRoyaleStats = (BattleRoyaleStats) getArguments().getSerializable("stats");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        ButterKnife.bind(this, view);
        populateBody(layoutBodySolo, battleRoyaleStats.getSolo());
        populateHeader(layoutHeaderSolo, battleRoyaleStats.getSolo());
        populateBody(layoutBodyDuo, battleRoyaleStats.getDuo());
        populateHeader(layoutHeaderDuo, battleRoyaleStats.getDuo());
        populateBody(layoutBodySquad, battleRoyaleStats.getSquad());
        populateHeader(layoutHeaderSquad, battleRoyaleStats.getSquad());
        return view;
    }

    private void populateHeader(View view, StatsModel stats) {
        TextView tScore = view.findViewById(R.id.stats_header_score);
        String score = "Score: " + NumberFormat.getNumberInstance(Locale.US).format(stats.getScore());
        tScore.setText(score);
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
