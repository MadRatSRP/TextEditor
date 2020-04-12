package com.madrat.texteditor.Fragments;

import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dbflow5.config.FlowConfig;
import com.dbflow5.config.FlowManager;
import com.dbflow5.database.DatabaseStatement;
import com.dbflow5.database.DatabaseWrapper;
import com.dbflow5.database.FlowCursor;
import com.madrat.texteditor.R;
import com.madrat.texteditor.Toolbars.FontToolbar;
import com.madrat.texteditor.Toolbars.ReplaceToolbar;
import com.madrat.texteditor.databinding.FragmentEditFieldBinding;
import com.madrat.texteditor.db.Note;

import org.jetbrains.annotations.NotNull;

public class EditFieldFragment extends Fragment {

    public static EditFieldFragment newInstance() {
        Bundle args = new Bundle();
        EditFieldFragment fragment = new EditFieldFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentManager fm;
    private FragmentTransaction ft;
    //private FrameLayout search, replace, font;

    private FragmentEditFieldBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditFieldBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        FlowManager.init(new FlowConfig.Builder(getActivity().getApplicationContext()).build());
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu main_menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_actions, main_menu);
        super.onCreateOptionsMenu(main_menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_undo:
                onUndoButtonClicked();
                break;
            case R.id.action_done:
                onDoneButtonClicked();
                break;
            case R.id.action_save:
                onSaveButtonClicked();
                break;
            case R.id.action_save_as:
                onSaveAsButtonClicked();
                break;
            case R.id.action_redo:
                onRedoButtonClicked();
                break;
            case R.id.action_search:
                onSearchButtonClicked();
                break;
            case R.id.action_show_font_options:
                onShowFontOptionsButtonClicked();
                break;
            case R.id.action_replace:
                onReplaceButtonClicked();
                break;
            case R.id.action_zoom_in:
                onZoomInButtonClicked();
                break;
            case R.id.action_zoom_out:
                onZoomOutButtonClicked();
                break;
            case R.id.action_text_wrap:
                onTextWrapButtonClicked();
                break;
            case R.id.action_show_suggestions:
                onShowSuggestionsButtonClicked();
                break;
            case R.id.action_share:
                onShareButtonClicked();
                break;
            case R.id.action_close:
                onCloseButtonClicked();
                break;
        }
        return false;
    }

    private void onUndoButtonClicked() {

    }
    private void onDoneButtonClicked() {

    }
    private void onSaveButtonClicked() {
        DatabaseWrapper wrapper = new DatabaseWrapper() {
            @Override
            public int getVersion() {
                return 0;
            }

            @Override
            public void execSQL(@NotNull String s) {

            }

            @Override
            public void beginTransaction() {

            }

            @Override
            public void setTransactionSuccessful() {

            }

            @Override
            public void endTransaction() {

            }

            @Override
            public DatabaseStatement compileStatement(@NotNull String s) {
                return null;
            }

            @NotNull
            @Override
            public DatabaseStatement compileStatement(@NotNull String s, @org.jetbrains.annotations.Nullable String[] strings) {
                return null;
            }

            @NotNull
            @Override
            public FlowCursor rawQuery(@NotNull String s, @org.jetbrains.annotations.Nullable String[] strings) {
                return null;
            }

            @Override
            public long updateWithOnConflict(@NotNull String s, @NotNull ContentValues contentValues, @org.jetbrains.annotations.Nullable String s1, @org.jetbrains.annotations.Nullable String[] strings, int i) {
                return 0;
            }

            @Override
            public long insertWithOnConflict(@NotNull String s, @org.jetbrains.annotations.Nullable String s1, @NotNull ContentValues contentValues, int i) {
                return 0;
            }

            @NotNull
            @Override
            public FlowCursor query(@NotNull String s, @org.jetbrains.annotations.Nullable String[] strings, @org.jetbrains.annotations.Nullable String s1, @org.jetbrains.annotations.Nullable String[] strings1, @org.jetbrains.annotations.Nullable String s2, @org.jetbrains.annotations.Nullable String s3, @org.jetbrains.annotations.Nullable String s4) {
                return null;
            }

            @Override
            public int delete(@NotNull String s, @org.jetbrains.annotations.Nullable String s1, @org.jetbrains.annotations.Nullable String[] strings) {
                return 0;
            }
        };
        Note note = new Note();
        note.setId(1);
        note.setName("j");
        note.setValue(binding.setupText.getText().toString());
        note.save(wrapper);
    }
    private void onSaveAsButtonClicked() {

    }
    private void onRedoButtonClicked() {

    }
    private void onSearchButtonClicked() {
                     /*FrameLayout search = getActivity().findViewById(R.id.search_container);
                search.setVisibility(View.VISIBLE);

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.search_container, SearchToolbar.newInstance());
                ft.commit();*/

        FrameLayout search = getActivity().findViewById(R.id.search_container);
        search.setVisibility(View.VISIBLE);

        Toolbar toolbar = new Toolbar(getActivity().getApplicationContext());
        LinearLayout.LayoutParams toolbarParams = new LinearLayout.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT,
                //?attr/actionBarSize
                Toolbar.LayoutParams.WRAP_CONTENT
        );
        toolbar.setLayoutParams(toolbarParams);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setPopupTheme(R.style.Theme_AppCompat_Light);

        EditText et = new EditText(getActivity().getApplicationContext());
        et.setHint(getResources().getString(R.string.search_text));
        LinearLayout.LayoutParams b_params = new LinearLayout.LayoutParams(
                475,
                Toolbar.LayoutParams.MATCH_PARENT
        );
        et.setSingleLine();
        et.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        et.setLayoutParams(b_params);

        ImageButton ib = new ImageButton(getActivity().getApplicationContext());
        ib.setImageResource(R.drawable.ic_close_black_24dp);
        LinearLayout.LayoutParams i_params = new LinearLayout.LayoutParams(
                50,
                Toolbar.LayoutParams.MATCH_PARENT
        );
        ib.setLayoutParams(i_params);
        ib.setBackgroundResource(android.R.color.transparent);

        Button previous_value = new Button(getActivity().getApplicationContext());
        LinearLayout.LayoutParams pre_params = new LinearLayout.LayoutParams(
                135,
                Toolbar.LayoutParams.MATCH_PARENT
        );
        previous_value.setLayoutParams(pre_params);

        Button next_value = new Button(getActivity().getApplicationContext());
        LinearLayout.LayoutParams n_params = new LinearLayout.LayoutParams(
                135,
                Toolbar.LayoutParams.MATCH_PARENT
        );
        next_value.setLayoutParams(n_params);

        Button first = new Button(getActivity().getApplicationContext());
        LinearLayout.LayoutParams f_params = new LinearLayout.LayoutParams(
                135,
                Toolbar.LayoutParams.MATCH_PARENT
        );
        first.setLayoutParams(f_params);

        Button second = new Button(getActivity().getApplicationContext());
        LinearLayout.LayoutParams s_params = new LinearLayout.LayoutParams(
                135,
                Toolbar.LayoutParams.MATCH_PARENT
        );
        second.setLayoutParams(s_params);

        toolbar.addView(et);
        toolbar.addView(ib);
        toolbar.addView(previous_value);
        toolbar.addView(next_value);
        toolbar.addView(first);
        toolbar.addView(second);
        search.addView(toolbar);
    }
    private void onShowFontOptionsButtonClicked() {
        FrameLayout font = getActivity().findViewById(R.id.font_container);
        font.setVisibility(View.VISIBLE);

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.font_container, FontToolbar.newInstance());
        ft.commit();
    }
    private void onReplaceButtonClicked() {
        FrameLayout replace = getActivity().findViewById(R.id.replace_container);
        replace.setVisibility(View.VISIBLE);

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.replace_container, ReplaceToolbar.newInstance());
        ft.commit();
    }
    private void onZoomInButtonClicked() {
        binding.setupText.setTextSize(0,
                binding.setupText.getTextSize() + 3.0f);
    }
    private void onZoomOutButtonClicked() {
        binding.setupText.setTextSize(0,
                binding.setupText.getTextSize() - 3.0f);
    }
    private void onTextWrapButtonClicked() {

    }
    private void onShowSuggestionsButtonClicked() {

    }
    private void onShareButtonClicked() {

    }
    private void onCloseButtonClicked() {
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.container, MainScreenFragment.newInstance());
        ft.commit();

        setHasOptionsMenu(false);
    }
}
