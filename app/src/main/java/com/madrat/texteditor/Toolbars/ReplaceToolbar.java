package com.madrat.texteditor.Toolbars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.madrat.texteditor.databinding.ToolbarReplaceBinding;

import org.jetbrains.annotations.NotNull;


public class ReplaceToolbar extends Fragment {

    private ToolbarReplaceBinding binding;

    public ReplaceToolbar() { }

    public static ReplaceToolbar newInstance() {
        Bundle args = new Bundle();
        ReplaceToolbar fragment = new ReplaceToolbar();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ToolbarReplaceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.toolbar.setVisibility(View.VISIBLE);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
