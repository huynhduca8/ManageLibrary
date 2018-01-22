package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLNhaXB;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lhd.huynhduc.managelibrary.AdapterHelper;
import com.lhd.huynhduc.managelibrary.BUS.BUS_NhaXuatBan;
import com.lhd.huynhduc.managelibrary.BUS.BUS_NhaXuatBan;
import com.lhd.huynhduc.managelibrary.Entity.EC_NhaXB;
import com.lhd.huynhduc.managelibrary.Entity.EC_NhaXB;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class NhaXBFragment extends Fragment{
    FloatingActionButton fab;
    boolean _isFilter = false;
    Spinner spin_timkiem;
    ListView listView;
    EditText txtTimKiem;
    TextView txtTong;
    TextWatcher mSearchTw;
    ArrayList<EC_NhaXB> arr_nhaxb = new ArrayList<>();
    public NhaXB_ListViewAdapter NhaXB_ListViewAdapter;
    BUS_NhaXuatBan bus_nhaXB = new BUS_NhaXuatBan();

    String[] adapterTimKiem = new String[]{"Theo mã", "Theo tên","Theo địa chỉ"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_nhaxb,container,false);
        initUI(view);
        eventObject();
        return view;
    }

    private void initUI(View view){
        arr_nhaxb = bus_nhaXB.selectAllData();
        fab = (FloatingActionButton)view.findViewById(R.id.fab_tuychon_nhaxb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(null,0);

            }
        });
        spin_timkiem = (Spinner) view.findViewById(R.id.spin_nhaxb_timkiem);
        listView = (ListView)view.findViewById(R.id.lv_nhaxb_danhsach);
        txtTimKiem = (EditText)view.findViewById(R.id.txt_nhaxb_timkiem);
        txtTong = (TextView)view.findViewById(R.id.txtTongNhaXB);
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
        ViewGroup viewGroup = (ViewGroup)getLayoutInflater().inflate(R.layout.header_nhaxb_danhsach,
                listView,false);
        listView.addHeaderView(viewGroup);

        NhaXB_ListViewAdapter = new NhaXB_ListViewAdapter(getContext(),
                R.layout.raw_nhaxb_danhsach,
                R.id.txtMaDocGia,
                arr_nhaxb);
        listView.setAdapter(NhaXB_ListViewAdapter);
        txtTong.setText("Tổng nhà xuất bản  "+NhaXB_ListViewAdapter.getCount());

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
                Log.v("TESST",((EC_NhaXB)adapterView.getItemAtPosition(i)).get_MSNXB());
                showDialog(adapterView,i);

            }
        });



    }
    private void showDialog(AdapterView<?> adapterView,int pos){
        try{
            FragmentManager manager = getFragmentManager();
            NXBDialogFragment nhaXBDialogFragment = new NXBDialogFragment();
            if(adapterView != null)
                nhaXBDialogFragment.params(arr_nhaxb,(EC_NhaXB) adapterView.getItemAtPosition(pos),0);
            else
                nhaXBDialogFragment.params(arr_nhaxb,null,1);
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
        arr_nhaxb = bus_nhaXB.selectAllData();
        txtTong.setText(arr_nhaxb.size() +"");
        new AdapterHelper().update((ArrayAdapter)NhaXB_ListViewAdapter, new ArrayList<Object>(arr_nhaxb));
        NhaXB_ListViewAdapter.notifyDataSetChanged();
        if(_isFilter)
            filterAdapter(txtTimKiem.getText().toString());
        txtTong.setText("Tổng nhà xuất bản  "+NhaXB_ListViewAdapter.getCount());
    }

    private void filterAdapter(String s){
        ArrayList<EC_NhaXB> temp = new ArrayList<>();
        for (EC_NhaXB e:arr_nhaxb
                ) {
            String data = "";
            int _intCheckMa = spin_timkiem.getSelectedItemPosition();
            if(_intCheckMa == 0) data = e.get_MSNXB();
            else if(_intCheckMa == 1) data = e.get_TenNXB();
            else data = e.get_DiaChiNXB();
            if (data.toLowerCase().contains(s.toLowerCase()))
                temp.add(e);
        }
        txtTong.setText(temp.size() +"");
        NhaXB_ListViewAdapter = new NhaXB_ListViewAdapter(getContext(),
                R.layout.raw_nhaxb_danhsach,
                R.id.txtMaDocGia,
                temp);
        listView.setAdapter(NhaXB_ListViewAdapter);
        txtTong.setText("Tổng nhà xuất bản  "+NhaXB_ListViewAdapter.getCount());


    }



}
