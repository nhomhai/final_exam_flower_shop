package com.flowershop.flowershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flowershop.flowershop.Adapter.CustomListAdapter_Cart;
import com.flowershop.flowershop.Object.Cart_Object;
import com.flowershop.flowershop.Object.Order;
import com.flowershop.flowershop.Object.OrderDetails;
import com.flowershop.flowershop.Object.User;
import com.flowershop.flowershop.Payment.ATM;
import com.flowershop.flowershop.Payment.visa_mastercard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kenjita.tran on 3/9/18.
 */

public class cart extends AppCompatActivity {
    private static String username;
    private static String userid;
    private static String address;
    private static String phone;
    private static String user_key;
    private static Double point;
    private static double money=0;
    NumberFormat formatter = new DecimalFormat("#0.000");
    private DatabaseReference ref;
    private DatabaseReference ref_paynow;
    private DatabaseReference ref_getName;
    private DatabaseReference ref_orderdetail;
    private DatabaseReference delete;
    private ArrayList<Cart_Object> data = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        Intent receive = this.getIntent();
        userid = receive.getStringExtra("value1");

        ref = FirebaseDatabase.getInstance().getReference("Cart");
        ref_getName = FirebaseDatabase.getInstance().getReference("Users");

        final String choice3[] = {"Chose payment method", "VISA/MasterCard", "ATM Internal", "Cash on Delivery"};
        final Spinner spinner_cart = (Spinner) findViewById(R.id.spinner_cart);

        class activity implements android.widget.AdapterView.OnItemSelectedListener {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = spinner_cart.getSelectedItem().toString();

                if (text.compareTo("VISA/MasterCard") == 0) {
                    Intent visa = new Intent(cart.this, visa_mastercard.class);
                    visa.putExtra("value1",userid);
                    startActivity(visa);
                }
                if (text.compareTo("ATM Internal")==0){
                    Intent atm = new Intent(cart.this,ATM.class);
                    atm.putExtra("value1",userid);
                    startActivity(atm);
                }
                if(text.compareTo("Cash on Delivery")==0)
                {
                    Intent cash = new Intent(cart.this,cash_on_delivery.class);
                    cash.putExtra("value1",userid);
                    startActivity(cash);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice3);
        spinner_cart.setAdapter(adapter1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cart.setOnItemSelectedListener(new activity());

    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    data.clear();
                    for(DataSnapshot cart_snapshot : dataSnapshot.getChildren())
                    {
                        Cart_Object obj_temp = cart_snapshot.getValue(Cart_Object.class);
                        if(obj_temp.getUserid().compareTo(userid)==0) {
                            data.add(obj_temp);
                            double tien = Double.parseDouble(obj_temp.getPrice());
                            int sl = Integer.parseInt(obj_temp.getSl());
                            double total = tien*sl;
                            money += total;
                        }
                    }
                    ListView listView = (ListView) findViewById(R.id.listview_cart);
                    listView.setAdapter(new CustomListAdapter_Cart(cart.this,data));
                    TextView total = (TextView) findViewById(R.id.textView_total_cart);
                    total.setText(formatter.format(money));
                    point = money*10/100;
                money = 0;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref_getName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    User temp = user_snapshot.getValue(User.class);
                    if(userid.compareTo(temp.getUserid())==0)
                    {
                        username = temp.getName();
                        address = temp.getAddress();
                        phone = temp.getPhone();
                        user_key = temp.getKey();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void remove (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Cart_Object> list = data;
        Cart_Object temp = list.get(index);
        data.remove(temp);
        ref.child(temp.getKey()).removeValue();
    }
    public void shopping (View v)
    {
        Intent shopping = new Intent(cart.this,login_view_flower.class);
        shopping.putExtra("value1",userid);
        startActivity(shopping);
    }
    public void add (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Cart_Object> list = data;
            Cart_Object temp = list.get(index);
        int sl = Integer.parseInt(temp.getSl());
        sl++;
        temp.setSl(String.valueOf(sl));
        ref.child(temp.getKey()).child("sl").setValue(String.valueOf(sl));

    }
    public void sub (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Cart_Object> list = data;
        Cart_Object temp = list.get(index);
        int sl = Integer.parseInt(temp.getSl());
        if(sl==1)
        {
            showAlertDialog("không thể giảm thêm");
        }
        else {
            sl--;
            temp.setSl(String.valueOf(sl));
            ref.child(temp.getKey()).child("sl").setValue(String.valueOf(sl));
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
    public void paynow (View v)
    {
        ref_getName = FirebaseDatabase.getInstance().getReference("Users");
        ref_paynow = FirebaseDatabase.getInstance().getReference("Order");
        ref_orderdetail = FirebaseDatabase.getInstance().getReference("OrderDetail");
        delete = FirebaseDatabase.getInstance().getReference();
        TextView total = (TextView) findViewById(R.id.textView_total_cart);
        Order order = new Order();

        String id = ref_paynow.push().getKey();
        order.setTotal(total.getText().toString());
        order.setUsername(username);
        order.setAddress(address);
        order.setKey(id);
        order.setPhone(phone);
        order.setStatus("đơn hàng được xác nhận");
        SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date time = new Date();
        order.setTime(format_time.format(time.getTime()));
        order.setUser_key(user_key);
        ref_paynow.child(id).setValue(order);
        delete.child("Cart").removeValue();
//        ref.addValueEventListener(new ValueEventListener() {
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot cart_snapshot : dataSnapshot.getChildren())
//                {
//                    Cart_Object temp = cart_snapshot.getValue(Cart_Object.class);
//                    if(temp.getUserid().compareTo(userid)==0)
//                    {
//                        ref.child(temp.getKey()).removeValue();
//                    }
//                }
//            }
//
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//            });

        ref_getName.child(user_key).child("point").setValue(String.valueOf(point));
        for(Cart_Object temp : data)
        {
            OrderDetails od = new OrderDetails();
            od.setPrice(temp.getPrice());
            od.setSL(temp.getSl());
            od.setImage(temp.getImage());
            od.setFlowername(temp.getFlowername());
            od.setOrderID(id);
            String key_orderdetail = ref_orderdetail.push().getKey();
            od.setKey(key_orderdetail);
            ref_orderdetail.child(key_orderdetail).setValue(od);
        }
        Toast toast=Toast.makeText(cart.this,"Đơn hàng đã được lưu \n Mời chọn hình thức thanh toán",   Toast.LENGTH_SHORT);
        toast.show();
    }
}
