package com.dev.androidpny61;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.androidpny61.activity.DatabaseActivity;
import com.dev.androidpny61.db.ContactEntity;
import com.dev.androidpny61.db.RoomDatabase;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.contactViewHolder> {

    private Activity activity;
    private List<ContactEntity> contacts;

    RoomDatabase roomDatabase;

    public ContactsAdapter(Activity activity, List<ContactEntity> contacts) {

        this.activity = activity;
        this.contacts = contacts;
        roomDatabase = RoomDatabase.getInstance(activity);

    }





    // view create
    @NonNull
    @Override
    public contactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(activity).inflate(R.layout.row_contacts, parent, false);

        return new contactViewHolder(v);
    }

    // created view -> data data bind
    @Override
    public void onBindViewHolder(@NonNull contactViewHolder holder, final int position) {

        final ContactEntity contact = contacts.get(position);

        holder.mName.setText(contact.contactName);
        holder.mContactNumber.setText(contact.contactNumber);
        holder.profilePic.setImageResource(R.drawable.avatar1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomDatabase.contactDao().deleteContact(getContact(contact.contactId));
                contacts = roomDatabase.contactDao().getAllContacts();
                notifyDataSetChanged();

                Intent intent = new Intent(activity, DatabaseActivity.class);
                if(position == 1) {
                    intent.putExtra("type","pizza");
                }else if(position==2){
                    intent.putExtra("type","burger");
                }
                activity.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class contactViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePic;
        TextView mName;
        TextView mContactNumber;

        public contactViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.profilePic);
            mName = itemView.findViewById(R.id.myName);
            mContactNumber = itemView.findViewById(R.id.myContact);

        }
    }


    public ContactEntity getContact(int id) {

        ContactEntity contactEntity = new ContactEntity();
        contactEntity.contactId = id;
        contactEntity.contactName = "edited name";
        contactEntity.contactNumber = "edited number";

        return contactEntity;

    }
}
