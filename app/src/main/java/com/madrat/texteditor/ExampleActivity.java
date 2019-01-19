package com.madrat.texteditor;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ExampleActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    final Uri NOTES_URI = Uri
            .parse("content:com.madrat.texteditor.NotesProvider/notes");

    final String NOTES_NAME = "name";
    final String NOTES_VALUE = "value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        Cursor cursor = getContentResolver().query(NOTES_URI, null, null,
                null, null);
        startManagingCursor(cursor);

        String from[] = { "name", "value" };
        int to[] = { android.R.id.text1, android.R.id.text2 };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor, from, to);

        ListView lvContact = (ListView) findViewById(R.id.lvContact);
        lvContact.setAdapter(adapter);
    }

    public void onClickInsert(View v) {
        ContentValues cv = new ContentValues();
        cv.put(NOTES_NAME, "name 4");
        cv.put(NOTES_VALUE, "value 4");
        Uri newUri = getContentResolver().insert(NOTES_URI, cv);
        Log.d(LOG_TAG, "insert, result Uri : " + newUri.toString());
    }

    public void onClickUpdate(View v) {
        ContentValues cv = new ContentValues();
        cv.put(NOTES_NAME, "name 5");
        cv.put(NOTES_VALUE, "email 5");
        Uri uri = ContentUris.withAppendedId(NOTES_URI, 2);
        int cnt = getContentResolver().update(uri, cv, null, null);
        Log.d(LOG_TAG, "update, count = " + cnt);
    }

    public void onClickDelete(View v) {
        Uri uri = ContentUris.withAppendedId(NOTES_URI, 3);
        int cnt = getContentResolver().delete(uri, null, null);
        Log.d(LOG_TAG, "delete, count = " + cnt);
    }

    public void onClickError(View v) {
        Uri uri = Uri.parse("content://com.madrat.texteditor.Notes/notes");
        try {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        } catch (Exception ex) {
            Log.d(LOG_TAG, "Error: " + ex.getClass() + ", " + ex.getMessage());
        }

    }
}
