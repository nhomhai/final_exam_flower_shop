package com.flowershop.flowershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.flowershop.flowershop.Object.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by kenjita.tran on 3/5/18.
 */

public class change_password extends AppCompatActivity {
    private static String _userid;
    private User usr = new User();
    private DatabaseReference ref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        Intent intent = this.getIntent();
        _userid=intent.getStringExtra("value1");
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
                    if(temp.getUserid().compareTo(_userid)==0)
                        usr = temp;
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
    public void showAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage("bạn cần điền đầy đủ thông tin!");
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
    public void showAlertDialog_success()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage("bạn đã đổi mật khẩu thành công");
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
    public void showAlertDialog_error()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage("mật khẩu mới không trùng nhau!");
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
    public void change (View v)
    {
        EditText userid = (EditText) findViewById(R.id.editText_userID_changepassword);
        EditText pass = (EditText) findViewById(R.id.editText_password_changepassword);
        EditText newpass = (EditText) findViewById(R.id.editText_newpassword_changepassword);
        EditText retype = (EditText) findViewById(R.id.editText_retypenew_changepassword);

        String text1 = userid.getText().toString();
        String text2 = pass.getText().toString();
        String text3 = newpass.getText().toString();
        String text4 = retype.getText().toString();


        if(text1.isEmpty()==true||text2.isEmpty()==true||text3.isEmpty()==true||text4.isEmpty()==true)
        {
            showAlertDialog();
        }
        else
        {
            if(text3.compareTo(text4)!=0)
            {
                showAlertDialog_error();
                newpass.setText(null);
                retype.setText(null);
            }
            else {
                ref.child(usr.getKey()).child("password").setValue(text3);
                showAlertDialog_success();
                Intent done = new Intent(change_password.this,login_home.class);
                done.putExtra("value1",_userid);
                startActivity(done);
            }
        }
    }
}
