package com.flowershop.flowershop.AdminPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.flowershop.flowershop.Object.Flower;
import com.flowershop.flowershop.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mpxv2 on 4/9/18.
 */

public class add_flower extends AppCompatActivity {
    DatabaseReference ref;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flower);
        Intent intent = this.getIntent();
        ref = FirebaseDatabase.getInstance().getReference("Flower");
    }

    public void add (View v)
    {
        EditText name = (EditText) findViewById(R.id.edittext_name_addflower);
        EditText price = (EditText) findViewById(R.id.edittext_price_addflower);
        EditText image = (EditText) findViewById(R.id.edittext_image_addflower);
        EditText status = (EditText) findViewById(R.id.edittext_status_addflower);
        EditText species = (EditText) findViewById(R.id.edittext_species_addflower);
        EditText detail = (EditText) findViewById(R.id.edittext_detail_addflower);

        String name_text = name.getText().toString();
        String price_text = price.getText().toString();
        String image_text = image.getText().toString();
        String status_text = status.getText().toString();
        String species_text = species.getText().toString();
        String detail_text = detail.getText().toString();

        final Flower flower = new Flower();
        flower.setFlowername(name_text);
        flower.setimageFlower(image_text);
        flower.setPrice(price_text);
        flower.setStatus(status_text);
        flower.setSpecies(species_text);
        flower.setDetails(detail_text);
        if(name_text.isEmpty()==true||price_text.isEmpty()==true||image_text.isEmpty()==true||status_text.isEmpty()==true||species_text.isEmpty()==true)
        {
            showAlertDialog("bạn cần điền đầy đủ thông tin hoa!");
        }
        else
        {
            String id = ref.push().getKey().toString();
            flower.setId(id);
            ref.child(id).setValue(flower);
            showAlertDialog("thêm hoa thành công!");
            Intent sucess = new Intent(add_flower.this, flower_manager.class);
            startActivity(sucess);
        }
    }
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
    public void back (View v)
    {
        this.onBackPressed();
    }
}
