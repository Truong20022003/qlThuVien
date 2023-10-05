package fpoly.truongtqph41980.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    public dbHelper(@Nullable Context context) {
        super(context, "QLTV", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        // Bảng thủ thư
        String dbThuThu = "CREATE TABLE THUTHU(MATT TEXT PRIMARY KEY," +
                                                    " HOTEN TEXT NOT NULL," +
                                                    " MATKHAU TEXT NOT NULL, " +
                                                    "LOAITAIKHOAN TEXT NOT NULL)";
        db.execSQL(dbThuThu);
        db.execSQL("INSERT INTO THUTHU VALUES('thuthu01','nguyễn văn a','609n','admin'),('thuthu02','nguyễn văn b','609n','thuthu'),('thuthu03','nguyễn văn c','609n','thuthu')");


       //Bảng thành viên
        String dbThanhVien = "CREATE TABLE THANHVIEN(MATV INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    "HOTENTV TEXT NOT NULL," +
                                                    "NAMSINH TEXT NOT NULL)";
        db.execSQL(dbThanhVien);
        db.execSQL("INSERT INTO THANHVIEN VALUES(1,'Trần Quang Trường','2003'),(2,'Phạm Thị Hồng Nhung','2003'),(3,'Trần Ngọc Hoàng','2004')");
        // Bảng loại sách
        String dbLoaiSach = "CREATE TABLE LOAISACH(MALOAI INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    " TENLOAI TEXT NOT NULL)";
        db.execSQL(dbLoaiSach);
        db.execSQL("INSERT INTO LOAISACH VALUES(1,'Truyện tranh'),(2,'Sách giáo khoa'),(3,'Truyện trinh thám')");
        // Bảng sách
        String dbSach = "CREATE TABLE SACH(MASACH INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    " TENSACH TEXT NOT NULL," +
                                                    " GIATHUE INTEGER NOT NULL," +
                                                    " MALOAI INTEGER REFERENCES LOAISACH(MALOAI))";
        db.execSQL(dbSach);
        db.execSQL("INSERT INTO SACH VALUES(1,'Sách ngữ văn',20000,2), (2,'Doraemon',30000,1), (3,'Harry Potter',10000,3)");
        // Bảng phiếu mượn
        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(MAPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    " MATT TEXT REFERENCES THUTHU(MATT)," +
                                                    " MATV INTEGER REFERENCES THANHVIEN(MATV)," +
                                                    " MASACH INTEGER REFERENCES SACH(MASACH)," +
                                                    " TIENTHUE INTEGER NOT NULL, " +
                                                    " NGAY TEXT NOT NULL," +
                                                    " TRASACH INTEGER NOT NULL)";
        db.execSQL(dbPhieuMuon);
        db.execSQL("INSERT INTO PHIEUMUON VALUES(1,'thuthu01',1,1,50000,'28/09/2023',1),(2,'thuthu02',1,1,30000,'29/09/2023',2),(3,'thuthu01',2,2,20000,'28/09/2023',2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            String dropTBLoaiThuThu = "DROP TABLE IF EXISTS THUTHU";
            db.execSQL(dropTBLoaiThuThu);
            String dropTBThanhVien = "DROP TABLE IF EXISTS THANHVIEN";
            db.execSQL(dropTBThanhVien);
            String dropTBLoaiSach = "DROP TABLE IF EXISTS LOAISACH";
            db.execSQL(dropTBLoaiSach);
            String dropTBSACH = "DROP TABLE IF EXISTS SACH";
            db.execSQL(dropTBSACH);
            String dropTBPhieuMuon = "DROP TABLE IF EXISTS PHIEUMUON";
            db.execSQL(dropTBPhieuMuon);
            onCreate(db);
        }
    }
}
