package fpoly.truongtqph41980.duanmau.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import fpoly.truongtqph41980.duanmau.R;
import fpoly.truongtqph41980.duanmau.adapter.phieuMuon_adapter;
import fpoly.truongtqph41980.duanmau.dao.phieuMuonDAO;
import fpoly.truongtqph41980.duanmau.dao.sachDAO;
import fpoly.truongtqph41980.duanmau.dao.thanhVienDAO;
import fpoly.truongtqph41980.duanmau.model.phieuMuon;
import fpoly.truongtqph41980.duanmau.model.sach;
import fpoly.truongtqph41980.duanmau.model.thanhVien;


public class ql_PhieuMuon extends Fragment {

    phieuMuonDAO phieuMuonDAO;
    RecyclerView rcvQLPhieuMuon;
    ArrayList<phieuMuon> list;
    public ql_PhieuMuon() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_ql__phieu_muon, container, false);
        // Inflate the layout for this fragment

        rcvQLPhieuMuon = view.findViewById(R.id.rcv_qlPhieuMuon);
        FloatingActionButton floadd = view.findViewById(R.id.fladdPhieuMuon);
//        phieuMuonDAO = new phieuMuonDAO(getContext());
//        list = phieuMuonDAO.selectAllPhieuMuon();

        loadData();
        floadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_themphieumuon,null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVienPM);
        Spinner spnSach = view.findViewById(R.id.spnSachPM);
//        EditText edtTien = view.findViewById(R.id.edtNhapTienPM);
        getDataThanhVien(spnThanhVien);
        getDataSach(spnSach);
        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //lấy mã thành viên

                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv =(int) hsTV.get("MATV");
                //
                //lấy mã sách
                HashMap<String,Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsSach.get("MASACH");
                int tien = (int) hsSach.get("GIATHUE");
                themPhieuMuon(matv,masach,tien);
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void loadData(){
        phieuMuonDAO = new phieuMuonDAO(getContext());
        list = phieuMuonDAO.selectAllPhieuMuon();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvQLPhieuMuon.setLayoutManager(layoutManager);
        phieuMuon_adapter adapter = new phieuMuon_adapter(list,getContext());
        rcvQLPhieuMuon.setAdapter(adapter);

    }
    private void getDataThanhVien(Spinner spnThanhVien){
        thanhVienDAO thanhVienDAO = new thanhVienDAO(getContext());
        ArrayList<thanhVien> list = thanhVienDAO.selectAll();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (thanhVien tv : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("MATV", tv.getMaTV());
            hs.put("HOTENTV", tv.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"HOTENTV"},
                new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);
    }
    private void getDataSach(Spinner spnSach){
        sachDAO sachDAO = new sachDAO(getContext());

        ArrayList<sach> list = sachDAO.getAll();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (sach sd : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("MASACH", sd.getMaSach());
            hs.put("TENSACH", sd.getTenSach());
            hs.put("GIATHUE", sd.getGiaThue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"TENSACH"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }
    private void themPhieuMuon(int matv, int masach, int tien){
        //lấy mã thủ thư
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("MATT","");
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(date);
//        this.maTT = maTT;
//        this.maTV = maTV;
//        this.maSach = maSach;
//        this.tienThue = tienThue;
//        this.ngay = ngay;
//        this.traSach = traSach;
        phieuMuon phieuMuon = new phieuMuon(matt,matv,masach,tien,ngay,0);
        boolean kiemtra = phieuMuonDAO.insertPhieuMuon(phieuMuon);
        if (kiemtra){
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            loadData();
        }else {
            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

        }


    }
}