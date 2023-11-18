package com.example.nhom1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom1.Activity.Admin.ChiTietSanPham;
import com.example.nhom1.R;
import com.example.nhom1.model.SanPham;

import java.util.List;

public class SanPhamUserAdapter extends RecyclerView.Adapter<SanPhamUserAdapter.ComicsViewHolder>{
    private final List<SanPham> list;
    private final Context context;
    private View view;
    public SanPhamUserAdapter(List<SanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ComicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp_user,parent,false);
        return new ComicsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsViewHolder holder, int position) {
        SanPham sp = list.get(position);
        holder.tenSP.setText(sp.getTen_sp());
        holder.anh.setImageBitmap(BitmapFactory.decodeByteArray(sp.getAnh_sp(), 0, sp.getAnh_sp().length));

        holder.anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPham.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_sp",sp.getId_sp());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        }
        return list.size();
    }

    public static  class ComicsViewHolder extends RecyclerView.ViewHolder{
        private final ImageView anh;
        private final TextView tenSP;
        public ComicsViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSP = itemView.findViewById(R.id.name);
            anh = itemView.findViewById(R.id.image);
        }
    }
}
