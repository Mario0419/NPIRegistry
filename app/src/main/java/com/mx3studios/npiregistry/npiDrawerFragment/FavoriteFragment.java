package com.mx3studios.npiregistry.npiDrawerFragment;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.mx3studios.npiregistry.MainActivity;
import com.mx3studios.npiregistry.R;
import com.mx3studios.npiregistry.npi.NpiQuery;
import com.mx3studios.npiregistry.npi.NpiResult;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private ArrayList<NpiResult> list = null;
    private NpiAdapter arrayAdapter = null;
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
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        listView = (ListView)view.findViewById(R.id.favoritelist);
        list = ((MainActivity)getActivity()).getNpiDbHelper().getAllFavorites();
        arrayAdapter = new NpiAdapter(
                getActivity().getApplicationContext(),
                list
        );
        searchButton = (Button)view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchDialog();
            }
        });
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetailedDialog((NpiResult)listView.getItemAtPosition(position));
            }
        });
        return view;
    }

    public int getLayoutRes() {
        return layout;
    }

    private void showDetailedDialog(NpiResult result) {
        mNpiDetailFragment = new NpiDetailDialogFragment();
        mNpiDetailFragment.setNpiResult(result);
        mNpiDetailFragment.show(getFragmentManager(), "dialog");
    }
    private void showSearchDialog() {
        mdialogSearchFragment = new SearchNpiDialogFragment();
        mdialogSearchFragment.show(getFragmentManager(), "dialog");
    }

    public void addNewFavorites(ArrayList<NpiResult> results) {
        for(NpiResult result : results) {
            arrayAdapter.add(result);
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
    public class NpiAdapter extends ArrayAdapter<NpiResult> {
        public NpiAdapter(Context context, ArrayList<NpiResult> providers) {
            super(context, 0, providers);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final NpiResult result = getItem(position);
            if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.npi_list_item, parent, false);
            }

            TextView npiName = (TextView) convertView.findViewById(R.id.npi_name);
            TextView npiTaxDesc = (TextView) convertView.findViewById(R.id.npi_tax_desc);
            TextView npiNumber = (TextView) convertView.findViewById(R.id.npi_number);
            CheckBox favoriteCheckBox = (CheckBox) convertView.findViewById(R.id.add_favorite_checkbox);
            favoriteCheckBox.setVisibility(View.GONE);

            npiName.setText(result.getBasicInfo().getFullName());
            npiTaxDesc.setText(result.getTaxonomies().get(0).getDesc());
            npiNumber.setText("NPI #: " + String.valueOf(result.getNpi()));
            return convertView;
        }
    }
}
