package com.flowershop.flowershop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import com.flowershop.flowershop.Adapter.CustomGridviewAdapter;
import com.flowershop.flowershop.Object.Flower;
import com.flowershop.flowershop.Object.getFlower;
import com.flowershop.flowershop.SQLite.Database_Manager;
import com.flowershop.flowershop.SQLite.FlowerAction;
import com.flowershop.flowershop.SQLite.MySqlHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/**
 * Created by kenjita.tran on 3/7/18.
 */

public class view_flower extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<Flower> listdata = new ArrayList<>();
    ArrayList<Flower> list_best = new ArrayList<>();
    ArrayList<Flower> list_dried = new ArrayList<>();
    ArrayList<Flower> list_single = new ArrayList<>();

    public void showAlertDialog(String s)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage(s);
        builder.setCancelable(false);
        builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_flower);

        Database_Manager db = new Database_Manager(view_flower.this);
        ref = FirebaseDatabase.getInstance().getReference("Flower");

        final String choice2[] = {"","Home","About us","Manager Account","Maps","Login"};
        final String choice6[] = {"CATEGORY","Best Seller Flower","Single Flower","Dried Flower"};
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        class activity2 implements android.widget.AdapterView.OnItemSelectedListener{
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                String text = spinner6.getSelectedItem().toString();
                if(text.compareTo("Best Seller Flower")==0)
                {
                    for (Flower temp : listdata) {
                        if(temp.getSpecies().toString().compareTo("best seller")==0)
                        {
                            list_best.add(temp);
                        }
                    }
                    GridView gridView = (GridView) findViewById(R.id.GridView);
                    gridView.setAdapter(new CustomGridviewAdapter(view_flower.this,list_best));

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
                    gridView.setAdapter(new CustomGridviewAdapter(view_flower.this,list_dried));
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
                    gridView.setAdapter(new CustomGridviewAdapter(view_flower.this,list_single));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice6);
        spinner6.setAdapter(adapter2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setOnItemSelectedListener(new activity2());
        class activity implements android.widget.AdapterView.OnItemSelectedListener {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String text = spinner2.getSelectedItem().toString();
                if(text.compareTo("Home")==0)
                {
                    Intent home = new Intent(view_flower.this,MainActivity.class);
                    startActivity(home);
                }
                if(text.compareTo("About us")==0)
                {
                    Intent about = new Intent(view_flower.this,about_us.class);
                    about.putExtra("value1","the application was designed and developed by group 2, Class: D14CQAT01-N ");
                    about.putExtra("value2","contact: 0169 333 8069");
                    startActivity(about);
                }
                if(text.compareTo("Manager Account")==0)
                {
                    showAlertDialog("bạn phải đăng nhập để thực hiện chức năng này");
                }
                if(text.compareTo("Maps")==0)
                {
                    Intent maps = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Học+Viện+Công+Nghệ+Bưu+Chính+Viễn+Thông+Cơ+Sở+2/@10.848037,106.7843943,17z/data=!3m1!4b1!4m5!3m4!1s0x31752772b245dff1:0xb838977f3d419d!8m2!3d10.848037!4d106.786583"));
                    startActivity(maps);
                }
                if(text.compareTo("Login")==0)
                {
                    Intent login = new Intent(view_flower.this, login.class);
                    startActivity(login);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice2);
        spinner2.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setOnItemSelectedListener(new activity());
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void  onDataChange(DataSnapshot dataSnapshot) {
                listdata.clear();
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    Flower flower = user_snapshot.getValue(Flower.class);
                    listdata.add(flower);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final GridView gridView = (GridView) findViewById(R.id.GridView);
        gridView.setAdapter(new CustomGridviewAdapter(this,listdata));

    }

    public void go_to_cart(View v)
    {
        showAlertDialog("Bạn phải đăng nhập để thực hiện chức năng này");
    }
    public void showDetail(Context aContext, int image, String name, String price, String status, String detail, String species)
    {

        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
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

        final android.app.AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    public int getMipmapResIdByName(String resName,Context context)
    {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    public void detail (View v)
    {
        Context context=view_flower.this;
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
    public void add_to_cart (View v)
    {
        showAlertDialog("bạn phải đăng nhập để thực hiện chức năng này");
    }
}
