package com.mx3studios.npiregistry.npiDrawerFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mx3studios.npiregistry.R;


public class SearchNpiDialogFragment extends DialogFragment {

    private FragmentManager fm;
    private View view;
    private Button searchButton;

    public static SearchNpiDialogFragment newInstance() {
        SearchNpiDialogFragment fragment = new SearchNpiDialogFragment();

        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        fm = getFragmentManager();
        view = inflater.inflate(R.layout.fragment_search_npi_dialog, null);
        searchButton = (Button)(view.findViewById(R.id.buttonSearchNpi));

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public String getFirstName() {
        TextView tv = (TextView)view.findViewById(R.id.first_name);
        return tv.getText().toString();
    }

    public String getLastName() {
        TextView tv = (TextView)view.findViewById(R.id.last_name);
        return tv.getText().toString();
    }

}
