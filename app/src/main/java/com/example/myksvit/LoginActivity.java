package com.example.myksvit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText useremail;
    private EditText userpassword;
    private Button btnlogin;
    private Button btnsignup;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        useremail=(EditText)findViewById(R.id.user_email);
        userpassword=(EditText)findViewById(R.id.user_password);
        btnlogin=(Button)findViewById(R.id.btn_login);
        btnsignup=(Button)findViewById(R.id.btn_signup);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                first();
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    private void first(){
        String email = useremail.getText().toString().trim();
        String password = userpassword.getText().toString().trim();

        if (email.isEmpty()) {
            useremail.setError("Email is required");
            useremail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            useremail.setError("Please enter a valid email address");
            useremail.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            userpassword.setError("Password is required");
            userpassword.requestFocus();
            return;
        }

        if(password.length()<6){
            userpassword.setError("Minimum length of password shoudld be 6");
            userpassword.requestFocus();
            return;
        }

        if ((!email.isEmpty())&&(!password.isEmpty())){


            btnlogin.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

        }



        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent intent = new Intent(LoginActivity.this ,HouseActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "User LOGIN Successfull", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();

                } else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(), "User Login Faied ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    btnlogin.setVisibility(View.VISIBLE);
                }

                // ...
            }
        });

    }

}

