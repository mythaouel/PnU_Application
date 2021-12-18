package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.Blog;
import com.example.pnu_application.R;

import java.util.List;

public class BlogAdapter extends BaseAdapter {
    Context context;
    int notification_blog_item;
    List<Blog> blogs;

    public BlogAdapter(Context context, int notification_blog_item, List<Blog> blogs) {
        this.context = context;
        this.notification_blog_item = notification_blog_item;
        this.blogs = blogs;
    }

    @Override
    public int getCount() {
        return blogs.size();
    }

    @Override
    public Object getItem(int i) {
        return blogs.get( i );
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate( notification_blog_item,null );
            holder.txtTitle = view.findViewById( R.id.txtTitle );
            holder.txtDetail = view.findViewById( R.id.txtDetail );
            view.setTag( holder );
        }else {
            holder = (ViewHolder) view.getTag();
        }
        Blog b = blogs.get( i );
        holder.txtTitle.setText( b.getBlogTitle() );
        holder.txtDetail.setText( b.getBlogDetail() );
        return view;
    }

    public static class ViewHolder {
        TextView txtTitle, txtDetail;
    }
}
