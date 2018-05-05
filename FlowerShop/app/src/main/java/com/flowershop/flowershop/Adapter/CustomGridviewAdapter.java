package com.flowershop.flowershop.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flowershop.flowershop.Object.Flower;
import com.flowershop.flowershop.R;

import java.util.List;

/**
 * Created by kenjita.tran on 3/7/18.
 */

public class CustomGridviewAdapter extends BaseAdapter {

        private List<Flower> listData;
        private LayoutInflater layoutInflater;
        private Context context;

        public CustomGridviewAdapter(Context aContext, List<Flower> listData)
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
            TextView flowerName;
            TextView flowerPrice;
            Button detail;
            Button add;
            TextView status;
        }

public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    if (convertView == null) {
        convertView = layoutInflater.inflate(R.layout.custom_test_view, null);
        holder = new ViewHolder();
        holder.flowerView = (ImageView) convertView.findViewById(R.id.imageView_testview_image);
        holder.flowerName = (TextView) convertView.findViewById(R.id.textView_testview_name);
        holder.flowerPrice = (TextView) convertView.findViewById(R.id.textView_testview_price);
        holder.detail = (Button) convertView.findViewById(R.id.button_testview_detail);
        holder.add = (Button) convertView.findViewById(R.id.button_testview_addtocart);
        convertView.setTag(holder);
    } else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.add.setTag((Integer) position);
        holder.detail.setTag((Integer) position);
        Flower flower = this.listData.get(position);
        holder.flowerName.setText(""+ flower.getFlowername());
        holder.flowerPrice.setText("" + flower.getPrice());

        int imageId = this.getMipmapResIdByName(flower.getimageFlower());
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
