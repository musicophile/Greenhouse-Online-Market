package com.example.musicophile.gom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText Email, Password;
    private Button btnSignIn;

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private TextView SignUp, ResetPassword;
    private ProgressDialog mPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPro = new ProgressDialog(this);
        mPro.setMessage("Please wait...");

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("PersonalInfo");

        btnSignIn = (Button) findViewById(R.id.button);
        SignUp = (TextView) findViewById(R.id.textView4);
        Email = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);

        ResetPassword = (TextView) findViewById(R.id.textView2);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this, SignupActivity.class );
                startActivity( intent );
            }

        });

        ResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                final String password = Password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    mPro.show();
                    if (password.length() < 6) {
                        Password.setError("Minimum paword");
                    }else {
                        //authenticate user
                        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                if (task.isSuccessful()) {

                                    checkUserExists();
                                }
                            }
                        });
                    }
                }






            }

        });
    }

    private void checkUserExists() {
        final String user_id = auth.getCurrentUser().getUid();
       mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(user_id)){
                    mPro.dismiss();
                    Intent intent = new Intent(LoginActivity.this, ProductActivity.class);
                    startActivity(intent);
                    finish();
                }
           }

           @Override
            public void onCancelled(DatabaseError databaseError) {

           }
        });
    }
}