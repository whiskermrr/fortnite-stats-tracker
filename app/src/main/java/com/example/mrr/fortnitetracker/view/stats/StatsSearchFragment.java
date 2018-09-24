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
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.Utils.ProjectConstants;
import com.example.mrr.fortnitetracker.dagger.modules.StatsSearchFragmentModule;
import com.example.mrr.fortnitetracker.view.MainActivity;
import com.example.rxjava_fortnite_api.models.stats.BattleRoyaleStats;

import java.util.ArrayList;

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

    private ArrayList<String> recentSearches;
    private String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        recentSearches = new ArrayList<>();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_stats, container, false);
        ButterKnife.bind(this, view);
        recentSearches = presenter.getRecentSearches();
        populateRecentSearchesTable();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.saveRecentSearches(recentSearches);
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
                    username = query;
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
        addUsernameToRecentSearches(username);
        populateRecentSearchesTable();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void openStatsFragment(BattleRoyaleStats battleRoyaleStats) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ProjectConstants.EXTRA_STATS, battleRoyaleStats);
        bundle.putString(ProjectConstants.EXTRA_USERNAME, username);
        Fragment fragment = new StatsSlidingTabsFragment();
        fragment.setArguments(bundle);

        ((MainActivity) getActivity()).replaceFragment(fragment);
    }

    private void populateRecentSearchesTable() {
        while(tableRecentSearches.getChildCount() > 1) {
            View row = tableRecentSearches.getChildAt(1);
            tableRecentSearches.removeView(row);
        }

        for(String username : recentSearches) {
            View view = new View(getActivity());
            view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
            tableRecentSearches.addView(view);
            tableRecentSearches.addView(prepareRecentSearchesRow(username));
        }
    }

    private TableRow prepareRecentSearchesRow(final String recentUsername) {
        final TableRow row = (TableRow) LayoutInflater
                .from(getActivity())
                .inflate(R.layout.recent_searches_table_row, null);

        TextView tUsername = row.findViewById(R.id.row_username);
        tUsername.setText(recentUsername);
        tUsername.setOnClickListener(view -> {
            username = recentUsername;
            presenter.getUserStats(recentUsername);
        });

        (row.findViewById(R.id.row_delete)).setOnClickListener(view -> {
            recentSearches.remove(recentUsername);
            tableRecentSearches.removeView(row);
        });

        return row;
    }

    private void addUsernameToRecentSearches(String recentUsername) {
        for(String user : recentSearches) {
            if (user.equals(recentUsername))
                return;
        }

        if(recentSearches.size() == 10) {
            recentSearches.remove(recentSearches.size() - 1);
        }
        recentSearches.add(0, recentUsername);
    }
}
