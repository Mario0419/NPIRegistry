package com.mx3studios.npiregistry;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mx3studios.npiregistry.npi.NpiParserResult;
import com.mx3studios.npiregistry.npi.NpiQuery;
import com.mx3studios.npiregistry.npi.NpiResult;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private List<String> list = null;
    private ArrayAdapter<String> arrayAdapter = null;
    private SearchNpiDialogFragment mdialogSearchFragment = null;
    int layout = R.layout.fragment_favorite;
    private NpiDetailDialogFragment mNpiDetailFragment = null;

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    public FavoriteFragment() {
        list = new ArrayList<>();
        list.add("mario");
        list.add("massad");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        listView = (ListView)view.findViewById(R.id.favoritelist);

        arrayAdapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                list
        );
        searchButton = (Button)view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        listView.setAdapter(arrayAdapter);
        return view;
    }

    public int getLayoutRes() {
        return layout;
    }
    private void showDialog() {
        mdialogSearchFragment = new SearchNpiDialogFragment();
        mdialogSearchFragment.show(getFragmentManager(), "dialog");
    }

    public void addNewFavorites(ArrayList<NpiResult> results) {
        for(NpiResult result : results) {
            arrayAdapter.add(result.toString());
        }
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);

    }
    public NpiQuery getQuery(View v) {
        NpiQuery query = new NpiQuery();
        query.setFirstName(mdialogSearchFragment.getFirstName());
        query.setLastName(mdialogSearchFragment.getLastName());
        return query;
    }
    public void closeSearchDialog() {
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if(prev != null) {
            DialogFragment df = (DialogFragment) prev;
            df.dismiss();
        }
    }

    private ListView listView;
    private Button searchButton;
}
