package com.example.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventbus.TotalCalculator;
import com.example.fragment.CartFragment;
import com.example.model.CartProduct;
import com.example.model.ImageButtonClick;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import utils.Constant;

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
        holder.txtCartQuantity.setText( String.valueOf(arrCartProduct.get( position ).getProductQuantity()));
        //holder.txtCartPrice.setText( String. format("%.3f", arrCartProduct.get( position ).getProductPrice())+ " " + "đ");
        holder.txtCartPrice.setText( Constant.decimalFormat.format( arrCartProduct.get( position ).getProductPrice() ));

        //Add Events for Buttons
        holder.setImageButtonClick( new ImageButtonClick() {
            @Override
            public void onImageClick(View view, int position, int flag) {
                //flag: 1 || 2 -> Event for decrement and increment button
                if (flag != 3){
                    if (flag == 1){
                        if(arrCartProduct.get( position ).getProductQuantity() > 1){
                            int newQty = arrCartProduct.get( position ).getProductQuantity() - 1;
                            arrCartProduct.get( position ).setProductQuantity( newQty );
                        }
                    }else if (flag == 2){
                        if(arrCartProduct.get( position ).getProductQuantity() < 10) {
                            int newQty = arrCartProduct.get( position ).getProductQuantity() + 1;
                            arrCartProduct.get( position ).setProductQuantity( newQty );
                        }
                    }
                    //change quantity in textview
                    holder.txtCartQuantity.setText(String.valueOf(arrCartProduct.get( position ).getProductQuantity()));
                    //update total
                    EventBus.getDefault().postSticky( new TotalCalculator() );
                }

                //flag = 3 -> Event delete item from Cart
                else if(flag == 3){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle( "Xóa?" );
                    builder.setMessage( "Xóa sản phẩm khỏi giỏ hàng?" );
                    builder.setPositiveButton( "Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            arrCartProduct.remove( position );
                            notifyDataSetChanged();
                            EventBus.getDefault().postSticky( new TotalCalculator() );
                            //Kiểm tra số lượng giỏ hàng, nếu không còn sản phẩm sẽ hiện màn hình giỏ hàng trống
                            if(Constant.arrCartProduct.size() == 0){
                                CartFragment.cartView.setVisibility( CartFragment.view.GONE );
                                CartFragment.emptyCartView.setVisibility( CartFragment.view.VISIBLE );
                            }
                        }
                    } );
                    builder.setNegativeButton( "Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    } );
                    builder.show();
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return arrCartProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imvCartThumb, imvPlus, imvMinus, imvDelete;
        TextView txtCartName, txtCartQuantity, txtCartPrice, txtTongCong;
        ImageButtonClick listener;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            imvCartThumb = itemView.findViewById(R.id.imvCartThumb);
            txtCartName = itemView.findViewById( R.id.txtCartName );
            txtCartQuantity = itemView.findViewById( R.id.txtCartQuantity );
            txtCartPrice = itemView.findViewById( R.id.txtCartPrice );
            txtTongCong = itemView.findViewById( R.id.txtTongCong );
            imvPlus = itemView.findViewById( R.id.imvPlus );
            imvMinus = itemView.findViewById( R.id.imvMinus );
            imvDelete = itemView.findViewById( R.id.imvDelete );

            imvMinus.setOnClickListener( this );
            imvPlus.setOnClickListener( this );
            imvDelete.setOnClickListener( this );
        }

        public void setImageButtonClick(ImageButtonClick listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            if (view == imvMinus){
                listener.onImageClick(view,getBindingAdapterPosition(),1);
            }else if (view == imvPlus){
                listener.onImageClick(view,getBindingAdapterPosition(),2);
            }else if (view == imvDelete){
                listener.onImageClick(view,getBindingAdapterPosition(),3);
            }
        }
    }
}
