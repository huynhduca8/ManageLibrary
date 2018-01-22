package com.lhd.huynhduc.managelibrary.BUS;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.Entity.EC_DocGia;
import com.lhd.huynhduc.managelibrary.Entity.EC_QuyenSach;
import com.lhd.huynhduc.managelibrary.HTTPDataHandle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduc on 1/15/18.
 */

public class BUS_QuyenSach {
    String col = "quyensach";
    Gson gson = new Gson();
    HTTPDataHandle dataHandle = new HTTPDataHandle();
    public String InseartData(EC_QuyenSach et)
    {
        String url = Variable.getUrlInsert(col,gson.toJson(et));
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //update dữ liệu
    public String UpdateData(EC_QuyenSach ec,EC_QuyenSach em)
    {
        String url = Variable.getUrlUpdate(col,gson.toJson(ec),gson.toJson(em) );
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //xóa
    public String DeleteData(EC_QuyenSach et)
    {
        String url = Variable.getUrldelete(col,et.get_MSSach());
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }

    public ArrayList<EC_QuyenSach> BaoCaoSLSach()
    {
        return null;
    }
    //lấy dữ liệu
    public ArrayList<EC_QuyenSach> selectAllData()
    {
        String url = Variable.getUrlGetAll(col);
        String rs = dataHandle.getHTTPData(url);
        Type listType = new TypeToken<List<EC_QuyenSach>>(){}.getType();
        ArrayList<EC_QuyenSach> ec_loaiSachArrayList = gson.fromJson(rs,listType);;
        return ec_loaiSachArrayList;
    }
    //lấy dữ liệu theo mã nhà xuất bản
    public ArrayList<EC_QuyenSach> selectDataTheoMa(EC_QuyenSach et)
    {
        ArrayList<EC_QuyenSach> dt = new ArrayList<EC_QuyenSach>();
        return dt;
    }
    public ArrayList<EC_QuyenSach> selectDataTheoTen(EC_QuyenSach et)
    {
        ArrayList<EC_QuyenSach> dt = new ArrayList<EC_QuyenSach>();
        return dt;
    }
    public ArrayList<EC_QuyenSach> selectDataTheoTacGia(EC_QuyenSach et)
    {
        ArrayList<EC_QuyenSach> dt = new ArrayList<EC_QuyenSach>();
        return dt;
    }
    public ArrayList<EC_QuyenSach> selectDataTheoMaNXB(EC_QuyenSach et)
    {
        ArrayList<EC_QuyenSach> dt = new ArrayList<EC_QuyenSach>();
        return dt;
    }
    public ArrayList<EC_QuyenSach> DSSMuon()
    {
        return null;
    }
    public ArrayList<EC_QuyenSach> CS_TimKiemTheoTen(String ten)
    {
        return null;
    }
}
