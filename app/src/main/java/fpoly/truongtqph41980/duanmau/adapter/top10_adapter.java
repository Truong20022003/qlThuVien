package fpoly.truongtqph41980.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.truongtqph41980.duanmau.R;
import fpoly.truongtqph41980.duanmau.model.sach;

public class top10_adapter extends RecyclerView.Adapter<top10_adapter.ViewHolder> {

    private final Context context;
    private final ArrayList<sach> list;

    public top10_adapter(Context context, ArrayList<sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcvtop10,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtMaSach.setText("Mã sách: " + String.valueOf(list.get(position).getMaSach()));
        holder.txtTenSach.setText("Tên sách: " + list.get(position).getTenSach());
        holder.txtSoLuongMuon.setText("Số lượng đã mượn: " + String.valueOf(list.get(position).getSoluongdamuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
       TextView txtMaSach, txtTenSach, txtSoLuongMuon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.txtMaSachTop10);
            txtTenSach = itemView.findViewById(R.id.txtTenSachTop10);
            txtSoLuongMuon = itemView.findViewById(R.id.txtSoLuongMuonTop10);
        }
    }
}
