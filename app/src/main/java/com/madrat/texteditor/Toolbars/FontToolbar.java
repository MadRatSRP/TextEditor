package com.madrat.texteditor.Toolbars;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madrat.texteditor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FontToolbar extends Fragment {

    @BindView(R.id.font_toolbar)
    Toolbar font_toolbar;

    public FontToolbar() { }

    public static FontToolbar newInstance() {

        Bundle args = new Bundle();
        FontToolbar fragment = new FontToolbar();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View font_view = inflater.inflate(R.layout.toolbar_font, container, false);
        ButterKnife.bind(this, font_view);
        return font_view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        font_toolbar.setVisibility(View.VISIBLE);
        font_toolbar.inflateMenu(R.menu.font_items);
    }
}
