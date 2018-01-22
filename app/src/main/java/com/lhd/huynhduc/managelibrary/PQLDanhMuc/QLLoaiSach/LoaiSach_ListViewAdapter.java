package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLLoaiSach;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.Entity.EC_LoaiSach;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class LoaiSach_ListViewAdapter extends ArrayAdapter<EC_LoaiSach> implements Filterable {
    int groupid;
    ArrayList<EC_LoaiSach> mStringFilterList;
    ArrayList<EC_LoaiSach> mStringList;
    ArrayList<EC_LoaiSach> item_list;
    Context context;
    public LoaiSach_ListViewAdapter(Context context, int vg, int id, ArrayList<EC_LoaiSach> item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;
        this.mStringFilterList = new ArrayList<EC_LoaiSach>();
        this.mStringFilterList.addAll(item_list);
        mStringList = item_list;


    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView txtMa;
        public TextView txtTen;
        void set(EC_LoaiSach ec_loaiSach){
            txtMa.setText(ec_loaiSach.get_MaLoaiSach());
            txtTen.setText(ec_loaiSach.get_LoaiSach());
        }

    }
    @Override
    public int getCount() {
        return mStringList.size();
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public EC_LoaiSach getItem(int position) {

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
            viewHolder.txtMa= (TextView) rowView.findViewById(R.id.txtMaLoaiSach);
            viewHolder.txtTen= (TextView) rowView.findViewById(R.id.txtTenLoaiSach);

            rowView.setTag(viewHolder);



        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.set(item_list.get(position));
        return rowView;
    }


}