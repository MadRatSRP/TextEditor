package com.madrat.texteditor.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madrat.texteditor.R;

public class MainScreenFragment extends Fragment {

    private static MainScreenFragment msf = new MainScreenFragment();

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        return view;
    }

    public static MainScreenFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainScreenFragment fragment = new MainScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
