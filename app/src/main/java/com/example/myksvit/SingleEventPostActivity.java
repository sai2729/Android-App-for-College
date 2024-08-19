package com.example.myksvit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SingleEventPostActivity extends AppCompatActivity {

    private TextView singleTitle, singleDesc,singleUrl;
    String post_key = null;
    private DatabaseReference mDatabase;
    private Button deleteBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event_post);


        singleTitle = (TextView)findViewById(R.id.singleTitle);
        singleDesc = (TextView)findViewById(R.id.singleDesc);
        singleUrl = (TextView)findViewById(R.id.singleUrl);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Eventzone");
        post_key = getIntent().getExtras().getString("PostID");
        deleteBtn = (Button)findViewById(R.id.deleteBtn);
        mAuth = FirebaseAuth.getInstance();
        deleteBtn.setVisibility(View.INVISIBLE);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase.child(post_key).removeValue();

                Intent mainintent = new Intent(SingleEventPostActivity.this, HouseActivity.class);
                startActivity(mainintent);
            }
        });

        mDatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_title = (String) dataSnapshot.child("title").getValue();
                String post_desc = (String) dataSnapshot.child("desc").getValue();
                String post_uid = (String) dataSnapshot.child("uid").getValue();
                final String post_url= (String) dataSnapshot.child("URL").getValue();

                singleTitle.setText(post_title);
                singleDesc.setText(post_desc);
                singleUrl.setText(post_url);





                if (mAuth.getCurrentUser().getUid().equals(post_uid)){

                    deleteBtn.setVisibility(View.VISIBLE);
                }



            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
