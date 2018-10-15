package com.example.mrr.fortnitetracker.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.view.catalog.CatalogFragment;
import com.example.mrr.fortnitetracker.view.news.NewsFragment;
import com.example.mrr.fortnitetracker.view.stats.StatsSearchFragment;
import com.example.mrr.fortnitetracker.view.twitter.TwitterFragment;
import com.example.mrr.fortnitetracker.view.weapons.WeaponsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    public static final String KEY_LAST_SHOWN_FRAGMENT_ID = "KEY_LAST_SHOWN_FRAGMENT_ID";
    private int selectedItemId = 0;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        if(savedInstanceState != null) {
            selectedItemId = savedInstanceState.getInt(KEY_LAST_SHOWN_FRAGMENT_ID);
        } else {
            selectedItemId = R.id.nav_stats;
            replaceFragmentWithoutBackStack(new StatsSearchFragment());
        }
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(selectedItemId);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LAST_SHOWN_FRAGMENT_ID, selectedItemId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectedItemId = item.getItemId();
        Fragment fragment = null;

        if(selectedItemId == R.id.nav_stats) {
            fragment = new StatsSearchFragment();
        }
        else if(selectedItemId == R.id.nav_twitter) {
            fragment = new TwitterFragment();
        }
        else if(selectedItemId == R.id.nav_news) {
            fragment = new NewsFragment();
        }
        else if(selectedItemId == R.id.nav_patch_notes) {
            fragment = new WeaponsFragment();
        } else if(selectedItemId == R.id.nav_about) {
            fragment = new CatalogFragment();
        }

        replaceNavigationFragment(fragment);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceNavigationFragment(Fragment fragment) {
        if(fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            fragmentManager
                    .beginTransaction()
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }
    }

    public void replaceFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }
    }

    public void replaceFragmentWithoutBackStack(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }
    }

    public void addFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .add(R.id.content_frame, fragment)
                    .commit();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        for(Fragment fragment : fragmentManager.getFragments()) {
            if(fragment.isVisible()) {
                FragmentManager childFragmentManager = fragment.getChildFragmentManager();
                if(childFragmentManager.getBackStackEntryCount() > 0) {
                    childFragmentManager.popBackStack();
                    return;
                }
            }
        }
        super.onBackPressed();
    }
}
