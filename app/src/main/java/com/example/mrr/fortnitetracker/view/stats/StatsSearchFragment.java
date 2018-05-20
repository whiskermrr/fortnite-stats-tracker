package com.example.mrr.fortnitetracker.view.stats;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.dagger.modules.StatsSearchFragmentModule;
import com.example.rxjava_fortnite_api.models.stats.BattleRoyaleStats;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;


public class StatsSearchFragment extends Fragment implements StatsContracts.View {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.table_recent_searches)
    TableLayout tableRecentSearches;

    @Inject
    StatsContracts.Presenter presenter;

    @Inject
    @Named(StatsSearchFragmentModule.STATS_FRAGMENT_MANAGER)
    FragmentManager fragmentManager;

    private List<String> recentSearches;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        recentSearches = new ArrayList<>();
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_stats, container, false);
        ButterKnife.bind(this, view);
        recentSearches.add("whiskermrr");
        recentSearches.add("arczitsu");
        recentSearches.add("dark");
        recentSearches.add("Wziuuum");
        recentSearches.add("Ninja");
        recentSearches.add("Terry 5L");
        recentSearches.add("miodeg");
        populateRecentSearchesTable();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if(searchManager != null) {
            SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    presenter.getUserStats(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(BattleRoyaleStats battleRoyaleStats) {
        openStatsFragment(battleRoyaleStats);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void openStatsFragment(BattleRoyaleStats battleRoyaleStats) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("stats", battleRoyaleStats);
        StatsFragment fragment = new StatsFragment();
        fragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.stats_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void populateRecentSearchesTable() {

        for(String username : recentSearches) {
            View view = new View(getActivity());
            view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
            tableRecentSearches.addView(view);
            tableRecentSearches.addView(prepareRecentSearchesRow(username));
        }
    }

    private TableRow prepareRecentSearchesRow(final String username) {
        final TableRow row = (TableRow) LayoutInflater
                .from(getActivity())
                .inflate(R.layout.recent_searches_table_row, null);

        TextView tUsername = row.findViewById(R.id.row_username);
        tUsername.setText(username);
        tUsername.setOnClickListener(view ->
            presenter.getUserStats(username)
        );

        (row.findViewById(R.id.row_delete)).setOnClickListener(view -> {
            recentSearches.remove(username);
            tableRecentSearches.removeView(row);
        });

        return row;
    }
}
