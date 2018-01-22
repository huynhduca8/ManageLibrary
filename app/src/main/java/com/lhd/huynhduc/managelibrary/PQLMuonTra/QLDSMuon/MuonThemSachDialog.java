package com.lhd.huynhduc.managelibrary.PQLMuonTra.QLDSMuon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.BUS.BUS_DocGia;
import com.lhd.huynhduc.managelibrary.BUS.BUS_LoaiSach;
import com.lhd.huynhduc.managelibrary.BUS.BUS_MuonSach;
import com.lhd.huynhduc.managelibrary.BUS.BUS_NhanVien;
import com.lhd.huynhduc.managelibrary.BUS.BUS_QuyenSach;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.Entity.EC_LoaiSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_MuonSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_QuyenSach;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/17/18.
 */

public class MuonThemSachDialog extends DialogFragment implements View.OnClickListener {
    TextView txtTimKiem;
    Spinner spinner_themsach_tim;
    ListView lvThemSach;
    Button btnHuy;
    String msphieu;
    BUS_QuyenSach bus_quyenSach;
    TextView txtTong;
    DSQuyenSach_ThemSach_Adapter dsQuyenSach_themSach_adapter;
    ArrayList<EC_QuyenSach> arr_quyensach;
    boolean _isFilter;
    ArrayList<EC_LoaiSach> ec_loaiSachArrayList;
    String[] lTitle = new String[]{"Theo mã","Theo tên","Tác giả"};
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dsmuonsach_themsach, container, false);
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupDiaLog();
    }

    public void params(String msphieu) {
        this.msphieu = msphieu;
    }

    private void setupEventButton() {

        btnHuy.setOnClickListener(this);

        TextWatcher mSearchTw=new TextWatcher() {
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
        lvThemSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });

    }

    private void setupDiaLog() {
        setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getDialog().getWindow().getAttributes());
        WindowManager window = (WindowManager)getContext()

                .getSystemService(Context.WINDOW_SERVICE);

        Display display = window.getDefaultDisplay();
        lp.width = display.getWidth();
        lp.height = display.getHeight()*8/10;
        lp.gravity = Gravity.CENTER;
        lp.dimAmount = 0.7f;
        getDialog().getWindow().setAttributes(lp);
    }

    private void init(View v) {
        ec_loaiSachArrayList = new BUS_LoaiSach().selectAllData();
        bus_quyenSach = new BUS_QuyenSach();
        arr_quyensach = bus_quyenSach.selectAllData();

        txtTong = (TextView)v.findViewById(R.id.lbTongSach);
        spinner_themsach_tim = (Spinner)v.findViewById(R.id.spin_themsach_timkiem);
        txtTimKiem = (EditText)v.findViewById(R.id.txt_themsach_timkiem);
        lvThemSach = (ListView)v.findViewById(R.id.lv_themsach);
        btnHuy = (Button) v.findViewById(R.id.btn_dsmuon_huysach);

        ArrayAdapter<String> adapterSpinTK = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,
                        lTitle);
        adapterSpinTK.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner_themsach_tim.setAdapter(adapterSpinTK);
        spinner_themsach_tim.setSelection(0);



        dsQuyenSach_themSach_adapter = new DSQuyenSach_ThemSach_Adapter(getContext(),
                R.layout.raw_themsach_danhsach,
                R.id.txt_themsach_mssach,
                arr_quyensach,ec_loaiSachArrayList);
        lvThemSach.setAdapter(dsQuyenSach_themSach_adapter);
        refreshTxtTong();
        setupEventButton();



    }
    private void refreshTxtTong(){
        txtTong.setText("Tổng sách có thể mượn "+dsQuyenSach_themSach_adapter.getCount());
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_dsmuon_huysach: {
                dismiss();
                break;
            }

        }
    }


    private void filterAdapter(String s){
        ArrayList<EC_QuyenSach> temp = new ArrayList<>();
        for (EC_QuyenSach e:arr_quyensach
                ) {
            String data = "";
            int _intCheckMa = spinner_themsach_tim.getSelectedItemPosition();
            if(_intCheckMa == 0) data = e.get_MSSach();
            else if(_intCheckMa == 1) data = e.get_TenSach();
            else data = e.get_TacGia();
            if (data.toLowerCase().contains(s.toLowerCase()))
                temp.add(e);
        }
        dsQuyenSach_themSach_adapter = new DSQuyenSach_ThemSach_Adapter(getContext(),
                R.layout.raw_themsach_danhsach,
                R.id.txt_themsach_mssach,
                temp,ec_loaiSachArrayList);
        lvThemSach.setAdapter(dsQuyenSach_themSach_adapter);
        refreshTxtTong();


    }




}
