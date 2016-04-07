package com.mx3studios.npiregistry.npiDrawerFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mx3studios.npiregistry.MainActivity;
import com.mx3studios.npiregistry.R;
import com.mx3studios.npiregistry.npi.NpiResult;

import org.w3c.dom.Text;

/**
 * Created by Mario on 3/6/2016.
 */
public class NpiDetailDialogFragment extends DialogFragment{
    private FragmentManager fm;
    private View view;
    private NpiResult provider;

    private Button deleteButton;
    private Button cancelButton;

    public void setNpiResult(NpiResult result) {
        provider = result;
    }
    public static NpiDetailDialogFragment newInstance() {
        NpiDetailDialogFragment fragment = new NpiDetailDialogFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        fm = getFragmentManager();
        view = inflater.inflate(R.layout.fragment_detail_npi_dialog, null);
        builder.setView(view);
        initiateComponents();
        return builder.create();
    }

    private void initiateComponents() {
        TextView textView = (TextView)view.findViewById(R.id.detailed_name);
        textView.setText(provider.getBasicInfo().getFullName());

        textView = (TextView)view.findViewById(R.id.detailed_taxonomy);
        textView.setText(provider.getTaxonomies().get(0).getDesc());

        textView = (TextView)view.findViewById(R.id.detailed_npi);
        textView.setText(String.valueOf(provider.getNpi()));

        textView = (TextView)view.findViewById(R.id.detailed_address);
        textView.setText(provider.getAddresses().get(0).getAddress1());

        textView = (TextView)view.findViewById(R.id.detailed_city);
        textView.setText(provider.getAddresses().get(0).getCity());

        textView = (TextView)view.findViewById(R.id.detailed_state);
        textView.setText(provider.getAddresses().get(0).getState());

        textView = (TextView)view.findViewById(R.id.detailed_country);
        textView.setText(provider.getAddresses().get(0).getCountryName());

        textView = (TextView)view.findViewById(R.id.detailed_zipcode);
        textView.setText(provider.getAddresses().get(0).getPostalCode());

        textView = (TextView)view.findViewById(R.id.detailed_phone);
        textView.setText(provider.getAddresses().get(0).getPhone());

        textView = (TextView)view.findViewById(R.id.detailed_fax);
        textView.setText(provider.getAddresses().get(0).getFax());

        deleteButton = (Button)view.findViewById(R.id.delete_button);
        cancelButton = (Button)view.findViewById(R.id.cancel_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).getNpiDbHelper().deleteNpiEntry(provider.getNpi());
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


}
