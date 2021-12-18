package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.model.Blog;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

import utils.Constant;

public class BlogDetailFragment extends Fragment {
    TextView txtTitle, txtDetail;
    Blog blog = null;
    ImageView imvBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_blog_detail,container,false);
        txtTitle = view.findViewById( R.id.txtTitle );
        txtDetail = view.findViewById( R.id.txtDetail );
        imvBack = view.findViewById( R.id.imvBack );
        MainActivity.hideBottomNav();
        addEvents();

        Bundle bundle = getArguments();
        if (bundle != null){
            blog = (Blog) bundle.getSerializable( Constant.SELECTED_BLOG );
            txtTitle.setText(blog.getBlogTitle());
            txtDetail.setText(blog.getBlogDetail());
        }
        return view;
    }

    private void addEvents() {
        imvBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null)
                    getFragmentManager().popBackStack();
            }
        } );
    }
    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
