package com.flowershop.flowershop.Payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flowershop.flowershop.R;

public class ATM extends AppCompatActivity {
    private String userid;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        Intent recive = this.getIntent();
        userid = recive.getStringExtra("value1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atm_payment);
    }
    public void donga(View v)
    {
        Intent donga_b = new Intent(ATM.this,ATM_Payment.class);
        donga_b.putExtra("value1",userid);
        startActivity(donga_b);
    }
    public void vcb(View v)
    {
        Intent vcb_b = new Intent(ATM.this,ATM_Payment.class);
        vcb_b.putExtra("value1",userid);
        startActivity(vcb_b);
    }
    public void vtb(View v)
    {
        Intent vtb_b = new Intent(ATM.this,ATM_Payment.class);
        vtb_b.putExtra("value1",userid);
        startActivity(vtb_b);
    }
    public void vib(View v)
    {
        Intent vib_b = new Intent(ATM.this,ATM_Payment.class);
        vib_b.putExtra("value1",userid);
        startActivity(vib_b);
    }
    public void exim(View v)
    {
        Intent exim_b = new Intent(ATM.this,ATM_Payment.class);
        exim_b.putExtra("value1",userid);
        startActivity(exim_b);
    }
    public void vp(View v)
    {
        Intent vp_b = new Intent(ATM.this,ATM_Payment.class);
        vp_b.putExtra("value1",userid);
        startActivity(vp_b);
    }
    public void sacom(View v)
    {
        Intent sacom_b = new Intent(ATM.this,ATM_Payment.class);
        sacom_b.putExtra("value1",userid);
        startActivity(sacom_b);
    }
    public void hdb(View v)
    {
        Intent hd_b = new Intent(ATM.this,ATM_Payment.class);
        hd_b.putExtra("value1",userid);
        startActivity(hd_b);
    }
    public void mrt(View v)
    {
        Intent mrt_b = new Intent(ATM.this,ATM_Payment.class);
        mrt_b.putExtra("value1",userid);
        startActivity(mrt_b);
    }
    public void tecg(View v)
    {
        Intent tech_b = new Intent(ATM.this,ATM_Payment.class);
        tech_b.putExtra("value1",userid);
        startActivity(tech_b);
    }
}
