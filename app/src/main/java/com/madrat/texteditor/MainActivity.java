package com.madrat.texteditor;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.madrat.texteditor.Fragments.EditFieldFragment;
import com.madrat.texteditor.Fragments.MainScreenFragment;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView nav_view;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.settings)
    Button settings;
    @BindView(R.id.new_file)
    Button new_file;
    @BindView(R.id.colors)
    Spinner spinner_colors;
    @BindView(R.id.languages)
    Spinner spinner_languages;
    @BindArray(R.array.themes)
    String[] colors;
    @BindArray(R.array.language_entries)
    String[] languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUp();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        /*Cursor cursor = getContentResolver().query(NOTES_URI, null, null,
                null, null);

        startManagingCursor(cursor);*/


    }

    private void setUp() {
        setNavigationView();
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainScreenFragment.newInstance()).commit();
    }

    private void setNavigationView() {
        nav_view.setNavigationItemSelectedListener((MenuItem menuItem) -> {
            menuItem.setChecked(true);
            drawer.closeDrawers();

            return true;
        });

        new_file.setOnClickListener((View v) -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EditFieldFragment.newInstance()).commit();
        });


        settings.setOnClickListener((View v) -> {
            //finish();
            startActivity(new Intent(this, MySettingsActivity.class));
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ArrayAdapter<String> adapter_colors = new ArrayAdapter<>(this,
                R.layout.colors_spinner, R.id.colors, colors);

        ArrayAdapter<String> adapter_languages = new ArrayAdapter<>(this,
                R.layout.languages_spinner, R.id.languages, languages);

        spinner_colors.setAdapter(adapter_colors);
        spinner_colors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    spinner_colors.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_languages.setAdapter(adapter_languages);
        spinner_languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    spinner_languages.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
