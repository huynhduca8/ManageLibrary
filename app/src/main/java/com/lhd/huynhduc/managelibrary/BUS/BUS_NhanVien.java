package com.lhd.huynhduc.managelibrary.BUS;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.Entity.EC_NhanVien;
import com.lhd.huynhduc.managelibrary.HTTPDataHandle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduc on 1/15/18.
 */

public class BUS_NhanVien {
    String col = "nhanvien";
    Gson gson = new Gson();
    HTTPDataHandle dataHandle = new HTTPDataHandle();
    //Thêm dư liệu
    public String InseartData(EC_NhanVien et)
    {
        String url = Variable.getUrlInsert(col,gson.toJson(et));
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //update dữ liệu
    public String UpdateData(EC_NhanVien ec,EC_NhanVien em)
    {
        String url = Variable.getUrlUpdate(col,gson.toJson(ec),gson.toJson(em) );
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //xóa
    public String DeleteData(EC_NhanVien et)
    {
        String url = Variable.getUrldelete(col,et.get_MSNV());
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //lấy dữ liệu
    public ArrayList<EC_NhanVien> selectAllData()
    {
        String url = Variable.getUrlGetAll(col);
        String rs = dataHandle.getHTTPData(url);
        Type listType = new TypeToken<List<EC_NhanVien>>(){}.getType();
        ArrayList<EC_NhanVien> ec_loaiSachArrayList = gson.fromJson(rs,listType);;
        return ec_loaiSachArrayList;
    }

    public String getNamebyMa(String ma)
    {
        ArrayList<EC_NhanVien> arrayList = selectAllData();
        for (EC_NhanVien e:arrayList
                ) {
            if(e.get_MSNV().equalsIgnoreCase(ma))
                return e.get_HoTenNV();
        }
        return  "";
    }
    //lấy dữ liệu theo mã nhân viên
    public ArrayList<EC_NhanVien> selectData(EC_NhanVien et)
    {
        return null;
    }
    public ArrayList<EC_NhanVien> selectData_DSdag()
    {
        return null;
    }
    public void ChangePassNV(EC_NhanVien et)
    {

    }
    //kiểm tra tài khoản
    public String checkAcount(String taikhoan, String matkhau)
    {
        return null;
    }
    //Tìm kiếm theo tên nhân viên
    public ArrayList<EC_NhanVien> searchName(String TenNV)
    {
        return null;
    }

    public ArrayList<String> getListMaNV() {
        ArrayList<String> lMA = new ArrayList<>();
        ArrayList<EC_NhanVien> arrayList= selectAllData();
        for (EC_NhanVien e:arrayList
                ) {
            lMA.add(e.get_MSNV());
        }
        return lMA;
    }
    public ArrayList<String> getListMaNVandNAme() {
        ArrayList<String> lMA = new ArrayList<>();
        ArrayList<EC_NhanVien> arrayList= selectAllData();
        for (EC_NhanVien e:arrayList
                ) {
            lMA.add(e.get_MSNV()+"---"+e.get_HoTenNV());
        }
        return lMA;
    }
}
