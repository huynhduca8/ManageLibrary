package com.lhd.huynhduc.managelibrary.PQLMuonTra.QLDSMuon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.Entity.EC_MuonSach;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class DSMuon_ListViewAdapter extends ArrayAdapter<EC_MuonSach> implements Filterable {
    int groupid;
    ArrayList<EC_MuonSach> mStringList;
    ArrayList<EC_MuonSach> item_list;
    Context context;
    public DSMuon_ListViewAdapter(Context context, int vg, int id, ArrayList<EC_MuonSach> item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;
        mStringList = item_list;


    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView txtSoPhieuMuon;
        public TextView txtMSDocGia;
        public TextView txtMSNhanVien;
        public TextView txtNgayMuon;
        public
        void set(EC_MuonSach ec_NXB){
                txtSoPhieuMuon.setText(ec_NXB.get_SoPhieuMuon());
                txtMSDocGia.setText(ec_NXB.get_MSDG());
                txtMSNhanVien.setText(ec_NXB.get_MSNV());
                txtNgayMuon.setText(ec_NXB.get_NgayMuon());
        }

    }
    @Override
    public int getCount() {
        return mStringList.size();
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public EC_MuonSach getItem(int position) {

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
            viewHolder.txtSoPhieuMuon= (TextView) rowView.findViewById(R.id.txtSoPhieuMuon);
            viewHolder.txtMSDocGia= (TextView) rowView.findViewById(R.id.txtSoDocGia);
            viewHolder.txtMSNhanVien= (TextView) rowView.findViewById(R.id.txtSoNhanVien);
            viewHolder.txtNgayMuon= (TextView) rowView.findViewById(R.id.txtNgayTra);

            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.set(item_list.get(position));
        return rowView;
    }


}