package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLNhaXB;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.BUS.BUS_NhaXuatBan;
import com.lhd.huynhduc.managelibrary.Entity.EC_NhaXB;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/17/18.
 */

public class NXBDialogFragment extends DialogFragment implements View.OnClickListener {
    boolean _isSua = true;
    LinearLayout linearLayout;
    ArrayList<EC_NhaXB> ec_nhaXBArrayList;
    EC_NhaXB ec_nhaXB;
    EC_NhaXB ec_nhaXBTemp;
    EditText txtMa, txtTen, txtDiaChi, txtWebsite, txtThongTinKhac;
    Spinner spinnerTimKiem;
    TextView txtThongbao;
    Button btnSua, btnXoa, btnHuy, btnThem;
    BUS_NhaXuatBan bus_docGia;
    ArrayList<String> listMa = new ArrayList<>();
    TextView lblThongTin;
    int _isThem = 0;
    String[] lTitle = new String[]{"Thông tin nhà xb", "Thêm nhà xb"};
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongtin_nhaxb_dialog, container, false);
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupDiaLog();
        checkShow();
    }

    public void params(ArrayList<EC_NhaXB> ec_loaiSaches, EC_NhaXB e, int _isThem) {
        this.ec_nhaXBArrayList = ec_loaiSaches;
        this._isThem = _isThem;
        this.ec_nhaXB = e;
        for (int i = 0; i < ec_nhaXBArrayList.size(); i++) {
            listMa.add(ec_nhaXBArrayList.get(i).get_MSNXB().toLowerCase());
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
        bus_docGia = new BUS_NhaXuatBan();

        linearLayout = (LinearLayout) v.findViewById(R.id.linearLayoutBtnsNhaXB);



        lblThongTin = (TextView) v.findViewById(R.id.lblThongTinNhaXB);
        txtMa       = (EditText) v.findViewById(R.id.txtMaNhaXB);
        txtTen      = (EditText) v.findViewById(R.id.txtTenNhaXB);
        spinnerTimKiem = (Spinner) v.findViewById(R.id.spin_nhaxb_timkiem);
        txtWebsite = (EditText) v.findViewById(R.id.txtWebsiteNhaXB);
        txtDiaChi      = (EditText) v.findViewById(R.id.txtDiaChiNhaXB);
        txtThongTinKhac   = (EditText) v.findViewById(R.id.txtThongTinKhacNhaXB);


        txtThongbao = (TextView) v.findViewById(R.id.txt_nhaxb_thongbao);

        lblThongTin.setText(lTitle[_isThem]);


        if (_isThem == 0) {
            txtMa.setText(ec_nhaXB.get_MSNXB());
            txtTen.setText(ec_nhaXB.get_TenNXB());
            txtDiaChi.setText(ec_nhaXB.get_DiaChiNXB());
            txtWebsite.setText(ec_nhaXB.get_WebSiteNXB());
            txtThongTinKhac.setText(ec_nhaXB.get_ThongTinKhacNXB());
            setEnableEditText(false);
        }

        btnSua = (Button) v.findViewById(R.id.btnSuaNhaXB);

        btnHuy = (Button) v.findViewById(R.id.btnHuyNhaXB);

        btnXoa = (Button) v.findViewById(R.id.btnXoaNhaXB);

        btnThem = (Button) v.findViewById(R.id.btnThemNhaXB);

        setupEventButton();



    }

    private void setEnableEditText(boolean b) {
        if (!b) {
            txtMa.setEnabled(false);
            txtTen.setEnabled(false);
            txtThongTinKhac.setEnabled(false);
            txtWebsite.setEnabled(false);
            txtDiaChi.setEnabled(false);
            txtMa.setBackgroundResource(R.color.colorDisable);
            txtTen.setBackgroundResource(R.color.colorDisable);
            txtDiaChi.setBackgroundResource(R.color.colorDisable);
            txtWebsite.setBackgroundResource(R.color.colorDisable);
            txtThongTinKhac.setBackgroundResource(R.color.colorDisable);
        } else {
            txtMa.setEnabled(true);
            txtTen.setEnabled(true);
            txtThongTinKhac.setEnabled(true);
            txtWebsite.setEnabled(true);
            txtDiaChi.setEnabled(true);
            txtMa.setBackgroundResource(R.color.white);
            txtTen.setBackgroundResource(R.color.white);
            txtDiaChi.setBackgroundResource(R.color.white);
            txtWebsite.setBackgroundResource(R.color.white);
            txtThongTinKhac.setBackgroundResource(R.color.white);
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
        ec_nhaXBTemp = new EC_NhaXB(txtMa.getText().toString(),
                txtTen.getText().toString(),txtDiaChi.getText().toString(),
                txtWebsite.getText().toString(),txtThongTinKhac.getText().toString());
        if(!ec_nhaXBTemp.isFullInfo()){
            txtThongbao.setText("Thông tin chưa đầy đủ");
            return false;
        }

        if(listMa.contains( ec_nhaXBTemp.get_MSNXB().toLowerCase())){
            if(_isThem == 0){
                if(!ec_nhaXB.get_MSNXB().toLowerCase().equalsIgnoreCase(ec_nhaXBTemp.get_MSNXB().toLowerCase())){
                    txtThongbao.setText("Mã đã tồn tại");
                    return false;
                }
                if(ec_nhaXB.toString().toLowerCase().equalsIgnoreCase(ec_nhaXBTemp.toString().toLowerCase())){
                    txtThongbao.setText("Thông tin không đổi");
                    return false;
                }

            }else {
                txtThongbao.setText("Mã đã tồn tại");
                return false;
            }
        }
        
        return true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSuaNhaXB: {
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
            case R.id.btnThemNhaXB: {
                if (checkInput()) {
                    insert();
                }
                break;
            }
            case R.id.btnHuyNhaXB: {
                dismiss();
                break;
            }
            case R.id.btnXoaNhaXB: {
                txtMa.setText(ec_nhaXB.get_MSNXB());
                txtTen.setText(ec_nhaXB.get_TenNXB());
                txtDiaChi.setText(ec_nhaXB.get_DiaChiNXB());
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
        String kq = bus_docGia.UpdateData(ec_nhaXB, ec_nhaXBTemp);
        if (!kq.equalsIgnoreCase("1"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else {
            dismiss();
        }
    }

    private void insert() {
        String kq = bus_docGia.InseartData(ec_nhaXBTemp);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else {
            txtThongbao.setText("Thêm thành công ");
            listMa.add(ec_nhaXBTemp.get_MSNXB());
        }

    }

    private void delete() {
        String kq = bus_docGia.DeleteData(ec_nhaXB);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi xóa ");
        else {
            dismiss();
        }
    }
}
