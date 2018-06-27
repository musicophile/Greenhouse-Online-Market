package com.example.musicophile.gom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.widget.Toast.LENGTH_LONG;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProductActivity extends AppCompatActivity {


    private ImageButton foodImage;
    private static final int SELECT_PHOTO = 1;
    private EditText PName;
    private EditText PSize;
    private EditText PPrice;
    private EditText PDate;
    private Button Upload;
    private Button ViewProduct;
    private ImageView FoodImage;
    private Uri uri = null;
    private StorageReference storageReference = null;
    private DatabaseReference mRef;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        Toolbar toolbar = (Toolbar)findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        initViews();
    }

    public void initViews() {
        PName = (EditText) findViewById( R.id.editText1 );
        PSize = (EditText) findViewById( R.id.editText2 );
        PPrice = (EditText) findViewById( R.id.editText3 );
        PDate = (EditText) findViewById( R.id.editText4 );
        Upload = (Button) findViewById( R.id.button );
        ViewProduct = (Button) findViewById( R.id.button2 );
        storageReference = FirebaseStorage.getInstance().getReference();
      mRef = FirebaseDatabase.getInstance().getReference( "ProductInfo" );
        mAuth = FirebaseAuth.getInstance();


    }


    public void imageClicked(View view) {
        Intent photoPickerIntent = new Intent( Intent.ACTION_PICK );
        photoPickerIntent.setType( "image/*" );
        startActivityForResult( photoPickerIntent, SELECT_PHOTO );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult( requestCode, resultCode, imageReturnedIntent );

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        uri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream( uri );
                        final Bitmap selectedImage = BitmapFactory.decodeStream( imageStream );
                        //imageView.setVisibility(View.VISIBLE);
                        FoodImage = (ImageView) findViewById( R.id.imageView_c );
                        FoodImage.setImageBitmap( selectedImage );
                        //String text = "Change Image";
                        //textinfo.setVisibility(View.VISIBLE);
                        //pickImage.setText(text);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }


    public void viewClicked(View view){
        Intent intent = new Intent( ProductActivity.this, showItem.class );
        startActivity( intent );
        this.finish();
    }

    public void submitClicked(View view){
/*

        final String PName_text = PName.getText().toString().trim();
        final String PSize_text = PSize.getText().toString().trim();
        final String PPrice_text = PPrice.getText().toString().trim();
        final String PDate_text = PDate.getText().toString().trim();
      // final String user_id = mAuth.getCurrentUser().getUid();

        if (!TextUtils.isEmpty( PName_text ) && !TextUtils.isEmpty( PSize_text ) && !TextUtils.isEmpty( PPrice_text ) && !TextUtils.isEmpty( PDate_text )) {

            StorageReference filePath = storageReference.child( uri.getLastPathSegment() );
            filePath.putFile( uri ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadurl = taskSnapshot.getDownloadUrl();
                    Toast.makeText( ProductActivity.this, "SuccessFull image stored ...", LENGTH_LONG ).show();


                   final DatabaseReference newPost = mRef.push();
                  //  mRef = FirebaseDatabase.getInstance().getReference( "ProductInfo" );
                 //   myRef = database.getReference("PersonalInfo").child( user_id);



                   // String PNam = PName.getText().toString().trim();
                   // DatabaseReference current = myRef.child(user_id);
                    //final DatabaseReference newPost = mRef.push();

                  //  mRef.child(PName_text);


                   // newPost.child("key").setValue(user_id);
                    newPost.child("PName").setValue( PName_text);
                    newPost.child("PSize").setValue( PSize_text);
                    newPost.child("PPrice").setValue( PPrice_text);
                    newPost.child("PDate").setValue( PDate_text);
                    newPost.child("PImage").setValue( downloadurl.toString());*/
        final String PName_text = PName.getText().toString().trim();
        final String PSize_text = PSize.getText().toString().trim();
        final String PPrice_text = PPrice.getText().toString().trim();
        final String PDate_text = PDate.getText().toString().trim();
        final String user_id = mAuth.getCurrentUser().getUid();

        if (!TextUtils.isEmpty( PName_text ) && !TextUtils.isEmpty( PSize_text ) && !TextUtils.isEmpty( PPrice_text ) && !TextUtils.isEmpty( PDate_text )) {

            StorageReference filePath = storageReference.child( uri.getLastPathSegment() );
            filePath.putFile( uri ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadurl = taskSnapshot.getDownloadUrl();
                    Toast.makeText( ProductActivity.this, "SuccessFull image stored ...", LENGTH_LONG ).show();
                    final DatabaseReference newPost = mRef.push();
                    //  mRef = FirebaseDatabase.getInstance().getReference( "ProductInfo" );
                    //   myRef = database.getReference("PersonalInfo").child( user_id);
                    newPost.child("PName").setValue( PName_text);
                    newPost.child("key").setValue(user_id);
                    newPost.child("PSize").setValue( PSize_text);
                    newPost.child("PPrice").setValue( PPrice_text);
                    newPost.child("PDate").setValue( PDate_text);
                    newPost.child("PImage").setValue( downloadurl.toString());


                    Toast.makeText( ProductActivity.this, "SuccessFull all  t  stored ...", LENGTH_LONG ).show();

                }

            } );

        }

    }

}
