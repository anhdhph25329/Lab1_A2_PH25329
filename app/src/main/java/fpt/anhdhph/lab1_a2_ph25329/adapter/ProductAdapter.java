package fpt.anhdhph.lab1_a2_ph25329.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.anhdhph.lab1_a2_ph25329.R;
import fpt.anhdhph.lab1_a2_ph25329.model.Product;

public class ProductAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product> list;
    public ProductAdapter(Context context, ArrayList<Product> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view != null){
            row = view;
        }else {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.layout_row_list_product, null);
        }
        Product product = list.get(i);
        TextView tvId = row.findViewById(R.id.tvId);
        TextView tvName = row.findViewById(R.id.tvName);
        TextView tvPrice = row.findViewById(R.id.tvPrice);
        TextView tvCate = row.findViewById(R.id.tvCategory);

        tvId.setText(product.getId()+"");
        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice()+"");
        tvCate.setText(product.getId_cat()+"");


        return row;
    }
}
