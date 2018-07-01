package com.example.mrr.fortnitetracker.view.stats;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrr.fortnitetracker.R;
import com.example.rxjava_fortnite_api.models.stats.StatsModel;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatsFragment extends Fragment {

    private StatsModel stats;

    @BindView(R.id.layout_stats_header)
    View layoutStatsHeader;

    @BindView(R.id.layout_stats_body)
    View layoutStatsBody;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        stats = (StatsModel) getArguments().getSerializable("stats_mode");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        ButterKnife.bind(this, view);
        populateBody();
        populateHeader();
        return view;
    }

    private void populateHeader() {
        String score = "Score: " + NumberFormat.getNumberInstance(Locale.US).format(stats.getScore());
        String mode = "";
        int headerBackgroundColor = getResources().getColor(R.color.colorPrimary);

        switch (stats.getMode()) {
            case "_p2":
                headerBackgroundColor = getResources().getColor(R.color.colorSoloBlue);
                mode = "SOLO";
                break;
            case "_p10":
                headerBackgroundColor = getResources().getColor(R.color.colorTurkishOrange);
                mode = "DUO";
                break;
            case "_p9":
                headerBackgroundColor = getResources().getColor(R.color.colorLightFortniteViolet);
                mode = "SQUAD";
                break;
            case "_p":
                // TODO: calculation for lifeTime stats
                break;
        }

        ((TextView) layoutStatsHeader.findViewById(R.id.stats_header_score)).setText(score);
        ((TextView) layoutStatsHeader.findViewById(R.id.stats_header_mode)).setText(mode);
        layoutStatsHeader.setBackgroundColor(headerBackgroundColor);
    }

    private void populateBody() {
        String winPercentage = String.format(Locale.US, "%.1f", stats.getWinPercentage()) + "%";
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

        ((TextView) layoutStatsBody.findViewById(R.id.stats_matches_played)).setText(String.valueOf(stats.getMatches()));
        ((TextView) layoutStatsBody.findViewById(R.id.stats_wins)).setText(String.valueOf(stats.getWins()));
        ((TextView) layoutStatsBody.findViewById(R.id.stats_percentage)).setText(winPercentage);
        ((TextView) layoutStatsBody.findViewById(R.id.stats_kills)).setText(String.valueOf(stats.getKills()));
        ((TextView) layoutStatsBody.findViewById(R.id.stats_ratio)).setText(String.format(Locale.US, "%.2f", stats.getKDRatio()));
        ((TextView) layoutStatsBody.findViewById(R.id.stats_kills_per_match)).setText(String.format(Locale.US, "%.2f", stats.getKillsPerMatch()));
        ((TextView) layoutStatsBody.findViewById(R.id.stats_top_10)).setText(top10);
        ((TextView) layoutStatsBody.findViewById(R.id.stats_top_25)).setText(top25);
        ((TextView) layoutStatsBody.findViewById(R.id.stats_hours_played)).setText(String.valueOf(stats.getTime()));
    }
}
