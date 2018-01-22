package com.lhd.huynhduc.managelibrary.PQLMuonTra.QLDSMuon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.BUS.BUS_LoaiSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_LoaiSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_QuyenSach;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class DSQuyenSach_ThemSach_Adapter extends ArrayAdapter<EC_QuyenSach> implements Filterable {
    int groupid;
    ArrayList<EC_QuyenSach> mStringList;
    ArrayList<EC_QuyenSach> item_list;
    Context context;
    static ArrayList<EC_LoaiSach> ec_loaiSachArrayList;
    public DSQuyenSach_ThemSach_Adapter(Context context, int vg, int id, ArrayList<EC_QuyenSach> item_list,ArrayList<EC_LoaiSach> ec_loaiSaches){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;
        mStringList = item_list;
        ec_loaiSachArrayList = ec_loaiSaches;


    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView txtMSS;
        public TextView txtTenSach;
        public TextView txtTacGia;
        public TextView txtLoaiSach;
        public TextView txtSoLuong;
        public void set(EC_QuyenSach ec_quyenSach){
            txtMSS.setText(ec_quyenSach.get_MSSach());
            txtTenSach.setText(ec_quyenSach.get_TenSach());
            txtTacGia.setText(ec_quyenSach.get_TacGia());
            for (EC_LoaiSach e:ec_loaiSachArrayList
                 ) {
                if(e.get_MaLoaiSach().equalsIgnoreCase(ec_quyenSach.get_MaLoaiSach()))
                    txtLoaiSach.setText(e.get_LoaiSach());
            }

            
            txtSoLuong.setText(ec_quyenSach.get_SoLuong());
        }

    }
    @Override
    public int getCount() {
        return mStringList.size();
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public EC_QuyenSach getItem(int position) {

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
            viewHolder.txtMSS= (TextView) rowView.findViewById(R.id.txt_themsach_mssach);
            viewHolder.txtTenSach= (TextView) rowView.findViewById(R.id.txt_themsach_tensach);
            viewHolder.txtTacGia= (TextView) rowView.findViewById(R.id.txt_themsach_tacgia);
            viewHolder.txtLoaiSach= (TextView) rowView.findViewById(R.id.txt_themsach_loaisach);
            viewHolder.txtSoLuong= (TextView) rowView.findViewById(R.id.txt_themsach_soluong);

            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.set(item_list.get(position));
        return rowView;
    }


}