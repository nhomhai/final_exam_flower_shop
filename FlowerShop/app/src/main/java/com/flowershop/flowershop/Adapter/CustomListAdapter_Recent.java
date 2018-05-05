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

public class CustomListAdapter_Recent extends BaseAdapter {
    private List<Order> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter_Recent(Context aContext,  List<Order> listData) {
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
            convertView = layoutInflater.inflate(R.layout.custom_recentorder, null);
            holder = new ViewHolder();
            holder.key_recent = (TextView) convertView.findViewById(R.id.textView_key_customrecent);
            holder.total_recent = (TextView) convertView.findViewById(R.id.textView_total_customrecent);
            holder.delete = (Button) convertView.findViewById(R.id.button_delete_customrecent);
            holder.success = (Button) convertView.findViewById(R.id.button_success_customrecent);
            holder.show_detail = (Button) convertView.findViewById(R.id.button_detail_customrecent) ;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Order od = this.listData.get(position);
        holder.delete.setTag((Integer)position);
        holder.success.setTag((Integer)position);
        holder.show_detail.setTag((Integer)position);
        holder.key_recent.setText(od.getKey());
        holder.total_recent.setText(od.getTotal());

        return convertView;
    }

    static class ViewHolder {
        TextView key_recent;
        TextView total_recent;
        Button success;
        Button delete;
        Button show_detail;
    }
}
