package com.example.nhom1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom1.Adapter.ContainerSanPhamAdapter;
import com.example.nhom1.DAO.LoaiSPDAO;
import com.example.nhom1.R;
import com.example.nhom1.model.LoaiSP;

import java.util.ArrayList;
import java.util.List;

public class Fragment_sanPham extends Fragment {
    private RecyclerView rcv_container_sp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sanpham,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_container_sp = view.findViewById(R.id.rcv_container_sp);
        LoaiSPDAO dao = new LoaiSPDAO(getContext());
        List<LoaiSP> list = new ArrayList<>();
        list = dao.getAllLoaiSP();
        ContainerSanPhamAdapter adapter = new ContainerSanPhamAdapter(list,getContext());
        rcv_container_sp.setAdapter(adapter);
    }

}
