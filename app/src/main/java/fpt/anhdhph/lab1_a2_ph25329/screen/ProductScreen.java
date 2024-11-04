package fpt.anhdhph.lab1_a2_ph25329.screen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

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

    EditText edtProductName, edtPrice;
    Spinner spCategory;
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

    }

    public void anhXa(){
        edtProductName = findViewById(R.id.edtProductName);
        edtPrice = findViewById(R.id.edtPrice);
        spCategory = findViewById(R.id.spCategory);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvProduct = findViewById(R.id.lvProduct);
    }

}