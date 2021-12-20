package com.example.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragment.HomeFragment;
import com.example.model.HomeBlog;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

import utils.Constant;

public class HomeBlogFragment extends Fragment {

    ImageButton btnBlogBack;
    HomeBlog homeBlog=null;
    ImageView imvBlogBanner;
    TextView txtBlogDetailsName, txtBlogContent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_blog_details, container, false);
        MainActivity.hideBottomNav();

        btnBlogBack=view.findViewById(R.id.btnBlogBack);
        imvBlogBanner=view.findViewById(R.id.imvBlogBanner);
        txtBlogDetailsName=view.findViewById(R.id.txtBlogDetailsName);
        txtBlogContent=view.findViewById(R.id.txtBlogContent);

        Bundle bundle=getArguments();
        if (bundle != null){
            homeBlog = (HomeBlog) bundle.getSerializable(Constant.SELECTED_HOME_BLOG);
            imvBlogBanner.setImageResource(homeBlog.getBlogBanner());
            txtBlogDetailsName.setText(homeBlog.getBlogName());
            txtBlogContent.setText(homeBlog.getBlogContent());
        }



        btnBlogBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new HomeFragment());
                transaction.commit();
                MainActivity.showBottomNav();
            }
        });

        return view;
    }
}
