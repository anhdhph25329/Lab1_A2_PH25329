package fpt.anhdhph.lab1_a2_ph25329.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    Context context;
    public DbHelper(Context context) {
        super(context, "QLBH.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng danh mục
        String sqlCategory = "CREATE TABLE tb_cat (\n" +
                "    id   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name TEXT    UNIQUE\n" +
                "                 NOT NULL\n" +
                ");";
        db.execSQL(sqlCategory);


        // Tạo bảng sản phẩm
        String sqlProduct = "CREATE TABLE tb_product (\n" +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name   TEXT    UNIQUE,\n" +
                "    price  REAL    DEFAULT (0.0) \n" +
                "                   NOT NULL,\n" +
                "    id_cat INTEGER REFERENCES tb_cat (id) \n" +
                ");\n";
        db.execSQL(sqlProduct);

        //mỗi khi chỉnh sửa câu lệnh SQL ở trên thì tăng version ở hàm khởi tạo
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i < i1){ //i1 là phiên bản mới, i là phiên bản cũ
            db.execSQL("DROP TABLE IF EXISTS tb_cat");
            db.execSQL("DROP TABLE IF EXISTS tb_product");
            onCreate(db);
        }
    }
}
