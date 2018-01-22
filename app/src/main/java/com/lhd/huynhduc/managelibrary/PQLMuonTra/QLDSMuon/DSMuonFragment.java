package com.lhd.huynhduc.managelibrary.PQLMuonTra.QLDSMuon;

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
import com.lhd.huynhduc.managelibrary.BUS.BUS_MuonSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_MuonSach;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class DSMuonFragment extends Fragment{
    FloatingActionButton fab;
    boolean _isFilter = false;
    boolean _isThem = false;
    boolean _isShow = false;
    Spinner spin_timkiem;
    ListView listView;
    EditText txtTimKiem;
    TextView txtTong;
    TextWatcher mSearchTw;
    ArrayList<EC_MuonSach> arr_dsmuon = new ArrayList<>();
    public DSMuon_ListViewAdapter DSMuon_ListViewAdapter;
    EC_MuonSach ec_muonSachCheck = null;
    BUS_MuonSach bus_muonSach = new BUS_MuonSach();

    String[] adapterTimKiem = new String[]{"Theo số phiếu mượn ", "Theo mã độc giả","Theo mã nhân viên"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muontra_dsmuon,container,false);
        initUI(view);
        eventObject();
        return view;
    }

    private void initUI(View view){
        arr_dsmuon = bus_muonSach.selectAllData();
        fab = (FloatingActionButton)view.findViewById(R.id.fab_tuychon_dsmuon);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _isThem = true;
                showDialog(null,0);

            }
        });
        spin_timkiem = (Spinner) view.findViewById(R.id.spin_dsmuon_timkiem);
        listView = (ListView)view.findViewById(R.id.lv_dsmuon_danhsach);
        txtTimKiem = (EditText)view.findViewById(R.id.txt_dsmuon_timkiem);
        txtTong = (TextView)view.findViewById(R.id.txtTongDSMuon);
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

        DSMuon_ListViewAdapter = new DSMuon_ListViewAdapter(getContext(),
                R.layout.raw_dsmuon_danhsach,
                R.id.txtMaDocGia,
                arr_dsmuon);
        listView.setAdapter(DSMuon_ListViewAdapter);
        txtTong.setText("Tổng danh sách mượn "+DSMuon_ListViewAdapter.getCount());

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
                _isThem = false;
                showDialog(adapterView,i);

            }
        });



    }
    private void showDialog(final AdapterView<?> adapterView, final int pos){
        _isShow = !_isShow;
        try{
            FragmentManager manager = getFragmentManager();
            DSMuonDialogFragment nhaXBDialogFragment = new DSMuonDialogFragment();
            if(adapterView != null){
                ec_muonSachCheck = (EC_MuonSach) adapterView.getItemAtPosition(pos);
                nhaXBDialogFragment.params(arr_dsmuon,ec_muonSachCheck,0);
            }

            else
                nhaXBDialogFragment.params(arr_dsmuon,null,1);
            nhaXBDialogFragment.show(manager,"");
            manager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {

                @Override
                public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                    super.onFragmentViewDestroyed(fm, f);
                    if(_isShow){
                        Log.v("DISMISSSSS","");
                        //&& ((EC_MuonSach) adapterView.getItemAtPosition(pos)).get_isMuonThem().equalsIgnoreCase("1")
                        if(!_isThem  ) {

                            bus_muonSach.changeisMuon();
                        }

                        updateAdapter();
                        _isShow = !_isShow;

                    }


            }
        }, false);
    }catch (Exception em){em.printStackTrace();}
    }


    private void updateAdapter(){

        arr_dsmuon = bus_muonSach.selectAllData();
        new AdapterHelper().update((ArrayAdapter)DSMuon_ListViewAdapter, new ArrayList<Object>(arr_dsmuon));
        DSMuon_ListViewAdapter.notifyDataSetChanged();
        if(_isFilter)
            filterAdapter(txtTimKiem.getText().toString());
        txtTong.setText("Tổng danh sách mượn "+DSMuon_ListViewAdapter.getCount());
    }

    private void filterAdapter(String s){
        ArrayList<EC_MuonSach> temp = new ArrayList<>();
        for (EC_MuonSach e:arr_dsmuon
                ) {
            String data = "";
            int _intCheckMa = spin_timkiem.getSelectedItemPosition();
            if(_intCheckMa == 0) data = e.get_SoPhieuMuon();
            else if(_intCheckMa == 1) data = e.get_MSDG();
            else data = e.get_MSNV();
            if (data.toLowerCase().contains(s.toLowerCase()))
                temp.add(e);
        }
        txtTong.setText(temp.size() +"");
        DSMuon_ListViewAdapter = new DSMuon_ListViewAdapter(getContext(),
                R.layout.raw_dsmuon_danhsach,
                R.id.txtMaDocGia,
                temp);
        listView.setAdapter(DSMuon_ListViewAdapter);
        txtTong.setText("Tổng danh sách mượn "+DSMuon_ListViewAdapter.getCount());


    }



}
