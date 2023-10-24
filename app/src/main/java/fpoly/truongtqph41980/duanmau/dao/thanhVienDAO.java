package fpoly.truongtqph41980.duanmau.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.truongtqph41980.duanmau.database.dbHelper;
import fpoly.truongtqph41980.duanmau.model.thanhVien;

public class thanhVienDAO {
    private dbHelper dbHelper;

    public thanhVienDAO(Context context) {
        this.dbHelper = new dbHelper(context);
    }
//    public  long insert (thanhVien obj){
//        ContentValues values = new ContentValues();
//        values.put("HOTENTV",obj.getHoTen());
//        values.put("NAMSINH",obj.getNamSinh());
//        return db.insert("THANHVIEN",null,values);
//    }
//    public int update(thanhVien obj){
//        ContentValues values = new ContentValues();
//        values.put("HOTENTV",obj.getHoTen());
//        values.put("NAMSINH",obj.getNamSinh());
//        return db.update("THANHVIEN",values,"MATV=?",new String[]{String.valueOf(obj.getMaTV())});
//
//    }
//    public int delete(String id){
//        return db.delete("THANHVIEN","MATV=?",new String[]{String.valueOf(id)});
//    }
    public ArrayList<thanhVien> selectAll(){
        ArrayList<thanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT*FROM THANHVIEN", null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
//                    private int maTV;
//                    private String hoTen;
//                    private String namSinh;
                    thanhVien thanhVien = new thanhVien();
                    thanhVien.setMaTV(cursor.getInt(0));
                    thanhVien.setHoTen(cursor.getString(1));
                    thanhVien.setNamSinh(cursor.getString(2));
                    thanhVien.setSotaikhoan(cursor.getInt(3));
                    list.add(thanhVien);
                    cursor.moveToNext();

                }
            }
        }catch (Exception e){
            Log.i(TAG, "loi",e);
        }
        return list;
    }
//    public List<thanhVien> getAll(String id){
//        String sql = "SELECT*FROM THANHVIEN WHERE maTV=?";
//        return selectAll(sql);
//    }
//    private List<thanhVien> getData(String sql, String...selectionArgs){
//        List<thanhVien> list = new ArrayList<thanhVien>();
//        Cursor c = db.rawQuery(sql,selectionArgs);
//        while (c.moveToNext()){
//            thanhVien obj = new thanhVien();
////            obj.hoTen = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
//        list.add(obj);
//        }
//
//        return list;
//    }
    public boolean themThanhVien(thanhVien tv){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTENTV",tv.getHoTen());
        values.put("NAMSINH", tv.getNamSinh());
        values.put("SOTAIKHOAN",tv.getSotaikhoan());
        long check = sqLiteDatabase.insert("THANHVIEN", null, values);
        return check>0;
    }
    public boolean suaThanhVien(thanhVien tv){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTENTV",tv.getHoTen());
        values.put("NAMSINH", tv.getNamSinh());
        long check = sqLiteDatabase.update("THANHVIEN", values, "MATV=?", new String[]{String.valueOf(tv.getMaTV())});

        return check>0;
    }
    public boolean xoaThanhVien(thanhVien tv){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("THANHVIEN","MATV=?",new String[]{String.valueOf(tv.getMaTV())});
        return check>0;

    }
}
