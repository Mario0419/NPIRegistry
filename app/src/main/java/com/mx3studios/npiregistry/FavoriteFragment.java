package com.mx3studios.npiregistry;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private List<String> list = null;
    private ArrayAdapter<String> arrayAdapter = null;
    private SearchNpiDialogFragment mdialogSearchFragment = null;
    private NpiResultDialogFragment mNpiResultFragment = null;
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        list = new ArrayList<String>();
        list.add("mario");
        list.add("massad");

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
//                list.add("1");
//                listView.setAdapter(arrayAdapter);
                showDialog();
            }
        });

        listView.setAdapter(arrayAdapter);

        return view;
    }
    private void showDialog() {
        mdialogSearchFragment = new SearchNpiDialogFragment();
        mdialogSearchFragment.show(getFragmentManager(), "dialog");
    }

    public void displayResults(NpiParserResult result) {
        mNpiResultFragment = new NpiResultDialogFragment();
        mNpiResultFragment.setNpiParserResult(result);
        mNpiResultFragment.show(getFragmentManager(), "resultDialog");
    }

    public NpiQuery getQuery(View v) {
        NpiQuery query = new NpiQuery();
        Log.i("Error", "bub " + mdialogSearchFragment.getFirstName());
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
