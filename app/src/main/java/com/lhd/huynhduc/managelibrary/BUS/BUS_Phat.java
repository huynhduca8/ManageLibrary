package com.lhd.huynhduc.managelibrary.BUS;

import com.lhd.huynhduc.managelibrary.Entity.EC_Phat;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class BUS_Phat {
    public void InseartData(EC_Phat et)
    {

    }
    //update dữ liệu
    public void UpdateData(EC_Phat et)
    {

    }
    //xóa
    public void DeleteData(EC_Phat et)
    {

    }
    public ArrayList<EC_Phat> LapPhieuPhat(EC_Phat et)
    {
        return null;
    }
    //lấy dữ liệu
    public ArrayList<EC_Phat> selectAllData()
    {
        return null;
    }
    //lấy dữ liệu theo mã Đọc giả
    public ArrayList<EC_Phat> selectData(EC_Phat et)
    {
        return null;
    }
    public ArrayList<EC_Phat> selectAllDataNhieuBang()
    {
        return null;
    }
    // kiểm tra MSSach và Kiểm tra MSDG tồn tại cùng 1 lúc hay không?
    public String checksBook(String MSSach)
    {
        String str = null;
        return str;
    }
    public String checksReader(String MSDG)
    {
        String str = null;
        return str;
    }
    public ArrayList<EC_Phat> checkValue(String MSSach, String MSDG)
    {
        ArrayList<EC_Phat> dt = new ArrayList<EC_Phat>();
        return dt;
    }
}
