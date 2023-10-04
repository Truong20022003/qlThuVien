package fpoly.truongtqph41980.duanmau.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.truongtqph41980.duanmau.R;
import fpoly.truongtqph41980.duanmau.adapter.sach_adapter;
import fpoly.truongtqph41980.duanmau.dao.loaiSachDAO;
import fpoly.truongtqph41980.duanmau.dao.sachDAO;
import fpoly.truongtqph41980.duanmau.model.loaiSach;
import fpoly.truongtqph41980.duanmau.model.sach;


public class ql_Sach extends Fragment {



    public ql_Sach() {
        // Required empty public constructor
    }

    sachDAO sachDAO;
    RecyclerView rcv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql__sach, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcv_qlSach);
        FloatingActionButton fladdSach = view.findViewById(R.id.fladdSach);
        sachDAO = new sachDAO(getContext());
        loadData();
        fladdSach.setOnClickListener(new View.OnClickListener() {
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
        View view = inflater.inflate(R.layout.item_themsach,null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtThemTenSachS);
        EditText edtGiaThue = view.findViewById(R.id.edtThemGiaThueS);
        Spinner spnLoaiSach = view.findViewById(R.id.spnMaLoaiS);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"TENLOAI"},
                new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenSach = edtTenSach.getText().toString();
                int gia = Integer.parseInt(edtGiaThue.getText().toString());
                HashMap<String, Object> hss = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maLoaiSach = (int) hss.get("MALOAI");

                boolean check = sachDAO.themSach(tenSach,gia,maLoaiSach);
                if (check){
                    Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }


            }

        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private ArrayList<HashMap<String,Object>> getDSLoaiSach(){
        loaiSachDAO loaiSachDAO = new loaiSachDAO(getContext());
        ArrayList<loaiSach> list = loaiSachDAO.getData();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();


        for (loaiSach loaiSach : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("MALOAI",loaiSach.getMaLoai());
            hs.put("TENLOAI",loaiSach.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }
    private void loadData(){
        ArrayList<sach> list  = sachDAO.selectAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        sach_adapter adapter = new sach_adapter(getContext(),list, getDSLoaiSach(), sachDAO);
        rcv.setAdapter(adapter);
    }
}