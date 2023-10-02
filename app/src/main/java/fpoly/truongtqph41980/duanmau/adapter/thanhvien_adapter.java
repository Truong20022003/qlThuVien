package fpoly.truongtqph41980.duanmau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.truongtqph41980.duanmau.R;
import fpoly.truongtqph41980.duanmau.dao.thanhVienDAO;
import fpoly.truongtqph41980.duanmau.model.thanhVien;

public class thanhvien_adapter extends RecyclerView.Adapter<thanhvien_adapter.ViewHolder> {

    private final Context context;
    private final ArrayList<thanhVien> list;
    thanhVienDAO thanhVienDAO;

    public thanhvien_adapter(Context context, ArrayList<thanhVien> list) {
        this.context = context;
        this.list = list;
        thanhVienDAO = new thanhVienDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcvthanhvien,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaTV.setText("Mã thành viên: "+ String.valueOf(list.get(position).getMaTV()));
        holder.txtTenTV.setText("Tên thành viên: " + list.get(position).getHoTen());
        holder.txtNamSinh.setText("Năm sinh: " + list.get(position).getNamSinh());
        thanhVien thanhVien = list.get(position);

        holder.imgXoaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (thanhVienDAO.xoaThanhVien(thanhVien)){
                            list.clear();
                            list.addAll(thanhVienDAO.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.imgSuaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.item_suathanhvien,null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edtSuaHoTenTV = view.findViewById(R.id.edtSuaHoTenTV);
                EditText edtSuaNamSinhTV = view.findViewById(R.id.edtSuaNamSinhTV);
                Button btnSuaThanhVien = view.findViewById(R.id.btnSuaThanhVienTV);
                Button btnHuySuaTV = view.findViewById(R.id.btnDongSuaThanhVienTV);
                btnHuySuaTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtSuaHoTenTV.setText("");
                        edtSuaNamSinhTV.setText("");
                        dialog.dismiss();
                    }
                });
                btnSuaThanhVien.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenTV = edtSuaHoTenTV.getText().toString();
                        String namSinhTV = edtSuaNamSinhTV.getText().toString();
                        thanhVien.setHoTen(tenTV);
                        thanhVien.setNamSinh(namSinhTV);
                        if (tenTV.isEmpty() || namSinhTV.isEmpty()){
                            Toast.makeText(context, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                        }else if (thanhVienDAO.suaThanhVien(thanhVien)){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(thanhVienDAO.selectAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaTV, txtTenTV, txtNamSinh;
        ImageView imgSuaTV, imgXoaTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaTV = itemView.findViewById(R.id.txtMaTVtv);
            txtTenTV = itemView.findViewById(R.id.txtHOTENTV);
            txtNamSinh = itemView.findViewById(R.id.txtNAMSINHTV);
            imgSuaTV = itemView.findViewById(R.id.imgSuaThanhVien);
            imgXoaTV = itemView.findViewById(R.id.imgXoaThanhVien);

        }
    }
}
