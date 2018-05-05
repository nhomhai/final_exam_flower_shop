package com.flowershop.flowershop.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flowershop.flowershop.Object.Cart_Object;
import com.flowershop.flowershop.R;

import java.util.List;

public class CustomListAdapter_Cart extends BaseAdapter{
    private List<Cart_Object> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter_Cart(Context aContext,  List<Cart_Object> listData) {
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
            holder.remove = (Button) convertView.findViewById(R.id.button_remove_listitem_cart);
            holder.add = (Button) convertView.findViewById(R.id.button_add_listitem_cart);
            holder.sub = (Button) convertView.findViewById(R.id.button_sub_listitem_cart);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cart_Object cart = this.listData.get(position);
        holder.remove.setTag((Integer) position);
        holder.add.setTag((Integer) position);
        holder.sub.setTag((Integer)position);
        holder.flowername.setText(cart.getName());
        holder.price.setText(cart.getPrice());
        holder.sl.setText(cart.getSl());

        int imageId = this.getMipmapResIdByName(cart.getImage());

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
        Button remove;
        Button add;
        Button sub;
    }

}
