package com.flowershop.flowershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.flowershop.flowershop.Object.Order;
import com.flowershop.flowershop.Object.User;
import com.flowershop.flowershop.Payment.visa_mastercard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class cash_on_delivery extends AppCompatActivity{
    private String recive_name;
    private String recive_address;
    private String recive_phone;
    private String userid;
    private User usr = new User();
    private Order ord = new Order();
    DatabaseReference ref;
    DatabaseReference ref_getName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cash_on_delivery);
        ref = FirebaseDatabase.getInstance().getReference("Order");
        ref_getName = FirebaseDatabase.getInstance().getReference("Users");

        Intent intent = this.getIntent();
        userid=intent.getStringExtra("value1");
    }
    @Override
    protected void onStart() {
        super.onStart();
        ref_getName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot usr_snapshot : dataSnapshot.getChildren())
                {
                    User temp = usr_snapshot.getValue(User.class);
                    if(temp.getUserid().compareTo(userid)==0)
                    {
                        usr=temp;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void accept (View v)
    {
        EditText name = (EditText) findViewById(R.id.editText_name_cash);
        EditText address = (EditText) findViewById(R.id.editText_address_cash);
        EditText phone = (EditText) findViewById(R.id.editText_phone_cash);

        recive_name = name.getText().toString();
        recive_address = address.getText().toString();
        recive_phone = phone.getText().toString();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot od_snapshot : dataSnapshot.getChildren())
                {
                    Order od = od_snapshot.getValue(Order.class);
                    if(od.getUsername().compareTo(usr.getName())==0)
                    {
                        ord = od;
                    }
                }
                ord.setRecieve_name(recive_name);
                ord.setRecieve_phone(recive_phone);
                ord.setGetRecieve_address(recive_address);
                ref.child(ord.getKey()).setValue(ord);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        showAlertDialog("Thanh toán thành công! \n Thank you");
        Intent goback = new Intent(cash_on_delivery.this,login_view_flower.class);
        goback.putExtra("value1",userid);
        startActivity(goback);
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
}
