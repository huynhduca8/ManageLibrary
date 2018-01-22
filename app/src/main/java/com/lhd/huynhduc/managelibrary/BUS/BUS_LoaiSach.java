package com.lhd.huynhduc.managelibrary.BUS;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.Entity.EC_LoaiSach;
import com.lhd.huynhduc.managelibrary.HTTPDataHandle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduc on 1/15/18.
 */

public class BUS_LoaiSach {
    Gson gson = new Gson();
    String col = "loaisach";
    HTTPDataHandle dataHandle = new HTTPDataHandle();
    public String InseartData(EC_LoaiSach et)
    {
        String url = Variable.getUrlInsert(col,gson.toJson(et));
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //update dữ liệu
    public String UpdateData(EC_LoaiSach ec,EC_LoaiSach em)
    {
        String url = Variable.getUrlUpdate(col,gson.toJson(ec),gson.toJson(em) );
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //xóa
    public String DeleteData(EC_LoaiSach et)
    {
        String url = Variable.getUrldelete(col,et.get_MaLoaiSach());
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //lấy dữ liệu
    public ArrayList<EC_LoaiSach> selectAllData()
    {
        String url = Variable.getUrlGetAll(col);
        String rs = dataHandle.getHTTPData(url);
        Type listType = new TypeToken<List<EC_LoaiSach>>(){}.getType();
        ArrayList<EC_LoaiSach> ec_loaiSachArrayList = gson.fromJson(rs,listType);;

        return ec_loaiSachArrayList;
    }
    public ArrayList<String> getListMaNXB(){
        ArrayList<String> lMA = new ArrayList<>();
        ArrayList<EC_LoaiSach> arrayList= selectAllData();
        for (EC_LoaiSach e:arrayList
                ) {
            lMA.add(e.get_MaLoaiSach());
        }
        return lMA;
    }
    //lấy dữ liệu theo mã loại sách
    public ArrayList<EC_LoaiSach> selectData(EC_LoaiSach et)
    {
        return null;
    }
    //tìm tiếm theo tên sách
    public ArrayList<EC_LoaiSach> searchNameBook(EC_LoaiSach et)
    {
        return null;
    }
}
