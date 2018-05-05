package com.flowershop.flowershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by kenjita.tran on 3/7/18.
 */

public class forget_password extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        Intent intent = this.getIntent();
    }
        public void OK (View v)
        {
            EditText userid = (EditText) findViewById(R.id.editText_userID_forgetpassword);
            String text = userid.getText().toString();
            if(text.isEmpty()==true)
            {
                showAlertDialog("bạn chưa nhập userid");
            }
                // chưa viết hàm checked radiogroup. lười zZ
            else
                showAlertDialog("vui lòng chờ mật khẩu gửi về trong giây lát");
        }


    public void showAlertDialog(String t)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage(t);
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
