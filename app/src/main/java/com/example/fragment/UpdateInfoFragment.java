package com.example.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


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


import com.example.fragment.category.ProductDetailsFragment;
import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;




import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateInfoFragment extends Fragment {

    Button btnUpdate;
    EditText edtName, edtBirthday, edtPhone, edtAddress, edtEmail;
    TextView txtName;
    ImageView  imvBack;
    CircleImageView imvAvatar;
    LinearLayout sheetOpenCamera,sheetOpenGallery;
    MainActivity mainActivity;

    View mView;

    BottomSheetDialog sheetDialog;

    ActivityResultLauncher<Intent> activityResultLauncher;

    Boolean isCamera;

    String name,address,email,birthday,phone;

    int MATK;
    private static final int REQUEST_CAMERA_PERMISSION_CODE = 123;
    private static final int REQUEST_READ_STORAGE_PERMISSION_CODE = 456;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.hideBottomNav();
        mView=inflater.inflate(R.layout.fragment_update_info, container, false);
        linkViews();

        //Lấy mã Khách hàng đang đăng nhập hiện
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

    private void addEvents() {

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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



                if(checkValidation()){
                    getValues();
                    Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME +
                            " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " + MATK );

                    // trường hợp account đã thông tin trước đó có ->sẽ cập nhật lại
                    if(cursor!=null && cursor.moveToFirst()) {
                        boolean flag = Loading_Screen.db.updateCustomerData(name, birthday, email, phone, address, covertPhoto(), MATK);
                        if (flag) {
                            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                        }
                        // trường hợp account chưa bấm cập nhật thông tin lần nào ->Insert Customer TableTable
               }else{
                        boolean flag = Loading_Screen.db.insertCustomerData(name, birthday, email, phone, address, covertPhoto(), MATK);
                        if (flag) {
                            Toast.makeText(getContext(), "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    cursor.close();
                }}


        });
//        loadData();
    }

    //Hàm lấy giá trị của editText
    private void getValues() {
        name = edtName.getText().toString().trim();
        email=edtEmail.getText().toString().trim();
        address=edtAddress.getText().toString().trim();
        birthday= edtBirthday.getText().toString().trim();
        phone= edtPhone.getText().toString().trim();
    }

    //Hàm check đầu vào editText
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

        //Ngày sinh nhập vào không hợp lệ hoac không đúng format
        boolean validatorBirthday = ValidatorBirthday(birthday);
        if (!validatorBirthday) {
            edtBirthday.requestFocus();
            edtBirthday.setError(getContext().getResources().getString(R.string.check_valid_birthday));
            return false;
        }

        //bỏ trống ô Email
        if(TextUtils.isEmpty(email)){
            edtEmail.requestFocus();
            edtEmail.setError(getContext().getResources().getString(R.string.loi_thieu_info));
            return false;
        }

        //Sai định dạng Email
        boolean validatorEmail = ValidatorEmail(email);
        if (!validatorEmail) {
            edtEmail.requestFocus();
            edtEmail.setError(getContext().getResources().getString(R.string.check_valid_email));
            return false;
        }

        //bỏ trống ô nhận Số Điện Thoại
        if(TextUtils.isEmpty(phone)){
            edtPhone.requestFocus();
            edtPhone.setError(getContext().getResources().getString(R.string.loi_thieu_info));
            return false;
        }

        //So dien thoai khong hop le
        boolean validatorPhone = ValidatorPhone(phone);
        if (!validatorPhone) {
            edtPhone.requestFocus();
            edtPhone.setError(getContext().getResources().getString(R.string.check_valid_phone));
            return false;
        }


        //bỏ trống ô Địa Chỉ
        if(TextUtils.isEmpty(address)){
            edtAddress.requestFocus();
            edtAddress.setError(getContext().getResources().getString(R.string.loi_thieu_info));
            return false;
        }


        return true;
    }

    // Ham kiểm tra định dạng ngay sinh
    public boolean ValidatorBirthday(String birthday) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        dateFormat.setLenient(false);
        // set false để kiểm tra tính hợp lệ của date

        try {
            // parse chuỗi birthday thành kiểu Date
            dateFormat.parse(birthday); //
            return true;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Ham kiểm tra định dạng email
    public boolean ValidatorEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        boolean checkEmail = email.matches(EMAIL_PATTERN);
        return checkEmail;

    }

    // Ham kiểm tra số điện thoại hợp lệ
    public boolean ValidatorPhone(String str) {

        //cấu trúc 1  so dien thoai
        String PHONE_PATTERN = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        // Kiem tra dinh dang
        boolean check = str.matches(PHONE_PATTERN);

        return  check;
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

   //Hàm chuyển từ ảnh drawable -> bitmap -> mảng byte (lưu vào database)
    private byte[] covertPhoto() {
        BitmapDrawable drawable = (BitmapDrawable) imvAvatar.getDrawable();
        Bitmap bitmap= drawable.getBitmap();
        ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        return outputStream.toByteArray();
    }

    private void getImage() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData()!=null){
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
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Camera
        if (requestCode == REQUEST_CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isCamera = true;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(intent);
                sheetDialog.dismiss();
            } else{
                Toast.makeText(getContext(), "Cấp quyền Camera không thành công", Toast.LENGTH_SHORT).show();
                sheetDialog.dismiss(); }
        }

        //Thu vien
        if (requestCode == REQUEST_READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isCamera = false;
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
                sheetDialog.dismiss();
            } else{
                Toast.makeText(getContext(), "Cấp quyền truy cập thư viện không thành công", Toast.LENGTH_SHORT).show();
                sheetDialog.dismiss();}
        }
    }

    private void createSheetDialog(){
        if (sheetDialog==null){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.account_updateinf_bottom_sheet,null);

            sheetOpenCamera= view.findViewById(R.id.sheetOpenCamera);
            sheetOpenCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean flag = checkCameraPermission();
                    if (flag){
                        //Open Camera
                        isCamera=true;
                        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        activityResultLauncher.launch(intent);
                        sheetDialog.dismiss();

                    } else{

                    String [] permissions= {Manifest.permission.CAMERA};

                    requestPermissions(permissions,REQUEST_CAMERA_PERMISSION_CODE);

                }
                }
            });

            sheetOpenGallery=view.findViewById(R.id.sheetOpenGallery);
            sheetOpenGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean flag = checkReadStoragePermission();
                    if (flag) {
                        //Open Gellery
                        isCamera = false;
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        activityResultLauncher.launch(intent);
                        sheetDialog.dismiss();
                    } else {

                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        requestPermissions(permissions, REQUEST_READ_STORAGE_PERMISSION_CODE);
                    }
                }
            });

            sheetDialog= new BottomSheetDialog(getContext());
            sheetDialog.setContentView(view);
        }
    }

    private boolean checkCameraPermission(){
        //kiem tra version > android 6
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    private boolean checkReadStoragePermission(){
        //kiem tra version > android 6
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        OrderFragment orderFragment = (OrderFragment) getParentFragmentManager().findFragmentByTag("orderFragment");

        if (orderFragment != null && orderFragment.isAdded()){
            MainActivity.hideBottomNav();
        }
        else {
            MainActivity.showBottomNav();
        }
        //Load thông tin vừa mới cập nhật cho AccountFragment khi bấm Back
        AccountFragment.loadData();
    }

}