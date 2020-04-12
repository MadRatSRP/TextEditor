package com.madrat.texteditor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.madrat.texteditor.Fragments.EditFieldFragment;
import com.madrat.texteditor.Fragments.MainScreenFragment;
import com.madrat.texteditor.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
        setSupportActionBar(binding.toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainScreenFragment.newInstance()).commit();
    }

    private void setNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener((MenuItem menuItem) -> {
            menuItem.setChecked(true);
            binding.drawerLayout.closeDrawers();

            return true;
        });

        binding.createFileButton.setOnClickListener((View v) -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EditFieldFragment.newInstance()).commit();
        });


        binding.showSettingsButton.setOnClickListener((View v) -> {
            //finish();
            startActivity(new Intent(this, MySettingsActivity.class));
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        String[] arrayOfColors = getResources().getStringArray(R.array.themes);
        String[] arrayOfLanguages = getResources().getStringArray(R.array.language_entries);

        ArrayAdapter adapter_colors = new ArrayAdapter<String>(this,
                R.layout.colors_spinner,
                R.id.colors_spinner,
                arrayOfColors);

        ArrayAdapter adapter_languages = new ArrayAdapter<String>(this,
                R.layout.languages_spinner, R.id.languages_spinner, arrayOfLanguages);

        binding.colorsSpinner.setAdapter(adapter_colors);
        binding.colorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    binding.colorsSpinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.languagesSpinner.setAdapter(adapter_languages);
        binding.languagesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    binding.languagesSpinner.setSelection(0);
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
                binding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
