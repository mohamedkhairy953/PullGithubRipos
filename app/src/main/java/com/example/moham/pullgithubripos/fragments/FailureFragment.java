package com.example.moham.pullgithubripos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moham.pullgithubripos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FailureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FailureFragment extends Fragment {
    TextView txt;
    private static final String ARG_PARAM1 = "failureMsg";

    private String mParam1;


    public FailureFragment() {
        // Required empty public constructor
    }

    public static FailureFragment newInstance(String param1) {
        FailureFragment fragment = new FailureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_no_connection, container, false);
        txt = (TextView) v.findViewById(R.id.textView_failure);
        txt.setText(mParam1);
        return v;
    }

}
