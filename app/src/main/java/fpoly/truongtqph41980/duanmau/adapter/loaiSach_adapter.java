package fpoly.truongtqph41980.duanmau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import fpoly.truongtqph41980.duanmau.dao.loaiSachDAO;
import fpoly.truongtqph41980.duanmau.model.loaiSach;

public class loaiSach_adapter extends RecyclerView.Adapter<loaiSach_adapter.ViewHolder>{

    private Context context;
    private ArrayList<loaiSach> list;
    loaiSachDAO dao;
    public loaiSach_adapter(Context context, ArrayList<loaiSach> list) {
        this.context = context;
        this.list = list;
        dao = new loaiSachDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcvloaisach,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLoai.setText("Tên loại: "+list.get(position).getTenLoai());
        holder.txtMaLoai.setText("Mã loại sách: "+list.get(position).getMaLoai());
        loaiSach loaiSach = list.get(position);
        holder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.item_sualoaisach,null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edtSua = view.findViewById(R.id.edtSuaLoaiSachLS);
                Button btnSuaLoaiSach =view.findViewById(R.id.btnSuaLoaiSachLS);
                Button btnDongSuaLoaiSach = view.findViewById(R.id.btnDongSuaLoaiSachLS);
                btnDongSuaLoaiSach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtSua.setText("");
                        dialog.dismiss();
                    }
                });
                edtSua.setText(loaiSach.getTenLoai());
                btnSuaLoaiSach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenLoai = edtSua.getText().toString();
                        loaiSach.setTenLoai(tenLoai);
                        if (tenLoai.isEmpty()){
                            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                        }else if (dao.suaLoaiSach(loaiSach)){

                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(dao.getData());
                                notifyDataSetChanged();
                                dialog.dismiss();

                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSachDAO loaiSachDAO = new loaiSachDAO(context);
                int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getMaLoai());
                switch (check){
                    case 1:
                        list.clear();
                        list = loaiSachDAO.getData();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa, có sách tồn tại trong thể loại này", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaLoai, txtTenLoai;
        ImageView imgXoa, imgSua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSua = itemView.findViewById(R.id.imgSuaLoaiSach);
            imgXoa = itemView.findViewById(R.id.imgXoaLoaiSach);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoaiLS);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoaiLS);
        }
    }

}
