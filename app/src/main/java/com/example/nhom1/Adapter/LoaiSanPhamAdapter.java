package com.example.nhom1.Adapter;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom1.Activity.Admin.QuanLyLoai;
import com.example.nhom1.DAO.LoaiSPDAO;
import com.example.nhom1.R;
import com.example.nhom1.model.LoaiSP;

import java.util.List;


public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.LoaiViewHolder> {
    private List<LoaiSP> list;
    private QuanLyLoai context;
    View view;
    public LoaiSanPhamAdapter(List<LoaiSP> list, QuanLyLoai context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spn_loai_sp, parent, false);
        return new LoaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiViewHolder holder, int position) {
        LoaiSP loaiSP = list.get(position);
        holder.tenTheLoai.setText(loaiSP.getTen_loaisp());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.Sua(loaiSP);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_xoa);
                TextView tieuDe = dialog.findViewById(R.id.tv_xoa);
                TextView noiDungXoa = dialog.findViewById(R.id.tv_nd_xoa);
                Button btn_xoa = dialog.findViewById(R.id.btn_dialog_xoa);
                Button btn_huy = dialog.findViewById(R.id.btn_dialog_huy);
                tieuDe.setText("Xóa loại sản phẩm");
                noiDungXoa.setText("Bạn chắc chắn muốn xóa loại sản phẩm "+loaiSP.getTen_loaisp()+" này?");
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoaiSPDAO dao=new LoaiSPDAO(context);
                        dao.deleteLoaiSP(loaiSP.getId_loaisp());
                        list.remove(loaiSP);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LoaiViewHolder extends RecyclerView.ViewHolder {
        TextView tenTheLoai;
        public LoaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tenTheLoai = itemView.findViewById(R.id.tvTen);
        }
    }
}
