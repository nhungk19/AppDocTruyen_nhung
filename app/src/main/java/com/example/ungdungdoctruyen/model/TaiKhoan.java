package com.example.ungdungdoctruyen.model;

public class TaiKhoan {
    private int mId;
    private String mTentaiKhoan;
    private String mMatKhau;
    private String  mEmail;
    private int mPhanQuyen;

    public TaiKhoan(String mTentaiKhoan, String mMatKhau, String mEmail, int mPhanQuyen) {
        this.mTentaiKhoan = mTentaiKhoan;
        this.mMatKhau = mMatKhau;
        this.mEmail = mEmail;
        this.mPhanQuyen = mPhanQuyen;
    }

    public TaiKhoan(String mTentaiKhoan, String mEmail) {
        this.mTentaiKhoan = mTentaiKhoan;
        this.mEmail = mEmail;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTentaiKhoan() {
        return mTentaiKhoan;
    }

    public void setmTentaiKhoan(String mTentaiKhoan) {
        this.mTentaiKhoan = mTentaiKhoan;
    }

    public String getmMatKhau() {
        return mMatKhau;
    }

    public void setmMatKhau(String mMatKhau) {
        this.mMatKhau = mMatKhau;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmPhanQuyen() {
        return mPhanQuyen;
    }

    public void setmPhanQuyen(int mPhanQuyen) {
        this.mPhanQuyen = mPhanQuyen;
    }
}
