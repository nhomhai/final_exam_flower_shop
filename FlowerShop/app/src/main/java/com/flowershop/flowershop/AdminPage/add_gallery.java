package com.flowershop.flowershop.AdminPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.flowershop.flowershop.Object.Flower_image;
import com.flowershop.flowershop.R;
import com.flowershop.flowershop.login_view_flower;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_gallery extends AppCompatActivity {
    DatabaseReference ref;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        ref = FirebaseDatabase.getInstance().getReference("Gallery");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_gallery);

    }
    public void add (View v)
    {
        EditText picture = (EditText) findViewById(R.id.editText_picturename);
        String name = picture.getText().toString();
        Flower_image fl = new Flower_image();
        fl.setImage(name);
        String key = ref.push().getKey();
        fl.setKey(key);
        ref.child(key).setValue(fl);
        Toast toast=Toast.makeText(add_gallery.this,"Added Successfully",   Toast.LENGTH_SHORT);
        toast.show();
    }
    public void back (View v)
    {
        this.onBackPressed();
    }
}
