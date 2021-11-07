package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ungdungdoctruyen.database.databasedoctruyen;

public class MainDangNhap extends AppCompatActivity {

    EditText editTaiKhoan, editMatKhau;
    Button btnDangNhap, btnDangKy;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        AnhXa();

        databasedoctruyen = new databasedoctruyen (this );

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDangNhap.this, MainDangKy.class);
                startActivity(intent);
            }
        }) ;
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoan = editTaiKhoan.getText().toString();
                String matkhau = editMatKhau.getText().toString();

                //sử dụng con trỏ để lấy dữ liệu
                Cursor cursor = databasedoctruyen.getData();

                while (cursor.moveToNext()){
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    if(datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)){
                        int phanquyen = cursor.getInt(4);
                        int id = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        Intent intent = new Intent(MainDangNhap.this,MainActivity.class);
                        intent.putExtra("phanq", phanquyen);
                        intent.putExtra("idd", id);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan", tentk);

                        startActivity(intent);
                    }
                }
                cursor.moveToFirst();
                cursor.close();
            }
        });
    }


    private void AnhXa() {
        editMatKhau = findViewById(R.id.matkhau);
        editTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
    }
}