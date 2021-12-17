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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateInfoFragment extends Fragment {

    Button btnUpdate;
    EditText edtName, edtBirthday, edtPhone, edtAddress, edtEmail;
    ImageView imvAvatar, imvBack;

    View mView;

    BottomSheetDialog sheetDialog;

    LinearLayout sheetOpenCamera,sheetOpenGallery;

    ActivityResultLauncher<Intent> activityResultLauncher;

    Boolean isCamera;
    String name,address,email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView=inflater.inflate(R.layout.fragment_update_info, container, false);
        linkViews();
        createSheetDialog();
        checkUpdate();
        loadData();
        addEvents();


        MainActivity.hideBottomNav();
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
        // Inflate the layout for this fragment
        return mView;

    }



    private void linkViews() {
        btnUpdate   = mView.findViewById(R.id.btnUpdate);

        edtName     = mView.findViewById(R.id.edtName);
        edtBirthday = mView.findViewById(R.id.edtBirthday);
        edtEmail    = mView.findViewById(R.id.edtEmail);
        edtPhone    = mView.findViewById(R.id.edtPhoneAct);
        edtAddress  = mView.findViewById(R.id.edtAddress);

        imvAvatar   = mView.findViewById(R.id.imvAvtInfo);
        imvBack     = mView.findViewById(R.id.imvAccountBack);

    }
    private void checkUpdate() {

    }


    private void loadData() {
        Cursor cursor = AccountFragment.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME + " ORDER BY "+ MyDatabaseHelper.CUSTOMER_COL_ID+" DESC LIMIT 1");
        while (cursor.moveToNext()){
            edtName.setText( cursor.getString( 1 ) );
            edtBirthday.setText(String.valueOf(cursor.getDouble(2)));
            edtEmail.setText( cursor.getString( 3) );
            edtPhone.setText(String.valueOf( cursor.getInt( 5 ) ));
            edtAddress.setText(cursor.getString( 4 ));
            //Covert to byte array->Bitmap
            byte[] photo= cursor.getBlob(6);
            if(photo==null){
            }
            Bitmap bitmap= BitmapFactory.decodeByteArray(photo,0, photo.length);
            imvAvatar.setImageBitmap(bitmap);
            AccountFragment.db.close();

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
                Double birthday;
                int phone;
                name = edtName.getText().toString();
                email=edtEmail.getText().toString();
                address=edtAddress.getText().toString();
                birthday= Double.parseDouble(edtBirthday.getText().toString().replace( "/","" ));
                phone=Integer.parseInt ( edtPhone.getText().toString());
                address=edtAddress.getText().toString();
                if(!name.equals("") && !birthday.equals("") &&  !edtPhone.getText().toString().equals("") && !address.equals("") && !email.equals("")){
                   boolean flag= AccountFragment.db.insertCustomerData(name,birthday,email,address,phone, covertPhoto());
                    if(flag==true){
                        Toast.makeText(getContext(), "Update Succes", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Update Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }

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