package com.flowershop.flowershop.AdminPage;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.flowershop.flowershop.Adapter.CustomListAdapter_UserManager;
import com.flowershop.flowershop.Object.User;
import com.flowershop.flowershop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class user_manager extends AppCompatActivity {

    ArrayList<User> userList = new ArrayList<>();
    private DatabaseReference ref;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_manager);
        ref = FirebaseDatabase.getInstance().getReference("Users");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot user_snapshot: dataSnapshot.getChildren()) {
                    User temp_user = user_snapshot.getValue(User.class);
                    userList.add(temp_user);
                }
                ListView listview = (ListView) findViewById(R.id.listview_user_manager);
                listview.setAdapter(new CustomListAdapter_UserManager(user_manager.this,userList));
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
    public void delete_info(View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<User> data = userList;
        User f = data.get(index);
        userList.remove(f);
        ref.child(f.getKey()).removeValue();
    }
    public void show_detail(String _point, String _name, String _id, String _email, String _address, String _per)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.detail_user, null);
        dialogBuilder.setView(dialogView);
        TextView id = (TextView) dialogView.findViewById(R.id.textView_id_detailuser);
        TextView name = (TextView) dialogView.findViewById(R.id.textView_name_detailuser);
        TextView email = (TextView) dialogView.findViewById(R.id.textView_email_detailuser);
        TextView address = (TextView) dialogView.findViewById(R.id.textView_address_detailuser);
        TextView point = (TextView) dialogView.findViewById(R.id.textView_point_detailuser);
        TextView per = (TextView) dialogView.findViewById(R.id.textView_per_detailuser);
        Button Back = (Button) dialogView.findViewById(R.id.button_back_detailuser);
        id.setText(_id);
        name.setText(_name);
        email.setText(_email);
        address.setText(_address);
        point.setText(_point);
        per.setText(_per);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    public void show_info(View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<User> data = userList;
        User temp_usr = data.get(index);

        String id = temp_usr.getUserid();
        String name = temp_usr.getName();
        String email = temp_usr.getEmail();
        String address = temp_usr.getAddress();
        String point = temp_usr.getPoint();
        String per = temp_usr.getKey();
        show_detail(point,name,id,email,address,per);
    }

    public void back (View v)
    {
        this.onBackPressed();
    }

}
