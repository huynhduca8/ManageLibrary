package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLDocGia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.BUS.BUS_DocGia;
import com.lhd.huynhduc.managelibrary.Entity.EC_DocGia;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/17/18.
 */

public class DocGiaDialogFragment extends DialogFragment implements View.OnClickListener {
    boolean _isSua = true;
    LinearLayout linearLayout;
    ArrayList<EC_DocGia> ec_docGiaArrayList;
    EC_DocGia ec_docGia;
    EC_DocGia ec_docGiaTemp;
    EditText txtMa, txtTen, txtQue, txtNgaySinh, txtHanThe, txtEmail;
    Spinner txtGioiTinh,spinnerTimKiem;
    TextView txtThongbao;
    Button btnSua, btnXoa, btnHuy, btnThem;
    BUS_DocGia bus_docGia;
    ArrayList<String> listMa = new ArrayList<>();
    TextView lblThongTin;
    int _isThem = 0;
    String[] lTitle = new String[]{"Thông tin độc giả", "Thêm độc giả"};
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongtin_docgia_dialog, container, false);
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupDiaLog();
        checkShow();
    }

    public void params(ArrayList<EC_DocGia> ec_loaiSaches, EC_DocGia e, int _isThem) {
        this.ec_docGiaArrayList = ec_loaiSaches;
        this._isThem = _isThem;
        this.ec_docGia = e;
        for (int i = 0; i < ec_docGiaArrayList.size(); i++) {
            listMa.add(ec_docGiaArrayList.get(i).get_MSDG().toLowerCase());
        }
    }

    private void setupEventButton() {
        btnHuy.setOnClickListener(this);
        btnSua.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnThem.setOnClickListener(this);
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
        bus_docGia = new BUS_DocGia();

        linearLayout = (LinearLayout) v.findViewById(R.id.linearLayoutBtnsDocgia);



        lblThongTin = (TextView) v.findViewById(R.id.lblThongTinDocGia);
        txtMa       = (EditText) v.findViewById(R.id.txtMaDocGia);
        txtTen      = (EditText) v.findViewById(R.id.txtTenDocGia);
        txtGioiTinh = (Spinner) v.findViewById(R.id.txtGioiTinhDocGia);
        spinnerTimKiem = (Spinner) v.findViewById(R.id.spin_timkiem);
        txtEmail    = (EditText) v.findViewById(R.id.txtEmailDocGia);
        txtNgaySinh = (EditText) v.findViewById(R.id.txtNgaySinhDocGia);
        txtQue      = (EditText) v.findViewById(R.id.txtDiaChiDocGia);
        txtHanThe   = (EditText) v.findViewById(R.id.txtHanTheDocGia);


        txtThongbao = (TextView) v.findViewById(R.id.txt_docgia_thongbao);

        lblThongTin.setText(lTitle[_isThem]);
        ArrayList<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Nam");
        spinnerArray.add("Nữ");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,
                        spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        txtGioiTinh.setAdapter(spinnerArrayAdapter);

        if (_isThem == 0) {
            txtMa.setText(ec_docGia.get_MSDG());
            txtTen.setText(ec_docGia.get_TenDG());
            txtQue.setText(ec_docGia.get_DiaChi());


            txtGioiTinh.setSelection(spinnerArrayAdapter.getPosition(ec_docGia.get_GioiTinh()));



            txtNgaySinh.setText(ec_docGia.get_NgaySinh());
            txtEmail.setText(ec_docGia.get_Email());
            txtHanThe.setText(ec_docGia.get_HanSuDung());
            setEnableEditText(false);
        }else{
            txtNgaySinh.setHint("dd-MM-yyyy");
            txtHanThe.setHint("dd-MM-yyyy");
        }


        btnSua = (Button) v.findViewById(R.id.btnSuaDocGia);

        btnHuy = (Button) v.findViewById(R.id.btnHuyDocGia);

        btnXoa = (Button) v.findViewById(R.id.btnXoaDocGia);

        btnThem = (Button) v.findViewById(R.id.btnThemDocGia);

        setupEventButton();



    }

    private void setEnableEditText(boolean b) {
        if (!b) {
            txtMa.setEnabled(false);
            txtTen.setEnabled(false);
            txtHanThe.setEnabled(false);
            txtEmail.setEnabled(false);
            txtNgaySinh.setEnabled(false);
            txtGioiTinh.setEnabled(false);
            txtQue.setEnabled(false);
            txtMa.setBackgroundResource(R.color.colorDisable);
            txtTen.setBackgroundResource(R.color.colorDisable);
            txtQue.setBackgroundResource(R.color.colorDisable);
            txtGioiTinh.setBackgroundResource(R.color.colorDisable);
            txtNgaySinh.setBackgroundResource(R.color.colorDisable);
            txtEmail.setBackgroundResource(R.color.colorDisable);
            txtHanThe.setBackgroundResource(R.color.colorDisable);
        } else {
            txtMa.setEnabled(true);
            txtTen.setEnabled(true);
            txtHanThe.setEnabled(true);
            txtEmail.setEnabled(true);
            txtNgaySinh.setEnabled(true);
            txtGioiTinh.setEnabled(true);
            txtQue.setEnabled(true);
            txtMa.setBackgroundResource(R.color.white);
            txtTen.setBackgroundResource(R.color.white);
            txtQue.setBackgroundResource(R.color.white);
            txtGioiTinh.setBackgroundResource(R.color.white);
            txtNgaySinh.setBackgroundResource(R.color.white);
            txtEmail.setBackgroundResource(R.color.white);
            txtHanThe.setBackgroundResource(R.color.white);
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
        ec_docGiaTemp = new EC_DocGia(txtMa.getText().toString(),
                txtTen.getText().toString(),txtQue.getText().toString(),
                txtNgaySinh.getText().toString(),txtEmail.getText().toString(),
                txtGioiTinh.getSelectedItem().toString(),txtHanThe.getText().toString());
        if(!ec_docGiaTemp.isFullInfo()){
            txtThongbao.setText("Thông tin chưa đầy đủ");
            return false;
        }

        if(listMa.contains( ec_docGiaTemp.get_MSDG().toLowerCase())){
            if(_isThem == 0){
                if(!ec_docGia.get_MSDG().toLowerCase().equalsIgnoreCase(ec_docGiaTemp.get_MSDG().toLowerCase())){
                    txtThongbao.setText("Mã đã tồn tại");
                    return false;
                }
                if(ec_docGia.toString().toLowerCase().equalsIgnoreCase(ec_docGiaTemp.toString().toLowerCase())){
                    txtThongbao.setText("Thông tin không đổi");
                    return false;
                }

            }else {
                txtThongbao.setText("Mã đã tồn tại");
                return false;
            }
        }
        if(!ec_docGiaTemp.checkDate(txtNgaySinh.getText().toString()) || !ec_docGiaTemp.checkDate(txtHanThe.getText().toString())){
            txtThongbao.setText("Ngày không đúng định dạng");
            return false;
        }
        return true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSuaDocGia: {
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
            case R.id.btnThemDocGia: {
                if (checkInput()) {
                    insert();
                }
                break;
            }
            case R.id.btnHuyDocGia: {
                dismiss();
                break;
            }
            case R.id.btnXoaDocGia: {
                txtMa.setText(ec_docGia.get_MSDG());
                txtTen.setText(ec_docGia.get_TenDG());
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn chắc chắn muốn xóa " + txtMa.getText().toString() + "-" + txtTen.getText().toString())
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
        }
    }

    private void update() {
        String kq = bus_docGia.UpdateData(ec_docGia, ec_docGiaTemp);
        if (!kq.equalsIgnoreCase("1"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else {
            dismiss();
        }
    }

    private void insert() {
        String kq = bus_docGia.InseartData(ec_docGiaTemp);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else {
            txtThongbao.setText("Thêm thành công ");
            listMa.add(ec_docGiaTemp.get_MSDG());
        }

    }

    private void delete() {
        String kq = bus_docGia.DeleteData(ec_docGia);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi xóa ");
        else {
            dismiss();
        }
    }
}
