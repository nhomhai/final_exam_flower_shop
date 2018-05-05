package com.flowershop.flowershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.flowershop.flowershop.Object.User;
import com.flowershop.flowershop.SQLite.Database_Manager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by kenjita.tran on 3/5/18.
 */

public class register extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<User> usr_list = new ArrayList<>();

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ref = FirebaseDatabase.getInstance().getReference("Users");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot usr_snapshot : dataSnapshot.getChildren())
                {
                    User temp = usr_snapshot.getValue(User.class);
                    usr_list.add(temp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void back(View v)
    {
        this.onBackPressed();
    }
    public void register(View v)
    {
        EditText userid = (EditText) findViewById(R.id.editText_register_userID);
        EditText pass = (EditText) findViewById(R.id.editText_register_password);
        EditText address = (EditText) findViewById(R.id.editText_register_address);
        EditText email = (EditText) findViewById(R.id.editText_register_email);
        EditText fullname = (EditText) findViewById(R.id.editText_register_fullname);
        EditText phone = (EditText) findViewById(R.id.editText_register_phone);

        String userid_text = userid.getText().toString();
        String password_text = pass.getText().toString();
        String address_text = address.getText().toString();
        String email_text = email.getText().toString();
        String fullname_text = fullname.getText().toString();
        String phone_text = phone.getText().toString();

        final User user = new User();
        user.setUserid(userid_text);
        user.setPassword(password_text);
        user.setAddress(address_text);
        user.setEmail(email_text);
        user.setName(fullname_text);
        user.setPhone(phone_text);
        user.setPermisson("0");
        user.setPoint("0");
        if(userid_text.isEmpty()==true||password_text.isEmpty()==true||address_text.isEmpty()==true||email_text.isEmpty()==true||fullname_text.isEmpty()==true||phone_text.isEmpty()==true)
        {
            showAlertDialog("bạn cần điền đầy đủ thông tin");
        }
        else {
            int count = 0;
            for(User temp : usr_list)
            {
                if(userid_text.compareTo(temp.getUserid())==0)
                {
                    count++;
                }
            }
            if(count==0) {
                String id = ref.push().getKey();
                user.setKey(id);
                ref.child(id).setValue(user);
                showAlertDialog("bạn đã đăng kí tài khoản thành công");
                Intent login = new Intent(register.this, login.class);
                startActivity(login);
            }
            else
                showAlertDialog("tên đăng nhập đã tồn tại");
        }
    }
}
