package com.example.nhom1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom1.DAO.SanPhamDAO;
import com.example.nhom1.R;
import com.example.nhom1.model.LoaiSP;
import com.example.nhom1.model.SanPham;

import java.util.ArrayList;
import java.util.List;


public class ContainerSanPhamAdapter extends RecyclerView.Adapter<ContainerSanPhamAdapter.ComicViewHolder> {
    private List<LoaiSP> list;
    private Context context;
    List<SanPham> listSP;
    public ContainerSanPhamAdapter(List<LoaiSP> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_sanpham, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        LoaiSP loaiSP = list.get(position);
        // Đổi LayoutManager thành GridLayoutManager với 2 cột
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        listSP = new ArrayList<>();
        SanPhamDAO dao = new SanPhamDAO(context);
        listSP =dao.getSanPhamTheoIDLoai(loaiSP.getId_loaisp());
        if (listSP.isEmpty()){
            return;
        }
        SanPhamUserAdapter adapter = new SanPhamUserAdapter(listSP,context);
        holder.recyclerView.setAdapter(adapter);
        holder.tenTheLoai.setText(loaiSP.getTen_loaisp());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ComicViewHolder extends RecyclerView.ViewHolder {
        TextView tenTheLoai;
        RecyclerView recyclerView;
        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            tenTheLoai = itemView.findViewById(R.id.tenLoai);
            recyclerView = itemView.findViewById(R.id.rcv_sp);
        }
    }
}
