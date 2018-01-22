package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLLoaiSach;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.BUS.BUS_LoaiSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_LoaiSach;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/17/18.
 */

public class LoaiSachDialogFragment extends DialogFragment implements View.OnClickListener {
    boolean _isSua = true;
    LinearLayout linearLayout;
    ArrayList<EC_LoaiSach> ec_loaiSachArrayList;
    EC_LoaiSach ec_loaiSach;
    EditText txtMa;
    TextView txtThongbao;
    EditText txtTen;
    Button btnSua,btnXoa,btnHuy,btnThem;
    BUS_LoaiSach bus_loaiSach;
    ArrayList<String> listMa = new ArrayList<>();
    TextView lblThongTin;
    int _isThem = 0;
    String[] lTitle = new String[]{"Thông tin danh mục","Thêm danh mục"};
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongtin_loaisach_dialog,container,false);
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupDiaLog();
        checkShow();
    }
    public void params(ArrayList<EC_LoaiSach>ec_loaiSaches,EC_LoaiSach e,int _isThem){
        this.ec_loaiSachArrayList = ec_loaiSaches;
        this._isThem = _isThem;
        this.ec_loaiSach = e;
        for (int i = 0 ; i < ec_loaiSachArrayList.size();i++){
            listMa.add(ec_loaiSachArrayList.get(i).get_MaLoaiSach());
        }
    }
    private void setupEventButton(){
        btnHuy.setOnClickListener(this);
        btnSua.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnThem.setOnClickListener(this);
    }
    private void setupDiaLog(){
        setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getDialog().getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        lp.dimAmount=0.7f;
        getDialog().getWindow().setAttributes(lp);
    }
    private void init(View v){
        lblThongTin = (TextView)v.findViewById(R.id.lblThongTin);
        lblThongTin.setText(lTitle[_isThem]);
        linearLayout = (LinearLayout)v.findViewById(R.id.linearLayoutBtns);
        txtThongbao = (TextView)v.findViewById(R.id.txt_danhmuc_thongbao);
        bus_loaiSach = new BUS_LoaiSach();
        txtMa = (EditText)v.findViewById(R.id.txtMaLoaiSach);
        txtTen = (EditText)v.findViewById(R.id.txtTenLoaiSach);
        if(_isThem == 0) {
            txtMa.setText(ec_loaiSach.get_MaLoaiSach());
            txtTen.setText(ec_loaiSach.get_LoaiSach());
            setEnableEditText(false);
        }


        btnSua = (Button)v.findViewById(R.id.btnSuaDanhMuc);

        btnHuy = (Button)v.findViewById(R.id.btnHuyDanhMuc);

        btnXoa = (Button)v.findViewById(R.id.btnXoaDanhMuc);

        btnThem = (Button)v.findViewById(R.id.btnThemDanhMuc);

        setupEventButton();

    }
    private void setEnableEditText(boolean b){
        if(!b){
            txtMa.setEnabled(false);
            txtTen.setEnabled(false);
            txtMa.setBackgroundResource(R.color.colorDisable);
            txtTen.setBackgroundResource(R.color.colorDisable);
        }else{
            txtMa.setEnabled(true);
            txtTen.setEnabled(true);
            txtMa.setBackgroundResource(R.color.white);
            txtTen.setBackgroundResource(R.color.white);
        }
    }
    private void checkShow(){
        if(_isThem == 0){
            linearLayout.setWeightSum(3);
            btnThem.setVisibility(View.GONE);
        }
        else{
            linearLayout.setWeightSum(2);
            btnSua.setVisibility(View.GONE);
            btnXoa.setVisibility(View.GONE);
        }
    }
    private boolean checkInput(String m,String t){
        String ma = txtMa.getText().toString();
        String ten = txtTen.getText().toString();
        if(m != ""){
            if(m.equalsIgnoreCase(ma))
                if(ec_loaiSach.get_MaLoaiSach().equalsIgnoreCase(m) && ec_loaiSach.get_LoaiSach().equalsIgnoreCase(t)){
                    txtThongbao.setText("Thông tin không đổi");
                    return false;
                }
        }

        if(listMa.contains(ma)) {
            if(m != ""){
                if(!ec_loaiSach.get_MaLoaiSach().equalsIgnoreCase(m)){
                    txtThongbao.setText("Mã đã tồn tại");
                    return false;
                }
            }else{
                txtThongbao.setText("Mã đã tồn tại");
                return false;
            }
        }
        if(ma.length() == 0 || ten.length() == 0) {
            txtThongbao.setText("Chưa nhập đủ thông tin");
            return false;
        }

        return true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSuaDanhMuc:{
                if(_isSua){
                    _isSua = !_isSua;
                    btnSua.setText("Lưu");
                    setEnableEditText(true);



                }else{
                    String ma = txtMa.getText().toString();
                    String ten = txtTen.getText().toString();
                    if(checkInput(ma,ten)) {
                        _isSua = !_isSua;
                        btnSua.setText("Sửa");
                        setEnableEditText(false);
                        EC_LoaiSach ec_temp = new EC_LoaiSach(ma, ten);
                        update(ec_temp);
                    }
                }
                break;
            }
            case R.id.btnThemDanhMuc: {
                if(checkInput("","")) {
                    String ma = txtMa.getText().toString();
                    String ten = txtTen.getText().toString();
                    EC_LoaiSach ec_temp = new EC_LoaiSach(ma, ten);
                    insert(ec_temp);
                }
                break;
            }
            case R.id.btnHuyDanhMuc:{
                dismiss();
                break;
            }
            case R.id.btnXoaDanhMuc:{
                txtMa.setText(ec_loaiSach.get_MaLoaiSach());
                txtTen.setText(ec_loaiSach.get_LoaiSach());
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn chắc chắn muốn xóa "+txtMa.getText().toString() + "-"+txtTen.getText().toString())
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                delete();
                            }
                        })
                        .setNegativeButton("Hủy ",null);

                AlertDialog alert = builder.create();
                alert.show();
                break;
            }
        }
    }
    private void update(EC_LoaiSach ec_temp) {
        String kq = bus_loaiSach.UpdateData(ec_loaiSach,ec_temp);
        if(!kq.equalsIgnoreCase("1"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else {
            dismiss();
        }
    }

    private void insert(EC_LoaiSach ec_temp) {
        String kq = bus_loaiSach.InseartData(ec_temp);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else{
            txtThongbao.setText("Thêm thành công ");
            listMa.add(ec_temp.get_MaLoaiSach());
        }

    }

    private void delete(){
        String kq = bus_loaiSach.DeleteData(ec_loaiSach);
        if(kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi xóa ");
        else {
            dismiss();
        }
    }
}
