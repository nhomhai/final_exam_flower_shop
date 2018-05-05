package com.flowershop.flowershop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class store_system extends AppCompatActivity{
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_system);
    }
    public void back( View v)
    {
        this.onBackPressed();
    }
}
