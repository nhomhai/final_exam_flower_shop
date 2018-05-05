package com.flowershop.flowershop.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flowershop.flowershop.Object.Flower;
import com.flowershop.flowershop.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomListAdapter_Search extends ArrayAdapter{
    private List<Flower> listData;
    private int resource;
    private Context context;
    private  List<Flower> listSeach = null;

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }
    public int getResource() {
        return resource;
    }

    public List<Flower> getListData() {
        return listData;
    }

    public CustomListAdapter_Search(@NonNull Context context, int resource, @NonNull List<Flower> objects) {
        super(context, resource, objects);
        this.listSeach = objects;
        this.resource = resource;
        this.context = context;
        this.listData = new ArrayList<Flower>();
        this.listData.addAll(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layinf = LayoutInflater.from(context);
        convertView = layinf.inflate(R.layout.search_item, parent, false);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView_image_search);
        TextView flowername = (TextView)convertView.findViewById(R.id.textView_flowername_search);
        TextView price = (TextView)convertView.findViewById(R.id.textView_flowerprice_search);
        Button buy = (Button) convertView.findViewById(R.id.button_buy_search);
        Button detail = (Button) convertView.findViewById(R.id.button_detail_search);
        convertView.setTag((Integer)position);

        Flower  fl = listSeach.get(position);
        buy.setTag((Integer)position);
        detail.setTag((Integer)position);
        img.setImageResource(getMipmapResIdByName(fl.getimageFlower()));
        flowername.setText(fl.getFlowername());
        price.setText(fl.getPrice());
        return convertView;
    }

    public int getMipmapResIdByName(String resName)
    {
        String pkgName = context.getPackageName();

        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    public  void  filter(String searh){
        searh = searh.toLowerCase(Locale.getDefault());
        listSeach.clear();
        if(searh.length()==0)
            listSeach.addAll(listData);
        else {
            for (Flower flower: listData){
                if(flower.getFlowername().toLowerCase(Locale.getDefault()).contains(searh)){
                    listSeach.add(flower);
                }
            }
        }
        notifyDataSetChanged();
    }
}
