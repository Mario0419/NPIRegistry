package com.mx3studios.npiregistry.npiDrawerFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

import com.mx3studios.npiregistry.R;
import com.mx3studios.npiregistry.npi.NpiResult;

/**
 * Created by Mario on 3/6/2016.
 */
public class NpiDetailDialogFragment extends DialogFragment{
    private FragmentManager fm;
    private View view;
    private NpiResult provider;


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
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


}
