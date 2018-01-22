package com.lhd.huynhduc.managelibrary.BUS;

import com.google.gson.Gson;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.Entity.EC_ChiTietPhieuMuon;
import com.lhd.huynhduc.managelibrary.HTTPDataHandle;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class BUS_ChiTietPhieuMuon {
    String col = "chitietphieumuon";
    Gson gson = new Gson();
    HTTPDataHandle dataHandle = new HTTPDataHandle();
    //Thêm dư liệu
    public String InseartData(EC_ChiTietPhieuMuon et)
    {
        String url = Variable.getUrlInsert(col,gson.toJson(et));
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //update dữ liệu
    public String UpdateData(EC_ChiTietPhieuMuon ec,EC_ChiTietPhieuMuon em)
    {
        String url = Variable.getUrlUpdate(col,gson.toJson(ec),gson.toJson(em) );
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //xóa
    public String DeleteData(EC_ChiTietPhieuMuon et)
    {
        return "";
    }
    public String DeleteDatabyOB(EC_ChiTietPhieuMuon et)
    {
        String url = Variable.getUrldeleteJS(col,gson.toJson(et));
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    
    //lấy dữ liệu
    public ArrayList<EC_ChiTietPhieuMuon> selectAllData()
    {
        return null;
    }
    //lấy dữ liệu theo mã nhà xuất bản
    public ArrayList<EC_ChiTietPhieuMuon> selectData(String SoPhieuMuon, String MSSach)
    {
        return null;
    }
    
}
