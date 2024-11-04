package fpt.anhdhph.lab1_a2_ph25329.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpt.anhdhph.lab1_a2_ph25329.database.DbHelper;
import fpt.anhdhph.lab1_a2_ph25329.model.Product;

public class ProductDAO {
    DbHelper dbHelper;
    SQLiteDatabase db;
    Context context;

    public ProductDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //hàm thêm dữ liệu
    public int addRow(Product product){
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        int kq = (int) db.insert("tb_product", null, values);
        //kq: a nếu kq < 0 thì đó là ID của bản ghi mới sinh ra do cơ chế tự động tăng ID
        return kq;
    }

    //hàm sửa dữ liệu
    public boolean updateRow(Product product){
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        int kq = db.update("tb_product", values, "id = ?", new String[]{product.getId()+""});
        return kq > 0;
    }

    //hàm xóa dữ liệu
    public boolean deleteRow(Product product){
        int kq = db.delete("tb_product", "id = ?", new String[]{product.getId()+""});
        return kq > 0;
    }

    //hàm lấy danh sách'
    public ArrayList<Product> getList(){
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT id, name, price, id_cat FROM tb_product";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double price = cursor.getDouble(2);
                int id_cat = cursor.getInt(3);

                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                product.setId_cat(id_cat);

                list.add(product);
            }while (cursor.moveToNext());
        }else {
            Log.d("zzz", "ProductDAO::getList: Không lấy được dữ liệu");
        }
        return list;
    }
}
