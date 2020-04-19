package com.dev.androidpny61.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.androidpny61.db.ContactEntity;
import com.dev.androidpny61.R;
import com.dev.androidpny61.db.RoomDatabase;

public class DatabaseActivity extends AppCompatActivity {

    EditText contactNameEdt,contactNumberEdt;

    Button insert,fetch,update,delete;

    String contactNameStr,contactNumberStr;

    RoomDatabase roomDatabase;

    String typeOfOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);



        Intent intent = getIntent();

        if(intent!=null){
            if(intent.hasExtra("type")){
                typeOfOrder = intent.getStringExtra("type");

                assert typeOfOrder != null;

                if(typeOfOrder.equals("pizza")){
                    // Load data
                    // recycler view
                    Toast.makeText(this, typeOfOrder, Toast.LENGTH_SHORT).show();

                }else if(typeOfOrder.equals("burger")){
                    // Load data
                    // recycler view
                    Toast.makeText(this, typeOfOrder, Toast.LENGTH_SHORT).show();

                }

            }

        }


        contactNameEdt = findViewById(R.id.contactNameEdt);
        contactNumberEdt = findViewById(R.id.contactNumberEdt);

        insert = findViewById(R.id.insertData);
        fetch = findViewById(R.id.fetchData);
        update = findViewById(R.id.updateData);
        delete = findViewById(R.id.deleteData);

        roomDatabase = RoomDatabase.getInstance(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomDatabase.contactDao().insertContact(getContact());
            }
        });


        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DatabaseActivity.this,MainActivity.class));
                }
        });


    }

    public ContactEntity getContact(){

        contactNameStr = contactNameEdt.getText().toString();
        contactNumberStr = contactNumberEdt.getText().toString();


        ContactEntity contactEntity = new ContactEntity();
        contactEntity.contactName = contactNameStr;
        contactEntity.contactNumber = contactNumberStr;

        return contactEntity;

    }
}
