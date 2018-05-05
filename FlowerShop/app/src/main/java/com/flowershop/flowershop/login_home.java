package com.flowershop.flowershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;

import com.flowershop.flowershop.AdminPage.admin_manager;
import com.flowershop.flowershop.Object.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by mpxv2 on 3/31/18.
 */

public class login_home extends AppCompatActivity {
    DatabaseReference ref;
    private static String userid;
    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;
    ArrayList<User> listdata = new ArrayList<>();


    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();

        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        Log.i("AndroidVideoView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }
    //-------------------------------------------------------------------------
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) { // lưu lại vị trí play
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("CurrentPosition", videoView.getCurrentPosition());
        videoView.pause();
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        position = savedInstanceState.getInt("CurrentPosition");
        videoView.seekTo(position);
    }
    //-------------------------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void  onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    User user = user_snapshot.getValue(User.class);
                    listdata.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_home);
        videoView = (VideoView) findViewById(R.id.videoView3_2);
        mediaController = new MediaController(login_home.this);
        ref = FirebaseDatabase.getInstance().getReference("User");

        Intent receive = this.getIntent();
        userid = receive.getStringExtra("value1");

        int id = this.getRawResIdByName("myvideo");
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.requestFocus();
        videoView.start();

        final String choice4[] = {"","Home","Administrator","About us","Manager Account","Maps","Logout"};
        final Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        class activity implements android.widget.AdapterView.OnItemSelectedListener {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String text = spinner4.getSelectedItem().toString();
                if(text.compareTo("Home")==0)
                {
                    Intent home = new Intent(login_home.this,login_home.class);
                    startActivity(home);
                }
                if(text.compareTo("Administrator")==0)
                {
                    if(userid.compareTo("admin")==0)
                    {
                        Intent admin = new Intent(login_home.this, admin_manager.class);
                        startActivity(admin);
                    }
                    else
                        showAlertDialog("NO ACCESS PERMISSIONS!!!\nYou are not an Administrator");
                }
                if(text.compareTo("About us")==0)
                {
                    Intent about = new Intent(login_home.this,about_us.class);
                    about.putExtra("value1","the application was designed and developed by group 2, Class: D14CQAT01-N ");
                    about.putExtra("value2","contact: 0169 333 8069");
                    startActivity(about);
                }
                if(text.compareTo("Manager Account")==0)
                {
                    Intent manager = new Intent(login_home.this,manager_account.class);
                    manager.putExtra("value1",userid);
                    startActivity(manager);
                }
                if(text.compareTo("Maps")==0)
                {
                    Intent maps = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/maps/place/Học+Viện+Công+Nghệ+Bưu+Chính+Viễn+Thông+Cơ+Sở+2/@10.848037,106.7843943,17z/data=!3m1!4b1!4m5!3m4!1s0x31752772b245dff1:0xb838977f3d419d!8m2!3d10.848037!4d106.786583"));
                    startActivity(maps);
                }
                if(text.compareTo("Logout")==0)
                {
                    Intent logout = new Intent(login_home.this, MainActivity.class);
                    startActivity(logout);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice4);
        spinner4.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setOnItemSelectedListener(new activity());
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
    public void shoppingnow(View v)
    {
        Intent shopping = new Intent(login_home.this,login_view_flower.class);
        shopping.putExtra("value1",userid);
        startActivity(shopping);
    }
    public void cart (View v)
    {
        Intent intent = new Intent(login_home.this,cart.class);
        intent.putExtra("value1",userid);
        startActivity(intent);
    }
    public void gallery (View v)
    {
        Intent gallery = new Intent(login_home.this,gallery.class);
        startActivity(gallery);
    }
    public void store (View v)
    {
        Intent store = new Intent(login_home.this,store_system.class);
        startActivity(store);
    }
    public void search (View v)
    {
        Intent search = new Intent(login_home.this,Search.class);
        search.putExtra("value1",userid);
        startActivity(search);
    }
}
