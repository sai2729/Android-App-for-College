package com.example.myksvit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zzv;
import com.google.firebase.auth.zzx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private TextView etname,etemail,etphoneno,etbranch,etrollno,Txtdetails;
    private FirebaseAuth mAuth;
    private Button button,btnlogout;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        etname=(TextView)findViewById(R.id.Etname);
        Txtdetails=(TextView)findViewById(R.id.txtdetails);
        btnlogout=(Button)findViewById(R.id.btnlogout);
        etemail=(TextView)findViewById(R.id.ETemail);
        etphoneno=(TextView)findViewById(R.id.ETphoneno);
        etbranch=(TextView)findViewById(R.id.ETbranch);
        etrollno=(TextView)findViewById(R.id.ETrollno);
        button=(Button)findViewById(R.id.btn);

        mAuth = FirebaseAuth.getInstance();



        firebaseDatabase = FirebaseDatabase.getInstance();


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mAuth = FirebaseAuth.getInstance();
                mAuthListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {

                            mAuth.signOut();
                            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                            // User is signed in

                        } else {
                            // User is signed out


                        }
                    }
                };




            }
        });


        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Access").child(mAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

                if (userProfile.getUserrollno()!=null){
                    Txtdetails.setText("Student Profile");
                    etname.setText("Name: " + userProfile.getUserName());
                    etrollno.setVisibility(View.VISIBLE);
                    etemail.setText("Email: " + userProfile.getUserEmail());
                    etbranch.setText("Branch: " + userProfile.getUserbranch());
                    etphoneno.setText("Phoneno: " + userProfile.getUserphoneno());
                    etrollno.setText("rollno: " + userProfile.getUserrollno());
                }
                else {
                    Txtdetails.setText("Faculty Profile");
                    FacultyProfile facultyProfile = dataSnapshot.getValue(FacultyProfile.class);
                    etname.setText("Name: " + facultyProfile.getFacultyName());
                    etemail.setText("Email: " + facultyProfile.getFacultyEmail());
                    etbranch.setText("DEPT: " + facultyProfile.getFacultyDept());
                    etphoneno.setText("Phoneno: " + facultyProfile.getFacultyphoneno());
                }



                String msg=userProfile.getUserrollno();

                if (msg!=null){

                    button.setEnabled(false);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
