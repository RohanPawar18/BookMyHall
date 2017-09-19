package com.example.dhaval_2.bookmyhall;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

/**
 * Created by Patil on 4/28/2017.
 */

public class Info extends AppCompatActivity {


    ImageView imageView;
    //private StorageReference mStorageRef;
    TextView textView;
    //StorageReference filePath;
    //DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_row);
        //Toast.makeText(Info.this,"okkkkk",Toast.LENGTH_SHORT).show();

        textView=(TextView)findViewById(R.id.text1);
        imageView=(ImageView)findViewById(R.id.imageView);
        /*mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        StorageReference islandRef = mStorageRef.child("ConferenceHall/C2.jpg");
        try{
            final File localFile = File.createTempFile("images", "jpg");


            islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Uri uri=Uri.fromFile(localFile);
                    imageView.setImageURI(uri);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });

        }catch (IOException i){

        }



        mDatabase= mDatabase.child("Information");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=dataSnapshot.getValue(String.class);

                Toast.makeText(Info.this,"Hiiii",Toast.LENGTH_SHORT).show();
                textView.setText("Patil"+s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Info.this,"okkkkk",Toast.LENGTH_SHORT).show();
            }
        });
*/
    }
}
