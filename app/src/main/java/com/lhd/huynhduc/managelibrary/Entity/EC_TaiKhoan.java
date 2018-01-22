package com.lhd.huynhduc.managelibrary.Entity;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_TaiKhoan {
    private String _TaiKhoan;

    
    private String _MatKhau;

    public EC_TaiKhoan() {
    }

    public EC_TaiKhoan(String _TaiKhoan, String _MatKhau) {

        this._TaiKhoan = _TaiKhoan;
        this._MatKhau = _MatKhau;
    }

    public String get_TaiKhoan() {

        return _TaiKhoan;
    }

    public void set_TaiKhoan(String _TaiKhoan) {
        this._TaiKhoan = _TaiKhoan;
    }

    public String get_MatKhau() {
        return _MatKhau;
    }

    public void set_MatKhau(String _MatKhau) {
        this._MatKhau = _MatKhau;
    }
}

