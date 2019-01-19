package com.madrat.texteditor;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class NotesProvider extends ContentProvider {
    final String LOG_TAG = "myLogs";

    // // Константы для БД
    // БД
    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;

    // Таблица
    static final String NOTES_TABLE = "notes";

    // Поля
    static final String NOTES_ID = "_id";
    static final String NOTES_NAME = "name";
    static final String NOTES_VALUE = "value";

    // Скрипт создания таблицы
    static final String DB_CREATE = "create table " + NOTES_TABLE + "("
            + NOTES_ID + " integer primary key autoincrement, "
            + NOTES_NAME + " text, " + NOTES_VALUE + " text" + ");";

    // // Uri
    // authority
    static final String AUTHORITY = "com.madrat.texteditor.Notes";

    // path
    static final String NOTES_PATH = "notes";

    // Общий Uri
    public static final Uri NOTES_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + NOTES_PATH);

    // Типы данных
    // набор строк
    static final String NOTES_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + NOTES_PATH;

    // одна строка
    static final String NOTES_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + NOTES_PATH;

    //// UriMatcher
    // общий Uri
    static final int URI_NOTES = 1;

    // Uri с указанным ID
    static final int URI_NOTES_ID = 2;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, NOTES_PATH, URI_NOTES);
        uriMatcher.addURI(AUTHORITY, NOTES_PATH + "/#", URI_NOTES_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    // чтение
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, "query, " + uri.toString());
        // проверяем Uri
        switch (uriMatcher.match(uri)) {
            case URI_NOTES: // общий Uri
                Log.d(LOG_TAG, "URI_NOTES");
                // если сортировка не указана, ставим свою - по имени
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = NOTES_NAME + " ASC";
                }
                break;
            case URI_NOTES_ID: // Uri с ID
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_NOTES_ID, " + id);
                // добавляем ID к условию выборки
                if (TextUtils.isEmpty(selection)) {
                    selection = NOTES_ID + " = " + id;
                } else {
                    selection = selection + " AND " + NOTES_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(NOTES_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);
        // просим ContentResolver уведомлять этот курсор
        // об изменениях данных в NOTES_CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(),
                NOTES_CONTENT_URI);
        return cursor;
    }

    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LOG_TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != URI_NOTES)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(NOTES_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(NOTES_CONTENT_URI, rowID);
        // уведомляем ContentResolver, что данные по адресу resultUri изменились
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(LOG_TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_NOTES:
                Log.d(LOG_TAG, "URI_NOTES");
                break;
            case URI_NOTES_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_NOTES_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = NOTES_ID + " = " + id;
                } else {
                    selection = selection + " AND " + NOTES_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(NOTES_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(LOG_TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_NOTES:
                Log.d(LOG_TAG, "URI_NOTES");

                break;
            case URI_NOTES_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_NOTES_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = NOTES_ID + " = " + id;
                } else {
                    selection = selection + " AND " + NOTES_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(NOTES_TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public String getType(Uri uri) {
        Log.d(LOG_TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_NOTES:
                return NOTES_CONTENT_TYPE;
            case URI_NOTES_ID:
                return NOTES_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            ContentValues cv = new ContentValues();
            for (int i = 1; i <= 3; i++) {
                cv.put(NOTES_NAME, "name " + i);
                cv.put(NOTES_VALUE, "email " + i);
                db.insert(NOTES_TABLE, null, cv);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
