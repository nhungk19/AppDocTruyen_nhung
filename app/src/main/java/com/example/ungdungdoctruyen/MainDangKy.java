package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ungdungdoctruyen.database.databasedoctruyen;
import com.example.ungdungdoctruyen.model.TaiKhoan;

public class MainDangKy extends AppCompatActivity {

    EditText editDKTaiKhoan, editDKMatKhau, editDKEmail;
    Button btnDKDangKy, btnDKDangNhap;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        databasedoctruyen = new databasedoctruyen(this);

        AnhXa();
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = editDKTaiKhoan.getText().toString();
                String matkhau  = editDKMatKhau.getText().toString();
                String email = editDKEmail.getText().toString();

                TaiKhoan taiKhoan = CreateTaiKhoan();
                if(taikhoan.equals("") || matkhau.equals("")  || email.equals("")){
                    Log.e("Thông báo : ", "Chưa nhập đủ thông tin");
                }
                //Nếu đủ thông tin thì add taikhoan
                else{
                    databasedoctruyen.AddTaiKhoan(taiKhoan);
                    Toast.makeText(MainDangKy.this,"Đăng ký thành công", Toast.LENGTH_LONG).show();

                }
            }
        });

        //Trở về đăng nhập
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = editDKTaiKhoan.getText().toString();
        String matkhau  = editDKMatKhau.getText().toString();
        String email = editDKEmail.getText().toString();
        int phanquyen = 1;

        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);
        return tk;
    }

    private void AnhXa() {
        editDKEmail = findViewById(R.id.dkemail);
        editDKMatKhau = findViewById(R.id.dkmatkhau);
        editDKTaiKhoan = findViewById(R.id.dktaikhoan);
        btnDKDangKy = findViewById(R.id.dkdangnhap);
        btnDKDangNhap = findViewById(R.id.dkdangnhap);
    }
}