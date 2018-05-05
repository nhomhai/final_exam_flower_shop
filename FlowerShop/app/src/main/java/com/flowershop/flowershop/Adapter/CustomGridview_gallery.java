package com.flowershop.flowershop.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.flowershop.flowershop.Object.Flower_image;
import com.flowershop.flowershop.R;

import java.util.List;

/**
 * Created by kenjita.tran on 3/10/18.
 */

public class CustomGridview_gallery extends BaseAdapter {
    private List<Flower_image> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomGridview_gallery(Context aContext, List<Flower_image> listData)
    {
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
    static class ViewHolder {
        ImageView flowerView;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_item_gallery, null);
            holder = new ViewHolder();
            holder.flowerView = (ImageView) convertView.findViewById(R.id.imageView_item_gallery);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Flower_image flower = this.listData.get(position);

        int imageId = this.getMipmapResIdByName(flower.getImage());
        holder.flowerView.setImageResource(imageId);

        return convertView;
    }

    public int getMipmapResIdByName(String resName)
    {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

}
