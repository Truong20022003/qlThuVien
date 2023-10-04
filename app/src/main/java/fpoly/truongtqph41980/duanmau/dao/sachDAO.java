package fpoly.truongtqph41980.duanmau.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpoly.truongtqph41980.duanmau.database.dbHelper;
import fpoly.truongtqph41980.duanmau.model.sach;

public class sachDAO {
    dbHelper dbHelper;
    public sachDAO(Context context){
        dbHelper = new dbHelper(context);
    }
    // lấy toàn bộ sách
    public ArrayList<sach> getAll(){
        ArrayList<sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH",null);
        if (cursor.moveToFirst()){
            do {
                list.add(new sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<sach> selectAll(){
        ArrayList<sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.MASACH, sc.TENSACH, sc.GIATHUE, sc.MALOAI, lo.TENLOAI FROM SACH sc, LOAISACH lo WHERE sc.MALOAI = lo.MALOAI",null);
        if (cursor.moveToFirst()){
            do {
                list.add(new sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3), cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
//    private final fpoly.truongtqph41980.duanmau.database.dbHelper dbHelper;
//
//    public sachDAO(fpoly.truongtqph41980.duanmau.database.dbHelper dbHelper) {
//        this.dbHelper = dbHelper;
//    }
//    public ArrayList<sach> sellectAll(){
//        ArrayList<sach> list = new ArrayList<sach>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        try {
////            private int maSach;
////            private String tenSach;
////            private int giaThue;
////            private int maLoai;
//            Cursor cursor = db.rawQuery("SELECT*FROM SACH ",null);
//            if (cursor.getCount() > 0){
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()){
//                    sach sach = new sach();
//                    sach.setMaSach(cursor.getInt(0));
//                    sach.setTenSach(cursor.getString(1));
//                    sach.setGiaThue(cursor.getInt(2));
//                    sach.setMaLoai(cursor.getInt(3));
//                    list.add(sach);
//                    cursor.moveToNext();
//                }
//            }
//        }catch (Exception e) {
//            Log.i(TAG, "loi", e);
//        }
//        return list;
//    }

    public boolean themSach(String tensach, int gia, int maLoaiS){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        private String tenSach;
//        private int giaThue;
//        private int maLoai;
        values.put("TENSACH",tensach);
        values.put("GIATHUE", gia);
        values.put("MALOAI", maLoaiS);
        long check = database.insert("SACH", null,values);
        return  check > 0;

    }
    public boolean suaSach(int maSach, String tenSach, int giaThue, int maLoai){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        private String tenSach;
//        private int giaThue;
//        private int maLoai;
        values.put("TENSACH",tenSach);
        values.put("GIATHUE", giaThue);
        values.put("MALOAI", maLoai);

        long check = database.update("SACH", values, "MASACH = ?", new String[]{String.valueOf(maSach)});
        return  check > 0;

    }
    public int xoaSach(int masach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM PHIEUMUON WHERE MASACH = ?",new String[]{String.valueOf(masach)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = database.delete("SACH", "MASACH = ?",new String[]{String.valueOf(masach)});
        if (check== -1){
            return 0;
        }else {
            return 1;
        }

    }
}
