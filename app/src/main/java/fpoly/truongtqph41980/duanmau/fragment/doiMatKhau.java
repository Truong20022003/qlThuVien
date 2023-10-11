package fpoly.truongtqph41980.duanmau.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.truongtqph41980.duanmau.MainActivity;
import fpoly.truongtqph41980.duanmau.R;
import fpoly.truongtqph41980.duanmau.dang_nhap;
import fpoly.truongtqph41980.duanmau.dao.thuThuDAO;


public class doiMatKhau extends Fragment {

    EditText edtMatKhauCu, edtMatKhauMoi, edtNhapLaiMatKhau;
    Button btnLuuMatKhau, btnHuyLuuMK;
    public doiMatKhau() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau,container,false);
        // Inflate the layout for this fragment
         edtMatKhauCu = view.findViewById(R.id.edtMatKhauCu);
         edtMatKhauMoi = view.findViewById(R.id.edtMatKhauMoi);
         edtNhapLaiMatKhau = view.findViewById(R.id.edtNhapLaiMatKhau);
        btnLuuMatKhau = view.findViewById(R.id.btnLuuMatKhau);
        btnHuyLuuMK = view.findViewById(R.id.btnHuyLuuMK);
        btnHuyLuuMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMatKhauCu.setText("");
                edtMatKhauMoi.setText("");
                edtNhapLaiMatKhau.setText("");
            }
        });
        btnLuuMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doiMatKhau();
            }
        });
        return view;
    }
    private void doiMatKhau(){
                String oldPass = edtMatKhauCu.getText().toString();
                String newPass = edtMatKhauMoi.getText().toString();
                String reNewPass = edtNhapLaiMatKhau.getText().toString();
                if (newPass.equals(reNewPass)){
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                    String matt = sharedPreferences.getString("MATT", "");
                    String mk = sharedPreferences.getString("MATKHAU","");
                    //cập nhật
                    if(oldPass.equals(mk)){
                        thuThuDAO thuThuDAO = new thuThuDAO(getContext());
                        boolean check = thuThuDAO.capNhatMatKhau(matt,oldPass,newPass);

                        if (check) {
                            Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), dang_nhap.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }

            }

        }

