package fpoly.truongtqph41980.duanmau.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpoly.truongtqph41980.duanmau.database.dbHelper;
import fpoly.truongtqph41980.duanmau.model.loaiSach;

public class loaiSachDAO {
    private final dbHelper dbHelper;

    public loaiSachDAO(Context context) {
        this.dbHelper = new dbHelper(context);
    }


    public ArrayList<loaiSach> getData(){
        ArrayList<loaiSach> list = new ArrayList<loaiSach>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("Select*from LOAISACH",null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    loaiSach loaiSach = new loaiSach();
                    loaiSach.setMaLoai(cursor.getInt(0));
                    loaiSach.setTenLoai(cursor.getString(1));
                    list.add(loaiSach);
                    cursor.moveToNext();
                }

            }
        }catch (Exception e){
            Log.i(TAG, "loi",e);
        }
        return list;
    }

    public boolean themLoaiSach(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI",tenLoai);
        long check = sqLiteDatabase.insert("LOAISACH", null,values);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    //xóa loại sách
    // 1: xóa thành công - 0: xóa thất bại - -1: có sách tồn tại trong loại này
    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE MALOAI = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("LOAISACH","MALOAI = ?",new String[]{String.valueOf(id)});
        if (check == -1){
            return 0;

        }else {
            return 1;
        }
    }
    public boolean suaLoaiSach(loaiSach loaiSach){

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI",loaiSach.getTenLoai());
        long check = sqLiteDatabase.update("LOAISACH",values,"MALOAI=?",new String[]{String.valueOf(loaiSach.getMaLoai())});
        return check>0;
    }

}
