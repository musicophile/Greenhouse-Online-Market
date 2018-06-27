package com.example.musicophile.gom;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
 import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class showItem extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<ImageUploadInfo> list = new ArrayList<>();

    
    RecyclerView.Adapter adapter ;
  //  private TextView name;
  //  private String namea;

    String Shot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);

      //  name = findViewById(R.id.ImageNameTextView);



        recyclerView =(RecyclerView)findViewById(R.id.recyclerVie);
      //  recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(showItem.this));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        databaseReference = FirebaseDatabase.getInstance().getReference("ProductInfo");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {


                for( DataSnapshot postSnapshot : snapshot.getChildren()){
                    Shot = postSnapshot.getKey();

                    ImageUploadInfo imageUploadInfo = postSnapshot.getValue(ImageUploadInfo.class);

                    list.add(imageUploadInfo);





                }

               Toast.makeText(getApplicationContext(), "key "+ Shot, Toast.LENGTH_LONG).show();


            adapter = new RecyclerViewAdapter(getApplicationContext(), list);

        recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       // namea = name.getText().toString().trim();
    }

    public void onClick(View view) {

      //  Toast.makeText(showItem.this, "Shoot", Toast.LENGTH_LONG).show();
        Intent intenta = new Intent( showItem.this, MoreInfo.class );
        intenta.putExtra("key", Shot);
      //  intenta.putExtra("name", namea);
        startActivity( intenta );
       //this.finish();
   }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{

            private int spanCount;
            private int spacing;
            private boolean includeEdge;


            public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
                this.spanCount = spanCount;
                this.spacing = spacing;
                this.includeEdge = includeEdge;
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view); // item position
                int column = position % spanCount; // item column

                if (includeEdge) {
                    outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                    if (position < spanCount) { // top edge
                        outRect.top = spacing;
                    }
                    outRect.bottom = spacing; // item bottom
                } else {
                    outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                    outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    if (position >= spanCount) {
                        outRect.top = spacing; // item top
                    }
                }}




        }
        private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    }


