package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.SpNoiBat;
import com.example.pnu_application.R;

import java.util.List;

public class SpNoiBatAdapter extends RecyclerView.Adapter<SpNoiBatAdapter.ViewHolder>{
    Context context;
    List<SpNoiBat> spNoiBat;

    public SpNoiBatAdapter(Context context, List<SpNoiBat> spNoiBat){
        this.context=context;
        this.spNoiBat=spNoiBat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spnoibat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpNoiBat noiBat=spNoiBat.get(position);
        if(noiBat==null){
            return;
        }
        holder.imvHinh.setImageResource(noiBat.getImvHinh());
        holder.txtTen.setText(noiBat.getTxtTen());

    }

    @Override
    public int getItemCount() {
        if(spNoiBat!=null){
            return spNoiBat.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imvHinh;
        private TextView txtTen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvHinh=itemView.findViewById((R.id.imvSpNoiBat));
            txtTen=itemView.findViewById(R.id.txtSpNoiBat);
        }
    }
}
