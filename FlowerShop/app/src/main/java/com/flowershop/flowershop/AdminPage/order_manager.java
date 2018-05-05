package com.flowershop.flowershop.AdminPage;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.flowershop.flowershop.Adapter.CustomListView_OrderManager;
import com.flowershop.flowershop.Object.Order;
import com.flowershop.flowershop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class order_manager extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<Order> listdata = new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_manager);
        ref = FirebaseDatabase.getInstance().getReference("Order");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listdata.clear();
                for(DataSnapshot order_snapshot: dataSnapshot.getChildren()) {
                    Order temp = order_snapshot.getValue(Order.class);
                    listdata.add(temp);
                }
                ListView listview = (ListView) findViewById(R.id.listview_order_manager);
                listview.setAdapter(new CustomListView_OrderManager(order_manager.this,listdata));
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
        ref.child(od.getKey()).removeValue();
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
}
