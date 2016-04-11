package com.mx3studios.npiregistry.npiDrawerFragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.mx3studios.npiregistry.R;
import com.mx3studios.npiregistry.npi.NpiQuery;

/**
 * Created by Mario on 3/16/2016.
 */
public class AdvanceSearchFragment extends Fragment{
    int layout = 0;

    private FragmentManager m;
    private View adView;
    private Button searchButton;

    public int getLayoutRes() {
        return layout;
    }

    public static AdvanceSearchFragment newInstance() {
        AdvanceSearchFragment fragment = new AdvanceSearchFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adView = inflater.inflate(R.layout.fragment_advance_search, container, false);
        Spinner spinner = (Spinner) adView.findViewById(R.id.state_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.state_names, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return adView;
    }

    public NpiQuery getQuery(View v) {
        NpiQuery query = new NpiQuery();
        query.setFirstName(((TextView) adView.findViewById(R.id.first_name)).getText().toString());
        query.setLastName(((TextView) adView.findViewById(R.id.last_name)).getText().toString());
        query.setNpi(((TextView) adView.findViewById(R.id.npi_number)).getText().toString());
        query.setCity(((TextView) adView.findViewById(R.id.city_search)).getText().toString());
        query.setZipCode(((TextView) adView.findViewById(R.id.zip_search)).getText().toString());
        query.setOrgName(((TextView) adView.findViewById(R.id.organization_name)).getText().toString());
        return query;
    }
}
