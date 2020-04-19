package com.dev.androidpny61.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ContactEntity {


    @PrimaryKey(autoGenerate = true)
    public int contactId;

    @ColumnInfo(name = "contact_name")
    public String contactName;

    @ColumnInfo(name = "contact_number")
    public String contactNumber;


}
