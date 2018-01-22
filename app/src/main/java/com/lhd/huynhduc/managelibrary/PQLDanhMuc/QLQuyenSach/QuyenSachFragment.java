package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLQuyenSach;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.AdapterHelper;
import com.lhd.huynhduc.managelibrary.BUS.BUS_QuyenSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_QuyenSach;
import com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLNhaXB.NXBDialogFragment;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class QuyenSachFragment extends Fragment{
    FloatingActionButton fab;
    boolean _isFilter = false;
    Spinner spin_timkiem;
    ListView listView;
    TextView txtTong;
    EditText txtTimKiem;
    TextWatcher mSearchTw;
    ArrayList<EC_QuyenSach> arr_quyensach = new ArrayList<>();
    public QuyenSach_ListViewAdapter QuyenSach_ListViewAdapter;
    BUS_QuyenSach bus_quyenSach  = new BUS_QuyenSach();

    String[] adapterTimKiem = new String[]{"Theo mã", "Theo tên","Theo địa chỉ"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_quyensach,container,false);
        initUI(view);
        eventObject();
        return view;
    }

    private void initUI(View view){
        arr_quyensach = bus_quyenSach.selectAllData();
        fab = (FloatingActionButton)view.findViewById(R.id.fab_tuychon_quyensach);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(null,0);

            }
        });
        spin_timkiem = (Spinner) view.findViewById(R.id.spin_quyensach_timkiem);
        listView = (ListView)view.findViewById(R.id.lv_quyensach_danhsach);
        txtTimKiem = (EditText)view.findViewById(R.id.txt_quyensach_timkiem);
        txtTong = (TextView)view.findViewById(R.id.txtTongQuyenSach) ;
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,
                        adapterTimKiem);
        ArrayAdapter<String> adapterSpinTK = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,
                        adapterTimKiem); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spin_timkiem.setAdapter(spinnerArrayAdapter);
        spin_timkiem.setSelection(0);

    }


    private void eventObject(){
        ViewGroup viewGroup = (ViewGroup)getLayoutInflater().inflate(R.layout.header_quyensach_danhsach,
                listView,false);
        listView.addHeaderView(viewGroup);

        QuyenSach_ListViewAdapter = new QuyenSach_ListViewAdapter(getContext(),
                R.layout.raw_quyensach_danhsach,
                R.id.txtMaQuyenSach,
                arr_quyensach);
        listView.setAdapter(QuyenSach_ListViewAdapter);
        txtTong.setText("Tổng quyển sách "+QuyenSach_ListViewAdapter.getCount());



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
                filterAdapter(s.toString());

            }
        };
        txtTimKiem.addTextChangedListener(mSearchTw);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("TESST",((EC_QuyenSach)adapterView.getItemAtPosition(i)).get_MSSach());
                showDialog(adapterView,i);

            }
        });



    }
    private void showDialog(AdapterView<?> adapterView,int pos){
        try{
            FragmentManager manager = getFragmentManager();
            QuyenSachDialogFragment nhaXBDialogFragment = new QuyenSachDialogFragment();
            if(adapterView != null)
                nhaXBDialogFragment.params(arr_quyensach,(EC_QuyenSach) adapterView.getItemAtPosition(pos),0);
            else
                nhaXBDialogFragment.params(arr_quyensach,null,1);
            nhaXBDialogFragment.show(manager,"");
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
        arr_quyensach = bus_quyenSach.selectAllData();
        new AdapterHelper().update((ArrayAdapter)QuyenSach_ListViewAdapter, new ArrayList<Object>(arr_quyensach));
        QuyenSach_ListViewAdapter.notifyDataSetChanged();
        if(_isFilter)
            filterAdapter(txtTimKiem.getText().toString());
        txtTong.setText("Tổng quyển sách "+QuyenSach_ListViewAdapter.getCount());
    }

    private void filterAdapter(String s){
        ArrayList<EC_QuyenSach> temp = new ArrayList<>();
        for (EC_QuyenSach e:arr_quyensach
                ) {
            String data = "";
            int _intCheckMa = spin_timkiem.getSelectedItemPosition();
            if(_intCheckMa == 0) data = e.get_MaLoaiSach();
            else if(_intCheckMa == 1) data = e.get_TenSach();
            else data = e.get_TacGia();
            if (data.toLowerCase().contains(s.toLowerCase()))
                temp.add(e);
        }
        QuyenSach_ListViewAdapter = new QuyenSach_ListViewAdapter(getContext(),
                R.layout.raw_quyensach_danhsach,
                R.id.txtMaQuyenSach,
                temp);
        listView.setAdapter(QuyenSach_ListViewAdapter);
        txtTong.setText("Tổng quyển sách "+QuyenSach_ListViewAdapter.getCount());


    }



}
