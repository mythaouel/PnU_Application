package com.example.fragment;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.adapter.AccountLineAdapter;
import com.example.model.AccountLineItem;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.NotificationPopUp;
import com.example.pnu_application.R;
import com.example.pnu_application.SignIn_Screen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NoLoginAccountFragment extends Fragment {

    TextView txtLogin;
    Switch swtNoLogin;


    ListView lvAccount1,lvAccount2;
    AccountLineAdapter adapter1,adapter2;
    ArrayList<AccountLineItem> lineItems1,lineItems2;

    View view;

    int MATK=1;

    private static final int Notification_ID=2;

    private static final String Title_Notification_2="Bạn có blog mới chưa đọc nè!";


    private static final String Content_Notification_Small_2=
            "BLog : Chơi cùng thú cưng \n" +"Hãy chú ý quan sát để nhận biết được tính cách và sở thích cá nhân của từng thú cưng";

    private static final String Content_Notification_Expand_2=
            "Hãy chú ý quan sát để nhận biết được tính cách và sở thích cá nhân của từng thú cưng. Đừng cho rằng tất cả vẹt đều giống nhau hoặc tất cả chó lông vàng..............";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_account_no_login, container, false);

        MainActivity.showBottomNav();

        linkViews();
        initData();
        addEvents();
        return view;
    }

    private void linkViews() {


        lvAccount1   = view.findViewById(R.id.lvActNoLogin1);
        lvAccount2   = view.findViewById(R.id.lvActNoLogin2);

        txtLogin     = view.findViewById(R.id.txtLogin);
        swtNoLogin   = view.findViewById(R.id.swtNoLogin);

    }

    private void addEvents() {


        lvAccount2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //View About Us
                if(i==0){
                    AboutUsFragment fragment= new AboutUsFragment();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.add(R.id.layoutContainer, fragment).addToBackStack(null) ;
                    transaction.commit();
                }
                //View Policy Events
                else if(i==1){
                    PolicyFragment fragment= new PolicyFragment();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.add(R.id.layoutContainer, fragment).addToBackStack(null) ;
                    transaction.commit();
                }

            }
        });



       //Login event
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SignIn_Screen.class);
                startActivity(intent);
            }
        });

        swtNoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)  {
                    Toast.makeText(getContext(), "Đã bật thông báo", Toast.LENGTH_SHORT).show();
                    sendCustomNotification2();
                } else {
                    Toast.makeText(getContext(), "Đã tắt thông báo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void initData() {
        //Init Array
        lineItems1 = new ArrayList<AccountLineItem>();
        lineItems2 = new ArrayList<AccountLineItem>();

        //Add Array
        lineItems1.add(new AccountLineItem(R.drawable.act_ic_key,"Đổi mật khẩu",R.drawable.img));
        lineItems1.add(new AccountLineItem(R.drawable.act_ic_ord,"Lịch sử đơn hàng", R.drawable.img ));

        lineItems2.add(new AccountLineItem(R.drawable.act_ic_gt,"Giới thiệu",R.drawable.img));
        lineItems2.add(new AccountLineItem(R.drawable.act_ic_cs,"Chính sách",R.drawable.img));

        //Attach Adapter
        adapter1=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems1);
        lvAccount1.setAdapter(adapter1);

        adapter2=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems2);
        lvAccount2.setAdapter(adapter2);


    }
    private void sendCustomNotification2(){

        //collapsed-small
        RemoteViews notificationLayout = new RemoteViews(getContext().getPackageName(), R.layout.account_custom_notification_small_2);
        notificationLayout.setTextViewText(R.id.txtTitleCustom2,Title_Notification_2);
        notificationLayout.setTextViewText(R.id.txtInfoCustom2,Content_Notification_Small_2);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String strDateFromated = dateFormat.format(new Date());
        notificationLayout.setTextViewText(R.id.txtIimeCustom2,strDateFromated);

        //expanded
        RemoteViews notificationLayoutExpand = new RemoteViews(getContext().getPackageName(), R.layout.account_custom_notification_expand_2);
        notificationLayoutExpand.setTextViewText(R.id.txtTitleCustomExpand2, Content_Notification_Small_2);
        notificationLayoutExpand.setTextViewText(R.id.txtInfoCustomExpand2,Content_Notification_Expand_2);
        notificationLayoutExpand.setImageViewResource(R.id.imvCustomExpand2,R.drawable.blog_2);


        Notification notification = new NotificationCompat.Builder(getContext(), NotificationPopUp.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.act_ic_noti_small)
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpand)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.notify(getNotificationID(),notification);
    }

    private int getNotificationID(){
        return (int) new Date().getTime();
    }
}