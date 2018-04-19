package com.himebaugh.navigationdrawer1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AFragment extends BaseFragment {

    private static final String TAG = AFragment.class.getSimpleName();

    public AFragment() {
        // Required empty public constructor
    }

    public static AFragment newInstance() {
        AFragment fragment = new AFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // These work too...

//        ((MainActivity) getActivity()).setActionBarTitle("Fragment A1");
//
//        // -OR-
//
//        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setTitle("Fragment A2");
//        }

        // Assuming to need interface OnFragmentInteractionListener for other methods
        // so use this but not necessarily if it is the only reason to set up the interface
        // as the above options work.

        // Needs to be in onCreateView.  Will crash in onCreate
        mListener.setActionBarTitle("Fragment A");

        // Communication through an interface...
        // BaseFragment provides the interface OnFragmentInteractionListener
        // MainActivity implements BaseFragment.OnFragmentInteractionListener
        // Multiple fragments (AFragment, BFragment, CFragment) inherit from the BaseFragment and communicate with the activity

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

}
