package com.flowershop.flowershop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.flowershop.flowershop.Adapter.CustomGridview_gallery;
import com.flowershop.flowershop.Object.Flower_image;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by kenjita.tran on 3/10/18.
 */

public class gallery extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<Flower_image> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        ref = FirebaseDatabase.getInstance().getReference("Gallery");

    }
    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot picture_snapshot: dataSnapshot.getChildren()) {
                    Flower_image temp = picture_snapshot.getValue(Flower_image.class);
                    list.add(temp);
                }
                GridView grid = (GridView) findViewById(R.id.gridview_gallery);
                grid.setAdapter(new CustomGridview_gallery(gallery.this,list));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}
