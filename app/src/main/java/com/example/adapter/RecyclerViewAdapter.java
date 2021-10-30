package com.example.adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Blog;
import com.example.pnu_application.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Blog> blogs;


    public RecyclerViewAdapter(Context context, List<Blog> blogs) {
        this.context = context;
        this.blogs = blogs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notification_blog_item,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.txtTitle.setText(blogs.get(position).getBlogTitle());
        holder.txtDetail.setText(blogs.get(position).getBlogDetail());
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDetail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDetail = itemView.findViewById(R.id.txtDetail);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
