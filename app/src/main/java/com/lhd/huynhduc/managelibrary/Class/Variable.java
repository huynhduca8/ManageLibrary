package com.lhd.huynhduc.managelibrary.Class;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huynhduc on 13/01/2018.
 */

public class Variable {
    public static String URL_LOCAL = "http://10.0.2.2/";
    public static String _SoPhieuCanDoi = "";
    public static String getUrlGetAll(String col){
        return URL_LOCAL + "get?col="+col+"&action=getall";
    }
    public static String getUrldelete(String col,String ma){
        return URL_LOCAL + "get?col="+col+"&action=delete&ma="+ma;
    }
    public static String getUrldeleteJS(String col,String js){
        return URL_LOCAL + "get?col="+col+"&action=xoaphieumuonjs&data="+js;
    }
    public static String getUrlInsert(String col,String data){
        return URL_LOCAL + "get?col="+col+"&action=insert&data="+data;
    }
    public static String getUrlUpdate(String col,String dataold,String datanew){
        return URL_LOCAL + "get?col="+col+"&action=update&dataold="+dataold + "&datanew="+datanew;
    }
    public static String getUrllsMuonSach(String ma){
        return URL_LOCAL + "get?col=docgia&action=lsmuonsach&ma="+ma;
    }
    public static String getUrllsTraSachSach(String ma){
        return URL_LOCAL + "get?col=docgia&action=lstrasach&ma="+ma;
    }

    public static String getUrldsMuonSachTheoMa(String ma){
        return URL_LOCAL + "get?col=muonsach&action=dsmuontheomaphieu&ma="+ma;
    }

    public static String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        Date date = new Date();
        return (dateFormat.format(date));
    }
    public static boolean isOKDate(String psDate1) {
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
            Date date1 = dateFormat.parse(psDate1);
            Date date2 = dateFormat.parse(getCurrentDate());
            return (date1.compareTo(date2)<=0)?true:false;
        }catch (Exception e){return false;}

    }
    public static String decode( String in)
    {
        String working = in;
        int index;
        index = working.indexOf("\\u");
        while(index > -1)
        {
            int length = working.length();
            if(index > (length-6))break;
            int numStart = index + 2;
            int numFinish = numStart + 4;
            String substring = working.substring(numStart, numFinish);
            int number = Integer.parseInt(substring,16);
            String stringStart = working.substring(0, index);
            String stringEnd   = working.substring(numFinish);
            working = stringStart + ((char)number) + stringEnd;
            index = working.indexOf("\\u");
        }
        return working;
    }
}
