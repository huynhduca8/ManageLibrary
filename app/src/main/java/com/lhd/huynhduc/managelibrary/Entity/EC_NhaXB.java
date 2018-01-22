package com.lhd.huynhduc.managelibrary.Entity;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_NhaXB {
    private String _MSNXB;

    
    private String _TenNXB;

    
    private String _DiaChiNXB;

    
    private String _WebSiteNXB;

    
    private String _ThongTinKhacNXB;

    public EC_NhaXB() {
    }

    public EC_NhaXB(String _MSNXB, String _TenNXB, String _DiaChiNXB, String _WebSiteNXB, String _ThongTinKhacNXB) {

        this._MSNXB = _MSNXB;
        this._TenNXB = _TenNXB;
        this._DiaChiNXB = _DiaChiNXB;
        this._WebSiteNXB = _WebSiteNXB;
        this._ThongTinKhacNXB = _ThongTinKhacNXB;
    }

    public String get_MSNXB() {

        return _MSNXB;
    }

    public void set_MSNXB(String _MSNXB) {
        this._MSNXB = _MSNXB;
    }

    public String get_TenNXB() {
        return _TenNXB;
    }

    public void set_TenNXB(String _TenNXB) {
        this._TenNXB = _TenNXB;
    }

    public String get_DiaChiNXB() {
        return _DiaChiNXB;
    }

    public void set_DiaChiNXB(String _DiaChiNXB) {
        this._DiaChiNXB = _DiaChiNXB;
    }

    public String get_WebSiteNXB() {
        return _WebSiteNXB;
    }

    public void set_WebSiteNXB(String _WebSiteNXB) {
        this._WebSiteNXB = _WebSiteNXB;
    }

    public String get_ThongTinKhacNXB() {
        return _ThongTinKhacNXB;
    }

    public void set_ThongTinKhacNXB(String _ThongTinKhacNXB) {
        this._ThongTinKhacNXB = _ThongTinKhacNXB;
    }
    @Override
    public String toString() {
        return get_MSNXB()+get_TenNXB()+get_DiaChiNXB()+get_WebSiteNXB()+get_ThongTinKhacNXB();
    }
    public boolean isFullInfo(){
        if(get_MSNXB().length() > 0 && get_TenNXB().length() > 0 &&
                get_DiaChiNXB().length() > 0 && get_WebSiteNXB().length() > 0 &&
                get_ThongTinKhacNXB().length() > 0) return true;
        return false;
    }
}
