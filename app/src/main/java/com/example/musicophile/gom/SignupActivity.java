package com.example.musicophile.gom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;



public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = SignupActivity.this;

    private EditText Name;
    private EditText PhoneNumber;
    private EditText Location;
    private EditText Email;
    private EditText Username;
    private EditText Password;
    private EditText ConfirmPassword;
    private CheckBox Farmer;
    private Button Register;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    private TextView ViewLoginLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference( "PersonalInfo" );

        initViews();
        initListeners();
        initObjects();

    }
    private void initViews() {


        Name = (EditText) findViewById(R.id.editText3);
        PhoneNumber = (EditText)findViewById(R.id.editText4);
        Location = (EditText)findViewById(R.id.editText5);
        Email = (EditText) findViewById(R.id.editText6);
        Username = (EditText)findViewById( R.id.editText7);
        Password = (EditText) findViewById(R.id.editText10);
        ConfirmPassword = (EditText) findViewById(R.id.editText9);
        Farmer = (CheckBox)findViewById( R.id.checkBox );
        Register = (Button) findViewById(R.id.button2);
        // ViewLoginLink = (TextView) findViewById(R.id.TextViewLoginLink);

    }

    private void initListeners() {
        Register.setOnClickListener( this );
        //ViewLoginLink.setOnClickListener(this);

    }

    private void initObjects() {
        // inputValidation = new FirstValidation(activity);
        // databaseHelper = new DatabaseHelper( activity);
        // user = new User( );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button2:

                final String name_text = Name.getText().toString().trim();
                final String phone_text = PhoneNumber.getText().toString();
                final String location_text = Location.getText().toString().trim();
                final String email_text = Email.getText().toString().trim();
                final String username_text = Username.getText().toString().trim();
                final String pass_text = Password.getText().toString().trim();

                if (!TextUtils.isEmpty( email_text ) && !TextUtils.isEmpty(pass_text)){
                    mAuth.createUserWithEmailAndPassword( email_text, pass_text ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            try{
                                if (task.isSuccessful()) {
                                    String user_id = mAuth.getCurrentUser().getUid();
                                    DatabaseReference current = myRef.child(user_id);

                                    // String child = Name.getText().toString();
                                    //  myRef = database.getReference("PersonalInfo").child( user_id);


                                    current.child("Name").setValue(name_text);
                                    current.child("PhoneNumber").setValue(phone_text);
                                    current.child("Location").setValue(location_text);
                                    current.child("Email").setValue(email_text);
                                    current.child("Username").setValue(username_text);
                                    current.child("Password").setValue(pass_text);

                                    Toast.makeText(SignupActivity.this, "Succesfully data stored...", Toast.LENGTH_LONG).show();


                                }} catch (NullPointerException e){
                                e.printStackTrace();
                            }
                        }
                    } );

                }
                break;}
    }
    /*public void registerClicked(View v){
        Intent login = new Intent( getApplicationContext(), LoginActivity.class );
        startActivity( login );
    }

    public void SubmitClicked(View view){
        Intent login = new Intent( getApplicationContext(), LoginActivity.class );
        startActivity( login );
    }
    */}
