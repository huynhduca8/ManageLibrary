package com.lhd.huynhduc.managelibrary.BUS;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.Entity.EC_DocGia;
import com.lhd.huynhduc.managelibrary.Entity.EC_NhaXB;
import com.lhd.huynhduc.managelibrary.Entity.EC_NhaXB;
import com.lhd.huynhduc.managelibrary.HTTPDataHandle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduc on 1/15/18.
 */

public class BUS_NhaXuatBan {
    String col = "nhaxb";
    Gson gson = new Gson();
    HTTPDataHandle dataHandle = new HTTPDataHandle();
    public String InseartData(EC_NhaXB et)
    {
        String url = Variable.getUrlInsert(col,gson.toJson(et));
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //update dữ liệu
    public String UpdateData(EC_NhaXB ec,EC_NhaXB em)
    {
        String url = Variable.getUrlUpdate(col,gson.toJson(ec),gson.toJson(em) );
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //xóa
    public String DeleteData(EC_NhaXB et)
    {
        String url = Variable.getUrldelete(col,et.get_MSNXB());
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //lấy dữ liệu
    public ArrayList<EC_NhaXB> selectAllData()
    {
        String url = Variable.getUrlGetAll(col);
        String rs = dataHandle.getHTTPData(url);
        Type listType = new TypeToken<List<EC_NhaXB>>(){}.getType();
        ArrayList<EC_NhaXB> ec_loaiSachArrayList = gson.fromJson(rs,listType);;
        return ec_loaiSachArrayList;
    }
    public ArrayList<String> getListMaNXB(){
        ArrayList<String> lMA = new ArrayList<>();
        ArrayList<EC_NhaXB> arrayList= selectAllData();
        for (EC_NhaXB e:arrayList
             ) {
            lMA.add(e.get_MSNXB());
        }
        return lMA;
    }
    //lấy dữ liệu theo mã nhà xuất bản
    public ArrayList<EC_NhaXB> selectData(EC_NhaXB et)
    {
        return null;
    }
    public ArrayList<EC_NhaXB> searchCompany(String nameCompany)
    {
        return null;
    }
}
