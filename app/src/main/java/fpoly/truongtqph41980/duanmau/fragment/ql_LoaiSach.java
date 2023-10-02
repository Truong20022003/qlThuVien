package fpoly.truongtqph41980.duanmau.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.truongtqph41980.duanmau.R;
import fpoly.truongtqph41980.duanmau.adapter.loaiSach_adapter;
import fpoly.truongtqph41980.duanmau.dao.loaiSachDAO;
import fpoly.truongtqph41980.duanmau.model.loaiSach;


public class ql_LoaiSach extends Fragment {

    RecyclerView rcvLoaiSach;
    loaiSachDAO loaiSachDAO;
    loaiSach sach;
    public ql_LoaiSach() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql__loai_sach, container, false);
        rcvLoaiSach = view.findViewById(R.id.rcvLoaiSach);
//        EditText edtNhapLoaiSach = view.findViewById(R.id.edtNhapLoaiSach);
//        Button btnThemLoaiSach = view.findViewById(R.id.btnThemLoaiSach);
        loadData();
        FloatingActionButton flaLoaiSach = view.findViewById(R.id.fladdLoaiSach);
        flaLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themLoaiSach();
            }
        });
        // Inflate the layout for this fragment

//        btnThemLoaiSach.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String tenLoai = edtNhapLoaiSach.getText().toString();
//                if (loaiSachDAO.themLoaiSach(tenLoai)){
//                    //thông báo + load lại danh sách
//                    loadData();
//                    edtNhapLoaiSach.setText("");
//                }else {
//                    Toast.makeText(getContext(), "Thêm loại sách thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        return view;
    }
    private void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvLoaiSach.setLayoutManager(layoutManager);
        loaiSachDAO = new loaiSachDAO(getContext());
        ArrayList<loaiSach> list = loaiSachDAO.getData();
        loaiSach_adapter adapter = new loaiSach_adapter(getContext(),list);
        rcvLoaiSach.setAdapter(adapter);
    }
    private void themLoaiSach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_themloaisach,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText edtNhapTenLS = view.findViewById(R.id.edtNhapLoaiSach);
        Button btnThem = view.findViewById(R.id.btnThemLoaiSach);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ls = edtNhapTenLS.getText().toString();
                 sach = new loaiSach();
                sach.setTenLoai(ls);
                if (ls.isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }else if (loaiSachDAO.themLoaiSach(ls)){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                    loadData();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}