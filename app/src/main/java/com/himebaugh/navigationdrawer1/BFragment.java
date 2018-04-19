package com.himebaugh.navigationdrawer1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BFragment extends BaseFragment {

    private static final String TAG = BFragment.class.getSimpleName();

    public BFragment() {
        // Required empty public constructor
    }

    public static BFragment newInstance() {
        BFragment fragment = new BFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Needs to be in onCreateView.  Will crash in onCreate
        mListener.setActionBarTitle("Fragment B");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

}
