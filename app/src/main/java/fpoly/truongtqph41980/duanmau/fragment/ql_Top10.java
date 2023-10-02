package fpoly.truongtqph41980.duanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fpoly.truongtqph41980.duanmau.R;
import fpoly.truongtqph41980.duanmau.adapter.top10_adapter;
import fpoly.truongtqph41980.duanmau.dao.thongKeDAO;
import fpoly.truongtqph41980.duanmau.model.sach;


public class ql_Top10 extends Fragment {



    public ql_Top10() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_ql__top10, container, false);
        RecyclerView rcvTop10 = view.findViewById(R.id.rcvTop10);

        thongKeDAO thongKeDAO = new thongKeDAO(getContext());
        ArrayList<sach> list = thongKeDAO.getTop10();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvTop10.setLayoutManager(layoutManager);

        top10_adapter adapter = new top10_adapter(getContext(),list);
        rcvTop10.setAdapter(adapter);

        return view;
    }
}