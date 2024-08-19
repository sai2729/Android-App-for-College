package com.example.myksvit;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText username;
    private EditText useremail;
    private EditText userpassword;
    private EditText userphoneno;
    private EditText userrollno;
    private Button btnsignup;
    String name,mobileno,email,password,rollno,branch,year;
    private Spinner yearspinner;
    private Spinner branchspinner;
    private RadioGroup radioGroup;


    private static final String TAG = SignupActivity.class.getSimpleName();
    private String userId;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        radioGroup = (RadioGroup)findViewById(R.id.groupradio);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);

                int selectedid=radioGroup.getCheckedRadioButtonId();

                if (selectedid==-1)
                {

                }
                else {
                    Intent intent=new Intent(SignupActivity.this,facultysignup.class);
                    startActivity(intent);
                }

            }
        });

                setupUIViews();

        btnsignup.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate())
                {
                    //Upload data to the database
                    String user_email = useremail.getText().toString().trim();
                    String user_password = userpassword.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                //sendEmailVerification();
                                sendUserData();
                                mAuth.signOut();
                                Toast.makeText(SignupActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                    });

                }

            }
        });

        yearspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String useryear=adapterView.getItemAtPosition(i).toString();

                switch (useryear)
                {
                    case "FIRST":
                        branchspinner.setAdapter(new ArrayAdapter<String>(SignupActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.FIRST)));
                        break;
                    case "SECOND":
                        branchspinner.setAdapter(new ArrayAdapter<String>(SignupActivity.this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.SECOND)));
                        break;
                    case "THIRD":
                        branchspinner.setAdapter(new ArrayAdapter<String>(SignupActivity.this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.THIRD)));
                        break;
                    case "FOURTH":
                        branchspinner.setAdapter(new ArrayAdapter<String>(SignupActivity.this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.FOURTH)));
                        break;
                }
                branchspinner.setVisibility(View.VISIBLE);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        branchspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String userbranch=adapterView.getItemAtPosition(i).toString();

                btnsignup.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    private void setupUIViews()
    {
        mAuth = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.user_name);
        useremail = (EditText) findViewById(R.id.user_email);
        userpassword = (EditText) findViewById(R.id.user_password);
        userphoneno = (EditText) findViewById(R.id.user_phone);
        userrollno=(EditText)findViewById(R.id.rollno);
        btnsignup = (Button) findViewById(R.id.btn_signup);
        yearspinner=(Spinner)findViewById(R.id.year_spinner);
        branchspinner=(Spinner)findViewById(R.id.branch_spinner);
    }

    private Boolean validate(){
        Boolean result = false;

        name = username.getText().toString();
        password = userpassword.getText().toString();
        email = useremail.getText().toString();
        mobileno = userphoneno.getText().toString();
        rollno = userrollno.getText().toString();
        year=yearspinner.getSelectedItem().toString();
        branch=branchspinner.getSelectedItem().toString();


        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || mobileno.isEmpty() ||rollno.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }



    private void sendUserData()
    {
        UserProfile userProfile = new UserProfile(mobileno,email,name,rollno,branch);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();
        DatabaseReference myuserdata=myRef.child("UserData");
        DatabaseReference myyear=myuserdata.child(yearspinner.getSelectedItem().toString());
        DatabaseReference mybranch=myyear.child(branchspinner.getSelectedItem().toString());
        DatabaseReference myrollno=mybranch.child(userProfile.userrollno);
        myrollno.setValue(userProfile);


        FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = firebaseDatabase1.getReference();
        DatabaseReference myuserdata1=myRef1.child("Access");
        DatabaseReference myrollno1=myuserdata1.child(mAuth.getUid());
        myrollno1.setValue(userProfile);

    }

    public void onBackPressed(){

        Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(intent);
    }


}
