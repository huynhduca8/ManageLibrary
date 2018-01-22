package com.lhd.huynhduc.managelibrary.Entity;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_TraSach {
    private String _SoPhieuMuon;

    
    private String _MSSach;

    
    private String _MSNV;

    
    private String _NgayTra;

    public EC_TraSach() {
    }

    public EC_TraSach(String _SoPhieuMuon, String _MSSach, String _MSNV, String _NgayTra) {

        this._SoPhieuMuon = _SoPhieuMuon;
        this._MSSach = _MSSach;
        this._MSNV = _MSNV;
        this._NgayTra = _NgayTra;
    }

    public String get_SoPhieuMuon() {

        return _SoPhieuMuon;
    }

    public void set_SoPhieuMuon(String _SoPhieuMuon) {
        this._SoPhieuMuon = _SoPhieuMuon;
    }

    public String get_MSSach() {
        return _MSSach;
    }

    public void set_MSSach(String _MSSach) {
        this._MSSach = _MSSach;
    }

    public String get_MSNV() {
        return _MSNV;
    }

    public void set_MSNV(String _MSNV) {
        this._MSNV = _MSNV;
    }

    public String get_NgayTra() {
        return _NgayTra;
    }

    public void set_NgayTra(String _NgayTra) {
        this._NgayTra = _NgayTra;
    }
}
