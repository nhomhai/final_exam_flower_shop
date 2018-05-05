package com.flowershop.flowershop.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flowershop.flowershop.R;

/**
 * Created by mpxv2 on 4/9/18.
 */

public class admin_manager extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_manager);

    }
    public void flower(View v)
    {
        Intent flower_manager = new Intent(admin_manager.this, com.flowershop.flowershop.AdminPage.flower_manager.class);
        startActivity(flower_manager);
    }
    public void user(View v)
    {
        Intent user_manager = new Intent(admin_manager.this, com.flowershop.flowershop.AdminPage.user_manager.class);
        startActivity(user_manager);
    }
    public void gallery_admin(View v)
    {
        Intent gallery_manager = new Intent(admin_manager.this,com.flowershop.flowershop.AdminPage.gallery_manager.class);
        startActivity(gallery_manager);
    }
    public void order (View v)
    {
        Intent order_manager = new Intent(admin_manager.this,com.flowershop.flowershop.AdminPage.order_manager.class);
        startActivity(order_manager);
    }
    public void exit (View v)
    {
        this.onBackPressed();
    }

}
