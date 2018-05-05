package com.flowershop.flowershop.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flowershop.flowershop.Object.Flower;
import com.flowershop.flowershop.R;

import java.util.List;

/**
 * Created by kenjita.tran on 3/9/18.
 */

public class CustomListAdapter extends BaseAdapter {

    private List<Flower> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext,  List<Flower> listData) {
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
            convertView = layoutInflater.inflate(R.layout.cart_item_list,parent,false);
            holder = new ViewHolder();
            holder.imageview = (ImageView) convertView.findViewById(R.id.imageView_image_cart_item);
            holder.flowername = (TextView) convertView.findViewById(R.id.textView_name_listitem_cart);
            holder.price = (TextView) convertView.findViewById(R.id.textView_price_listitem_cart);
            holder.sl = (EditText) convertView.findViewById(R.id.editText_sl_listitem_cart);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Flower flower = this.listData.get(position);
        holder.flowername.setText(flower.getFlowername());
        holder.price.setText("" + flower.getPrice());
        holder.sl.setText("1");

        int imageId = this.getMipmapResIdByName(flower.getimageFlower());

        holder.imageview.setImageResource(imageId);

        return (convertView);
    }

    public int getMipmapResIdByName(String resName)
    {
        String pkgName = context.getPackageName();

        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView imageview;
        TextView flowername;
        TextView price;
        EditText sl;
    }

}