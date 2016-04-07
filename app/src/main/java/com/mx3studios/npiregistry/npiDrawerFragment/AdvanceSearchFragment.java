package com.mx3studios.npiregistry.npiDrawerFragment;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mx3studios.npiregistry.R;

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
}
