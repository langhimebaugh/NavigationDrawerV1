package com.himebaugh.navigationdrawer1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CFragment extends BaseFragment {

    private static final String TAG = CFragment.class.getSimpleName();

    public CFragment() {
        // Required empty public constructor
    }

    public static CFragment newInstance() {
        CFragment fragment = new CFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Needs to be in onCreateView.  Will crash in onCreate
        mListener.setActionBarTitle("Fragment C");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

}
