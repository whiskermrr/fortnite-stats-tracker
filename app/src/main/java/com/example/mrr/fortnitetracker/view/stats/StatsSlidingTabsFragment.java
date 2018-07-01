package com.example.mrr.fortnitetracker.view.stats;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.Utils.ProjectConstants;
import com.example.mrr.fortnitetracker.layout_components.SlidingTabLayout;
import com.example.rxjava_fortnite_api.models.stats.BattleRoyaleStats;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatsSlidingTabsFragment extends Fragment {

    private BattleRoyaleStats battleRoyaleStats;

    @BindView(R.id.stats_view_pager)
    ViewPager viewPager;

    @BindView(R.id.sliding_tabs_layout)
    SlidingTabLayout slidingTabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        battleRoyaleStats = (BattleRoyaleStats) getArguments().getSerializable(ProjectConstants.EXTRA_STATS);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_sliding_tabs_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        viewPager.setAdapter(new StatsViewPagerAdapter(getChildFragmentManager()));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);

        return view;
    }

    class StatsViewPagerAdapter extends FragmentPagerAdapter {

        private String[] titles;

        private StatsViewPagerAdapter(FragmentManager manager) {
            super(manager);
            titles = getResources().getStringArray(R.array.tabs_titles);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new StatsFragment();
            Bundle bundle = new Bundle();

            if(position == 0) {
                bundle.putSerializable(ProjectConstants.EXTRA_STATS_MODE, battleRoyaleStats.getSolo());
                bundle.putString(ProjectConstants.EXTRA_MODE, getResources().getString(R.string.solo));
                bundle.putInt(ProjectConstants.EXTRA_HEADER_BACKGROUND, getResources().getColor(R.color.colorSoloBlue));
            }
            else if(position == 1) {
                bundle.putSerializable(ProjectConstants.EXTRA_STATS_MODE, battleRoyaleStats.getDuo());
                bundle.putString(ProjectConstants.EXTRA_MODE, getResources().getString(R.string.duo));
                bundle.putInt(ProjectConstants.EXTRA_HEADER_BACKGROUND, getResources().getColor(R.color.colorTurkishOrange));
            }
            else if(position == 2) {
                bundle.putSerializable(ProjectConstants.EXTRA_STATS_MODE, battleRoyaleStats.getSquad());
                bundle.putString(ProjectConstants.EXTRA_MODE, getResources().getString(R.string.squad));
                bundle.putInt(ProjectConstants.EXTRA_HEADER_BACKGROUND, getResources().getColor(R.color.colorLightFortniteViolet));
            }

            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
