package fpoly.truongtqph41980.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.truongtqph41980.duanmau.R;
import fpoly.truongtqph41980.duanmau.dao.phieuMuonDAO;
import fpoly.truongtqph41980.duanmau.model.phieuMuon;

public class phieuMuon_adapter extends RecyclerView.Adapter<phieuMuon_adapter.viewHolder> {
    private ArrayList<phieuMuon> list;
    private Context context;

    public phieuMuon_adapter(ArrayList<phieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon,parent,false);



        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        //        private int maPhieuMuon;
//        private String maTT;
//        private int maTV;
//        private int maSach;
//        private int tienThue;
//        private String ngay;
//        private int traSach;
        holder.txtMaPhieuMuonPM.setText("Mã PM: " + String.valueOf(list.get(position).getMaPhieuMuon()));
        holder.txtMaTTPM.setText("Mã Thủ Thư: " + list.get(position).getMaTT());
        holder.txtMaTvPM.setText("Mã Thành viên: " + String.valueOf(list.get(position).getMaTV()));
        holder.txtMaSachPM.setText("Mã sách: " + String.valueOf(list.get(position).getMaSach()));
        holder.txtTienThuePM.setText("Tiền thuê: " + String.valueOf(list.get(position).getTienThue()));
        holder.txtNgayPM.setText("Ngày: " + list.get(position).getNgay());
        String trangThai = "";
        if (list.get(position).getTraSach() == 1){
            trangThai = "Đã trả sách";
            holder.btnTraSach.setVisibility(View.GONE);
        }else {
            trangThai = "Chưa trả sách";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.txtTraSachPM.setText("Trạng thái: " + trangThai);

        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phieuMuonDAO phieuMuonDAO = new phieuMuonDAO(context);
                boolean kiemTra = phieuMuonDAO.traSach(list.get(holder.getAdapterPosition()).getMaPhieuMuon());
                if (kiemTra){
                    list.clear();
                    list = phieuMuonDAO.selectAllPhieuMuon();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thay đổi không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView txtMaPhieuMuonPM, txtMaTTPM, txtMaTvPM, txtMaSachPM, txtTienThuePM, txtNgayPM, txtTraSachPM;
        Button btnTraSach;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaPhieuMuonPM = itemView.findViewById(R.id.txtMaPhieuMuonPM);
            txtMaTTPM = itemView.findViewById(R.id.txtMaTTPM);
            txtMaTvPM = itemView.findViewById(R.id.txtMaTVPM);
            txtMaSachPM = itemView.findViewById(R.id.txtMaSachPM);
            txtTienThuePM = itemView.findViewById(R.id.txtTienThuePM);
            txtNgayPM = itemView.findViewById(R.id.txtNgayPM);
            txtTraSachPM = itemView.findViewById(R.id.txtTraSachPM);
            btnTraSach = itemView.findViewById(R.id.btnTraSachPM);

        }

    }


}

