package com.flowershop.flowershop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.flowershop.flowershop.Adapter.CustomListAdapter_Search;
import com.flowershop.flowershop.Object.Cart_Object;
import com.flowershop.flowershop.Object.Flower;
import com.flowershop.flowershop.Object.User;
import com.flowershop.flowershop.Object.getFlower;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener {
    static List<Flower> data ;// list chua data
    static List<Flower> SearchList;// list chua flower search
    static Flower fl;
    static String seacrh;
    static String userid;
    static String username;
    SearchView searchView;
    ListView listSearch;
    CustomListAdapter_Search adapter;
    DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference ref_getName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        listSearch = (ListView) findViewById(R.id.listSearch);
        searchView = (SearchView)findViewById(R.id.search_view);
        SearchList = new ArrayList<>();
        data = new ArrayList<>();
        searchView.setOnQueryTextListener(Search.this);

        Intent receive = this.getIntent();
        userid = receive.getStringExtra("value1");

        ref = FirebaseDatabase.getInstance().getReference("Flower");
        ref2 = FirebaseDatabase.getInstance().getReference("Cart");
        ref_getName = FirebaseDatabase.getInstance().getReference("Users");

        ref_getName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    User usr = user_snapshot.getValue(User.class);
                    if(usr.getUserid().compareTo(userid)==0)
                    {
                        username = usr.getName();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        data = getFlower.Get();
        SearchList.addAll(data);
        adapter = new CustomListAdapter_Search(getBaseContext(),R.layout.search_item, SearchList);
        listSearch.setAdapter(adapter);
    }
    public void showDetail(Context aContext, int image, String name, String price, String status, String detail, String species)
    {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_detail, null);
        dialogBuilder.setView(dialogView);

        TextView Name = (TextView) dialogView.findViewById(R.id.textView_name_customdetail);
        TextView Price = (TextView) dialogView.findViewById(R.id.textView_price_customdetail);
        TextView Status = (TextView) dialogView.findViewById(R.id.textView_status_customdetail);
        TextView Species = (TextView) dialogView.findViewById(R.id.textView_species_customdetail);
        ImageView img = (ImageView) dialogView.findViewById(R.id.imageView_img_customdetail);
        TextView Detail = (TextView) dialogView.findViewById(R.id.textView_detail_customdetail);
        Button Back = (Button) dialogView.findViewById(R.id.button_dissmiss);

        Name.setText(name);
        Price.setText(price);
        Status.setText(status);
        Species.setText(species);
        Detail.setText(detail);
        img.setImageResource(image);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void detail (View v)
    {
        Context context=Search.this;
        Integer index = (Integer)v.getTag();
        List<Flower> listdata = data;
        Flower f = listdata.get(index);
        String name = f.getFlowername();
        String price = f.getPrice();
        String status = f.getStatus();
        String detail = f.getDetails();
        String species = f.getSpecies();
        int imageId = this.getMipmapResIdByName(f.getimageFlower(),context);
        showDetail(context,imageId,name,price,status,detail,species);

    }
    public int getMipmapResIdByName(String resName,Context context)
    {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    /*public void buy (View v)
        {
            int index = (Integer)v.getTag();
            Flower temp = data.get(index);
            Cart_Object item =  new Cart_Object();

            item.setImage(temp.getimageFlower());
            item.setFlowername(temp.getFlowername());
            item.setPrice(temp.getPrice());
            item.setUserid(userid);
            item.setSl("1");
            item.setName(username);

            String id = ref2.push().getKey();
            item.setKey(id);
            ref2.child(id).setValue(item);
            Toast toast=Toast.makeText(Search.this,"Added Successfully",   Toast.LENGTH_SHORT);
            toast.show();
        }*/
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        seacrh = newText;
        adapter.filter(seacrh);
        return false;
    }
}
