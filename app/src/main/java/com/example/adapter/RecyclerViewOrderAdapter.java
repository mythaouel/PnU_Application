package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.CartProduct;
import com.example.pnu_application.R;

import java.util.ArrayList;

public class RecyclerViewOrderAdapter extends RecyclerView.Adapter<RecyclerViewOrderAdapter.MyViewHolder>{
    Context context;
    ArrayList<CartProduct> arrOrderProduct;

    public RecyclerViewOrderAdapter(Context context, ArrayList<CartProduct> arrOrderProduct) {
        this.context = context;
        this.arrOrderProduct = arrOrderProduct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.order_item,parent,false );
        MyViewHolder holder = new MyViewHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imvOrderThumb.setImageResource(arrOrderProduct.get(position).getProductThumb());
        holder.txtOrderName.setText(arrOrderProduct.get( position ).getProductName());
        holder.txtOrderQuantity.setText( String.valueOf( arrOrderProduct.get( position ).getProductQuantity()  ));
        holder.txtOrderPrice.setText( String.valueOf( arrOrderProduct.get( position ).getProductPrice()  ));

    }

    @Override
    public int getItemCount() {
        return arrOrderProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imvOrderThumb;
        TextView txtOrderName, txtOrderQuantity, txtOrderPrice;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            imvOrderThumb = itemView.findViewById( R.id.imvOrderThumb);
            txtOrderName = itemView.findViewById( R.id.txtOrderName );
            txtOrderQuantity = itemView.findViewById( R.id.txtOrderQuantity );
            txtOrderPrice = itemView.findViewById( R.id.txtOrderPrice );
        }
    }
}
