package com.flowershop.flowershop;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;

import com.flowershop.flowershop.Object.Flower;
import com.flowershop.flowershop.Object.getFlower;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;

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
    //----------------------------------------------------------------------
    public void cart (View v)
    {
        showAlertDialog("Bạn phải đăng nhập để thực hiện chức năng này");
    }
    public void shoppingnow(View v)
    {
        Intent shopping = new Intent(MainActivity.this,view_flower.class);
        startActivity(shopping);
    }
//    public void wedding (View v)
//    {
//        Intent wed = new Intent(MainActivity.this,wedding_flower.class);
//        startActivity(wed);
//    }
    public void gallery (View v)
    {
        Intent gallery = new Intent(MainActivity.this,gallery.class);
        startActivity(gallery);
    }
    public void store (View v)
    {
        Intent store = new Intent(MainActivity.this,store_system.class);
        startActivity(store);
    }
    public void search (View v)
    {
        showAlertDialog("bạn phải đăng nhập để thực hiện chức năng này");
    }
    //-----------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoView3);
        mediaController = new MediaController(MainActivity.this);

            int id = this.getRawResIdByName("myvideo");
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
            videoView.requestFocus();
            videoView.start();

        final String choice[] = {"","Home","About us","Manager Account","Maps","Login"};
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        class activity implements android.widget.AdapterView.OnItemSelectedListener {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String text = spinner.getSelectedItem().toString();
                if(text.compareTo("Home")==0)
                {
                    Intent home = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(home);
                }
                if(text.compareTo("About us")==0)
                {
                    Intent about = new Intent(MainActivity.this,about_us.class);
                    about.putExtra("value1","the application was designed and developed by group 2, Class: D14CQAT01-N ");
                    about.putExtra("value2","contact: 0169 333 8069");
                    startActivity(about);
                }
                if(text.compareTo("Manager Account")==0)
                {
                    showAlertDialog("bạn phải đăng nhập để thực hiện chức năng này");
                }
                if(text.compareTo("Maps")==0)
                {
                    Intent maps = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/maps/place/Học+Viện+Công+Nghệ+Bưu+Chính+Viễn+Thông+Cơ+Sở+2/@10.848037,106.7843943,17z/data=!3m1!4b1!4m5!3m4!1s0x31752772b245dff1:0xb838977f3d419d!8m2!3d10.848037!4d106.786583"));
                    startActivity(maps);
                }
                if(text.compareTo("Login")==0)
                {
                    Intent login = new Intent(MainActivity.this, login.class);
                    startActivity(login);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new activity());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // lay hoa tu firebase de add no vo getFlower as
        // chi lay data add vo thoi, chu k co hien len
        FirebaseDatabase.getInstance().getReference("Flower").addValueEventListener(new ValueEventListener() {
            @Override
            public void  onDataChange(DataSnapshot dataSnapshot){
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    Flower flower = user_snapshot.getValue(Flower.class);
                    getFlower.Add(flower);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
