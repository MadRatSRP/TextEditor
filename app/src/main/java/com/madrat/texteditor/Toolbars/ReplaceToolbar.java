package com.madrat.texteditor.Toolbars;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.madrat.texteditor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReplaceToolbar extends Fragment {

    @BindView(R.id.replace_toolbar)
    Toolbar toolbar;
    @BindView(R.id.replace_text)
    EditText replace_text;
    @BindView(R.id.firsty)
    Button button1;
    @BindView(R.id.secondy)
    Button button2;

    public ReplaceToolbar() { }

    public static ReplaceToolbar newInstance() {

        Bundle args = new Bundle();
        ReplaceToolbar fragment = new ReplaceToolbar();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_replace, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbar.setVisibility(View.VISIBLE);
    }
}
