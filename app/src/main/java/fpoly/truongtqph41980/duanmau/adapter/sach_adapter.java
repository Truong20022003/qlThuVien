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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import fpoly.truongtqph41980.duanmau.R;
import fpoly.truongtqph41980.duanmau.dao.sachDAO;
import fpoly.truongtqph41980.duanmau.model.sach;

public class sach_adapter extends RecyclerView.Adapter<sach_adapter.ViewHolder>{
private Context context;
private ArrayList<sach> list;
private ArrayList<HashMap<String, Object>> listHM;
private sachDAO sachDAO;

    public sach_adapter(Context context, ArrayList<sach> list, ArrayList<HashMap<String,Object>> listHM, sachDAO sachDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sachDAO = sachDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcvrsach,parent,false);
//        RecyclerView rcv_sach = view.findViewById(R.id.);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSach.setText("Mã sách: "+String.valueOf(list.get(position).getMaSach()));
        holder.txtTenSach.setText("Tên sách: "+list.get(position).getTenSach());
        holder.txtGiaThue.setText("Giá thuê: "+String.valueOf(list.get(position).getGiaThue()));
        holder.txtMaLoai.setText("Mã loại: "+String.valueOf(list.get(position).getMaLoai()));
        holder.txtTenLoai.setText("Tên loại: "+list.get(position).getTenLoaiS());
        holder.txtSoLuongSach.setText("Số lượng: "+ String.valueOf(list.get(position).getSoLuongSach()));

        holder.imgXoaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = sachDAO.xoaSach(list.get(holder.getAdapterPosition()).getMaSach());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa vì sách có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }
            }
        });
        holder.imgSuaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView txtMaSach, txtTenSach, txtGiaThue, txtMaLoai, txtTenLoai,txtSoLuongSach;
        ImageView imgSuaSach, imgXoaSach;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.txtMaSachS);
            txtTenSach = itemView.findViewById(R.id.txtTenSachS);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThueS);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoaiS);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoaiS);
            imgSuaSach = itemView.findViewById(R.id.imgSuaSachS);
            imgXoaSach = itemView.findViewById(R.id.imgXoaSachS);
            txtSoLuongSach = itemView.findViewById(R.id.txtSoLuongSach);
        }

    }
    private void showDialog(sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_suasach,null);
        builder.setView(view);
        EditText edtSUASoLuongSACH = view.findViewById(R.id.edtSUASoLuongSACH);
        TextView txtSuaMaSach = view.findViewById(R.id.txtSuaMaSach);
        EditText edtTenSach = view.findViewById(R.id.edtSuaTenSachS);
        EditText edtGiaThue = view.findViewById(R.id.edtSuaGiaThueS);
        Spinner spnSuaMaLoaiS = view.findViewById(R.id.spnSuaMaLoaiS);

        txtSuaMaSach.setText("Mã sách: "+sach.getMaSach());
        edtTenSach.setText(sach.getTenSach());
        edtGiaThue.setText(String.valueOf(sach.getGiaThue()));
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"TENLOAI"},
                new int[]{android.R.id.text1}
        );
        spnSuaMaLoaiS.setAdapter(simpleAdapter);
        int index = 0;
        int postion = -1;
        for (HashMap<String, Object> item : listHM){
            if ((int) item.get("MALOAI") == sach.getMaLoai()) {
                postion = index;
            }else {
                index++;
            }
        }
        spnSuaMaLoaiS.setSelection(postion);
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenSach = edtTenSach.getText().toString();
                int gia = Integer.parseInt(edtGiaThue.getText().toString());
                HashMap<String, Object> hss = (HashMap<String, Object>) spnSuaMaLoaiS.getSelectedItem();
                int maLoaiSach = (int) hss.get("MALOAI");
                int sl = Integer.parseInt(edtSUASoLuongSACH.getText().toString());
                boolean check = sachDAO.suaSach(sach.getMaSach(),tenSach,gia,maLoaiSach,sl);
                if (check){
                    Toast.makeText(context, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
    private void loadData(){
        list.clear();
        list = sachDAO.selectAll();
        notifyDataSetChanged();
    }
}
