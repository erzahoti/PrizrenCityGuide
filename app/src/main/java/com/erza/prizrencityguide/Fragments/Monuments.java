package com.erza.prizrencityguide.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erza.prizrencityguide.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Monuments.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Monuments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Monuments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.entertainment_layout_list, container, false);
        TextView textview = (TextView) view.findViewById(R.id.tvName);
        return view;



    }



}
