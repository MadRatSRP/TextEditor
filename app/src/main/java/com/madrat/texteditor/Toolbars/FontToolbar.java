package com.madrat.texteditor.Toolbars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.madrat.texteditor.R;
import com.madrat.texteditor.databinding.ToolbarFontBinding;

import org.jetbrains.annotations.NotNull;

public class FontToolbar extends Fragment {

    private ToolbarFontBinding binding;

    public FontToolbar() { }

    public static FontToolbar newInstance() {

        Bundle args = new Bundle();
        FontToolbar fragment = new FontToolbar();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ToolbarFontBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.toolbar.setVisibility(View.VISIBLE);
        binding.toolbar.inflateMenu(R.menu.font_items);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
