package com.madrat.texteditor.Toolbars;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.madrat.texteditor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchToolbar extends Fragment {

    @BindView(R.id.search_toolbar)
    Toolbar toolbar;

    @BindView(R.id.delete_text)
    ImageButton button;
    @BindView(R.id.search_text)
    EditText search_text;
    @BindView(R.id.previous_value)
    Button previous_value;
    @BindView(R.id.next_value)
    Button next_value;
    @BindView(R.id.first)
    Button first;
    @BindView(R.id.second)
    Button second;

    public SearchToolbar() { }

    public static SearchToolbar newInstance() {

        Bundle args = new Bundle();
        SearchToolbar fragment = new SearchToolbar();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_search, container, false);
        ButterKnife.bind(this, view);
        //FrameLayout fl = view.findViewById(R.id.sear)
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbar.setVisibility(View.VISIBLE);
        button.setOnClickListener((View v) -> {
            search_text.setText(null);
        });
    }

}
