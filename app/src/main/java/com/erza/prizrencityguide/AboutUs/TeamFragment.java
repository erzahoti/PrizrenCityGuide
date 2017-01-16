package com.erza.prizrencityguide.AboutUs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erza.prizrencityguide.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment {

    public static TeamFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        TeamFragment teamFragment = new TeamFragment();
        teamFragment.setArguments(args);
        return teamFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

}
