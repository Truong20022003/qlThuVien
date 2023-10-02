package fpoly.truongtqph41980.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.truongtqph41980.duanmau.dao.thuThuDAO;

public class dang_nhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        EditText edtUSERNAMEDN = findViewById(R.id.edtUSERNAMEDN);
        EditText edtPassDN = findViewById(R.id.edtPASSWORDDN);
        Button btnDNDN = findViewById(R.id.btnLOGINDN);
        thuThuDAO thuThuDAO = new thuThuDAO(this);
        btnDNDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUSERNAMEDN.getText().toString();
                String pass = edtPassDN.getText().toString();
                if (thuThuDAO.checkDangNhap(user,pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("MATT", user);
                    editor.putString("mk",pass);
                    editor.commit();
                    startActivity(new Intent(dang_nhap.this, MainActivity.class));

                }else {
                    Toast.makeText(dang_nhap.this, "User và pass không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}