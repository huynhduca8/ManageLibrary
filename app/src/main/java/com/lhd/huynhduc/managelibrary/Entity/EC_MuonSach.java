package com.lhd.huynhduc.managelibrary.Entity;

import com.lhd.huynhduc.managelibrary.Class.Variable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_MuonSach {
    private String _SoPhieuMuon;

    
    private String _MSDG;

    
    private String _MSNV;

    
    private String _NgayMuon;

    private String _isMuonThem = "0";

    public String get_isMuonThem() {
        return _isMuonThem;
    }

    public void set_isMuonThem(String _isMuonThem) {
        this._isMuonThem = _isMuonThem;
    }

    public EC_MuonSach() {
    }

    public EC_MuonSach(String _SoPhieuMuon, String _MSDG, String _MSNV, String _NgayMuon, String _isMuonThem) {
        this._SoPhieuMuon = _SoPhieuMuon;
        this._MSDG = _MSDG;
        this._MSNV = _MSNV;
        this._NgayMuon = _NgayMuon;
        this._isMuonThem = _isMuonThem;
    }

    public EC_MuonSach(String _SoPhieuMuon, String _MSDG, String _MSNV, String _NgayMuon) {

        this._SoPhieuMuon = _SoPhieuMuon;
        this._MSDG = _MSDG;
        this._MSNV = _MSNV;
        this._NgayMuon = _NgayMuon;
    }

    public String get_SoPhieuMuon() {

        return _SoPhieuMuon;
    }

    public void set_SoPhieuMuon(String _SoPhieuMuon) {
        this._SoPhieuMuon = _SoPhieuMuon;
    }

    public String get_MSDG() {
        return _MSDG;
    }

    public void set_MSDG(String _MSDG) {
        this._MSDG = _MSDG;
    }

    public String get_MSNV() {
        return _MSNV;
    }

    public void set_MSNV(String _MSNV) {
        this._MSNV = _MSNV;
    }

    public String get_NgayMuon() {
        return _NgayMuon;
    }

    public void set_NgayMuon(String _NgayMuon) {
        this._NgayMuon = _NgayMuon;
    }



    @Override
    public String toString() {
        return get_SoPhieuMuon()+get_MSDG()+get_MSNV()+get_NgayMuon();
    }
    public boolean isFullInfo(){
        if(get_SoPhieuMuon().length() > 0 && get_MSDG().length() > 0 &&
                get_MSNV().length() > 0 && get_NgayMuon().length() > 0 ) return true;
        return false;
    }
    public boolean checkDate(String date) {

        return Variable.isOKDate(date);
    }
    public class DSMuonTheoMa{
        private String _MSSach;
        private String _TenSach;
        private String _NgayMuon;
        private String _HanTra;

        public DSMuonTheoMa(String _MSSach, String _TenSach, String _NgayMuon, String _HanTra) {
            this._MSSach = _MSSach;
            this._TenSach = _TenSach;
            this._NgayMuon = _NgayMuon;
            this._HanTra = _HanTra;
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

        public String get_NgayMuon() {
            return _NgayMuon;
        }

        public void set_NgayMuon(String _NgayMuon) {
            this._NgayMuon = _NgayMuon;
        }

        public String get_HanTra() {
            return _HanTra;
        }

        public void set_HanTra(String _HanTra) {
            this._HanTra = _HanTra;
        }
    }
}
