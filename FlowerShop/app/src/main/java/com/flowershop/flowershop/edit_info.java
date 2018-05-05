package com.flowershop.flowershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class edit_info extends AppCompatActivity {
    private String userid;
    private User usr = new User();
    DatabaseReference ref;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);
        ref = FirebaseDatabase.getInstance().getReference("Users");

        Intent intent = this.getIntent();
        userid=intent.getStringExtra("value1");
    }
    public void update (View v)
    {
        EditText fullname = (EditText) findViewById(R.id.editText_editinfo_fullname);
        EditText address = (EditText) findViewById(R.id.editText_editinfo_address);
        EditText email = (EditText) findViewById(R.id.editText_editinfo_email);
        EditText phone = (EditText) findViewById(R.id.editText_editinfo_phone);

        String text1 = fullname.getText().toString();
        String text2 = address.getText().toString();
        String text3 = email.getText().toString();
        String text4 = phone.getText().toString();

        if(text1.isEmpty()==true||text2.isEmpty()==true||text3.isEmpty()==true||text4.isEmpty()==true)
        {
            showAlertDialog();
        }
        else {
            ref.child(usr.getKey()).child("name").setValue(text1);
            ref.child(usr.getKey()).child("address").setValue(text2);
            ref.child(usr.getKey()).child("email").setValue(text3);
            ref.child(usr.getKey()).child("phone").setValue(text4);
            showAlertDialog_success();
            Intent done = new Intent(edit_info.this,manager_account.class);
            done.putExtra("value1",userid);
            startActivity(done);

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user_snapshot: dataSnapshot.getChildren())
                {
                    User temp = user_snapshot.getValue(User.class);
                    if(temp.getUserid().compareTo(userid)==0)
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
        builder.setMessage("bạn đã cập nhật thông tin thành công");
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
