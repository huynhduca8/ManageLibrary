package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLNhaXB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.Entity.EC_NhaXB;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class NhaXB_ListViewAdapter extends ArrayAdapter<EC_NhaXB> implements Filterable {
    int groupid;
    ArrayList<EC_NhaXB> mStringFilterList;
    ArrayList<EC_NhaXB> mStringList;
    ArrayList<EC_NhaXB> item_list;
    Context context;
    public NhaXB_ListViewAdapter(Context context, int vg, int id, ArrayList<EC_NhaXB> item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;
        this.mStringFilterList = new ArrayList<EC_NhaXB>();
        this.mStringFilterList.addAll(item_list);
        mStringList = item_list;


    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView txtMa;
        public TextView txtTen;
        public TextView txtDiaChi;
        void set(EC_NhaXB ec_NXB){
            txtMa.setText(ec_NXB.get_MSNXB());
            txtTen.setText(ec_NXB.get_TenNXB());
            txtDiaChi.setText(ec_NXB.get_DiaChiNXB());
        }

    }
    @Override
    public int getCount() {
        return mStringList.size();
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public EC_NhaXB getItem(int position) {

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
            viewHolder.txtMa= (TextView) rowView.findViewById(R.id.txtMaNXB);
            viewHolder.txtTen= (TextView) rowView.findViewById(R.id.txtTenNXB);
            viewHolder.txtDiaChi= (TextView) rowView.findViewById(R.id.txtDiaChiNXB);

            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.set(item_list.get(position));
        return rowView;
    }


}