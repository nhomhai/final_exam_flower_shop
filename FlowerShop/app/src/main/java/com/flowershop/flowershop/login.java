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

public class login extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<User> list_user = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ref = FirebaseDatabase.getInstance().getReference("Users");
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
    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    User user = user_snapshot.getValue(User.class);
                    list_user.add(user);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void login (View v)
    {
        User user = new User();
        final String text1;
        final String text2;
        String id;
        String password;
        EditText userid = (EditText) findViewById(R.id.editText_userID_login);
        EditText pass = (EditText) findViewById(R.id.editText_password_login);
        text1 = userid.getText().toString();
        text2 = pass.getText().toString();
        int dem = 0;
        if(text1.isEmpty()==true||text2.isEmpty()==true)
        {
            showAlertDialog("yêu cầu nhập đầy đủ UserID và Password");
            userid.setText(null);
            pass.setText(null);
        }
        else
        {
            for (User user_test : list_user) {
                user=user_test;
                if (text1.compareTo(user_test.getUserid()) == 0 && text2.compareTo(user_test.getPassword()) == 0) {
                    dem++;
                    showAlertDialog("bạn đã đăng nhập thành công");
                    Intent gotohome = new Intent(login.this,login_home.class);
                    gotohome.putExtra("value1",text1);
                    startActivity(gotohome);
                    break;
                }
            }
            if (dem == 0) {
                showAlertDialog("UserID hoặc Password không đúng");
            }
        }
    }
    public void back (View v)
    {
        this.onBackPressed();
    }

    public void register(View v)
    {
        Intent register_activity = new Intent(login.this,register.class);
        startActivity(register_activity);
    }
    public void forget_password(View v)
    {
        Intent forget = new Intent(login.this,forget_password.class);
        startActivity(forget);
    }
}
