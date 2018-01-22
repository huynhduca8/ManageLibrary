package com.lhd.huynhduc.managelibrary.PQLMuonTra.QLDSMuon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.Entity.EC_MuonSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_MuonSach.DSMuonTheoMa;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class DSMuonTheoMa_ListViewAdapter extends ArrayAdapter<EC_MuonSach.DSMuonTheoMa> implements Filterable {
    int groupid;
    ArrayList<EC_MuonSach.DSMuonTheoMa> mStringFilterList;
    ArrayList<EC_MuonSach.DSMuonTheoMa> mStringList;
    ArrayList<EC_MuonSach.DSMuonTheoMa> item_list;
    Context context;
    public DSMuonTheoMa_ListViewAdapter(Context context, int vg, int id, ArrayList<EC_MuonSach.DSMuonTheoMa> item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;
        this.mStringFilterList = new ArrayList<EC_MuonSach.DSMuonTheoMa>();
        this.mStringFilterList.addAll(item_list);
        mStringList = item_list;


    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView txtMSS;
        public TextView txtTenSach;
        public TextView txtNgayMuon;
        public TextView txtNgayTra;
        public TextView txtTinhTrang;
        public
        void set(EC_MuonSach.DSMuonTheoMa ec_NXB){
            txtMSS.setText(ec_NXB.get_MSSach());
            txtTenSach.setText(ec_NXB.get_TenSach());
            txtNgayMuon.setText(ec_NXB.get_NgayMuon());
            txtNgayTra.setText(ec_NXB.get_HanTra());
            String tt = (new EC_MuonSach().checkDate(txtNgayTra.getText().toString()))?"Hết hạn":"Còn hạn";
            txtTinhTrang.setText(tt);
        }

    }
    @Override
    public int getCount() {
        return mStringList.size();
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public EC_MuonSach.DSMuonTheoMa getItem(int position) {

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
            viewHolder.txtMSS= (TextView) rowView.findViewById(R.id.txt_dsmuon_theoma_mss);
            viewHolder.txtTenSach= (TextView) rowView.findViewById(R.id.txt_dsmuon_theoma_tensach);
            viewHolder.txtNgayMuon= (TextView) rowView.findViewById(R.id.txt_dsmuon_theoma_ngaymuon);
            viewHolder.txtNgayTra= (TextView) rowView.findViewById(R.id.txt_dsmuon_theoma_ngaytra);
            viewHolder.txtTinhTrang= (TextView) rowView.findViewById(R.id.txt_dsmuon_theoma_tinhtrang);

            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.set(item_list.get(position));
        return rowView;
    }


}