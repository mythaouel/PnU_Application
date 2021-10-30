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

public class RecyclerViewCartAdapter extends RecyclerView.Adapter<RecyclerViewCartAdapter.MyViewHolder> {
    Context context;
    ArrayList<CartProduct> arrCartProduct;

    public RecyclerViewCartAdapter(Context context, ArrayList<CartProduct> arrCartProduct) {
        this.context = context;
        this.arrCartProduct = arrCartProduct;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imvCartThumb.setImageResource(arrCartProduct.get(position).getProductThumb());
        holder.txtCartName.setText(arrCartProduct.get( position ).getProductName());
        holder.txtCartQuantity.setText( String.valueOf( arrCartProduct.get( position ).getProductQuantity()  ));
        holder.txtCartPrice.setText( String.valueOf( arrCartProduct.get( position ).getProductPrice()  ));
    }

    @Override
    public int getItemCount() {
        return arrCartProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imvCartThumb;
        TextView txtCartName, txtCartQuantity, txtCartPrice;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            imvCartThumb = itemView.findViewById(R.id.imvCartThumb);
            txtCartName = itemView.findViewById( R.id.txtCartName );
            txtCartQuantity = itemView.findViewById( R.id.txtCartQuantity );
            txtCartPrice = itemView.findViewById( R.id.txtCartPrice );
        }
    }
}
