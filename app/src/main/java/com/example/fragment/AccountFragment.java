package com.example.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.adapter.AccountLineAdapter;

import com.example.fragment.home.PromoFragment;
import com.example.model.AccountLineItem;


import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.NotificationPopUp;
import com.example.pnu_application.R;
import com.example.pnu_application.SignIn_Screen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import utils.Constant;


public class AccountFragment extends Fragment {

    Button btnUpdateInf;
    public static TextView txtNameAct, txtTestAct;
    public static CircleImageView imvAvatarAct;

    ListView lvAccount1,lvAccount2,lvAccount3;
    AccountLineAdapter adapter1,adapter2,adapter3;
    ArrayList<AccountLineItem> lineItems1,lineItems2,lineItems3;

    private MainActivity mainActivity;
    public static int MATK;

    Switch swtNotification;

    private static final int Notification_ID=1;

    private static final String Title_Notification_1="Gi??ng sinh b??n Pet, qu?? t???ng trao tay";

    private static final String Content_Notification_Small_1=
            "Noel n??y h??y c??ng PnU t???n h?????ng c??c ph??c gi??y h???nh ph??c b??n pet c??ng h??ng lo???t khuy???n m??i....";

    private static final String Content_Notification_Expand_1=
            "H??ng lo???t khuy???n m??i h???p d???n PnU d??nh ri??ng cho b???n d???p Gi??ng Sinh:\n" +
            "-Gi???m 10% gi?? tr??? ????n h??ng khi nh???p m??: NOELPNUSALE.\n" +
            "-T???ng v??ng c??? kh???c t??n theo y??u c???u khi mua combo ph??? ki???n Gi??ng Sinh.";

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_account, container, false);

        MainActivity.showBottomNav();

        linkViews();

        mainActivity = (MainActivity) getActivity();
        MATK = mainActivity.getMATK();

        initData();
        loadData();
        addEvents();
        return view;
    }


    private void linkViews() {

        imvAvatarAct       = view.findViewById(R.id.imvAvatar) ;

        txtNameAct         = view.findViewById(R.id.txtAccountName);
        txtTestAct         = view.findViewById(R.id.txtTest);

        swtNotification = view.findViewById(R.id.switchNotification);

        btnUpdateInf       = view.findViewById(R.id.btnUpdateInf);

        lvAccount1         = view.findViewById(R.id.lvAccount1);
        lvAccount2         = view.findViewById(R.id.lvAccount2);
        lvAccount3         = view.findViewById(R.id.lvAccount3);

    }

    private void addEvents() {

        //ChangePass + Order History Event
        lvAccount1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ChangePass Event
                if(i==0){
                   ChangePassFragment fragment= new ChangePassFragment();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.add(R.id.layoutContainer, fragment).addToBackStack(null) ;
                    transaction.commit();
                }
                //View Order History Event
                if(i==1){
                    OrderHistoryFragment fragment= new OrderHistoryFragment();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.layoutContainer, fragment).addToBackStack(null) ;
                    transaction.commit();
                }
            }
        });

        //About Us +  Policy Events
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

        // LogOut Event
        lvAccount3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0){
                    openLogOutDialog();
                }
            }
        });

        //Update Information Event
        btnUpdateInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateInfoFragment fragment= new UpdateInfoFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_account, fragment).addToBackStack(null) ;
                transaction.commit();
            }
        });

        //Switch On/Off Notification Event
        swtNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)  {
                    Toast.makeText(getContext(), "???? b???t th??ng b??o", Toast.LENGTH_SHORT).show();
                    sendCustomNotification();
                } else {
                    Toast.makeText(getContext(), "???? t???t th??ng b??o", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public static void loadData() {
        Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME +
                " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " + MATK );
        if( cursor!=null && cursor.moveToFirst()) {
            {
                txtNameAct.setText( cursor.getString( 1 ) );
                //Covert to byte array->Bitmap
                byte[] photo= cursor.getBlob(6);
                if(photo==null){
                    imvAvatarAct.setImageResource(R.drawable.user_avt_default);
                }else{
                    Bitmap bitmap= BitmapFactory.decodeByteArray(photo,0, photo.length);
                    imvAvatarAct.setImageBitmap(bitmap);
                    Loading_Screen.db.close();
                }
            }
        }
        cursor.close();
    }

    private void initData() {
        //Init Array
        lineItems1 = new ArrayList<AccountLineItem>();
        lineItems2 = new ArrayList<AccountLineItem>();
        lineItems3 = new ArrayList<AccountLineItem>();
        //Add Array
        lineItems1.add(new AccountLineItem(R.drawable.act_ic_key,"?????i m???t kh???u",R.drawable.img));
        lineItems1.add(new AccountLineItem(R.drawable.act_ic_ord,"L???ch s??? ????n h??ng", R.drawable.img ));

        lineItems2.add(new AccountLineItem(R.drawable.act_ic_gt,"Gi???i thi???u",R.drawable.img));
        lineItems2.add(new AccountLineItem(R.drawable.act_ic_cs,"Ch??nh s??ch",R.drawable.img));
        lineItems3.add(new AccountLineItem(R.drawable.act_ic_logout,"????ng xu???t",R.drawable.img));
        //Attach Adapter
        adapter1=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems1);
        lvAccount1.setAdapter(adapter1);

        adapter2=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems2);
        lvAccount2.setAdapter(adapter2);

        adapter3=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems3);
        lvAccount3.setAdapter(adapter3);
    }

    private void openLogOutDialog(){
        final Dialog dialog= new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.account_custom_dialog);
        Window window= dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes= window.getAttributes();
        windowAttributes.gravity= Gravity.BOTTOM;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(false);
        //LinkView
        Button btnLogOut     = dialog.findViewById(R.id.btnLogOut);
        Button btnCancel     = dialog.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag= Loading_Screen.db.updateAccountStatus(0,MATK);
                if(flag =true){
                    Constant.arrCartProduct.clear();
                    dialog.dismiss();
                    getActivity().finishAndRemoveTask();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "H??? th???ng g???p l???i!", Toast.LENGTH_LONG).show();
                    System.exit(0);
                }
                }

        });
        dialog.show();
    }

    private void sendCustomNotification(){

        // Collapsed-small
        RemoteViews notificationLayout = new RemoteViews(getContext().getPackageName(), R.layout.account_custom_notification_small_1);
        notificationLayout.setTextViewText(R.id.txtTitleCustom1,Title_Notification_1);
        notificationLayout.setTextViewText(R.id.txtInfoCustom1,Content_Notification_Small_1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String strDateFromated = dateFormat.format(new Date());
        notificationLayout.setTextViewText(R.id.txtIimeCustom1,strDateFromated);

        // Expanded
        RemoteViews notificationLayoutExpand = new RemoteViews(getContext().getPackageName(), R.layout.account_custom_notification_expand_1);
        notificationLayoutExpand.setTextViewText(R.id.txtTitleCustomExpand1,Title_Notification_1);
        notificationLayoutExpand.setTextViewText(R.id.txtInfoCustomExpand1,Content_Notification_Expand_1);
        notificationLayoutExpand.setImageViewResource(R.id.imvCustomExpand1,R.drawable.event1);

        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(getContext(),MainActivity.class);
        resultIntent.putExtra("From", "notifyFrag");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addNextIntentWithParentStack(resultIntent);

        // Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(getNotificationID(), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(getContext(), NotificationPopUp.CHANNEL_ID_1)
                .setSmallIcon(R.drawable.act_ic_noti_small)
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpand)
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.notify(getNotificationID(),notification);

    }

    private int getNotificationID(){
        return (int) new Date().getTime();
    }
}