package com.lhd.huynhduc.managelibrary.Entity;

/**
 * Created by huynhduc on 1/15/18.
 */

public class EC_Phat {
    private String _MSSach;

    
    private String _MSDG;

    
    private String _LyDoPhat;

    public EC_Phat() {
    }

    public EC_Phat(String _MSSach, String _MSDG, String _LyDoPhat) {

        this._MSSach = _MSSach;
        this._MSDG = _MSDG;
        this._LyDoPhat = _LyDoPhat;
    }

    public String get_MSSach() {

        return _MSSach;
    }

    public void set_MSSach(String _MSSach) {
        this._MSSach = _MSSach;
    }

    public String get_MSDG() {
        return _MSDG;
    }

    public void set_MSDG(String _MSDG) {
        this._MSDG = _MSDG;
    }

    public String get_LyDoPhat() {
        return _LyDoPhat;
    }

    public void set_LyDoPhat(String _LyDoPhat) {
        this._LyDoPhat = _LyDoPhat;
    }
}
