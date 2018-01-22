package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLDocGia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.Entity.EC_DocGia;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class DocGia_ListViewAdapter extends ArrayAdapter<EC_DocGia> implements Filterable {
    int groupid;
    ArrayList<EC_DocGia> mStringFilterList;
    ArrayList<EC_DocGia> mStringList;
    ArrayList<EC_DocGia> item_list;
    Context context;
    public DocGia_ListViewAdapter(Context context, int vg, int id, ArrayList<EC_DocGia> item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;
        this.mStringFilterList = new ArrayList<EC_DocGia>();
        this.mStringFilterList.addAll(item_list);
        mStringList = item_list;

        getFilter();

    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView txtMa;
        public TextView txtTen;
        public TextView txtGioiTinh;
        void set(EC_DocGia ec_loaiSach){
            txtMa.setText(ec_loaiSach.get_MSDG());
            txtTen.setText(ec_loaiSach.get_TenDG());
            txtGioiTinh.setText(ec_loaiSach.get_GioiTinh());
        }

    }
    @Override
    public int getCount() {
        return mStringList.size();
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public EC_DocGia getItem(int position) {

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
            viewHolder.txtMa= (TextView) rowView.findViewById(R.id.txtMaDocGia);
            viewHolder.txtTen= (TextView) rowView.findViewById(R.id.txtTenDocGia);
            viewHolder.txtGioiTinh = (TextView) rowView.findViewById(R.id.txtGioiTinhDocGia);
            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.set(item_list.get(position));
        return rowView;
    }



}