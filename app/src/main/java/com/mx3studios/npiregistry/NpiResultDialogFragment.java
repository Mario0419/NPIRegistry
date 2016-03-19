package com.mx3studios.npiregistry;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mx3studios.npiregistry.npi.NpiParserResult;
import com.mx3studios.npiregistry.npi.NpiResult;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 3/5/2016.
 */
public class NpiResultDialogFragment extends DialogFragment{
    private FragmentManager fm;
    private View view;
    private NpiParserResult npiresult;
    private boolean noResult = false;
    private ListView listView;
    private NpiAdapter npiAdapter = null;

    private SimpleCursorAdapter adapter;


    public static NpiResultDialogFragment newInstance() {
        NpiResultDialogFragment fragment = new NpiResultDialogFragment();
        return fragment;
    }

    private void processResult() {

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
                NpiResult result = (NpiResult)o;
            }
        });
    }


    public void setNpiParserResult(NpiParserResult result) {
        npiresult = result;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        fm = getFragmentManager();
        view = inflater.inflate(R.layout.fragment_result_npi_dialog, null);
        processResult();
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public class NpiAdapter extends ArrayAdapter<NpiResult> {
        public NpiAdapter(Context context, ArrayList<NpiResult> providers) {
            super(context, 0, providers);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NpiResult result = getItem(position);
            if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.npi_list_item, parent, false);
            }

            TextView npiName = (TextView) convertView.findViewById(R.id.npi_name);
            TextView npiTaxDesc = (TextView) convertView.findViewById(R.id.npi_tax_desc);
            TextView npiNumber = (TextView) convertView.findViewById(R.id.npi_number);

            npiName.setText(result.getBasicInfo().getFullName());
            npiTaxDesc.setText(result.getTaxonomies().get(0).getDesc());
            npiNumber.setText("NPI #: " + String.valueOf(result.getNpi()));
            return convertView;
        }
    }
}
