package fpt.anhdhph.lab1_a2_ph25329.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpt.anhdhph.lab1_a2_ph25329.R;
import fpt.anhdhph.lab1_a2_ph25329.adapter.CateAdapter;
import fpt.anhdhph.lab1_a2_ph25329.dao.CateDAO;
import fpt.anhdhph.lab1_a2_ph25329.model.Category;

public class CategoryScreen extends AppCompatActivity {

    EditText edtCategoryName;
    Button btnAdd, btnUpdate, btnDelete;
    ListView lvCategory;

    CateDAO cateDAO;
    ArrayList<Category> list;
    CateAdapter adapter;
    Category currentCate = null;
    String TAG = "zz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhXa();

        //khởi tạo DAO
        cateDAO = new CateDAO(this);
        //khởi tạo list
        list = cateDAO.getList();
        //khởi tạo adapter
        adapter = new CateAdapter(this, list);
        lvCategory.setAdapter(adapter);

        addRow();

        updateRow();

        deleteRow();

    }

    public void anhXa(){
        edtCategoryName = findViewById(R.id.edtCategoryName);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvCategory = findViewById(R.id.lvCategory);
    }

    public void addRow(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtCategoryName.getText().toString();
                // kiểm tra hợp lệ dữ liệu truowcs khi thêm, VD:
                if(name.length() < 3){
                    Toast.makeText(CategoryScreen.this, "Tên thể loại phải có ít nhất 3 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }

                Category category = new Category();
                category.setName(name);
                //2. Ghi vao CSDL
                int kq = cateDAO.addRow(category);
                //3. Cập nhật ds
                if(kq > 0){ // Ghi thành công
                    list.clear(); // xóa hết
                    list.addAll(cateDAO.getList());
                    adapter.notifyDataSetChanged();
                    edtCategoryName.setText("");
                    Toast.makeText(CategoryScreen.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CategoryScreen.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateRow(){
        lvCategory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // ghi log để xem 2 tham số i, l là gì
                Log.d(TAG, "onItemLongClick: i = " + i + ", l = " + l );
                // xem log--> i là thứ tự phần tử trong arrayList, l là id trong bảng
                currentCate = list.get(i);
                edtCategoryName.setText(currentCate.getName());

                return true;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lấy dữ liệu
                String name = edtCategoryName.getText().toString();
                //validate
                if(name.length() < 3){
                    Toast.makeText(CategoryScreen.this, "Tên thể loại phải có ít nhất 3 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    currentCate.setName(name);
                    boolean kq = cateDAO.updateRow(currentCate);
                    if(kq){
                        list.clear();
                        list.addAll(cateDAO.getList());
                        adapter.notifyDataSetChanged();
                        currentCate = null;
                        edtCategoryName.setText("");
                    }else {
                        Toast.makeText(CategoryScreen.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    public void deleteRow(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentCate != null){
                    boolean kq = cateDAO.deleteRow(currentCate);
                    if(kq){
                        list.clear();
                        list.addAll(cateDAO.getList());
                        adapter.notifyDataSetChanged();
                        currentCate = null;
                        edtCategoryName.setText("");
                    }else {
                        Toast.makeText(CategoryScreen.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(CategoryScreen.this, "Vui lòng chọn dòng cần xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}