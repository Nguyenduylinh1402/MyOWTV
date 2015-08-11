package com.example.steve.ownslidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.steve.ownslidingmenu.DataItem.Item;
import com.example.steve.ownslidingmenu.DataItem.SectionItem;
import com.example.steve.ownslidingmenu.fragment.HomeFragment;
import com.example.steve.ownslidingmenu.fragment.LatinoFragment;
import com.example.steve.ownslidingmenu.fragment.LiveTVFragment;
import com.example.steve.ownslidingmenu.fragment.LoginFragment;
import com.example.steve.ownslidingmenu.fragment.RadioFragment;
import com.example.steve.ownslidingmenu.fragment.SearchFragment;
import com.example.steve.ownslidingmenu.fragment.SeriesFragment;
import com.example.steve.ownslidingmenu.fragment.ShowsFragment;
import com.example.steve.ownslidingmenu.fragment.VGNFragment;
import com.example.steve.ownslidingmenu.fragment.VietnameseFragment;

import java.util.ArrayList;

public class MainActivity extends Activity {
    int selectionPosition = 0;
    private DrawerLayout mDrawerLayout;
    private ListView mListDrawer;
    private android.support.v4.app.ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private String[] navSections;
    private TypedArray navMenuIcons;
    private ArrayList<Item> navDrawerItems;
    private NavDrawerItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icon);
        navSections = getResources().getStringArray(R.array.nav_drawer_section);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListDrawer = (ListView) findViewById(R.id.drawer_list);
        navDrawerItems = new ArrayList<Item>();

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new SectionItem(navSections[0]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(5, -1)));
        navDrawerItems.add(new SectionItem(navSections[1]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(6, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons.getResourceId(7, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[10], navMenuIcons.getResourceId(8, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[11], navMenuIcons.getResourceId(9, -1)));

        navMenuIcons.recycle();
        adapter = new NavDrawerItemAdapter(getApplicationContext(), navDrawerItems);
        mListDrawer.setAdapter(adapter);
        mListDrawer.setOnItemClickListener(new SlideMenuClickListener());
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
//--------Custom layout Actionbar---
       /*
        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);

        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.actionbar_layout, null);

        //if you need to customize anything else about the text, do it here.
        //I'm using a custom TextView with a custom font in my layout xml so all I need to do is set title
        ((TextView) v.findViewById(R.id.action_bar_title)).setText(this.getTitle());

        //assign the view to the actionbar
        this.getActionBar().setCustomView(v);
       *//* getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.actionbar_layout);*/

        mDrawerToggle = new android.support.v4.app.ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {

                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            displayView(0);
        }
    }

    private void displayView(int i) {
        selectionPosition = i;
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new SearchFragment();
                break;
            case 2:
                fragment = new LoginFragment();
                break;
            case 4:
                fragment = new VietnameseFragment();
                break;
            case 5:
                fragment = new LatinoFragment();
                break;
            case 6:
                fragment = new VGNFragment();
                break;
            case 8:
                fragment = new SeriesFragment();
                break;
            case 9:
                fragment = new ShowsFragment();
                break;
            case 10:
                fragment = new LiveTVFragment();
                break;
            case 11:
                fragment = new RadioFragment();
                break;
           /* case 6:
                Intent myIntent = new Intent(this, ExampleActivity.class);
                startActivity(myIntent);
                break;*/
            default:
                break;
        }
        //control fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_content, fragment).commit();

            // update selected item and title, then close the drawer
            mListDrawer.setItemChecked(i, true);
           // mListDrawer.setChoiceMode(mListDrawer.CHOICE_MODE_SINGLE);
            mListDrawer.setSelection(i);
            if (i == 0) {
                setTitle("On World TV");
            } else {
                setTitle(navMenuTitles[i]);
            }

            mDrawerLayout.closeDrawer(mListDrawer);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (id) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mListDrawer);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else if (selectionPosition != 0) {
            displayView(0);
        } else {
            super.onBackPressed();
        }

    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            displayView(i);
        }
    }
}
