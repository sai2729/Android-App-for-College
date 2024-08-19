package com.example.myksvit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class facultysignup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText username;
    private EditText useremail;
    private EditText userpassword;
    private EditText userphoneno,userpin;
    private Button btnsignup;
    String name,mobileno,email,password,department,pin;
    private RadioGroup radioGroup;
    private Spinner dept;

    private static final String TAG = facultysignup.class.getSimpleName();
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultysignup);

        userpin=(EditText)findViewById(R.id.editpin);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

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
                    Intent intent=new Intent(facultysignup.this,SignupActivity.class);
                    startActivity(intent);
                }

            }
        });
        setupUIViews();

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
                                Toast.makeText(facultysignup.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(facultysignup.this, LoginActivity.class));
                            } else {
                                Toast.makeText(facultysignup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                    });

                }

            }
        });


        dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String userbranch=adapterView.getItemAtPosition(i).toString();


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
        btnsignup = (Button) findViewById(R.id.btn_signup);
        dept=(Spinner)findViewById(R.id.dept_spinner);
    }

    private Boolean validate(){
        Boolean result = false;

        name = username.getText().toString();
        password = userpassword.getText().toString();
        email = useremail.getText().toString();
        mobileno = userphoneno.getText().toString();
        department=dept.getSelectedItem().toString();
        pin=userpin.getText().toString();


        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || mobileno.isEmpty() ||department.isEmpty()||pin.isEmpty()){

            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            if (pin.equals("272931")){
                result = true;
            }
            else {
                Toast.makeText(this,"Incorrect PIN",Toast.LENGTH_SHORT).show();
            }

        }

        return result;
    }



    private void sendUserData()
    {
        FacultyProfile facultyProfile = new FacultyProfile(mobileno,email,name,department);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();
        DatabaseReference myuserdata=myRef.child("FacultyData");
        DatabaseReference mydept=myuserdata.child(dept.getSelectedItem().toString());
        DatabaseReference myname=mydept.child(facultyProfile.facultyName);
        myname.setValue(facultyProfile);


        FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = firebaseDatabase1.getReference();
        DatabaseReference myuserdata1=myRef1.child("Access");
        DatabaseReference teacher=myuserdata1.child(mAuth.getUid());
        teacher.setValue(facultyProfile);
    }


    public void onBackPressed(){

        Intent intent=new Intent(facultysignup.this,SignupActivity.class);
        startActivity(intent);
    }
}

