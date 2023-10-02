package fpoly.truongtqph41980.duanmau.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import fpoly.truongtqph41980.duanmau.database.dbHelper;
import fpoly.truongtqph41980.duanmau.model.phieuMuon;

public class phieuMuonDAO {
    private  final dbHelper dbHelper;

    public phieuMuonDAO(Context context) {
        this.dbHelper = new dbHelper(context);
    }
    public ArrayList<phieuMuon> selectAllPhieuMuon(){
        ArrayList<phieuMuon> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("Select*from PHIEUMUON ORDER BY PHIEUMUON.MAPM DESC",null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
//                    MAPM INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    " MATT TEXT REFERENCES THUTHU(MATT)," +
//                            " MATV INTEGER REFERENCES THANHVIEN(MATV)," +
//                            " MASACH INTEGER REFERENCES SACH(MASACH)," +
//                            " TIENTHUE INTEGER NOT NULL, " +
//                            " NGAY TEXT NOT NULL," +
//                            " TRASACH INTEGER NOT NULL)";
                    phieuMuon phieuMuon = new phieuMuon();
                    phieuMuon.setMaPhieuMuon(cursor.getInt(0));
                    phieuMuon.setMaTT(cursor.getString(1));
                    phieuMuon.setMaTV(cursor.getInt(2));
                    phieuMuon.setMaSach(cursor.getInt(3));
                    phieuMuon.setTienThue(cursor.getInt(4));
                    phieuMuon.setNgay(cursor.getString(5));
                    phieuMuon.setTraSach(cursor.getInt(6));
                    list.add(phieuMuon);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i(TAG, "loi",e);
        }
        return list;
    }
    public boolean traSach(int maPm){
    SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TRASACH",1);
        long check = sqLiteDatabase.update("PHIEUMUON",values,"maPm = ?",new  String[]{String.valueOf(maPm)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean insertPhieuMuon(phieuMuon pm){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        MAPM INTEGER PRIMARY KEY AUTOINCREMENT," +
////                    " MATT TEXT REFERENCES THUTHU(MATT)," +
////                            " MATV INTEGER REFERENCES THANHVIEN(MATV)," +
////                            " MASACH INTEGER REFERENCES SACH(MASACH)," +
////                            " TIENTHUE INTEGER NOT NULL, " +
////                            " NGAY TEXT NOT NULL," +
////                            " TRASACH INTEGER NOT NULL)";
//        values.put("MAPM", pm.getMaPhieuMuon());
        values.put("MATT", pm.getMaTT());
        values.put("MATV",pm.getMaTV());
        values.put("MASACH", pm.getMaSach());
        values.put("TIENTHUE", pm.getTienThue());
        values.put("NGAY", pm.getNgay());
        values.put("TRASACH", pm.getTraSach());
        long check = sqLiteDatabase.insert("PHIEUMUON", null,values);
        if (check == -1){
            return false;
        }else {
            return true;
        }

    }
}
