package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.HomeBlog;
import com.example.pnu_application.R;

import java.util.ArrayList;

public class HomeBlogAdapter extends BaseAdapter {
    Context context;
    int itemGridview;
    ArrayList<HomeBlog> blogs;

    public HomeBlogAdapter(Context context, int itemGridview, ArrayList<HomeBlog> blogs) {
        this.context = context;
        this.itemGridview = itemGridview;
        this.blogs = blogs;
    }

    @Override
    public int getCount() {
        return blogs.size();
    }

    @Override
    public Object getItem(int position) {
        return blogs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemGridview, null);
            holder.imvBlogThumb = view.findViewById(R.id.imvBlogThumb);
            holder.txtBlogName = view.findViewById(R.id.txtBlogName);

            view.setTag(holder);

        } else {
            holder  = (ViewHolder) view.getTag();
        }

        HomeBlog p = blogs.get(position);
        holder.imvBlogThumb.setImageResource(p.getBlogThumb());
        holder.txtBlogName.setText(p.getBlogName());
        return view;
    }
    private class ViewHolder{
        ImageView imvBlogThumb;
        TextView txtBlogName;
    }
}
