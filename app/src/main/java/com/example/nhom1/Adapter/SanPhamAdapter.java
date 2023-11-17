package com.example.nhom1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom1.DAO.SanPhamDAO;
import com.example.nhom1.R;
import com.example.nhom1.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder>{
    View view;
    Context context;

    List<SanPham> list;

    public SanPhamAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        view = inflater.inflate(R.layout.item_ds_sanpham,parent,false);
        SanPhamViewHolder viewHolder=new SanPhamViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham = list.get(position);
        // Set data to the views
        holder.item_imgSP.setImageBitmap(BitmapFactory.decodeByteArray(sanPham.getAnh_sp(), 0, sanPham.getAnh_sp().length));
        holder.item_tenSP.setText(sanPham.getTen_sp());
        holder.item_motaSP.setText(sanPham.getMota_sp());
        holder.item_giaSP.setText(sanPham.getGiatien_sp()+"");
        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // Handle click events for edit and delete
        holder.item_suaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle edit click
            }
        });

        holder.item_xoaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete click
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("CÓ đồng ý xóa không"+sanPham.getId_sp());
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SanPhamDAO dao=new SanPhamDAO(context);
                        dao.deleteSanPham(sanPham);
                        list.remove(sanPham);
                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    class SanPhamViewHolder extends RecyclerView.ViewHolder{
        ImageView item_imgSP,item_xoaSP,item_suaSP;
        TextView item_tenSP,item_motaSP,item_giaSP;
        LinearLayout ln;
        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            item_imgSP=itemView.findViewById(R.id.img_item_imgSp);
            item_tenSP=itemView.findViewById(R.id.tv_item_tenSP);
            item_motaSP=itemView.findViewById(R.id.tv_item_ThanhphanSP);
            item_giaSP=itemView.findViewById(R.id.tv_item_tiendm);
            item_suaSP=item_imgSP.findViewById(R.id.img_suaSP);
            item_xoaSP=itemView.findViewById(R.id.img_xoaSP);
            ln=itemView.findViewById(R.id.linear_dsSP);
        }
    }
}
