package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateInfoFragment extends Fragment {

    Button btnUpdate;
    EditText edtName, edtBirthday, edtPhone, edtAddress, edtEmail;
    TextView txtName;
    ImageView  imvBack;
    CircleImageView imvAvatar;

    View mView;

    BottomSheetDialog sheetDialog;

    LinearLayout sheetOpenCamera,sheetOpenGallery;

    ActivityResultLauncher<Intent> activityResultLauncher;

    Boolean isCamera;
    String name,address,email,birthday,phone;

    MainActivity mainActivity;
    int MATK;
    int MATK1=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.hideBottomNav();
        mView=inflater.inflate(R.layout.fragment_update_info, container, false);
        linkViews();
        mainActivity = (MainActivity) getActivity();
        MATK = mainActivity.getMATK();
        createSheetDialog();
        getImage();
        loadData();
        addEvents();

        return mView;

    }


    private void linkViews() {
        btnUpdate   = mView.findViewById(R.id.btnUpdate);

        edtName     = mView.findViewById(R.id.edtName);
        edtBirthday = mView.findViewById(R.id.edtBirthday);
        edtEmail    = mView.findViewById(R.id.edtEmail);
        edtPhone    = mView.findViewById(R.id.edtPhoneAct);
        edtAddress  = mView.findViewById(R.id.edtAddress);

        txtName     = mView.findViewById(R.id.txtInfName);

        imvAvatar   = mView.findViewById(R.id.imvAvtInfo);
        imvBack     = mView.findViewById(R.id.imvAccountBack);

    }

    private void getValues() {

        name = edtName.getText().toString().trim();
        email=edtEmail.getText().toString().trim();
        address=edtAddress.getText().toString().trim();
        birthday= edtBirthday.getText().toString().trim();
        phone= edtPhone.getText().toString().trim();
    }


    private boolean checkValidation() {
        getValues();
        // bỏ trống ô Họ Tên
        if(TextUtils.isEmpty(name)){
            edtName.requestFocus();
            edtName.setError(getContext().getResources().getString(R.string.loi_thieu_info));
            return false;
        }

        //bỏ trống ô Ngày Sinh
        if(TextUtils.isEmpty(birthday)){
            edtBirthday.requestFocus();
            edtBirthday.setError(getContext().getResources().getString(R.string.loi_thieu_info));
            return false;
        }

        //bỏ trống ô Email
        if(TextUtils.isEmpty(email)){
            edtEmail.requestFocus();
            edtEmail.setError(getContext().getResources().getString(R.string.loi_thieu_info));
            return false;
        }

        //bỏ trống ô nhận Số Điện Thoại
        if(TextUtils.isEmpty(phone)){
            edtPhone.requestFocus();
            edtPhone.setError(getContext().getResources().getString(R.string.loi_thieu_info));
            return false;
        }

        //bỏ trống ô Địa Chỉ
        if(TextUtils.isEmpty(address)){
            edtAddress.requestFocus();
            edtAddress.setError(getContext().getResources().getString(R.string.loi_thieu_info));
            return false;
        }
        // Mật khẩu mới đặt không đúng yêu cầu (từ 5 ký tự trở )

        return true;
    }
    private void getImage() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData()!=null);
                if (isCamera) {
                    Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                    imvAvatar.setImageBitmap(bitmap);
                }else {
                    Uri uri = result.getData().getData();
                    if (uri!=null){
                        try {
                            InputStream inputStream= getActivity().getContentResolver().openInputStream(uri);
                            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                            imvAvatar.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void loadData() {

        Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME + " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " + MATK );
        if (cursor!=null && cursor.moveToFirst()){
            {
                {
                    edtName.setText( cursor.getString( 1 ) );
                    edtBirthday.setText(String.valueOf(cursor.getString(2)));
                    edtEmail.setText( cursor.getString( 3) );
                    edtPhone.setText(cursor.getString( 4 ) );
                    edtAddress.setText(cursor.getString( 5 ));

                    txtName.setText( cursor.getString( 1) );

                    //Covert to byte array->Bitmap
                    byte[] photo= cursor.getBlob(6);
                    if(photo==null){
                        imvAvatar.setImageResource(R.drawable.user_avt_default);
                    }else{
                        Bitmap bitmap= BitmapFactory.decodeByteArray(photo,0, photo.length);
                        imvAvatar.setImageBitmap(bitmap);
                        Loading_Screen.db.close();
                    }
                }
            }
        }

    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
                MainActivity.showBottomNav();
            }
        });
        imvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Bottom Sheet Dialog
                sheetDialog.show();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Insert Customer Data

                if(checkValidation()){
                    getValues();
                    Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME + " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " + MATK );
                     // trường hợp account đã thông tin trước đó có ->sẽ cập nhật lại
                      if(cursor!=null && cursor.moveToFirst()) {
                          boolean flag = Loading_Screen.db.updateCustomerData(name, birthday, email, phone, address, covertPhoto(), MATK);
                          if (flag == true) {
                              Toast.makeText(getContext(), "Update2 Succes", Toast.LENGTH_SHORT).show();
                          } else {
                              Toast.makeText(getContext(), "Update2 Fail", Toast.LENGTH_SHORT).show();
                          }
                          // trường hợp account chưa bấm cập nhật thông tin lần nào ->insert vào 
                      }else{
                          boolean flag = Loading_Screen.db.insertCustomerData(name, birthday, email, phone, address, covertPhoto(), MATK);
                          if (flag == true) {
                              Toast.makeText(getContext(), "Update Succes", Toast.LENGTH_SHORT).show();
                          } else {
                              Toast.makeText(getContext(), "Update Fail", Toast.LENGTH_SHORT).show();
                          }
                      }
                }}


        });
//        loadData();
    }

    private byte[] covertPhoto() {
        BitmapDrawable drawable = (BitmapDrawable) imvAvatar.getDrawable();
        Bitmap bitmap= drawable.getBitmap();
        ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        return outputStream.toByteArray();
    }

    private void createSheetDialog(){
        if (sheetDialog==null){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.account_updateinf_bottom_sheet,null);

            sheetOpenCamera= view.findViewById(R.id.sheetOpenCamera);
            sheetOpenCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Open Camera
                    isCamera=true;
                    Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activityResultLauncher.launch(intent);
                    sheetDialog.dismiss();
                }
            });

            sheetOpenGallery=view.findViewById(R.id.sheetOpenGallery);
            sheetOpenGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Open Gellery
                    isCamera=false;
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    activityResultLauncher.launch(intent);
                    sheetDialog.dismiss();
                }
            });

            sheetDialog= new BottomSheetDialog(getContext());
            sheetDialog.setContentView(view);
        }
    }
}