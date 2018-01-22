package com.lhd.huynhduc.managelibrary.PQLMuonTra.QLDSMuon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.Toast;

import com.lhd.huynhduc.managelibrary.BUS.BUS_ChiTietPhieuMuon;
import com.lhd.huynhduc.managelibrary.BUS.BUS_DocGia;
import com.lhd.huynhduc.managelibrary.BUS.BUS_MuonSach;
import com.lhd.huynhduc.managelibrary.BUS.BUS_NhanVien;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.Entity.EC_ChiTietPhieuMuon;
import com.lhd.huynhduc.managelibrary.Entity.EC_MuonSach;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/17/18.
 */

public class DSMuonDialogFragment extends DialogFragment implements View.OnClickListener {
    boolean _isSua = true;
    LinearLayout linearLayout;
    ArrayList<EC_MuonSach> ec_muonSachArrayList;
    ArrayAdapter<String> adapter_msdocgia,adapter_msnv;
    ArrayList<String> arr_MaAndNameDG,arr_MaAndNameNV;
    ArrayList<String> arr_MaDG,arr_MaNV;

    EC_MuonSach ec_muonSach;
    EC_MuonSach ec_muonSachTemp;
    EditText txtMaSoPhieu;
    Spinner  SpinerMaSoDocGia, SpinerMaSoNhanVien;
    TextView txtTenDocGia,txtTenNhanVien;
    EditText txtNgayMuon;
    TextView txtThongbao;
    TextView txtTong;
    ListView listView;
    Button btnSua, btnXoa, btnHuy, btnThem;

    DSMuonTheoMa_ListViewAdapter dsMuonTheoMa_listViewAdapter;
    ArrayList<EC_MuonSach.DSMuonTheoMa> arr_dsmuontheoma;
    BUS_MuonSach bus_muonSach;
    ImageButton imageButtonThem;
    EC_ChiTietPhieuMuon ec_chiTietPhieuMuonChonXoa;

    ArrayList<String> listMa = new ArrayList<>();
    TextView lblThongTin;
    int _isThem = 0;
    String[] lTitle = new String[]{"Thông tin phiếu mượn", "Thêm phiếu mượn"};
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongtin_dsmuon_dialog, container, false);
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupDiaLog();
        checkShow();
    }

    public void params(ArrayList<EC_MuonSach> ec_loaiSaches, EC_MuonSach e, int _isThem) {
        this.ec_muonSachArrayList = ec_loaiSaches;
        this._isThem = _isThem;
        this.ec_muonSach = e;
        if(e != null) Variable._SoPhieuCanDoi = ec_muonSach.get_SoPhieuMuon();
        for (int i = 0; i < ec_muonSachArrayList.size(); i++) {
            listMa.add(ec_muonSachArrayList.get(i).get_SoPhieuMuon().toLowerCase());
        }
    }

    private void setupEventButton() {
        SpinerMaSoDocGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txtTenDocGia.setText( arr_MaAndNameDG.get( arr_MaDG.indexOf(adapterView.getItemAtPosition(i))).split("---")[1]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SpinerMaSoNhanVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txtTenNhanVien.setText( arr_MaAndNameNV.get( arr_MaNV.indexOf(adapterView.getItemAtPosition(i))).split("---")[1]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnHuy.setOnClickListener(this);
        btnSua.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnThem.setOnClickListener(this);
        imageButtonThem.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EC_MuonSach.DSMuonTheoMa temp = (EC_MuonSach.DSMuonTheoMa)adapterView.getItemAtPosition(i);
                ec_chiTietPhieuMuonChonXoa = new EC_ChiTietPhieuMuon(ec_muonSach.get_SoPhieuMuon(),temp.get_MSSach(),temp.get_HanTra());
                deleteSachMuon();
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
        bus_muonSach = new BUS_MuonSach();

        linearLayout = (LinearLayout) v.findViewById(R.id.linearLayoutBtnsDSMuon);
        imageButtonThem = (ImageButton)v.findViewById(R.id.ib_dsmuon_themsach);
        txtTong = (TextView)v.findViewById(R.id.txt_dsmuon_theoma_tongbanghi);
        listView = (ListView)v.findViewById(R.id.lv_dsmuon_danhsachphieu);
        lblThongTin = (TextView) v.findViewById(R.id.lblThongTinDSMuon);
        txtMaSoPhieu       = (EditText) v.findViewById(R.id.spin_dsmuon_msphieu);
        SpinerMaSoDocGia      = (Spinner) v.findViewById(R.id.spin_dsmuon_msdocgia);
        SpinerMaSoNhanVien      = (Spinner) v.findViewById(R.id.spin_dsmuon_msnhanvien);
        txtNgayMuon   = (EditText) v.findViewById(R.id.txt_dsmuon_ngaymuon);
        txtTenDocGia = (TextView)v.findViewById(R.id.txt_dsmuon_tendocgia);
        txtTenNhanVien = (TextView)v.findViewById(R.id.txt_dsmuon_tennhanvien);

        txtThongbao = (TextView) v.findViewById(R.id.txt_dsmuon_thongbao);

        lblThongTin.setText(lTitle[_isThem]);
        arr_MaAndNameDG = new BUS_DocGia().getListMaDGandName();
        arr_MaDG = new ArrayList<>();
        for (String sub:arr_MaAndNameDG
             ) {
            arr_MaDG.add(sub.split("---")[0]);
        }
        adapter_msdocgia = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,
                        arr_MaDG); //selected item will look like a spinner set from XML
        adapter_msdocgia.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        SpinerMaSoDocGia.setAdapter(adapter_msdocgia);


        arr_MaNV =  new ArrayList<>();
        arr_MaAndNameNV = new BUS_NhanVien().getListMaNVandNAme();
        for (String sub:arr_MaAndNameNV
                ) {
            arr_MaNV.add(sub.split("---")[0]);
        }
        adapter_msnv = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,
                        arr_MaNV); //selected item will look like a spinner set from XML
        adapter_msnv.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        SpinerMaSoNhanVien.setAdapter(adapter_msnv);



        if (_isThem == 0) {
            txtMaSoPhieu.setText(ec_muonSach.get_SoPhieuMuon());
            SpinerMaSoDocGia.setSelection(adapter_msdocgia.getPosition( ec_muonSach.get_MSDG()));
            SpinerMaSoNhanVien.setSelection(adapter_msnv.getPosition( ec_muonSach.get_MSNV()));
            txtNgayMuon.setText(ec_muonSach.get_NgayMuon());
            arr_dsmuontheoma = bus_muonSach.getDSMuonTheoMa(ec_muonSach.get_SoPhieuMuon());
            dsMuonTheoMa_listViewAdapter = new DSMuonTheoMa_ListViewAdapter(getContext(),
                    R.layout.raw_dsmuon_danhsachtheoma,
                    R.id.txt_dsmuon_theoma_mss,
                    arr_dsmuontheoma);
            listView.setAdapter(dsMuonTheoMa_listViewAdapter);
            setEnableEditText(false);
            refreshTxtTong();
            if(ec_muonSach.get_isMuonThem().equalsIgnoreCase("1"))
                imageButtonThem.setVisibility(View.VISIBLE);
            else{
                imageButtonThem.setVisibility(View.GONE);
            }

        }else{
            imageButtonThem.setVisibility(View.GONE);
            txtNgayMuon.setEnabled(false);
            txtNgayMuon.setBackgroundResource(R.color.colorDisable);
            txtNgayMuon.setText(Variable.getCurrentDate());
        }


        btnSua = (Button) v.findViewById(R.id.btnSuaDSMuon);

        btnHuy = (Button) v.findViewById(R.id.btnHuyDSMuon);

        btnXoa = (Button) v.findViewById(R.id.btnXoaDSMuon);

        btnThem = (Button) v.findViewById(R.id.btnThemDSMuon);

        setupEventButton();



    }
    private void refreshTxtTong(){
        txtTong.setText("Tổng bản ghi "+dsMuonTheoMa_listViewAdapter.getCount());
    }
    private void setEnableEditText(boolean b) {
        if (!b) {
            txtMaSoPhieu.setEnabled(false);
            SpinerMaSoDocGia.setEnabled(false);
            SpinerMaSoNhanVien.setEnabled(false);
            txtNgayMuon.setEnabled(false);
            txtMaSoPhieu.setBackgroundResource(R.color.colorDisable);
            SpinerMaSoDocGia.setBackgroundResource(R.color.colorDisable);
            SpinerMaSoNhanVien.setBackgroundResource(R.color.colorDisable);
            txtNgayMuon.setBackgroundResource(R.color.colorDisable);
        } else {
            txtMaSoPhieu.setEnabled(true);
            SpinerMaSoDocGia.setEnabled(true);
            txtNgayMuon.setEnabled(true);
            SpinerMaSoNhanVien.setEnabled(true);
            txtMaSoPhieu.setBackgroundResource(R.color.white);
            SpinerMaSoDocGia.setBackgroundResource(R.color.white);
            SpinerMaSoNhanVien.setBackgroundResource(R.color.white);
            txtNgayMuon.setBackgroundResource(R.color.white);
        }
    }

    private void checkShow() {
        if (_isThem == 0) {
            linearLayout.setWeightSum(3);
            btnThem.setVisibility(View.GONE);
        } else {
            linearLayout.setWeightSum(2);
            btnSua.setVisibility(View.GONE);
            btnXoa.setVisibility(View.GONE);
        }
    }

    private boolean checkInput() {
        ec_muonSachTemp = new EC_MuonSach(txtMaSoPhieu.getText().toString(),
                SpinerMaSoDocGia.getSelectedItem().toString(),SpinerMaSoNhanVien.getSelectedItem().toString(),
                txtNgayMuon.getText().toString());
        if(!ec_muonSachTemp.isFullInfo()){
            txtThongbao.setText("Thông tin chưa đầy đủ");
            return false;
        }

        if(listMa.contains( ec_muonSachTemp.get_SoPhieuMuon().toLowerCase())){
            if(_isThem == 0){
                if(!ec_muonSach.get_SoPhieuMuon().toLowerCase().equalsIgnoreCase(ec_muonSachTemp.get_SoPhieuMuon().toLowerCase())){
                    txtThongbao.setText("Mã đã tồn tại");
                    return false;
                }
                if(ec_muonSach.toString().toLowerCase().equalsIgnoreCase(ec_muonSachTemp.toString().toLowerCase())){
                    txtThongbao.setText("Thông tin không đổi");
                    return false;
                }

            }else {
                txtThongbao.setText("Mã đã tồn tại");
                return false;
            }
        }
        if(!ec_muonSachTemp.checkDate(txtNgayMuon.getText().toString())){
            txtThongbao.setText("Ngày không hợp lệ");
            return false;
        }
        return true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSuaDSMuon: {
                if (_isSua) {
                    _isSua = !_isSua;
                    btnSua.setText("Lưu");
                    setEnableEditText(true);


                } else {

                    if (checkInput()) {
                        _isSua = !_isSua;
                        btnSua.setText("Sửa");
                        setEnableEditText(false);
                        update();
                    }
                }
                break;
            }
            case R.id.btnThemDSMuon: {
                if (checkInput()) {
                    insert();
                }
                break;
            }
            case R.id.btnHuyDSMuon: {
                dismiss();
                break;
            }
            case R.id.btnXoaDSMuon: {
                txtMaSoPhieu.setText(ec_muonSach.get_SoPhieuMuon());
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn chắc chắn muốn xóa " + txtMaSoPhieu.getText().toString() )
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                delete();
                            }
                        })
                        .setNegativeButton("Hủy ", null);

                AlertDialog alert = builder.create();
                alert.show();
                break;
            }
            case R.id.ib_dsmuon_themsach:
                showDialog();
                break;
        }
    }

    private void update() {
        String kq = bus_muonSach.UpdateData(ec_muonSach, ec_muonSachTemp);
        if (!kq.equalsIgnoreCase("1"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else {
            Variable._SoPhieuCanDoi = ec_muonSachTemp.get_SoPhieuMuon();
            dismiss();
        }
    }

    private void insert() {
        ec_muonSachTemp.set_isMuonThem("1");
        String kq = bus_muonSach.InseartData(ec_muonSachTemp);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else {
            txtThongbao.setText("Thêm thành công ");
            listMa.add(ec_muonSachTemp.get_SoPhieuMuon());
        }

    }

    private void delete() {
        String kq = bus_muonSach.DeleteData(ec_muonSach);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi xóa ");
        else {
            dismiss();
        }
    }
    private void showDialog(){

        try{
            FragmentManager manager = getFragmentManager();
            MuonThemSachDialog muonThemSachDialog = new MuonThemSachDialog();
            muonThemSachDialog.params(ec_muonSach.get_SoPhieuMuon());
            muonThemSachDialog.show(manager,"");
            manager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {

                @Override
                public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                    super.onFragmentViewDestroyed(fm, f);

                   // updateAdapter();


                }
            }, false);
        }catch (Exception em){em.printStackTrace();}
    }

    private void deleteSachMuon() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn chắc chắn muốn xóa không ?" )
                        .setCancelable(true)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String kq = new BUS_ChiTietPhieuMuon().DeleteDatabyOB(ec_chiTietPhieuMuonChonXoa);
                                if (!kq.equalsIgnoreCase("1"))
                                    Toast.makeText(getContext(),"Xảy ra lỗi khi xóa",Toast.LENGTH_SHORT).show();
                                else {
                                    Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                                }
                                arr_dsmuontheoma = bus_muonSach.getDSMuonTheoMa(ec_muonSach.get_SoPhieuMuon());
                                dsMuonTheoMa_listViewAdapter = new DSMuonTheoMa_ListViewAdapter(getContext(),
                                        R.layout.raw_dsmuon_danhsachtheoma,
                                        R.id.txt_dsmuon_theoma_mss,
                                        arr_dsmuontheoma);
                                listView.setAdapter(dsMuonTheoMa_listViewAdapter);
                                refreshTxtTong();
                            }
                        })

                        .setNegativeButton("Hủy ", null);

                AlertDialog alert = builder.create();
                alert.show();

    }
}
