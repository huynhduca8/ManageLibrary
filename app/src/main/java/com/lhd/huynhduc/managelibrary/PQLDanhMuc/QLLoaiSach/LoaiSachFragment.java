package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLLoaiSach;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.AdapterHelper;
import com.lhd.huynhduc.managelibrary.BUS.BUS_LoaiSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_LoaiSach;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class LoaiSachFragment extends Fragment{
    FloatingActionButton fab;
    boolean _isFilter = false;
    boolean _isCheckMa = false;
    RadioButton rb_danhmuc_timma,rb_danhmuc_timten;
    TextView txtTong;
    ListView listView;
    EditText txtTimKiem;
    TextWatcher mSearchTw;
    ArrayList<EC_LoaiSach> arr_loaisach = new ArrayList<>();
    public LoaiSach_ListViewAdapter _danhMucListViewAdapter;
    BUS_LoaiSach bus_loaiSach = new BUS_LoaiSach();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_loaisach,container,false);
        initUI(view);
        eventObject();
        return view;
    }

    private void initUI(View view){
        fab = (FloatingActionButton)view.findViewById(R.id.fab_add_loaisach);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(null,0);

            }
        });
        rb_danhmuc_timten = (RadioButton)view.findViewById(R.id.rb_danhmuc_timten);
        rb_danhmuc_timma = (RadioButton)view.findViewById(R.id.rb_danhmuc_timma);
        listView = (ListView)view.findViewById(R.id.lv_danhmuc_danhsach);
        txtTimKiem = (EditText)view.findViewById(R.id.txt_danhmuc_timkiem);
        txtTong = (TextView)view.findViewById(R.id.txtTongLoaiSach);

    }


    private void eventObject(){

        arr_loaisach = bus_loaiSach.selectAllData();
        if(_danhMucListViewAdapter != null) _danhMucListViewAdapter.clear();
        _danhMucListViewAdapter = new LoaiSach_ListViewAdapter(getContext(),
                R.layout.raw_danhmuc_danhsach,
                R.id.txtMaLoaiSach,
                arr_loaisach);
        listView.setAdapter(_danhMucListViewAdapter);
        txtTong.setText("Tổng loại sách "+_danhMucListViewAdapter.getCount());


        mSearchTw=new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 0) _isFilter = true;
                else _isFilter = false;
                _isCheckMa = rb_danhmuc_timma.isChecked();
                filterAdapter(s.toString());

            }
        };
        txtTimKiem.addTextChangedListener(mSearchTw);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(adapterView,i);

            }
        });


    }
    private void showDialog(AdapterView<?> adapterView,int pos){
        try{
            FragmentManager manager = getFragmentManager();
            LoaiSachDialogFragment loaiSachDialogFragment = new LoaiSachDialogFragment();
            if(adapterView != null)
                loaiSachDialogFragment.params(arr_loaisach,(EC_LoaiSach) adapterView.getItemAtPosition(pos),0);
            else
                loaiSachDialogFragment.params(arr_loaisach,null,1);
            loaiSachDialogFragment.show(manager,"");
            manager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {

                @Override
                public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                    super.onFragmentViewDestroyed(fm, f);
                    updateAdapter();


                }
            }, false);
        }catch (Exception em){}
    }

    private void updateAdapter(){
        arr_loaisach = bus_loaiSach.selectAllData();
        new AdapterHelper().update((ArrayAdapter)_danhMucListViewAdapter, new ArrayList<Object>(arr_loaisach));
        _danhMucListViewAdapter.notifyDataSetChanged();
        if(_isFilter)
            filterAdapter(txtTimKiem.getText().toString());
        txtTong.setText("Tổng loại sách "+_danhMucListViewAdapter.getCount());
    }

    private void filterAdapter(String s){
        ArrayList<EC_LoaiSach> temp = new ArrayList<>();

        for (EC_LoaiSach e:arr_loaisach
                ) {
            String data = "";
            if(_isCheckMa) data = e.get_MaLoaiSach();
            else data = e.get_LoaiSach();
            if (data.toLowerCase().contains(s.toLowerCase()))
                temp.add(e);
        }
        Log.v("LENGHT",arr_loaisach.size()+"--"+temp.size());
        _danhMucListViewAdapter = new LoaiSach_ListViewAdapter(getContext(),
                R.layout.raw_danhmuc_danhsach,
                R.id.txtMaLoaiSach,
                temp);
        listView.setAdapter(_danhMucListViewAdapter);
        txtTong.setText("Tổng loại sách "+_danhMucListViewAdapter.getCount());

    }


}
