package com.flowershop.flowershop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.flowershop.flowershop.Object.Order;
import com.flowershop.flowershop.R;

import java.util.List;

public class CustomListView_OrderManager extends BaseAdapter {
        private List<Order> listData;
        private LayoutInflater layoutInflater;
        private Context context;

        public CustomListView_OrderManager(Context aContext,  List<Order> listData) {
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
                convertView = layoutInflater.inflate(R.layout.custom_order, null);
                holder = new ViewHolder();
                holder.userid = (TextView) convertView.findViewById(R.id.textView_userid_customorder);
                holder.key = (TextView) convertView.findViewById(R.id.textView_key_customorder);
                holder.show_detail = (Button) convertView.findViewById(R.id.button_detail_customorder);
                holder.delete = (Button) convertView.findViewById(R.id.button_delete_customorder);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Order od = this.listData.get(position);
            holder.delete.setTag((Integer)position);
            holder.show_detail.setTag((Integer)position);
            holder.userid.setText(od.getUsername());
            holder.key.setText(od.getKey());

            return convertView;
        }

        static class ViewHolder {
            TextView userid;
            TextView key;
            Button delete;
            Button show_detail;
        }
}
