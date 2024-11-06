package fpt.anhdhph.lab1_a2_ph25329.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import fpt.anhdhph.lab1_a2_ph25329.R;
import fpt.anhdhph.lab1_a2_ph25329.adapter.ProductAdapter;
import fpt.anhdhph.lab1_a2_ph25329.dao.ProductDAO;
import fpt.anhdhph.lab1_a2_ph25329.model.Product;

public class ProductScreen extends AppCompatActivity {

    EditText edtProductName, edtPrice, edtIdCate;
    Button btnAdd, btnUpdate, btnDelete;
    ListView lvProduct;

    ProductDAO productDAO;
    ArrayList<Product> list;
    ProductAdapter adapter;
    Product currentProduct = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhXa();

        productDAO = new ProductDAO(this);

        list = productDAO.getList();

        adapter = new ProductAdapter(this, list);
        lvProduct.setAdapter(adapter);

        addRow();

        updateRow();

        deleteRow();

    }

    public void anhXa(){
        edtProductName = findViewById(R.id.edtProductName);
        edtPrice = findViewById(R.id.edtPrice);
        edtIdCate = findViewById(R.id.edtIdCate);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvProduct = findViewById(R.id.lvProduct);
    }

    public void addRow(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtProductName.getText().toString();
                double price = Double.parseDouble(edtPrice.getText().toString());
                int id_cat = Integer.parseInt(edtIdCate.getText().toString());

                if (name.length() < 5) {
                    Toast.makeText(ProductScreen.this, "Tên sản phẩm phải có ít nhất 5 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }

                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setId_cat(id_cat);

                int kq = productDAO.addRow(product);

                if (kq > 0) {
                    list.clear();
                    list.addAll(productDAO.getList());
                    adapter.notifyDataSetChanged();
                    edtProductName.setText("");
                    edtPrice.setText("");
                    edtIdCate.setText("");
                    Toast.makeText(ProductScreen.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductScreen.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateRow(){
        lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("zz", "onItemLongClick: i = " + i + ", l = " + l );
                currentProduct = list.get(i);
                edtProductName.setText(currentProduct.getName());
                edtPrice.setText(currentProduct.getPrice() + "");
                edtIdCate.setText(currentProduct.getId_cat() + "");

                return true;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtProductName.getText().toString();
                double price = Double.parseDouble(edtPrice.getText().toString());
                int id_cat = Integer.parseInt(edtIdCate.getText().toString());

                if(name.length() < 5){
                    Toast.makeText(ProductScreen.this, "Tên sản phẩm phải có ít nhất 5 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    currentProduct.setName(name);
                    currentProduct.setPrice(price);
                    currentProduct.setId_cat(id_cat);

                    boolean kq = productDAO.updateRow(currentProduct);
                    if(kq){
                        list.clear();
                        list.addAll(productDAO.getList());
                        adapter.notifyDataSetChanged();
                        currentProduct = null;
                        edtProductName.setText("");
                        edtPrice.setText("");
                        edtIdCate.setText("");
                        Toast.makeText(ProductScreen.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ProductScreen.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void deleteRow(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentProduct != null){
                    boolean kq = productDAO.deleteRow(currentProduct);
                    if(kq){
                        list.clear();
                        list.addAll(productDAO.getList());
                        adapter.notifyDataSetChanged();
                        currentProduct = null;
                        edtProductName.setText("");
                        edtPrice.setText("");
                        edtIdCate.setText("");
                    }else {
                        Toast.makeText(ProductScreen.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ProductScreen.this, "Vui lòng chọn dòng cần xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}