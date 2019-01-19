package com.madrat.texteditor.db;

import com.dbflow5.annotation.Column;
import com.dbflow5.annotation.PrimaryKey;
import com.dbflow5.annotation.Table;
import com.dbflow5.structure.BaseModel;

@Table(database = MyDatabase.class)
public class Note extends BaseModel {

    @Column
    @PrimaryKey
    int id;

    @Column
    String name;

    @Column
    String value;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
