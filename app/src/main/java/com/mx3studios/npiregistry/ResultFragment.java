package com.mx3studios.npiregistry;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mx3studios.npiregistry.npi.NpiParserResult;
import com.mx3studios.npiregistry.npi.NpiResult;

import java.util.ArrayList;

/**
 * Created by Mario on 3/16/2016.
 */
public class ResultFragment extends Fragment {
    private FragmentManager fm;
//    private View view = null;
    private NpiParserResult npiresult;
    private boolean noResult = false;
    private ListView listView;
    private NpiAdapter npiAdapter = null;

    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();
        return fragment;
    }

    public void setNpiParserResult(NpiParserResult result) {
        npiresult = result;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_npi_dialog, container, false);
        if(npiresult == null) {
            npiresult = new NpiParserResult();
        }
        processResult(view);
        Button addFavoriteButton = (Button)view.findViewById(R.id.add_favorite_button);
        addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<NpiResult> resultList = getFavoriteResults();
                ((MainActivity)getActivity()).getFavoriteFragment().addNewFavorites(resultList);
            }
        });
        return view;
    }

    private ArrayList<NpiResult> getFavoriteResults() {
        ArrayList<NpiResult> resultList = new ArrayList<>();
        for(int i = 0; i < npiAdapter.getCount(); i++) {
            NpiResult result = npiAdapter.getItem(i);
            View v = npiAdapter.getView(i, null, null);
            CheckBox box = (CheckBox)v.findViewById(R.id.add_favorite_checkbox);
            if(box.isChecked()) {
                resultList.add(result);
            }
        }
        return resultList;
    }

    private void processResult(View view) {

        listView = (ListView)view.findViewById(R.id.resultList);
        npiAdapter = new NpiAdapter(
                getActivity().getApplicationContext(),
                npiresult.getResultList()
        );
        listView.setAdapter(npiAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //display detailed fragment here
                Object o = listView.getItemAtPosition(position);
                NpiResult result = (NpiResult) o;
            }
        });

    }

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
            favoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        result.setFavorite(true);
                    } else {
                        result.setFavorite(false);
                    }
                }
            });
            if(result.getFavorite()) {
                favoriteCheckBox.setChecked(true);
            } else {
                favoriteCheckBox.setChecked(false);
            }
            npiName.setText(result.getBasicInfo().getFullName());
            npiTaxDesc.setText(result.getTaxonomies().get(0).getDesc());
            npiNumber.setText("NPI #: " + String.valueOf(result.getNpi()));
            return convertView;
        }
    }
}
