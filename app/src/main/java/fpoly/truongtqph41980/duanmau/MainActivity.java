package fpoly.truongtqph41980.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import fpoly.truongtqph41980.duanmau.dao.loaiSachDAO;
import fpoly.truongtqph41980.duanmau.dao.sachDAO;
import fpoly.truongtqph41980.duanmau.dao.thuThuDAO;
import fpoly.truongtqph41980.duanmau.fragment.doiMatKhau;
import fpoly.truongtqph41980.duanmau.fragment.ql_DoanhThu;
import fpoly.truongtqph41980.duanmau.fragment.ql_LoaiSach;
import fpoly.truongtqph41980.duanmau.fragment.ql_PhieuMuon;
import fpoly.truongtqph41980.duanmau.fragment.ql_Sach;
import fpoly.truongtqph41980.duanmau.fragment.ql_ThanhVien;
import fpoly.truongtqph41980.duanmau.fragment.ql_Top10;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawerLayout;
LinearLayout lin1;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        lin1 = findViewById(R.id.lin1);
        drawerLayout = findViewById(R.id.drawerLayout);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setTitle("Thư viện");
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ql_PhieuMuon()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.qlPhieuMuon){
                    fragment = new ql_PhieuMuon();
                }else if (item.getItemId() == R.id.qlLoaiSach){
                    fragment = new ql_LoaiSach();
                }else if (item.getItemId() == R.id.qlSach){
                    fragment = new ql_Sach();
                }else if (item.getItemId() == R.id.qlThanhVien){
                    fragment = new ql_ThanhVien();
                }else if (item.getItemId() == R.id.qlTop10){
                    fragment = new ql_Top10();
                }else if (item.getItemId() == R.id.qlDoanhThu){
                    fragment = new ql_DoanhThu();
                } else if (item.getItemId() == R.id.doiMatKhau){
                    fragment = new doiMatKhau();
//                    showDialogDoiMatKhau();
                    //                    Intent = new ''
//                    startActivity(new Intent(MainActivity.this, dang_nhap.class));
                }else if (item.getItemId() == R.id.thoat){
                    Intent intent = new Intent(MainActivity.this, dang_nhap.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                getSupportActionBar().setTitle(item.getTitle());
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


//    private void showDialogDoiMatKhau(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.item_doimatkhau,null);
//        EditText edtMatKhauCu = view.findViewById(R.id.edtMatKhauCu);
//        EditText edtMatKhauMoi = view.findViewById(R.id.edtMatKhauMoi);
//        EditText edtNhapLaiMatKhau = view.findViewById(R.id.edtNhapLaiMatKhau);
//        builder.setView(view);
//
//        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String oldPass = edtMatKhauCu.getText().toString();
//                String newPass = edtMatKhauMoi.getText().toString();
//                String reNewPass = edtNhapLaiMatKhau.getText().toString();
//                if (newPass.equals(reNewPass)){
//                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
//                    String matt = sharedPreferences.getString("MATT", "");
//                    //cập nhật
//                    thuThuDAO thuThuDAO = new thuThuDAO(MainActivity.this);
//                    boolean check = thuThuDAO.capNhatMatKhau(matt,oldPass,newPass);
//                    Intent intent = new Intent(MainActivity.this, dang_nhap.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    if (check){
//                        Toast.makeText(MainActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(MainActivity.this, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }else {
//                    Toast.makeText(MainActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}