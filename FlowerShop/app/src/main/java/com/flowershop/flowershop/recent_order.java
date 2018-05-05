package com.flowershop.flowershop;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.flowershop.flowershop.Adapter.CustomListAdapter_Recent;
import com.flowershop.flowershop.Object.Order;
import com.flowershop.flowershop.Object.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class recent_order extends AppCompatActivity {
    private String userid;
    DatabaseReference ref;
    DatabaseReference ref_getOrder;
    private User usr = new User();
    ArrayList<Order> listdata = new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recent_orders);
        Intent intent = this.getIntent();
        userid=intent.getStringExtra("value1");

        ref = FirebaseDatabase.getInstance().getReference("Users");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    User temp = user_snapshot.getValue(User.class);
                    if(temp.getUserid().compareTo(userid)==0)
                        usr = temp;
                }
                TextView usr_name = (TextView) findViewById(R.id.textView_userid_recentorder);
                TextView usr_point = (TextView) findViewById(R.id.textView_point_recentorder);
                usr_name.setText(usr.getName());
                usr_point.setText(usr.getPoint());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref_getOrder = FirebaseDatabase.getInstance().getReference("Order");
        ref_getOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listdata.clear();
                for(DataSnapshot order_snapshot : dataSnapshot.getChildren())
                {
                    Order temp = order_snapshot.getValue(Order.class);
                    if(temp.getUser_key().compareTo(usr.getKey())==0)
                    {
                        listdata.add(temp);
                    }
                }
                ListView listview = (ListView) findViewById(R.id.listview_recentorder_user);
                listview.setAdapter(new CustomListAdapter_Recent(recent_order.this,listdata));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void delete (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Order> data = listdata;
        Order od = data.get(index);
        listdata.remove(od);
        ref_getOrder.child(od.getKey()).removeValue();
        String money = od.getTotal();
        Double sub_point = Double.parseDouble(money)*10/100;
        String p = usr.getPoint();
        Double point = Double.parseDouble(p);
        ref.child(usr.getKey()).child("point").setValue(String.valueOf(point-sub_point));
    }
    public void success (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Order> data = listdata;
        Order od = data.get(index);
        ref_getOrder.child(od.getKey()).child("status").setValue("DONE!");
        Toast toast= Toast.makeText(recent_order.this, "Đơn hàng đã được hoàn thành\nThank you and See you again", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void show_detail(String _key, String _username, String _phone, String _total, String _address, String _status, String _rname, String _raddress, String _rphone)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.detail_order, null);
        dialogBuilder.setView(dialogView);
        TextView key = (TextView) dialogView.findViewById(R.id.textView_key_detailorder);
        TextView name = (TextView) dialogView.findViewById(R.id.textView_userid_detailorder);
        TextView phone = (TextView) dialogView.findViewById(R.id.textView_sdt_detailorder);
        TextView address = (TextView) dialogView.findViewById(R.id.textView_address_detailorder);
        TextView total = (TextView) dialogView.findViewById(R.id.textView_total_detailorder);
        TextView status = (TextView) dialogView.findViewById(R.id.textView_status_detailorder);
        TextView recive_name = (TextView) dialogView.findViewById(R.id.textView_rname_detailorder);
        TextView recive_address = (TextView) dialogView.findViewById(R.id.textView_raddress_detailorder);
        TextView recive_phone = (TextView) dialogView.findViewById(R.id.textView_rphone_detailorder);
        Button Back = (Button) dialogView.findViewById(R.id.button_back_detailorder);
        key.setText(_key);
        name.setText(_username);
        phone.setText("sdt người đặt: "+_phone);
        address.setText("địa chỉ người đặt: "+_address);
        total.setText("tổng tiền: "+_total);
        status.setText("trạng thái "+_status);
        recive_name.setText("tên người nhận: "+_rname);
        recive_address.setText("địa chỉ người nhận: "+_raddress);
        recive_phone.setText("sdt người nhận: "+_rphone);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    public void show (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Order> data = listdata;
        Order od = data.get(index);

        String id = od.getKey();
        String username = od.getUsername();
        String phone = od.getPhone();
        String address = od.getAddress();
        String total = od.getTotal();
        String status = od.getStatus();
        String rname = od.getRecieve_name();
        String raddress = od.getGetRecieve_address();
        String rphone = od.getRecieve_phone();

        show_detail(id,username,phone,total,address,status,rname,raddress,rphone);
    }
    public void back (View v)
    {
        this.onBackPressed();
    }
}
