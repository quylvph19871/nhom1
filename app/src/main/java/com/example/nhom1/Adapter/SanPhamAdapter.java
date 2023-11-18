package com.example.nhom1.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom1.Activity.Admin.ChiTietSanPham;
import com.example.nhom1.Activity.Admin.SuaSanPham;
import com.example.nhom1.DAO.SanPhamDAO;
import com.example.nhom1.R;
import com.example.nhom1.model.SanPham;

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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPham.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_sp",sanPham.getId_sp());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.item_suaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SuaSanPham.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_sp",sanPham.getId_sp());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.item_xoaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_xoa);
                TextView tieuDe = dialog.findViewById(R.id.tv_xoa);
                TextView noiDungXoa = dialog.findViewById(R.id.tv_nd_xoa);
                Button btn_xoa = dialog.findViewById(R.id.btn_dialog_xoa);
                Button btn_huy = dialog.findViewById(R.id.btn_dialog_huy);
                tieuDe.setText("Xóa sản phẩm");
                noiDungXoa.setText("Bạn chắc chắn muốn xóa sản phẩm "+sanPham.getTen_sp()+" này?");
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SanPhamDAO dao=new SanPhamDAO(context);
                        dao.deleteSanPham(sanPham);
                        list.remove(sanPham);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
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
        RelativeLayout rlt;
        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            item_imgSP=itemView.findViewById(R.id.img_item_imgSp);
            item_tenSP=itemView.findViewById(R.id.tv_item_tenSP);
            item_motaSP=itemView.findViewById(R.id.tv_item_ThanhphanSP);
            item_giaSP=itemView.findViewById(R.id.tv_item_tiendm);
            item_suaSP=itemView.findViewById(R.id.img_suaSP);
            item_xoaSP=itemView.findViewById(R.id.img_xoaSP);
        }
    }
}
