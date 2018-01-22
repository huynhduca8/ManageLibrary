package com.lhd.huynhduc.managelibrary.Entity;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_QuyenSach {
    private String _MSSach;


    private String _TenSach;


    private String _TacGia;


    private String _MSNhaXB;


    private String _MaLoaiSach;


    private String _NamXB;


    private String _LanXB;


    private String _SoLuong;

    
    private String _NoiDungTomLuoc;

    public EC_QuyenSach() {
    }

    public EC_QuyenSach(String _MSSach, String _TenSach, String _TacGia, String _MSNhaXB, String _MaLoaiSach, String _NamXB, String _LanXB, String _SoLuong, String _NoiDungTomLuoc) {
        this._MSSach = _MSSach;
        this._TenSach = _TenSach;
        this._TacGia = _TacGia;
        this._MSNhaXB = _MSNhaXB;
        this._MaLoaiSach = _MaLoaiSach;
        this._NamXB = _NamXB;
        this._LanXB = _LanXB;
        this._SoLuong = _SoLuong;
        this._NoiDungTomLuoc = _NoiDungTomLuoc;
    }

    public String get_MSSach() {
        return _MSSach;
    }

    public void set_MSSach(String _MSSach) {
        this._MSSach = _MSSach;
    }

    public String get_TenSach() {
        return _TenSach;
    }

    public void set_TenSach(String _TenSach) {
        this._TenSach = _TenSach;
    }

    public String get_TacGia() {
        return _TacGia;
    }

    public void set_TacGia(String _TacGia) {
        this._TacGia = _TacGia;
    }

    public String get_MSNhaXB() {
        return _MSNhaXB;
    }

    public void set_MSNhaXB(String _MSNhaXB) {
        this._MSNhaXB = _MSNhaXB;
    }

    public String get_MaLoaiSach() {
        return _MaLoaiSach;
    }

    public void set_MaLoaiSach(String _MaLoaiSach) {
        this._MaLoaiSach = _MaLoaiSach;
    }

    public String get_NamXB() {
        return _NamXB;
    }

    public void set_NamXB(String _NamXB) {
        this._NamXB = _NamXB;
    }

    public String get_LanXB() {
        return _LanXB;
    }

    public void set_LanXB(String _LanXB) {
        this._LanXB = _LanXB;
    }

    public String get_SoLuong() {
        return _SoLuong;
    }

    public void set_SoLuong(String _SoLuong) {
        this._SoLuong = _SoLuong;
    }

    public String get_NoiDungTomLuoc() {
        return _NoiDungTomLuoc;
    }

    public void set_NoiDungTomLuoc(String _NoiDungTomLuoc) {
        this._NoiDungTomLuoc = _NoiDungTomLuoc;
    }

    @Override
    public String toString() {
        return get_MSSach()+get_TenSach()+get_TacGia()+get_MSNhaXB()+get_MaLoaiSach()+get_NamXB()+get_LanXB()+get_SoLuong()+get_NoiDungTomLuoc();
    }
    public boolean isFullInfo(){
        if(get_MSNhaXB().length() > 0 && get_TenSach().length() > 0 &&
                get_TacGia().length() > 0 && get_MSNhaXB().length() > 0 &&
                get_MaLoaiSach().length() > 0 && get_NamXB().length() > 0 &&
                get_SoLuong().length() > 0 && get_NoiDungTomLuoc().length() > 0) return true;
        return false;
    }
    public boolean checkNamXB(){
        try {
            int nam = Integer.parseInt(get_NamXB());
            if (nam > 1900 && nam < 2019) return true;
            return false;
        }catch (Exception e){
            return false;
        }
    }
    public boolean checkSL(){
        try {
            int nam = Integer.parseInt(get_SoLuong());
            if (nam > 0) return true;
            return false;
        }catch (Exception e){
            return false;
        }
    }
    public boolean checkLanXB(){
        try {
            int nam = Integer.parseInt(get_LanXB());
            if (nam > 0) return true;
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
