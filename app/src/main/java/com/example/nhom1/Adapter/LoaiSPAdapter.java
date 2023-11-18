package com.example.nhom1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.nhom1.R;
import com.example.nhom1.model.LoaiSP;

import java.util.List;

public class LoaiSPAdapter extends BaseAdapter implements SpinnerAdapter {
    private Context mContext;
    private List<LoaiSP> list;

    public LoaiSPAdapter(Context mContext, List<LoaiSP> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewOfItem viewOfItem;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_spn_loai_sp, parent, false);
            viewOfItem = new ViewOfItem();
            viewOfItem.tvTen = convertView.findViewById(R.id.tvTen);
            convertView.setTag(viewOfItem);
        } else {
            viewOfItem = (ViewOfItem) convertView.getTag();
        }

        LoaiSP item = list.get(position);
        if (item != null) {
            viewOfItem.tvTen.setText(item.getTen_loaisp());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    public static class ViewOfItem {
        TextView tvTen;
    }
}
