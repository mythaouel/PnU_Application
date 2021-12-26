package com.example.fragment;

import static com.example.pnu_application.MainActivity.bottomNavigationView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.BannerAdapter;
import com.example.adapter.HomeBlogAdapter;
import com.example.adapter.HotProductAdapter;
import com.example.adapter.TipsAdapter;
import com.example.fragment.category.CatFoodFragment;
import com.example.fragment.category.DogFoodFragment;
import com.example.fragment.category.PetFashionFragment;
import com.example.fragment.category.PetToyFragment;
import com.example.fragment.home.MenuFragment;
import com.example.fragment.home.PromoFragment;
import com.example.model.Banner;
import com.example.model.HomeBlog;
import com.example.model.HomeBlogItemClick;
import com.example.model.Product;
import com.example.model.ProductItemClick;
import com.example.model.Tips;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment {

    ImageButton btnFind;
    TextView txtAllProduct,txtGreeting;
    ImageView imvNoel;
    Button btnHomeCall;

    //Banner
    private ViewPager2 nViewPager2;
    private CircleIndicator3 nCircleIndicator3;
    BannerAdapter bannerAdapter;
    ArrayList<Banner> banners;
    LinearLayout icon_cat, icon_dog, icon_toys, icon_clothes;
    private Timer timer;

    //Tips
    RecyclerView rcvTips;
    TipsAdapter tipsAdapter;
    ArrayList<Tips> tips;

    //Sản phẩm nổi bật
    GridView gvNoiBat;
    ImageView imvSpNoiBat;
    TextView txtSpNoiBat;
    ArrayList<Product> products;
    HotProductAdapter productAdapter;
    ProductItemClick productItemClick;
    ImageView imvThumbDetails;
    TextView txtNameDetails, txtPriceDetails, txtDescription;

    //Blog
    GridView gvBlog;
    ImageView imvBlogBanner;
    TextView txtBlogDetailsName, txtBlogContent;
    ArrayList<HomeBlog> blogs;
    HomeBlogAdapter homeBlogAdapter;
    HomeBlogItemClick blogItemClick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        MainActivity.showBottomNav();
        txtGreeting=view.findViewById(R.id.txtGreeting);
        greetingMaker();

        btnHomeCall=view.findViewById(R.id.btnHomeCall);

        //linkview button tìm kiếm
        btnFind=view.findViewById(R.id.btnFindItem);

        //Xem tất cả sản phẩm
        txtAllProduct=view.findViewById(R.id.txtAllProduct);



        //linkview banner và indicator
        nViewPager2=view.findViewById(R.id.vpBanner);
        nCircleIndicator3=view.findViewById(R.id.circleIndicator);

        //linkview menu
        icon_cat=view.findViewById(R.id.icon_cat);
        icon_dog=view.findViewById(R.id.icon_dog);
        icon_toys=view.findViewById(R.id.icon_toys);
        icon_clothes=view.findViewById(R.id.icon_clothes);

        //linkview và xử lý recyclerview tips
        rcvTips=view.findViewById(R.id.rcvTips);
        configRecyclerView();

        //linkview và xử lý sp nổi bật
        gvNoiBat=view.findViewById(R.id.gvNoiBat);
        imvSpNoiBat=view.findViewById(R.id.imvSpNoiBat);
        txtSpNoiBat=view.findViewById(R.id.txtSpNoiBat);

        imvThumbDetails = view.findViewById(R.id.imvThumbDetails);
        txtNameDetails = view.findViewById(R.id.txtNameDetails);
        txtPriceDetails = view.findViewById(R.id.txtPriceDetails);
        txtDescription = view.findViewById(R.id.txtDescription);


        //linkview homeblog
        gvBlog=view.findViewById(R.id.gvBlog);
        imvBlogBanner=view.findViewById(R.id.imvBlogBanner);
        txtBlogContent=view.findViewById(R.id.txtBlogContent);
        txtBlogDetailsName=view.findViewById(R.id.txtBlogDetailsName);

        imvNoel=view.findViewById(R.id.imvNoel);

        addEvent();
        initData();

        autoSlideImages();

        gvBlog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                blogItemClick= (HomeBlogItemClick) getActivity();
                if(blogItemClick!=null){
                    blogItemClick.click(blogs.get(position));
                }
            }
        });

        gvNoiBat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productItemClick = (ProductItemClick) getActivity();
                if (productItemClick != null){
                    productItemClick.click(products.get(position));
                }
            }
        });

        return view;

    }

    private void autoSlideImages() {
        if (banners==null || banners.isEmpty() || nViewPager2==null){
            return;
        }
        //Init timer
        if(timer==null){
            timer=new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem=nViewPager2.getCurrentItem();
                        int totalItem=banners.size()-1;
                        if(currentItem<totalItem){
                            currentItem++;
                            nViewPager2.setCurrentItem(currentItem);
                        }else{
                            nViewPager2.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500,2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
            timer=null;
        }
    }

    private void configRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rcvTips.setLayoutManager(manager);
        rcvTips.setHasFixedSize(true);
        rcvTips.setItemAnimator( new DefaultItemAnimator());
    }

    private void greetingMaker() {
        Date dt = new Date();
        int hours = dt.getHours();

        if(hours>=0 && hours<=3){
            txtGreeting.setText("Chào buổi tối !");
        }else if(hours>=3 && hours<=10){
            txtGreeting.setText("Chào buổi sáng!");
        } else if(hours>=10 && hours<=15){
            txtGreeting.setText("Chào buổi trưa !");
        }else if(hours>=15 && hours<=18){
            txtGreeting.setText("Chào buổi chiều !");
        }else if(hours>=18 && hours<=24){
            txtGreeting.setText("Chào buổi tối !");
        }

    }


    private void addEvent() {
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new FindFragment()).addToBackStack(null);
                transaction.commit();
            }
        });
        txtAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new CategoryFragment()).addToBackStack(null);
                transaction.commit();

                bottomNavigationView.setSelectedItemId( R.id.itCategory );
            }
        });
        btnHomeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                Uri uri=Uri.parse("tel:"+"0123456777");
                intent.setData(uri);
                startActivity(intent);

            }
        });
        icon_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new MenuFragment()).addToBackStack(null);
                transaction.commit();

                FragmentTransaction menu_transaction = getParentFragmentManager().beginTransaction();
                menu_transaction.replace(R.id.fragment_home_menu,new CatFoodFragment()).addToBackStack(null);
                menu_transaction.commit();

            }
        });

        icon_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new MenuFragment()).addToBackStack(null);
                transaction.commit();

                FragmentTransaction menu_transaction = getParentFragmentManager().beginTransaction();
                menu_transaction.replace(R.id.fragment_home_menu,new DogFoodFragment()).addToBackStack(null);
                menu_transaction.commit();
            }
        });

        icon_clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new MenuFragment()).addToBackStack(null);
                transaction.commit();

                FragmentTransaction menu_transaction = getParentFragmentManager().beginTransaction();
                menu_transaction.replace(R.id.fragment_home_menu,new PetFashionFragment()).addToBackStack(null);
                menu_transaction.commit();
            }
        });
        icon_toys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new MenuFragment()).addToBackStack(null);
                transaction.commit();

                FragmentTransaction menu_transaction = getParentFragmentManager().beginTransaction();
                menu_transaction.replace(R.id.fragment_home_menu,new PetToyFragment()).addToBackStack(null);
                menu_transaction.commit();
            }
        });
        imvNoel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new PromoFragment()).addToBackStack(null);
                transaction.commit();

            }
        });
    }


    private void initData() {
        //Banner
        banners=new ArrayList<>();
        banners.add(new Banner(R.drawable.banner_1));
        banners.add(new Banner(R.drawable.banner_2));
        banners.add(new Banner(R.drawable.banner_3));
        banners.add(new Banner(R.drawable.banner_4));

        bannerAdapter=new BannerAdapter(getContext(),banners);
        nViewPager2.setAdapter(bannerAdapter);
        nCircleIndicator3.setViewPager(nViewPager2);

        //Tips
        tips=new ArrayList<>();
        tips.add(new Tips(R.drawable.tips_1,getString(R.string.tips_01)));
        tips.add(new Tips(R.drawable.tips_2,getString(R.string.tips_02)));
        tips.add(new Tips(R.drawable.tips_3,getString(R.string.tips_03)));
        tips.add(new Tips(R.drawable.tips_4,getString(R.string.tips_04)));

        tipsAdapter=new TipsAdapter(getContext().getApplicationContext(),tips);
        rcvTips.setAdapter(tipsAdapter);




        //SP nổi bật
        products=new ArrayList<>();
        products.add(new Product("sp0017",R.drawable.pet_toy_01, getString(R.string.pet_toy_01), 25, getString(R.string.des_pet_toy_01)));
        products.add(new Product("sp0025",R.drawable.pet_fashion_01, getString(R.string.pet_fashion_01), 62, getString(R.string.des_pet_fashion_01)));
        products.add(new Product("sp0027",R.drawable.pet_fashion_03, getString(R.string.pet_fashion_03), 119, getString(R.string.des_pet_fashion_03)));
        products.add(new Product("sp0031",R.drawable.pet_fashion_07, getString(R.string.pet_fashion_07), 29, getString(R.string.des_pet_fashion_07)));
        products.add(new Product("sp0032",R.drawable.pet_fashion_08, getString(R.string.pet_fashion_08), 50, getString(R.string.des_pet_fashion_08)));
        products.add(new Product("sp0024",R.drawable.pet_toy_08, getString(R.string.pet_toy_08), 15, getString(R.string.des_pet_toy_08)));

        productAdapter = new HotProductAdapter(getContext(), R.layout.item_spnoibat, products);
        gvNoiBat.setAdapter(productAdapter);


        //Blog nổi bật
        blogs=new ArrayList<>();
        blogs.add(new HomeBlog(R.drawable.blog_1,getString(R.string.blog_name_01),getString(R.string.blog_content_01),R.drawable.blog_details_1));

        blogs.add(new HomeBlog(R.drawable.blog_2,getString(R.string.blog_name_02),getString(R.string.blog_content_02), R.drawable.blog_details_2));

        blogs.add(new HomeBlog(R.drawable.blog_3,getString(R.string.blog_name_03),getString(R.string.blog_content_03),R.drawable.blog_details_3));

        blogs.add(new HomeBlog(R.drawable.blog_4,getString(R.string.blog_name_04),getString(R.string.blog_content_04),R.drawable.blog_details_4));

        blogs.add(new HomeBlog(R.drawable.blog_5,getString(R.string.blog_name_05),getString(R.string.blog_content_05),R.drawable.blog_details_5));

        blogs.add(new HomeBlog(R.drawable.blog_6,getString(R.string.blog_name_06),getString(R.string.blog_content_06),R.drawable.blog_details_6));

        homeBlogAdapter=new HomeBlogAdapter(getContext(),R.layout.item_blog, blogs);
        gvBlog.setAdapter(homeBlogAdapter);
        
    }
}