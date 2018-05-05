package com.flowershop.flowershop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.flowershop.flowershop.Object.Flower_image;
import com.flowershop.flowershop.R;

import java.util.List;

public class CustomGridviewAdapter_GalleryManager extends BaseAdapter {
    private List<Flower_image> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomGridviewAdapter_GalleryManager(Context aContext,  List<Flower_image> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_gallery, null);
            holder = new ViewHolder();
            holder.imageview = (ImageView) convertView.findViewById(R.id.imageView10);
            holder.delete = (Button) convertView.findViewById(R.id.button_delete_gallerymanager);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Flower_image f = this.listData.get(position);
        holder.delete.setTag((Integer)position);
        int imageId = this.getMipmapResIdByName(f.getImage());

        holder.imageview.setImageResource(imageId);

        return convertView;
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        return resID;
    }

    static class ViewHolder {
        ImageView imageview;
        Button delete;
    }
}
