package com.mx3studios.npiregistry.npiDrawerFragment;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mx3studios.npiregistry.R;
import com.mx3studios.npiregistry.npi.NpiQuery;

import org.w3c.dom.Text;

/**
 * Created by Mario on 3/16/2016.
 */
public class AdvanceSearchFragment extends Fragment{
    int layout = 0;

    private FragmentManager m;
    private View view;
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
        View view = inflater.inflate(R.layout.fragment_advance_search, container, false);

        return view;
    }

    public NpiQuery getQuery(View v) {
        NpiQuery query = new NpiQuery();
        query.setFirstName(((TextView) v.findViewById(R.id.first_name)).getText().toString());
        query.setLastName(((TextView) v.findViewById(R.id.last_name)).getText().toString());
        query.setNpi(((TextView) v.findViewById(R.id.npi_number)).getText().toString());
        query.setCity(((TextView) v.findViewById(R.id.city_search)).getText().toString());
        query.setState(((TextView) v.findViewById(R.id.state_search)).getText().toString());
        query.setCountryCode(((TextView) v.findViewById(R.id.country_search)).getText().toString());
        query.setZipCode(((TextView) v.findViewById(R.id.zip_search)).getText().toString());
        return query;
    }
}
