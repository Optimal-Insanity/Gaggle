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
import com.gaggle.snoretrain.gaggle.fragments.MessageListFragment;
import com.gaggle.snoretrain.gaggle.fragments.MyEventListFragment;
import com.gaggle.snoretrain.gaggle.listener.IExpandCallbackListener;
import com.gaggle.snoretrain.gaggle.models.DataSet;
import com.gaggle.snoretrain.gaggle.models.GroupModel;
import com.gaggle.snoretrain.gaggle.models.UserModel;

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
    EventListFragment eventListFragment;
    GroupListFragment groupListFragment;
    MessageListFragment messageListFragment;
    private final int EVENT_LIST_INDEX = 0;
    private Adapter viewPagerAdapter;
    private UserModel user;

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
        user = (UserModel)intent.getSerializableExtra("UserObj");
        if (user.getUserName() == null || user.getUserName().equals("")){
            intent = new Intent(NavActivity.this, StartActivity.class);
            startActivity(intent);
        }
        eventListFragment = new EventListFragment();
        groupListFragment = new GroupListFragment();
        messageListFragment = new MessageListFragment();
        groupListFragment.setUser(user);
        messageListFragment.setUser(user);
        messageListFragment.setExpandCallbackListener(new IExpandCallbackListener() {
            @Override
            public void onTapCallback(DataSet expandedData) {
                Intent displayMessages = new Intent(NavActivity.this, ConversationActivity.class);
                displayMessages.putExtra("messages", expandedData);
                startActivity(displayMessages);

            }
        });
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
        textView.setText(user.getFname() + ' ' + user.getLname());
        setViewPager(viewPager);
        navTabs.setupWithViewPager(viewPager);

        navTabs.getTabAt(0).setIcon(R.drawable.ic_whatshot_black_24dp);
        navTabs.getTabAt(1).setIcon(R.drawable.ic_group_black_24dp);
        navTabs.getTabAt(2).setIcon(R.drawable.ic_forum_black_24dp);
        navTabs.getTabAt(3).setIcon(R.drawable.ic_public_black_24dp);

        navTabs.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.color_primary),
                PorterDuff.Mode.SRC_IN);

        navTabs.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        tab.getIcon().setColorFilter(
                                getResources().getColor(R.color.color_primary),
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
        viewPagerAdapter = new Adapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(eventListFragment, getString(R.string.event_tab_title));
        viewPagerAdapter.addFragment(groupListFragment, getString(R.string.group_tab_title));
        viewPagerAdapter.addFragment(messageListFragment, getString(R.string.message_tab_title));

        groupListFragment = new GroupListFragment();
        groupListFragment.setUser(user);

        viewPagerAdapter.addFragment(groupListFragment, getString(R.string.notification_tab_title));

        vp.setAdapter(viewPagerAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(viewPagerAdapter.getTitles(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setPageMargin(20);
    }
    class Adapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        Adapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }
        String getTitles(int position){
            return mFragmentTitles.get(position);
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
    public static void expandGroup(GroupModel group){

    }
    public void setFragment(Fragment frag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_nav, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == EventListFragment.REQUEST_LOCATION_PERMISSION){
            viewPagerAdapter
                    .getItem(EVENT_LIST_INDEX)
                    .onActivityResult(requestCode, resultCode, data);
        }
    }
}
