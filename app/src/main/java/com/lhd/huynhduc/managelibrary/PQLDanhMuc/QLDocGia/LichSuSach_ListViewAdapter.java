package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLDocGia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.BUS.BUS_DocGia;
import com.lhd.huynhduc.managelibrary.Entity.EC_DocGia;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class LichSuSach_ListViewAdapter extends ArrayAdapter<EC_DocGia.LichSuSach> implements Filterable {
    int groupid;
    ArrayList<EC_DocGia.LichSuSach> mStringList;
    Context context;
    public LichSuSach_ListViewAdapter(Context context, int vg, int id, ArrayList<EC_DocGia.LichSuSach> item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        mStringList = item_list;

        getFilter();

    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView txt_raw_lssach_maphieu;
        public TextView txt_raw_lssach_masach;
        public TextView txt_raw_lssach_tensach;
        public TextView txt_raw_lssach_ngay;


        void set(EC_DocGia.LichSuSach lichSuMuonSach){
            txt_raw_lssach_maphieu.setText(lichSuMuonSach.get_SoPhieuMuon());
            txt_raw_lssach_masach.setText(lichSuMuonSach.get_MSSach());
            txt_raw_lssach_ngay.setText(lichSuMuonSach.get_Ngay());
            txt_raw_lssach_tensach.setText(lichSuMuonSach.get_TenSach());
        }

    }
    @Override
    public int getCount() {
        return mStringList.size();
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public EC_DocGia.LichSuSach getItem(int position) {

        return mStringList.get(position);
    }

    //Get the row id associated with the specified position in the list.
    @Override
    public long getItemId(int position) {

        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // Inflate the rowlayout.xml file if convertView is null
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txt_raw_lssach_tensach= (TextView) rowView.findViewById(R.id.txt_raw_lssach_tensach);
            viewHolder.txt_raw_lssach_ngay= (TextView) rowView.findViewById(R.id.txt_raw_lssach_ngay);
            viewHolder.txt_raw_lssach_masach = (TextView) rowView.findViewById(R.id.txt_raw_lssach_masach);
            viewHolder.txt_raw_lssach_maphieu = (TextView) rowView.findViewById(R.id.txt_raw_lssach_maphieu);
            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.set(mStringList.get(position));
        return rowView;
    }



}