package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Tips;
import com.example.pnu_application.R;

import java.util.ArrayList;
import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {
    Context context;
    ArrayList<Tips> tips;

    public TipsAdapter(Context context, ArrayList<Tips> tips) {
        this.context = context;
        this.tips = tips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tips,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tips tip=tips.get(position);
        if(tip==null){
            return;
        }
        holder.imvTips.setImageResource(tip.getImvTips());
        holder.txtTips.setText(tip.getTxtTips());

    }

    @Override
    public int getItemCount() {
        if(tips!=null){
            return tips.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imvTips;
        private TextView txtTips;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvTips=itemView.findViewById((R.id.imvTips));
            txtTips=itemView.findViewById(R.id.txtTips);
        }
    }
}
