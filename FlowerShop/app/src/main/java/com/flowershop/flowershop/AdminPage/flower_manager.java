package com.flowershop.flowershop.AdminPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import com.flowershop.flowershop.Adapter.CustomGridviewAdapter;
import com.flowershop.flowershop.Adapter.CustomListAdapter_FlowerManager;
import com.flowershop.flowershop.Object.Flower;
import com.flowershop.flowershop.R;
import com.flowershop.flowershop.view_flower;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by mpxv2 on 4/9/18.
 */

public class flower_manager extends AppCompatActivity {
    DatabaseReference ref;
    static String id;
    static String image;

    ArrayList<Flower> list_best = new ArrayList<>();
    ArrayList<Flower> list_dried = new ArrayList<>();
    ArrayList<Flower> list_single = new ArrayList<>();
    ArrayList<Flower> listdata = new ArrayList<>();
    public static final int rq = 100;
    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listdata.clear();
                for(DataSnapshot flower_snapshot : dataSnapshot.getChildren())
                {
                    Flower flower = flower_snapshot.getValue(Flower.class);
                    listdata.add(flower);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flower_manager);
        ref = FirebaseDatabase.getInstance().getReference("Flower");

        final Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        final String choice7[] = {"All of Flower","Best Seller Flower","Single Flower","Dried Flower"};
        class activity implements android.widget.AdapterView.OnItemSelectedListener {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String text = spinner7.getSelectedItem().toString();
                if(text.compareTo("All of Flower")==0)
                {
                    ListView listView = (ListView) findViewById(R.id.listview_flower_manager);
                    listView.setAdapter(new CustomListAdapter_FlowerManager(flower_manager.this,listdata));
                }
                if(text.compareTo("Best Seller Flower")==0)
                {
                    for (Flower temp : listdata) {
                        if(temp.getSpecies().toString().compareTo("best seller")==0)
                        {
                            list_best.add(temp);
                        }
                    }
                    ListView listView = (ListView) findViewById(R.id.listview_flower_manager);
                    listView.setAdapter(new CustomListAdapter_FlowerManager(flower_manager.this,list_best));

                }
                if(text.compareTo("Dried Flower")==0)
                {
                    for (Flower temp : listdata) {
                        if(temp.getSpecies().toString().compareTo("Dried")==0)
                        {
                            list_dried.add(temp);
                        }
                    }
                    ListView listView = (ListView) findViewById(R.id.listview_flower_manager);
                    listView.setAdapter(new CustomListAdapter_FlowerManager(flower_manager.this,list_dried));
                }
                if(text.compareTo("Single Flower")==0)
                {
                    for (Flower temp : listdata) {
                        if(temp.getSpecies().toString().compareTo("Single")==0)
                        {
                            list_single.add(temp);
                        }
                    }
                    ListView listView = (ListView) findViewById(R.id.listview_flower_manager);
                    listView.setAdapter(new CustomListAdapter_FlowerManager(flower_manager.this,list_single));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice7);
        spinner7.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setOnItemSelectedListener(new activity());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == rq ) {
            String name = data.getStringExtra("name");
            String price = data.getStringExtra("price");
            String status = data.getStringExtra("status");
            String detail = data.getStringExtra("detail");
            String species = data.getStringExtra("species");
            Flower fl = new Flower();
            fl.setFlowername(name);
            fl.setPrice(price);
            fl.setStatus(status);
            fl.setSpecies(detail);
            fl.setDetails(species);
            fl.setId(id);
            fl.setimageFlower(image);
            ref.child(id).setValue(fl);
            ListView listView = (ListView) findViewById(R.id.listview_flower_manager);
            listView.setAdapter(new CustomListAdapter_FlowerManager(flower_manager.this,listdata));
        }
    }
    public void add_flower(View v)
    {
        Intent add_flower = new Intent(flower_manager.this, com.flowershop.flowershop.AdminPage.add_flower.class);
        startActivity(add_flower);
    }
    public void delete (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Flower> data = listdata;
        Flower f = data.get(index);
        listdata.remove(f);
        ref.child(f.getId()).removeValue();
        ListView listView = (ListView) findViewById(R.id.listview_flower_manager);
        listView.setAdapter(new CustomListAdapter_FlowerManager(flower_manager.this,listdata));
    }
    public void edit_info_flower (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Flower> data = listdata;
        Flower f = data.get(index);
        id = f.getId();
        image = f.getimageFlower();
        Intent edit = new Intent(this,com.flowershop.flowershop.AdminPage.edit_info_flower.class);
        this.startActivityForResult(edit,rq);

    }
    public void back (View v)
    {
        this.onBackPressed();
    }
}
