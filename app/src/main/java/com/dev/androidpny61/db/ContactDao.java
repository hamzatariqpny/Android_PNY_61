package com.dev.androidpny61.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insertContact(ContactEntity contactEntity);

    @Delete
    void deleteContact(ContactEntity contactEntity);

    @Update
    void updateContact(ContactEntity contactEntity);

    @Query("SELECT * FROM contactentity")
    List<ContactEntity> getAllContacts();


}
