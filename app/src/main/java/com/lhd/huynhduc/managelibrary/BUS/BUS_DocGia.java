package com.lhd.huynhduc.managelibrary.BUS;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.Entity.EC_DocGia;
import com.lhd.huynhduc.managelibrary.Entity.EC_DocGia;
import com.lhd.huynhduc.managelibrary.Entity.EC_LoaiSach;
import com.lhd.huynhduc.managelibrary.HTTPDataHandle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduc on 1/15/18.
 */

public class BUS_DocGia {
    String col = "docgia";
    Gson gson = new Gson();
    HTTPDataHandle dataHandle = new HTTPDataHandle();
    //Thêm dư liệu
    public String InseartData(EC_DocGia et)
    {
        String url = Variable.getUrlInsert(col,gson.toJson(et));
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //update dữ liệu
    public String UpdateData(EC_DocGia ec,EC_DocGia em)
    {
        String url = Variable.getUrlUpdate(col,gson.toJson(ec),gson.toJson(em) );
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //xóa
    public String DeleteData(EC_DocGia et)
    {
        String url = Variable.getUrldelete(col,et.get_MSDG());
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    public ArrayList<EC_DocGia> LichSuMuonSach(EC_DocGia et)
    {
        return null;
    }
    public ArrayList<EC_DocGia> LichSuTraSach(EC_DocGia et)
    {
        return null;
    }
    public ArrayList<EC_DocGia> BaoCaoTraSach()
    {
        return null;
    }
    public ArrayList<EC_DocGia> BaoCaoDocGiaMuon()
    {
        return null;
    }
    public void GiaHanTheThuVien(EC_DocGia et)
    {

    }
    //lấy dữ liệu
    public ArrayList<EC_DocGia> selectAllData()
    {
        String url = Variable.getUrlGetAll(col);
        String rs = dataHandle.getHTTPData(url);
        Type listType = new TypeToken<List<EC_DocGia>>(){}.getType();
        ArrayList<EC_DocGia> ec_loaiSachArrayList = gson.fromJson(rs,listType);;
        return ec_loaiSachArrayList;
    }

    public ArrayList<EC_DocGia.LichSuSach> getlsMuonSach(String ma)
    {
        String url = Variable.getUrllsMuonSach(ma);
        String rs = dataHandle.getHTTPData(url);
        Type listType = new TypeToken<List<EC_DocGia.LichSuSach>>(){}.getType();
        ArrayList<EC_DocGia.LichSuSach> ec_loaiSachArrayList = gson.fromJson(rs,listType);

        return ec_loaiSachArrayList;
    }
    public ArrayList<EC_DocGia.LichSuSach> getlsTraSach(String ma)
    {
        String url = Variable.getUrllsTraSachSach(ma);
        String rs = dataHandle.getHTTPData(url);
        Type listType = new TypeToken<List<EC_DocGia.LichSuSach>>(){}.getType();
        ArrayList<EC_DocGia.LichSuSach> ec_loaiSachArrayList = gson.fromJson(rs,listType);;
        return ec_loaiSachArrayList;
    }

    public ArrayList<EC_DocGia> LayTenvaEmailDG(EC_DocGia et)
    {
        return null;
    }
    //lấy dữ liệu theo mã Đọc giả
    public ArrayList<EC_DocGia> selectData(EC_DocGia et)
    {
        return null;
    }
    //Tìm kiếm đọc giả theo tên
    public String getNamebyMa(String ma)
    {
        ArrayList<EC_DocGia> arrayList = selectAllData();
        for (EC_DocGia e:arrayList
             ) {
            if(e.get_MSDG().equalsIgnoreCase(ma))
                return e.get_TenDG();
        }
        return  "";
    }
    public ArrayList<EC_DocGia> LapTheThuVien(EC_DocGia et)
    {
        return null;
    }

    public ArrayList<String> getListMaDG() {
        ArrayList<String> lMA = new ArrayList<>();
        ArrayList<EC_DocGia> arrayList= selectAllData();
        for (EC_DocGia e:arrayList
                ) {
            lMA.add(e.get_MSDG());
        }
        return lMA;
    }
    public ArrayList<String> getListMaDGandName(){
        ArrayList<String> lMA = new ArrayList<>();
        ArrayList<EC_DocGia> arrayList= selectAllData();
        for (EC_DocGia e:arrayList
                ) {
            lMA.add(e.get_MSDG()+"---"+e.get_TenDG());
        }
        return lMA;
    }


}
