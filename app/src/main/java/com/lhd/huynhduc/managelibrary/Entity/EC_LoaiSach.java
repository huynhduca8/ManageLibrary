package com.lhd.huynhduc.managelibrary.Entity;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_LoaiSach {
    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private String _MaLoaiSach;


    private String _LoaiSach;

    public EC_LoaiSach(String _MaLoaiSach, String _LoaiSach) {
        this._MaLoaiSach = _MaLoaiSach;
        this._LoaiSach = _LoaiSach;
    }

    public EC_LoaiSach() {
    }

    public String get_MaLoaiSach() {
        return _MaLoaiSach;
    }

    public void set_MaLoaiSach(String _MaLoaiSach) {
        this._MaLoaiSach = _MaLoaiSach;
    }

    public String get_LoaiSach() {
        return _LoaiSach;
    }

    public void set_LoaiSach(String _LoaiSach) {
        this._LoaiSach = _LoaiSach;
    }
}
