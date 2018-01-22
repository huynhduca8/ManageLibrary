package com.lhd.huynhduc.managelibrary.Entity;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_NhanVien {
    private String _MSNV;

    
    private String _MatMa;

    
    private String _HoTenNV;

    
    private String _DiaChiNV;

    
    private String _NgaySinhNV;

    
    private String _GioiTinhNV;

    
    private String _DienThoaiNV;

    
    private String _EmailNV;

    
    private String _NgayVaoLam;

    public EC_NhanVien() {
    }

    public EC_NhanVien(String _MSNV, String _MatMa, String _HoTenNV, String _DiaChiNV, String _NgaySinhNV, String _GioiTinhNV, String _DienThoaiNV, String _EmailNV, String _NgayVaoLam) {

        this._MSNV = _MSNV;
        this._MatMa = _MatMa;
        this._HoTenNV = _HoTenNV;
        this._DiaChiNV = _DiaChiNV;
        this._NgaySinhNV = _NgaySinhNV;
        this._GioiTinhNV = _GioiTinhNV;
        this._DienThoaiNV = _DienThoaiNV;
        this._EmailNV = _EmailNV;
        this._NgayVaoLam = _NgayVaoLam;
    }

    public String get_MSNV() {

        return _MSNV;
    }

    public void set_MSNV(String _MSNV) {
        this._MSNV = _MSNV;
    }

    public String get_MatMa() {
        return _MatMa;
    }

    public void set_MatMa(String _MatMa) {
        this._MatMa = _MatMa;
    }

    public String get_HoTenNV() {
        return _HoTenNV;
    }

    public void set_HoTenNV(String _HoTenNV) {
        this._HoTenNV = _HoTenNV;
    }

    public String get_DiaChiNV() {
        return _DiaChiNV;
    }

    public void set_DiaChiNV(String _DiaChiNV) {
        this._DiaChiNV = _DiaChiNV;
    }

    public String get_NgaySinhNV() {
        return _NgaySinhNV;
    }

    public void set_NgaySinhNV(String _NgaySinhNV) {
        this._NgaySinhNV = _NgaySinhNV;
    }

    public String get_GioiTinhNV() {
        return _GioiTinhNV;
    }

    public void set_GioiTinhNV(String _GioiTinhNV) {
        this._GioiTinhNV = _GioiTinhNV;
    }

    public String get_DienThoaiNV() {
        return _DienThoaiNV;
    }

    public void set_DienThoaiNV(String _DienThoaiNV) {
        this._DienThoaiNV = _DienThoaiNV;
    }

    public String get_EmailNV() {
        return _EmailNV;
    }

    public void set_EmailNV(String _EmailNV) {
        this._EmailNV = _EmailNV;
    }

    public String get_NgayVaoLam() {
        return _NgayVaoLam;
    }

    public void set_NgayVaoLam(String _NgayVaoLam) {
        this._NgayVaoLam = _NgayVaoLam;
    }
}
