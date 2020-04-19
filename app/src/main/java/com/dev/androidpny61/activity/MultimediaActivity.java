package com.dev.androidpny61.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.androidpny61.R;

public class MultimediaActivity extends AppCompatActivity {

    Button camera, gallery;
    ImageView picture;

    int camera_callback = 100;
    int gallery_callback = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        camera = findViewById(R.id.camera);
        gallery = findViewById(R.id.gallery);
        picture = findViewById(R.id.image);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Denied -1
                // Allowed 0

                int isPermissionGranted = ActivityCompat.checkSelfPermission(
                        MultimediaActivity.this,
                        Manifest.permission.CAMERA);

                if(isPermissionGranted == PackageManager.PERMISSION_DENIED){
                    // ask for permission

                    String[] permissions = {Manifest.permission.CAMERA};

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(permissions,camera_callback);
                    }else {
                        launchCamera();
                    }

                }else {
                    launchCamera();
                }



            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Denied -1
                // Allowed 0

                int isPermissionGranted = ActivityCompat.checkSelfPermission(
                        MultimediaActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                if(isPermissionGranted == PackageManager.PERMISSION_DENIED){
                    // ask for permission
                    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(permissions,gallery_callback);
                    }else {
                        launchGallery();
                    }

                }else {
                    launchGallery();
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, // call back variable
                                           @NonNull String[] permissions, // asked permission
                                           @NonNull int[] grantResults) { // result 0 , -1
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == camera_callback){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera();
            }else {
                Toast.makeText(this, "User cancelled Camera permission", Toast.LENGTH_SHORT).show();
            }

        }

        if(requestCode == gallery_callback){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchGallery();
            }else {
                Toast.makeText(this, "User cancelled Gallery permission", Toast.LENGTH_SHORT).show();
            }
        }



    }

    public void launchCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,camera_callback);

    }

    public void launchGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent,gallery_callback);

    }

    @Override
    protected void onActivityResult(int requestCode, // call back variable
                                    int resultCode, // success , fail
                                    @Nullable Intent data) { // related data
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == camera_callback){

            if (resultCode == RESULT_OK) {

                // data is the varable carrying image data .

                Bundle extras = data.getExtras();

                //A bitmap is a type of memory organization or image file format
                // used to store digital
                // images. The term bitmap comes from the computer programming
                // terminology, meaning just
                // a map of bits, a spatially mapped array of bits.

                Bitmap imageBitmap = (Bitmap) extras.get("data");
                picture.setImageBitmap(imageBitmap);

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if(requestCode == gallery_callback){

            if (resultCode == RESULT_OK) {

                Uri uri = data.getData();

                picture.setImageURI(uri);


            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled Gallery Image", Toast.LENGTH_SHORT)
                        .show();
            }

        }


    }
}
