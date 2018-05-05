package com.flowershop.flowershop;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kenjita.tran on 3/4/18.
 */

public class about_us extends AppCompatActivity {
    //Typeface face;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        Intent intent = this.getIntent();
        //face = Typeface.createFromAsset(getAssets(),"fonts/BEAUCESC.TTF");

        String value1 = intent.getStringExtra("value1");
        String value2 = intent.getStringExtra("value2");
        TextView tv1 = (TextView) findViewById(R.id.textView_about_us);
        TextView tv2 = (TextView) findViewById(R.id.textView_about_us_contact);
        tv1.setText(value1);
        tv2.setText(value2);
//        tv1.setTypeface(face);
//        tv2.setTypeface(face);
    }

    public void back(View view)
    {
        this.onBackPressed();
    }
}
