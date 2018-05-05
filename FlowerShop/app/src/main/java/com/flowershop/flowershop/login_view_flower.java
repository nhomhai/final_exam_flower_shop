package com.flowershop.flowershop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flowershop.flowershop.Adapter.CustomGridviewAdapter;
import com.flowershop.flowershop.AdminPage.admin_manager;
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

/**
 * Created by mpxv2 on 3/31/18.
 */

public class login_view_flower extends AppCompatActivity {
    DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference ref_getName;
    static Cart_Object co = new Cart_Object();
    private static String userid;
    private static String username;
    ArrayList<Flower> listdata = new ArrayList<>();
    ArrayList<Flower> list_best = new ArrayList<>();
    ArrayList<Flower> list_dried = new ArrayList<>();
    ArrayList<Flower> list_single = new ArrayList<>();
    private static User user = new User();

    public void showAlertDialog(String s)
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage(s);
        builder.setCancelable(false);
        builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view_flower);

        Intent receive = this.getIntent();
        userid = receive.getStringExtra("value1");

        ref = FirebaseDatabase.getInstance().getReference("Flower");
        ref2 = FirebaseDatabase.getInstance().getReference("Cart");
        ref_getName = FirebaseDatabase.getInstance().getReference("Users");

        final String choice3[] = {"","Home","Administrator","About us","Manager Account","Maps","Logout"};
        final String choice5[] = {"CATEGORY","Best Seller Flower","Single Flower","Dried Flower"};
        final Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        final Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);

        class activity implements android.widget.AdapterView.OnItemSelectedListener {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String text = spinner3.getSelectedItem().toString();
                if(text.compareTo("Home")==0)
                {
                    Intent home = new Intent(login_view_flower.this,login_home.class);
                    startActivity(home);
                }
                if(text.compareTo("Administrator")==0)
                {
                    if(userid.compareTo("admin")==0) {
                        Intent admin = new Intent(login_view_flower.this, admin_manager.class);
                        startActivity(admin);
                    }
                    else
                        showAlertDialog("NO ACCESS PERMISSIONS!!!\nYou are not an Administrator");
                }
                if(text.compareTo("About us")==0)
                {
                    Intent about = new Intent(login_view_flower.this,about_us.class);
                    about.putExtra("value1","the application was designed and developed by group 2, Class: D14CQAT01-N ");
                    about.putExtra("value2","contact: 0169 333 8069");
                    startActivity(about);
                }
                if(text.compareTo("Manager Account")==0)
                {
                    Intent manager = new Intent(login_view_flower.this,manager_account.class);
                    manager.putExtra("value1",userid);
                    startActivity(manager);
                }
                if(text.compareTo("Maps")==0)
                {
                    Intent maps = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Học+Viện+Công+Nghệ+Bưu+Chính+Viễn+Thông+Cơ+Sở+2/@10.848037,106.7843943,17z/data=!3m1!4b1!4m5!3m4!1s0x31752772b245dff1:0xb838977f3d419d!8m2!3d10.848037!4d106.786583"));
                    startActivity(maps);
                }
                if(text.compareTo("Logout")==0)
                {
                    Intent logout = new Intent(login_view_flower.this, MainActivity.class);
                    startActivity(logout);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice3);
        spinner3.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setOnItemSelectedListener(new activity());

        class activity2 implements android.widget.AdapterView.OnItemSelectedListener{
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String text = spinner5.getSelectedItem().toString();

                if(text.compareTo("Best Seller Flower")==0)
                {
                    for (Flower temp : listdata) {
                        if(temp.getSpecies().toString().compareTo("best seller")==0)
                        {
                            list_best.add(temp);
                        }
                    }
                    GridView gridView = (GridView) findViewById(R.id.GridView2);
                    gridView.setAdapter(new CustomGridviewAdapter(login_view_flower.this,list_best));

                }
                if(text.compareTo("Dried Flower")==0)
                {
                    for (Flower temp : listdata) {
                        if(temp.getSpecies().toString().compareTo("Dried")==0)
                        {
                            list_dried.add(temp);
                        }
                    }
                    GridView gridView = (GridView) findViewById(R.id.GridView2);
                    gridView.setAdapter(new CustomGridviewAdapter(login_view_flower.this,list_dried));
                }
                if(text.compareTo("Single Flower")==0)
                {
                    for (Flower temp : listdata) {
                        if(temp.getSpecies().toString().compareTo("Single")==0)
                        {
                            list_single.add(temp);
                        }
                    }
                    GridView gridView = (GridView) findViewById(R.id.GridView2);
                    gridView.setAdapter(new CustomGridviewAdapter(login_view_flower.this,list_single));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice5);
        spinner5.setAdapter(adapter2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setOnItemSelectedListener(new activity2());
    }
    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void  onDataChange(DataSnapshot dataSnapshot) {
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
        GridView gridView = (GridView) findViewById(R.id.GridView2);
        gridView.setAdapter(new CustomGridviewAdapter(this,listdata));

        ref_getName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    User usr = user_snapshot.getValue(User.class);
                    if(userid.compareTo(usr.getUserid())==0)
                    {
                        username = usr.getName();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void add_to_cart (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Flower> data = listdata;
        Flower f = data.get(index);

        String image = f.getimageFlower();
        String name = f.getFlowername();
        String price = f.getPrice();

        Cart_Object item =  new Cart_Object();
        item.setImage(image);
        item.setFlowername(name);
        item.setPrice(price);
        item.setUserid(userid);
        item.setSl("1");
        item.setName(username);

        String id = ref2.push().getKey();
        item.setKey(id);
        ref2.child(id).setValue(item);
        Toast toast=Toast.makeText(login_view_flower.this,"Added Successfully",   Toast.LENGTH_SHORT);
        toast.show();
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
        Context context=login_view_flower.this;
        Integer index = (Integer)v.getTag();
        ArrayList<Flower> data = listdata;
        Flower f = data.get(index);
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
    public void go_to_cart(View v)
    {

        Intent intent = new Intent(login_view_flower.this,cart.class);
        intent.putExtra("value1",userid);
        startActivity(intent);
    }
    public void search (View v)
    {
        Intent search = new Intent(login_view_flower.this,Search.class);
        search.putExtra("value1",userid);
        startActivity(search);
    }
}
