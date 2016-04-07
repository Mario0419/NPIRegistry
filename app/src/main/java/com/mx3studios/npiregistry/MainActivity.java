package com.mx3studios.npiregistry;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mx3studios.npiregistry.npi.*;
import com.mx3studios.npiregistry.npiDatabase.NpiReaderDbHelper;
import com.mx3studios.npiregistry.npiDrawerFragment.AboutFragment;
import com.mx3studios.npiregistry.npiDrawerFragment.AdvanceSearchFragment;
import com.mx3studios.npiregistry.npiDrawerFragment.FavoriteFragment;
import com.mx3studios.npiregistry.npiDrawerFragment.ResultFragment;
import com.mx3studios.npiregistry.npiDrawerFragment.SearchFragment;


public class MainActivity extends FragmentActivity {

    //fragments in navigational drawer fragment
    private SearchFragment mSearchFragment = null;
    private FavoriteFragment mFavoriteFragment = null;
    private ResultFragment mResultFragment = null;
    private AdvanceSearchFragment mAdvanceSearchFragment = null;
    private AboutFragment mAboutFragment = null;

    private int selectedPosition = 0;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private Toolbar toolbar;

    //db helper
    private NpiReaderDbHelper mDbHelper;

    //options in navigational drawer fragment
    private String[] menuList = new String[]{"Favorites", "Results", "Advanced Search", "About"};

    private String mTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.npi_drawer_layout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(menuList[0]);
        toolbar.setTitleTextColor(Color.WHITE);
        mDbHelper = new NpiReaderDbHelper(getApplicationContext());
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);
        if(findViewById(R.id.fragment_container) != null) {
            if(savedInstanceState != null) {
                return;
            }
        }
        mFavoriteFragment = new FavoriteFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, mFavoriteFragment).commit();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, menuList);
        mDrawerList.setAdapter(arrayAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //makes api call to npi registry and opens up result tab
    public void searchNpi(View v) {
        final NpiQuery query = mFavoriteFragment.getQuery(v);
        mFavoriteFragment.closeSearchDialog();
        AsyncTask<Void, Void, NpiParserResult> worker = new AsyncTask<Void, Void, NpiParserResult>() {
            @Override
            protected NpiParserResult doInBackground(Void... voids) {

                NpiParser parser = new NpiParser(query.getApiUrl());
                NpiParserResult result = null;
                try {
                    result = parser.getResults();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(NpiParserResult result) {
                getResultFragment();
                mResultFragment.setNpiParserResult(result);
                setPosition(1);
            }
        };
        worker.execute();
    }

    public void searchAdvanceNpi(View v) {
        final NpiQuery query = mAdvanceSearchFragment.getQuery(v);

        AsyncTask<Void, Void, NpiParserResult> worker = new AsyncTask<Void, Void, NpiParserResult>() {
            @Override
            protected NpiParserResult doInBackground(Void... voids) {

                NpiParser parser = new NpiParser(query.getApiUrl());
                NpiParserResult result = null;
                try {
                    result = parser.getResults();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(NpiParserResult result) {
                getResultFragment();
                mResultFragment.setNpiParserResult(result);
                setPosition(1);
            }
        };
        worker.execute();
    }



    public FavoriteFragment getFavoriteFragment() {
        return mFavoriteFragment == null ? new FavoriteFragment() : mFavoriteFragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }

    }
    public void setPosition(int position) {
        selectItem(position);
    }

    //changes fragment from user input in navigational drawer fragment
    private void selectItem(int position) {
        Fragment fragment = null;
        Bundle args = new Bundle();
        args.putInt("1", position);
        if(selectedPosition == position) {
            mDrawerLayout.closeDrawer(mDrawerList);
            return;
        }
        switch(position) {
            case 0:
                if(mFavoriteFragment == null) {
                    mFavoriteFragment = new FavoriteFragment();
                }
                fragment = mFavoriteFragment;
                break;
            case 1:
                if(mResultFragment == null) {
                    mResultFragment = new ResultFragment();
                }
                fragment = mResultFragment;
                break;
            case 2:
                if(mAdvanceSearchFragment == null) {
                    mAdvanceSearchFragment = new AdvanceSearchFragment();
                }
                fragment = mAdvanceSearchFragment;
                break;
            case 3:
                if(mAboutFragment == null) {
                    mAboutFragment = new AboutFragment();
                }
                fragment = mAboutFragment;
                break;
            default:
                fragment = new FavoriteFragment();
                break;
        }
        fragment.setArguments(args);
        toolbar.setTitle(menuList[position]);
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        selectedPosition = position;
        setTitle(menuList[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    public void setTitle(CharSequence title) {
        mTitle = title.toString();
    }

    public ResultFragment getResultFragment() {
        if(mResultFragment == null) {
            mResultFragment = new ResultFragment();
        }
        return mResultFragment;
    }

    public NpiReaderDbHelper getNpiDbHelper() {
        return mDbHelper;
    }
}
