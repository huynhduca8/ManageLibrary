package com.lhd.huynhduc.managelibrary.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_DocGia {
    private String _MSDG;


    private String _TenDG;


    private String _DiaChi;


    private String _NgaySinh;


    private String _Email;


    private String _GioiTinh;


    private String _HanSuDung;

    public EC_DocGia() {
    }

    public EC_DocGia(String _MSDG, String _TenDG, String _DiaChi, String _NgaySinh, String _Email, String _GioiTinh, String _HanSuDung) {

        this._MSDG = _MSDG;
        this._TenDG = _TenDG;
        this._DiaChi = _DiaChi;
        this._NgaySinh = _NgaySinh;
        this._Email = _Email;
        this._GioiTinh = _GioiTinh;
        this._HanSuDung = _HanSuDung;
    }

    public String get_MSDG() {
        return _MSDG;
    }

    public void set_MSDG(String _MSDG) {
        this._MSDG = _MSDG;
    }

    public String get_TenDG() {
        return _TenDG;
    }

    public void set_TenDG(String _TenDG) {
        this._TenDG = _TenDG;
    }

    public String get_DiaChi() {
        return _DiaChi;
    }

    public void set_DiaChi(String _DiaChi) {
        this._DiaChi = _DiaChi;
    }

    public String get_NgaySinh() {
        return _NgaySinh;
    }

    public void set_NgaySinh(String _NgaySinh) {
        this._NgaySinh = _NgaySinh;
    }

    public String get_Email() {
        return _Email;
    }

    public void set_Email(String _Email) {
        this._Email = _Email;
    }

    public String get_GioiTinh() {
        return _GioiTinh;
    }

    public void set_GioiTinh(String _GioiTinh) {
        this._GioiTinh = _GioiTinh;
    }

    public String get_HanSuDung() {
        return _HanSuDung;
    }

    public void set_HanSuDung(String _HanSuDung) {
        this._HanSuDung = _HanSuDung;
    }

    @Override
    public String toString() {
        return get_MSDG()+get_TenDG()+get_DiaChi()+get_NgaySinh()+get_Email()+get_GioiTinh()+get_HanSuDung();
    }
    public boolean isFullInfo(){
        if(get_MSDG().length() > 0 && get_TenDG().length() > 0 &&
                get_DiaChi().length() > 0 && get_NgaySinh().length() > 0 &&
                get_Email().length() > 0 && get_GioiTinh().length() > 0 &&
                get_HanSuDung().length() > 0) return true;
        return false;
    }
    public boolean checkDate(String dateToValdate) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //To make strict date format validation
        formatter.setLenient(false);
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(dateToValdate);
            return true;

        } catch (ParseException e) {
            //Handle exception
        }
        return false;
    }


    public class LichSuSach{
        private String _SoPhieuMuon,_Ngay,_MSSach,_TenSach;

        public LichSuSach(String _SoPhieuMuon, String _Ngay, String _MSSach, String _TenSach) {
            this._SoPhieuMuon = _SoPhieuMuon;
            this._Ngay = _Ngay;
            this._MSSach = _MSSach;
            this._TenSach = _TenSach;
        }

        public String get_SoPhieuMuon() {
            return _SoPhieuMuon;
        }

        public void set_SoPhieuMuon(String _SoPhieuMuon) {
            this._SoPhieuMuon = _SoPhieuMuon;
        }

        public String get_Ngay() {
            return _Ngay;
        }

        public void set_Ngay(String _Ngay) {
            this._Ngay = _Ngay;
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
    }
}
