package fpoly.truongtqph41980.duanmau.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

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
import fpoly.truongtqph41980.duanmau.adapter.thanhvien_adapter;
import fpoly.truongtqph41980.duanmau.dao.thanhVienDAO;
import fpoly.truongtqph41980.duanmau.model.thanhVien;


public class ql_ThanhVien extends Fragment {
RecyclerView rcv;
private ArrayList<thanhVien> list = new ArrayList<>();
thanhVienDAO thanhVienDAO;
    thanhvien_adapter adapter;
    public ql_ThanhVien() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_ql__thanh_vien, container, false);
        FloatingActionButton fladd = view.findViewById(R.id.fladdThanhVien);
        rcv = view.findViewById(R.id.rcv_qlThanhVien);

        thanhVienDAO = new thanhVienDAO(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        list = thanhVienDAO.selectAll();
        adapter = new thanhvien_adapter(getContext(),list);
        rcv.setAdapter(adapter);
        fladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.item_themthanhvien, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();

                EditText edtNhapTenTV = view.findViewById(R.id.edtNhapHoTenTV);
                EditText edtNhapNamSinhTV = view.findViewById(R.id.edtNhapNamSinhTV);
                Button btnThemTV = view.findViewById(R.id.btnThemThanhVien);
                Button btnHuyThemTV = view.findViewById(R.id.btnDongThemThanhVien);

                btnHuyThemTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnThemTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String hoten = edtNhapTenTV.getText().toString();
                        String namSinh = edtNhapNamSinhTV.getText().toString();

                        thanhVien tv = new thanhVien(hoten,namSinh);

                        if (hoten.isEmpty() || namSinh.isEmpty()){
                            Toast.makeText(getContext(), "Không được để trống thông tin", Toast.LENGTH_SHORT).show();

                        }else if (thanhVienDAO.themThanhVien(tv)){
                            list.clear();
                            list.addAll(thanhVienDAO.selectAll());
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        return view;
    }
}