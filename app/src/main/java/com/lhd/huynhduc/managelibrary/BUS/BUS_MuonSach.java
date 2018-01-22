package com.lhd.huynhduc.managelibrary.BUS;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.Entity.EC_MuonSach;
import com.lhd.huynhduc.managelibrary.HTTPDataHandle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduc on 1/15/18.
 */

public class BUS_MuonSach {
    String col = "muonsach";
    Gson gson = new Gson();
    HTTPDataHandle dataHandle = new HTTPDataHandle();
    //Thêm dư liệu
    public String InseartData(EC_MuonSach et)
    {
        String url = Variable.getUrlInsert(col,gson.toJson(et));
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //update dữ liệu
    public String UpdateData(EC_MuonSach ec,EC_MuonSach em)
    {
        String url = Variable.getUrlUpdate(col,gson.toJson(ec),gson.toJson(em) );
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    //xóa
    public String DeleteData(EC_MuonSach et)
    {
        String url = Variable.getUrldelete(col,et.get_SoPhieuMuon());
        String rs = dataHandle.getHTTPData(url);
        return rs;
    }
    public ArrayList<EC_MuonSach> selectAllData()
    {
        String url = Variable.getUrlGetAll(col);
        String rs = dataHandle.getHTTPData(url);
        Type listType = new TypeToken<List<EC_MuonSach>>(){}.getType();
        ArrayList<EC_MuonSach> ec_loaiSachArrayList = gson.fromJson(rs,listType);;
        return ec_loaiSachArrayList;
    }


    public ArrayList<EC_MuonSach.DSMuonTheoMa> getDSMuonTheoMa(String ma)
    {
        String url = Variable.getUrldsMuonSachTheoMa(ma);
        String rs = dataHandle.getHTTPData(url);
        Type listType = new TypeToken<List<EC_MuonSach.DSMuonTheoMa>>(){}.getType();
        ArrayList<EC_MuonSach.DSMuonTheoMa> ec_loaiSachArrayList = gson.fromJson(rs,listType);

        return ec_loaiSachArrayList;
    }
    public void changeisMuon(){
        if(Variable._SoPhieuCanDoi.length() > 0) {
            String url = Variable.getUrlUpdate(col, "{\"_SoPhieuMuon\":\"" + Variable._SoPhieuCanDoi + "\"}", "{\"_isMuonThem\":\"0\"}");
            dataHandle.getHTTPData(url);
            Variable._SoPhieuCanDoi = "";
        }
    }

}
