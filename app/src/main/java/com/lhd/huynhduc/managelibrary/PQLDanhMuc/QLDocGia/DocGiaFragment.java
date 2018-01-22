package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLDocGia;

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
import com.lhd.huynhduc.managelibrary.BUS.BUS_DocGia;
import com.lhd.huynhduc.managelibrary.Entity.EC_DocGia;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/15/18.
 */

public class DocGiaFragment extends Fragment{
    FloatingActionButton fab;
    boolean _isFilter = false;
    Spinner spin_timkiem;
    ListView listView;
    EditText txtTimKiem;
    TextView txtTong;
    TextWatcher mSearchTw;
    String MaDGDaChon = "";
    String TenDGDaChon = "";
    ArrayList<EC_DocGia> arr_docgia = new ArrayList<>();
    public DocGia_ListViewAdapter docGia_listViewAdapter;
    BUS_DocGia bus_docGia = new BUS_DocGia();

    String[] adapterTimKiem = new String[]{"Theo mã", "Theo tên","Theo giới tính"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_docgia,container,false);
        initUI(view);
        eventObject();
        return view;
    }

    private void initUI(View view){
        arr_docgia = bus_docGia.selectAllData();
        fab = (FloatingActionButton)view.findViewById(R.id.fab_tuychon_docgia);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(null,0);

            }
        });
        spin_timkiem = (Spinner) view.findViewById(R.id.spin_timkiem);
        listView = (ListView)view.findViewById(R.id.lv_docgia_danhsach);
        txtTimKiem = (EditText)view.findViewById(R.id.txt_docgia_timkiem);
        txtTong = (TextView)view.findViewById(R.id.txtTongDocGia);
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
        ViewGroup viewGroup = (ViewGroup)getLayoutInflater().inflate(R.layout.header_docgia_danhsach,
                listView,false);
        listView.addHeaderView(viewGroup);

        docGia_listViewAdapter = new DocGia_ListViewAdapter(getContext(),
                R.layout.raw_docgia_danhsach,
                R.id.txtMaDocGia,
                arr_docgia);
        listView.setAdapter(docGia_listViewAdapter);
        txtTong.setText("Tổng độc giả "+docGia_listViewAdapter.getCount());

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
                showDialog(adapterView,i);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerForContextMenu(listView);
                MaDGDaChon = ((EC_DocGia) adapterView.getItemAtPosition(i)).get_MSDG();
                TenDGDaChon = ((EC_DocGia) adapterView.getItemAtPosition(i)).get_TenDG();
                return false;
            }
        });


    }
    private void showDialog(AdapterView<?> adapterView,int pos){
        try{
            FragmentManager manager = getFragmentManager();
            DocGiaDialogFragment docGiaDialogFragment = new DocGiaDialogFragment();
            if(adapterView != null)
                docGiaDialogFragment.params(arr_docgia,(EC_DocGia) adapterView.getItemAtPosition(pos),0);
            else
                docGiaDialogFragment.params(arr_docgia,null,1);
            docGiaDialogFragment.show(manager,"");
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
        arr_docgia = bus_docGia.selectAllData();
        new AdapterHelper().update((ArrayAdapter)docGia_listViewAdapter, new ArrayList<Object>(arr_docgia));
        docGia_listViewAdapter.notifyDataSetChanged();
        if(_isFilter)
            filterAdapter(txtTimKiem.getText().toString());
        txtTong.setText("Tổng độc giả "+docGia_listViewAdapter.getCount());
    }

    private void filterAdapter(String s){
        ArrayList<EC_DocGia> temp = new ArrayList<>();
        for (EC_DocGia e:arr_docgia
                ) {
            String data = "";
            int _intCheckMa = spin_timkiem.getSelectedItemPosition();
            if(_intCheckMa == 0) data = e.get_MSDG();
            else if(_intCheckMa == 1) data = e.get_TenDG();
            else data = e.get_GioiTinh();
            if (data.toLowerCase().contains(s.toLowerCase()))
                temp.add(e);
        }
        docGia_listViewAdapter = new DocGia_ListViewAdapter(getContext(),
                R.layout.raw_docgia_danhsach,
                R.id.txtMaDocGia,
                temp);
        listView.setAdapter(docGia_listViewAdapter);
        txtTong.setText("Tổng độc giả "+docGia_listViewAdapter.getCount());


    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Lịch sử mượn sách");
        menu.add(0, 0, 0, "Lịch sử trả sách");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
            FragmentManager manager = getFragmentManager();
            LichSuSachDialogFragment docGiaDialogFragment = new LichSuSachDialogFragment();
            docGiaDialogFragment.params(item.getItemId(),MaDGDaChon,TenDGDaChon);
            docGiaDialogFragment.show(manager,"");

        return true;
    }

}
