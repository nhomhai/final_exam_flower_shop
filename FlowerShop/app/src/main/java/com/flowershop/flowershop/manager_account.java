package com.flowershop.flowershop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.flowershop.flowershop.Object.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by kenjita.tran on 3/4/18.
 */

public class manager_account extends AppCompatActivity {
    private String userid;
    DatabaseReference ref;
    private User usr = new User();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mange_account);
        Intent intent = this.getIntent();
        userid = intent.getStringExtra("value1");
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
                    {
                        usr = temp;
                    }
                    TextView name = (TextView) findViewById(R.id.textView_manageraccount_fullname);
                    TextView uid = (TextView) findViewById(R.id.textView_manageraccount_userid);
                    TextView email = (TextView) findViewById(R.id.textView_manageraccount_email);
                    TextView address = (TextView) findViewById(R.id.textView_manageraccount_address);
                    TextView phone = (TextView) findViewById(R.id.textView_manageraccount_phone);
                    TextView point = (TextView) findViewById(R.id.textView_manageraccount_point);

                    name.setText(usr.getName());
                    uid.setText(usr.getUserid());
                    email.setText(usr.getEmail());
                    address.setText(usr.getAddress());
                    point.setText(usr.getPoint());
                    phone.setText(usr.getPhone());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void back (View v)
    {
        this.onBackPressed();
    }
    public void changepass (View v)
    {
        Intent changepass = new Intent(manager_account.this,change_password.class);
        changepass.putExtra("value1",userid);
        startActivity(changepass);
    }
    public void edit (View v)
    {
        Intent edit = new Intent(manager_account.this,edit_info.class);
        edit.putExtra("value1",userid);
        startActivity(edit);
    }
    public void recent (View v)
    {
        Intent recent = new Intent(manager_account.this,recent_order.class);
        recent.putExtra("value1",userid);
        startActivity(recent);
    }
}
