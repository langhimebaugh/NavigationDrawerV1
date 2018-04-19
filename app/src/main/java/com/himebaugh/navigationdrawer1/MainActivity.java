package com.himebaugh.navigationdrawer1;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

// Navigation Drawer with fragments and settings fragment

public class MainActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener, NavigationView.OnNavigationItemSelectedListener, BaseFragment.OnFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager mFragmentManager;
    private Fragment mFragment;
    private Toolbar mToolbar;
    // private static final String TAG = "current-frag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            Log.i(TAG, "onCreate: savedInstanceState == null");
            // mFragmentManager = getSupportFragmentManager();
            displayFragment(MainFragment.class);
        } else {
            Log.i(TAG, "onCreate: savedInstanceState != null");
        }

        // Just to show SharedPreferences works...
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Register the listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        loadColorFromPreferences(sharedPreferences);

    }

    @Override
    public void onBackPressed() {

        int mFragmentCount = mFragmentManager.getBackStackEntryCount();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.i(TAG, "onBackPressed COUNT: " + mFragmentCount);
            if (mFragmentCount > 1) {

                // To handle updating title
                String fragmentTag = mFragmentManager.getBackStackEntryAt(mFragmentCount - 2).getName();
                getSupportActionBar().setTitle(fragmentTag);

                // mFragmentManager.popBackStack();
                super.onBackPressed();
            }

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                displayFragment(MainFragment.class);
                break;
            case R.id.nav_a:
                displayFragment(AFragment.class);
                break;
            case R.id.nav_b:
                displayFragment(BFragment.class);
                break;
            case R.id.nav_c:
                displayFragment(CFragment.class);
                break;
            case R.id.nav_settings:
                displayFragment(SettingsFragment.class);
                break;
            default:
                displayFragment(MainFragment.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void displayFragment(Class<? extends Fragment> fragmentClass) {

        mFragment = mFragmentManager.findFragmentByTag(fragmentClass.getSimpleName());
        if (mFragment == null) {
            Log.i(TAG, "displayFragment: fragment == null");
            try {
                mFragment = fragmentClass.newInstance();
            } catch (Exception e) {
                Log.e(TAG, "Error while getting/instantiating a new fragment to display");
            }
        } else {
            Log.i(TAG, "displayFragment: fragment != null");
        }

        mFragmentManager.beginTransaction().replace(R.id.main_activity_content, mFragment, fragmentClass.getSimpleName()).addToBackStack(fragmentClass.getSimpleName()).commit();

    }

    private void loadColorFromPreferences(SharedPreferences sharedPreferences) {

        String color = sharedPreferences.getString(getString(R.string.pref_color_key), getString(R.string.pref_color_red_value));

        if (color.equals("blue")) {
            mToolbar.setBackgroundColor(Color.BLUE);
        } else if (color.equals("green")) {
            mToolbar.setBackgroundColor(Color.GREEN);
        } else {
            mToolbar.setBackgroundColor(Color.RED);
        }

    }

    @Override
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        loadColorFromPreferences(sharedPreferences);
    }
}
