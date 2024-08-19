package com.example.myksvit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EventPostActivity extends AppCompatActivity {


    private Uri uri = null;
    private EditText textTitle;
    private EditText textDesc;
    private EditText textUrl;
    private Button postBtn;
    private StorageReference storage;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_post);

        // initializing objects
        postBtn = (Button)findViewById(R.id.postBtn);
        textDesc = (EditText)findViewById(R.id.textDesc);
        textTitle = (EditText)findViewById(R.id.textTitle);
        textUrl= (EditText)findViewById(R.id.textUrl);
        storage = FirebaseStorage.getInstance().getReference();
        databaseRef = database.getInstance().getReference().child("Eventzone");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());


        // posting to Firebase
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EventPostActivity.this, "POSTING...", Toast.LENGTH_LONG).show();
                final String PostTitle = textTitle.getText().toString().trim();
                final String PostDesc = textDesc.getText().toString().trim();
                final String PostUrl = textUrl.getText().toString().trim();
                // do a check for empty fields
                if (!TextUtils.isEmpty(PostDesc) && !TextUtils.isEmpty(PostTitle)){



                            Toast.makeText(getApplicationContext(), "Succesfully Uploaded", Toast.LENGTH_SHORT).show();
                            final DatabaseReference newPost = databaseRef.push();
                            //adding post contents to database reference
                            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    newPost.child("title").setValue(PostTitle);
                                    newPost.child("desc").setValue(PostDesc);
                                    newPost.child("URL").setValue(PostUrl);
                                    newPost.child("uid").setValue(mCurrentUser.getUid());
                                    newPost.child("username").setValue(dataSnapshot.child("name").getValue())
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()){
                                                        Intent intent = new Intent(EventPostActivity.this, HouseActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                }
            }
        });

    }
}
