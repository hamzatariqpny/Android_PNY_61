package com.dev.androidpny61.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dev.androidpny61.ContactsAdapter;
import com.dev.androidpny61.R;
import com.dev.androidpny61.db.RoomDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactsAdapter contactsAdapter;
    RoomDatabase roomDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.contactsRV);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        roomDatabase = RoomDatabase.getInstance(this);

        contactsAdapter = new ContactsAdapter(this,roomDatabase.contactDao().getAllContacts());


        recyclerView.setAdapter(contactsAdapter);

    }


}
