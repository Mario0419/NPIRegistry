package com.mx3studios.npiregistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.os.AsyncTask;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mx3studios.npiregistry.npi.*;


public class MainActivity extends FragmentActivity {

    private SearchFragment mSearchFragment = null;
    private FavoriteFragment mFavoriteFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_container) != null) {
            if(savedInstanceState != null) {
                return;
            }
            mFavoriteFragment = new FavoriteFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mFavoriteFragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void searchNpi(View v) {
        final NpiQuery query = mFavoriteFragment.getQuery(v);
        Log.i("Error", "Came here");
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
                mFavoriteFragment.displayResults(result);
            }
        };
        worker.execute();



    }

    public void displayDetailedResult(NpiResult result) {

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



}
