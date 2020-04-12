package com.madrat.texteditor.Toolbars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.madrat.texteditor.databinding.ToolbarSearchBinding;

import org.jetbrains.annotations.NotNull;


public class SearchToolbar extends Fragment {

    private ToolbarSearchBinding binding;

    public SearchToolbar() { }

    public static SearchToolbar newInstance() {
        Bundle args = new Bundle();
        SearchToolbar fragment = new SearchToolbar();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ToolbarSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.toolbar.setVisibility(View.VISIBLE);
        binding.deleteText.setOnClickListener((View v) -> {
            binding.searchText.setText(null);
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
