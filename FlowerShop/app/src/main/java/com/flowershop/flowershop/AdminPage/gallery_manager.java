package com.flowershop.flowershop.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.flowershop.flowershop.Adapter.CustomGridviewAdapter_GalleryManager;
import com.flowershop.flowershop.Object.Flower_image;
import com.flowershop.flowershop.Object.Order;
import com.flowershop.flowershop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class gallery_manager extends AppCompatActivity{

    ArrayList<Flower_image> listdata = new ArrayList<>();
    private DatabaseReference ref;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_manager);
        ref = FirebaseDatabase.getInstance().getReference("Gallery");

    }

    @Override
    protected void onStart() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listdata.clear();
                for(DataSnapshot picture_snapshot: dataSnapshot.getChildren()) {
                    Flower_image picture = picture_snapshot.getValue(Flower_image.class);
                    listdata.add(picture);
                }
                GridView grid = (GridView) findViewById(R.id.gridview_gallery_manager);
                grid.setAdapter(new CustomGridviewAdapter_GalleryManager(gallery_manager.this,listdata));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.onStart();
    }

    public void add (View v)
    {
        Intent add = new Intent(gallery_manager.this,add_gallery.class);
        startActivity(add);
    }
    public void delete (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Flower_image> data = listdata;
        Flower_image od = data.get(index);
        listdata.remove(od);
        ref.child(od.getKey()).removeValue();
    }
    public void back (View v)
    {
        this.onBackPressed();
    }
}
