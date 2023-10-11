package fpoly.truongtqph41980.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.truongtqph41980.duanmau.database.dbHelper;
import fpoly.truongtqph41980.duanmau.model.thuThu;

public class thuThuDAO {
    private final dbHelper dbHelper;
    SharedPreferences sharedPreferences;

    public thuThuDAO(Context context) {
        this.dbHelper = new dbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", context.MODE_PRIVATE);
    }
    public ArrayList<thuThu> selectAll(){
        ArrayList<thuThu> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM THUTHU", null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    thuThu thuThu = new thuThu();
//                    private String maTT;
//                    private String hoTen;
//                    private String matKhau;
                    thuThu.setMaTT(cursor.getString(0));
                    thuThu.setHoTen(cursor.getString(1));
                    thuThu.setMatKhau(cursor.getString(2));
                    list.add(thuThu);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){

        }
        return list;
    }
    public boolean checkDangNhap(String matt, String mk){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM THUTHU WHERE MATT=? AND MATKHAU=?",new String[]{matt,mk});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("MATT", cursor.getString(0));
            editor.putString("HOTEN",cursor.getString(1));
            editor.putString("MATKHAU",cursor.getString(2));
            editor.putString("LOAITAIKHOAN",cursor.getString(3));
            editor.commit();
            return true;
        }else {
            return false;
        }
    }

    public boolean capNhatMatKhau(String username, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE MATT = ? AND MATKHAU = ?",new String[]{username,oldPass});
        if (cursor.getCount() > 0){
            ContentValues values = new ContentValues();
            values.put("MATKHAU", newPass);
            long check = sqLiteDatabase.update("THUTHU", values, "MATT = ?", new String[]{username});
            if (check == -1){
                return false;
            }else {
                return true;
            }
        }else {

            return false;
        }
    }
}
