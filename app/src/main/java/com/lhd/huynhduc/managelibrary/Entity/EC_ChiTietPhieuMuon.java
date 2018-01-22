package com.lhd.huynhduc.managelibrary.Entity;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_ChiTietPhieuMuon {
    private String _SoPhieuMuon;


    private String _MSSach;


    private String _HanTra;

    public EC_ChiTietPhieuMuon() {
    }

    public EC_ChiTietPhieuMuon(String _SoPhieuMuon, String _MSSach, String _HanTra) {

        this._SoPhieuMuon = _SoPhieuMuon;
        this._MSSach = _MSSach;
        this._HanTra = _HanTra;
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

    public String get_HanTra() {
        return _HanTra;
    }

    public void set_HanTra(String _HanTra) {
        this._HanTra = _HanTra;
    }
}
