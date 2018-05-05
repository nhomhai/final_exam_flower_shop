package com.flowershop.flowershop.AdminPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.flowershop.flowershop.AdminPage.flower_manager;
import com.flowershop.flowershop.R;

/**
 * Created by mpxv2 on 4/21/18.
 */

public class edit_info_flower extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info_flower);


    }

    public void update(View v)
    {

        EditText name = (EditText) findViewById(R.id.editText_editflower_name);
        EditText price = (EditText) findViewById(R.id.editText_editflower_price);
        EditText status = (EditText) findViewById(R.id.editText_editflower_status);
        EditText detail = (EditText) findViewById(R.id.editText_editflower_detail);
        EditText species = (EditText) findViewById(R.id.editText_editflower_species);

        String text_name = name.getText().toString();
        String text_price = price.getText().toString();
        String text_stt = status.getText().toString();
        String text_detail = detail.getText().toString();
        String text_species = species.getText().toString();

        Intent data = new Intent(this,flower_manager.class);

        data.putExtra("name",text_name);
        data.putExtra("price",text_price);
        data.putExtra("status",text_stt);
        data.putExtra("detail",text_detail);
        data.putExtra("species",text_species);

        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
