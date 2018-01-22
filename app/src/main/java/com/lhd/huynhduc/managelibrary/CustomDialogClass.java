package com.lhd.huynhduc.managelibrary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.BUS.BUS_LoaiSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_LoaiSach;
import com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLLoaiSach.LoaiSach_ListViewAdapter;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/16/18.
 */

public class CustomDialogClass extends Dialog implements View.OnClickListener {

    boolean _isSua = true;
    Activity activity;
    LinearLayout linearLayout;
    LoaiSach_ListViewAdapter _danhMucListViewAdapter;
    int pos = 0;
    EC_LoaiSach ec_loaiSach;
    EditText txtMa;
    TextView txtThongbao;
    EditText txtTen;
    Button btnSua,btnXoa,btnHuy,btnThem;
    BUS_LoaiSach bus_loaiSach;
    ArrayList<String> listMa = new ArrayList<>();
    TextView lblThongTin;
    String title;
    int _check;

    public CustomDialogClass(Activity a, LoaiSach_ListViewAdapter _danhMucListViewAdapter, int pos, int _check, String title) {
        super(a);
        this.title = title;
        this._danhMucListViewAdapter = _danhMucListViewAdapter;
        ec_loaiSach = _danhMucListViewAdapter.getItem(pos);
        // TODO Auto-generated constructor stub
        this.pos = pos;
        this.activity = a;
        this._check = _check;
        for (int i = 0; i < _danhMucListViewAdapter.getCount(); i++){
            listMa.add(_danhMucListViewAdapter.getItem(i).get_MaLoaiSach());
        }

    }
    public interface buttonDisMiss{
        void dialogDisMiss();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.thongtin_loaisach_dialog);
        setupDiaLog();
        init();
        btnHuy.setOnClickListener(this);
        btnSua.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnThem.setOnClickListener(this);
        checkShow();



    }
    private void checkShow(){
        if(_check == 0){
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
    private void setupDiaLog(){
        setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        lp.dimAmount=0.7f;
        getWindow().setAttributes(lp);
    }
    private void init(){
        lblThongTin = (TextView)findViewById(R.id.lblThongTin);
        lblThongTin.setText(title);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutBtns);
        txtThongbao = (TextView)findViewById(R.id.txt_danhmuc_thongbao);
        bus_loaiSach = new BUS_LoaiSach();
        txtMa = (EditText)findViewById(R.id.txtMaLoaiSach);
        txtTen = (EditText)findViewById(R.id.txtTenLoaiSach);
        if(_check == 0) {
            txtMa.setText(ec_loaiSach.get_MaLoaiSach());
            txtTen.setText(ec_loaiSach.get_LoaiSach());
            setEnableEditText(false);
        }


        btnSua = (Button)findViewById(R.id.btnSuaDanhMuc);

        btnHuy = (Button)findViewById(R.id.btnHuyDanhMuc);

        btnXoa = (Button)findViewById(R.id.btnXoaDanhMuc);

        btnThem = (Button)findViewById(R.id.btnThemDanhMuc);


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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSuaDanhMuc:{
                if(_isSua){
                    _isSua = !_isSua;
                    //txtTen.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tick, 0);
                    btnSua.setText("Lưu ");
                    setEnableEditText(true);



                }else{
                    String ma = txtMa.getText().toString();
                    String ten = txtTen.getText().toString();
                    if(checkInput(ma,ten)) {
                        _isSua = !_isSua;
                        btnSua.setText("Sửa ");
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
                refreshAdapter();
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
            refreshAdapter();
            dismiss();
        }
    }

    private void insert(EC_LoaiSach ec_temp) {
        String kq = bus_loaiSach.InseartData(ec_temp);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else{
            refreshAdapter();
            txtThongbao.setText("Thêm thành công ");
            listMa.add(ec_temp.get_MaLoaiSach());
        }

    }

    private void delete(){
        String kq = bus_loaiSach.DeleteData(ec_loaiSach);
        if(kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi xóa ");
        else {
            refreshAdapter();
            dismiss();
        }
    }
    private void refreshAdapter(){
        ArrayList<EC_LoaiSach> saches = new BUS_LoaiSach().selectAllData();
        _danhMucListViewAdapter.clear();
        _danhMucListViewAdapter.addAll(saches);
        _danhMucListViewAdapter.notifyDataSetChanged();
    }
}
