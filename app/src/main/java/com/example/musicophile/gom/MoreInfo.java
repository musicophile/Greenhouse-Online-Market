package com.example.musicophile.gom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MoreInfo extends AppCompatActivity {

    RecyclerView recyclerView;
  //  DatabaseReference databaseReference;
  //  List<MoreProductInfo> list = new ArrayList<>();
    RecyclerView.Adapter adapter ;

   private  String Shoot;
//   private String namename;
    DatabaseReference databaseReference;
    List<ImageUploadInfo> list = new ArrayList<>();




    // nae = Intent.get



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);


        recyclerView =(RecyclerView)findViewById(R.id.recyclerViewA);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MoreInfo.this));

        Intent intenta = getIntent();
        Shoot = intenta.getStringExtra("key");
    //    namename = intenta.getStringExtra("name");



        Toast.makeText(getApplicationContext(), "key " + Shoot, Toast.LENGTH_LONG).show();
    //   Toast.makeText(getApplicationContext(), "name " + namename, Toast.LENGTH_LONG).show();

        databaseReference = FirebaseDatabase.getInstance().getReference("ProductInfo");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                   ImageUploadInfo imageUploadInfo = postSnapshot.getValue(ImageUploadInfo.class);

                    list.add(imageUploadInfo);
              }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        adapter = new RecyclerViewAdapterA(getApplicationContext() , list);

        recyclerView.setAdapter(adapter);
       // databaseReference = FirebaseDatabase.getInstance().getReference("ProductInfo");
       /* databaseReference = FirebaseDatabase.getInstance().getReference("PersonalInfo");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {


                    MoreProductInfo moreProductInf = postSnapshot.child("eRC8t8rJTMeEr1NWFJzK8TPYZAv2").getValue(MoreProductInfo.class);


                    list.add(moreProductInf);

                }


                adapter = new RecyclerViewAdapterA(getApplicationContext(), list, name);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }}