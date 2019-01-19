package com.madrat.texteditor.db;

import com.dbflow5.annotation.Database;

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDatabase";
    public static final int VERSION = 1;
}
