package com.flowershop.flowershop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.flowershop.flowershop.R;
import com.flowershop.flowershop.Object.User;

import java.util.List;


public class CustomListAdapter_UserManager  extends BaseAdapter {

    private List<User> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter_UserManager(Context aContext,  List<User> listData) {
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
            convertView = layoutInflater.inflate(R.layout.custom_user_manager, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.textView_id_usermanager);
            holder.name = (TextView) convertView.findViewById(R.id.textView_name_usermanager);
            holder.detail = (Button) convertView.findViewById(R.id.button_show_info);
            holder.delete = (Button) convertView.findViewById(R.id.button_delete_info);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = this.listData.get(position);
        holder.delete.setTag((Integer) position);
        holder.detail.setTag((Integer) position);
        holder.id.setText(""+user.getUserid());
        holder.name.setText(""+user.getName());

        return convertView;
    }

    static class ViewHolder {
        TextView id;
        TextView name;
        Button detail;
        Button delete;

    }
}

