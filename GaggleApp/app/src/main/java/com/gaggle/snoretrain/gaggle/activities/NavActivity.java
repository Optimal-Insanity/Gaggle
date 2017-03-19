package com.gaggle.snoretrain.gaggle.activities;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.fragments.AttendingEventListFragment;
import com.gaggle.snoretrain.gaggle.fragments.EventListFragment;
import com.gaggle.snoretrain.gaggle.fragments.GroupListFragment;
import com.gaggle.snoretrain.gaggle.fragments.MyEventListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tabs)
    TabLayout navTabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = getIntent();
        String uNameString = intent.getExtras().getString("USER_NAME");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        TextView textView = (TextView) hView.findViewById(R.id.user_text_view);
        navigationView.setNavigationItemSelectedListener(this);
        ButterKnife.bind(this);
        textView.setText(uNameString);
        setViewPager(viewPager);
        navTabs.setupWithViewPager(viewPager);

        navTabs.getTabAt(0).setIcon(R.drawable.ic_whatshot_black_24dp);
        navTabs.getTabAt(1).setIcon(R.drawable.ic_group_black_24dp);
        navTabs.getTabAt(2).setIcon(R.drawable.ic_chat_bubble_black_24dp);
        navTabs.getTabAt(3).setIcon(R.drawable.ic_public_black_24dp);

        navTabs.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.color_primary),
                PorterDuff.Mode.SRC_IN);

        navTabs.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        tab.getIcon().setColorFilter(
                                getResources().getColor(R.color.color_primary_dark),
                                PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        tab.getIcon().setColorFilter(
                                Color.DKGRAY, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_groups) {
            // Send user to group fragment
            setFragment(new GroupListFragment());
        } else if (id == R.id.my_events) {
            setFragment(new MyEventListFragment());
        } else if (id == R.id.area_events) {
            // Send user to party fragment
            setFragment(new EventListFragment());
        } else if (id == R.id.attending_events) {
            setFragment(new AttendingEventListFragment());
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.gaggle_stories) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setViewPager(ViewPager vp){
        Adapter viewPagerAdapter = new Adapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new EventListFragment(), getString(R.string.party_tab_title));
        viewPagerAdapter.addFragment(new GroupListFragment(), getString(R.string.group_tab_title));
        viewPagerAdapter.addFragment(new EventListFragment(), getString(R.string.message_tab_title));
        viewPagerAdapter.addFragment(new GroupListFragment(), getString(R.string.notification_tab_title));
        vp.setAdapter(viewPagerAdapter);
    }
    class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }

    public void setFragment(Fragment frag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_nav, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
