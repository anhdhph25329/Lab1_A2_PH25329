package fpt.anhdhph.lab1_a2_ph25329.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpt.anhdhph.lab1_a2_ph25329.database.DbHelper;
import fpt.anhdhph.lab1_a2_ph25329.model.Category;

public class CateDAO {
    DbHelper dbHelper;
    SQLiteDatabase db;
    Context context;

    public CateDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //hàm thêm dữ liệu
    public int addRow(Category category){
        ContentValues values = new ContentValues();
        values.put("name", category.getName());
        int kq = (int) db.insert("tb_cat", null, values);
        //kq: a nếu kq < 0 thì đó là ID của bản ghi mới sinh ra do cơ chế tự động tăng ID
        return kq;
    }

    //hàm sửa dữ liệu
    public boolean updateRow(Category category){
        ContentValues values = new ContentValues();
        values.put("name", category.getName());
        int kq = db.update("tb_cat", values, "id = ?", new String[]{category.getId()+""});
        return kq > 0;
    }

    //hàm xóa dữ liệu
    public boolean deleteRow(Category category){
        int kq = db.delete("tb_cat", "id = ?", new String[]{category.getId()+""});
        return kq > 0;
    }

    //hàm lấy danh sách
    public ArrayList<Category> getList(){
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT id, name FROM tb_cat";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0){
            //lấy được dữ liệu
            cursor.moveToFirst();

            //duyệt vòng lặp
            do {
                //thứ tự cột: id là 0, name là 1
                int id = cursor.getInt(0);
                String name = cursor.getString(1);

                Category category = new Category();
                category.setId(id);
                category.setName(name);
                //thêm vào list
                list.add(category);
            }while (cursor.moveToNext());

        }else {
            Log.d("zzz", "CategoryDAO::getList: Không lấy được dữ liệu");
        }
        return list;
    }

}
