package com.gaggle.snoretrain.gaggle.activities;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.fragments.AttendingEventListFragment;
import com.gaggle.snoretrain.gaggle.fragments.GroupListFragment;
import com.gaggle.snoretrain.gaggle.fragments.MyEventListFragment;
import com.gaggle.snoretrain.gaggle.fragments.PartyListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.parties_tab) ImageView partiesTab;
    @BindView(R.id.messages_tab) ImageView messagesTab;
    @BindView(R.id.groups_tab) ImageView groupsTab;
    @BindView(R.id.notification_tab) ImageView notificationsTab;

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ButterKnife.bind(this);
        Fragment startFragment = new PartyListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_nav, startFragment).commit();

        partiesTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupsTab.setBackgroundColor(Color.TRANSPARENT);
                partiesTab.setBackgroundColor(getColor(R.color.color_accent));
                setFragment(new PartyListFragment());
            }
        });
        messagesTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        groupsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partiesTab.setBackgroundColor(Color.TRANSPARENT);
                groupsTab.setBackgroundColor(getColor(R.color.color_accent));
                setFragment(new GroupListFragment());

            }
        });
        notificationsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
            setFragment(new PartyListFragment());
        } else if (id == R.id.attending_events) {
            setFragment(new AttendingEventListFragment());
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.gaggle_stories) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment frag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_nav, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
