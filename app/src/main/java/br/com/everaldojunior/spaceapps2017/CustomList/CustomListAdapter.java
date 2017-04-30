package br.com.everaldojunior.spaceapps2017.CustomList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.everaldojunior.spaceapps2017.R;

public class CustomListAdapter extends BaseAdapter {
    private ArrayList<InfoItem> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<InfoItem> listData) {
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
            convertView = layoutInflater.inflate(R.layout.notas_list, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.info = (TextView) convertView.findViewById(R.id.info);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.icon.setImageDrawable(listData.get(position).geticon());
        holder.name.setText(listData.get(position).getname());
        holder.info.setText(listData.get(position).getinfo());
        return convertView;
    }

    static class ViewHolder {
        ImageView icon;
        TextView name;
        TextView info;
    }
}