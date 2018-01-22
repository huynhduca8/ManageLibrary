package com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLQuyenSach;

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

import com.lhd.huynhduc.managelibrary.BUS.BUS_LoaiSach;
import com.lhd.huynhduc.managelibrary.BUS.BUS_NhaXuatBan;
import com.lhd.huynhduc.managelibrary.BUS.BUS_QuyenSach;
import com.lhd.huynhduc.managelibrary.BUS.BUS_QuyenSach;
import com.lhd.huynhduc.managelibrary.Entity.EC_QuyenSach;
import com.lhd.huynhduc.managelibrary.R;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/17/18.
 */

public class QuyenSachDialogFragment extends DialogFragment implements View.OnClickListener {
    boolean _isSua = true;
    LinearLayout linearLayout;
    ArrayList<EC_QuyenSach> ec_quyenSachArrayList;
    EC_QuyenSach ec_quyenSach;
    EC_QuyenSach ec_quyenSachTemp;
    EditText txtMa,txtTen,txtTacGia,txtNamXB,txtLanXB,txtSL,txtTomLuoc;
    Spinner txtMSNXB,txtMaLoaiSach;
    TextView txtThongbao,lblThongTin;
    Button btnSua, btnXoa, btnHuy, btnThem;
    BUS_QuyenSach bus_quyenSach;
    ArrayList<String> listMa = new ArrayList<>();
    ArrayAdapter<String> arrayAdapterMSNXB,arrayAdapterMaLoaiSach;
    int _isThem = 0;
    String[] lTitle = new String[]{"Thông tin quyển sách","Thêm quyển sách"};
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongtin_quyensach_dialog, container, false);
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupDiaLog();
        checkShow();
    }

    public void params(ArrayList<EC_QuyenSach> ec_loaiSaches, EC_QuyenSach e, int _isThem) {
        this.ec_quyenSachArrayList = ec_loaiSaches;
        this._isThem = _isThem;
        this.ec_quyenSach = e;
        for (int i = 0 ; i < ec_quyenSachArrayList.size();i++){
            listMa.add(ec_quyenSachArrayList.get(i).get_MSSach());
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
        lblThongTin = (TextView)v.findViewById(R.id.lblThongTinQuyenSach);
        txtThongbao = (TextView)v.findViewById(R.id.txt_quyensach_thongbao);



        lblThongTin.setText(lTitle[_isThem]);
        linearLayout = (LinearLayout)v.findViewById(R.id.linearLayoutBtnsQuyenSach);

        bus_quyenSach = new BUS_QuyenSach();
        txtMa = (EditText)v.findViewById(R.id.txtMaQuyenSach);
        txtTen = (EditText)v.findViewById(R.id.txtTenQuyenSach);
        txtTacGia = (EditText)v.findViewById(R.id.txtTacGiaQuyenSach);
        txtMSNXB = (Spinner) v.findViewById(R.id.txtMaNXBQuyenSach);
        txtMaLoaiSach= (Spinner)v.findViewById(R.id.txtMaLoaiSachQuyenSach);
        txtNamXB = (EditText)v.findViewById(R.id.txtNamXBQuyenSach);
        txtLanXB = (EditText)v.findViewById(R.id.txtLanXBQuyenSach);
        txtSL = (EditText)v.findViewById(R.id.txtSoLuongQuyenSach);
        txtTomLuoc = (EditText)v.findViewById(R.id.txtTomLuocQuyenSach);



        arrayAdapterMaLoaiSach = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,
                        new BUS_LoaiSach().getListMaNXB()); //selected item will look like a spinner set from XML
        arrayAdapterMaLoaiSach.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        txtMaLoaiSach.setAdapter(arrayAdapterMaLoaiSach);





        arrayAdapterMSNXB = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,
                        new BUS_NhaXuatBan().getListMaNXB()); //selected item will look like a spinner set from XML
        arrayAdapterMSNXB.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        txtMSNXB.setAdapter(arrayAdapterMSNXB);

        if (_isThem == 0) {
            txtMa.setText(ec_quyenSach.get_MSSach());
            txtTen.setText(ec_quyenSach.get_TenSach());
            txtTacGia.setText(ec_quyenSach.get_TacGia());
            txtTomLuoc.setText(ec_quyenSach.get_NoiDungTomLuoc());
            txtSL.setText(ec_quyenSach.get_SoLuong());
            txtLanXB.setText(ec_quyenSach.get_LanXB());
            txtNamXB.setText(ec_quyenSach.get_NamXB());
            txtMaLoaiSach.setSelection(arrayAdapterMaLoaiSach.getPosition(ec_quyenSach.get_MaLoaiSach()));
            txtMSNXB.setSelection(arrayAdapterMaLoaiSach.getPosition(ec_quyenSach.get_MaLoaiSach()));



            setEnableEditText(1);
        }

        btnSua = (Button) v.findViewById(R.id.btnSuaQuyenSach);

        btnHuy = (Button) v.findViewById(R.id.btnHuyQuyenSach);

        btnXoa = (Button) v.findViewById(R.id.btnXoaQuyenSach);

        btnThem = (Button) v.findViewById(R.id.btnThemQuyenSach);

        setupEventButton();



    }

    private void setEnableEditText(int b){
        boolean[] booleans = new boolean[]{true,false};
        int[] ints = new int[]{R.color.white,R.color.colorDisable};
        txtMa.setEnabled(booleans[b]);
        txtTen.setEnabled(booleans[b]);
        txtTacGia.setEnabled(booleans[b]);
        txtNamXB.setEnabled(booleans[b]);
        txtLanXB.setEnabled(booleans[b]);
        txtSL.setEnabled(booleans[b]);
        txtTomLuoc.setEnabled(booleans[b]);
        txtMSNXB.setEnabled(booleans[b]);
        txtMaLoaiSach.setEnabled(booleans[b]);
        txtMa.setBackgroundResource(ints[b]);
        txtTen.setBackgroundResource(ints[b]);
        txtTomLuoc.setBackgroundResource(ints[b]);
        txtTacGia.setBackgroundResource(ints[b]);
        txtMSNXB.setBackgroundResource(ints[b]);
        txtSL.setBackgroundResource(ints[b]);
        txtLanXB.setBackgroundResource(ints[b]);
        txtNamXB.setBackgroundResource(ints[b]);
        txtMaLoaiSach.setBackgroundResource(ints[b]);

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
        ec_quyenSachTemp = new EC_QuyenSach(txtMa.getText().toString(),
                txtTen.getText().toString(),txtTacGia.getText().toString(),
                txtMSNXB.getSelectedItem().toString(),txtMSNXB.getSelectedItem().toString(),
                txtNamXB.getText().toString(),txtLanXB.getText().toString(),
                txtSL.getText().toString(),txtTomLuoc.getText().toString());
        if(!ec_quyenSachTemp.isFullInfo()){
            txtThongbao.setText("Thông tin chưa đầy đủ");
            return false;
        }

        if(listMa.contains( ec_quyenSachTemp.get_MSSach().toLowerCase())){
            if(_isThem == 0){
                if(!ec_quyenSach.get_MSSach().toLowerCase().equalsIgnoreCase(ec_quyenSachTemp.get_MSSach().toLowerCase())){
                    txtThongbao.setText("Mã đã tồn tại");
                    return false;
                }
                if(ec_quyenSach.toString().toLowerCase().equalsIgnoreCase(ec_quyenSachTemp.toString().toLowerCase())){
                    txtThongbao.setText("Thông tin không đổi");
                    return false;
                }

            }else {
                txtThongbao.setText("Mã đã tồn tại");
                return false;
            }
        }
        if(!ec_quyenSachTemp.checkLanXB()){
            txtThongbao.setText("Lần xuất bản không đúng");
            return false;
        }
        if(!ec_quyenSachTemp.checkSL()){
            txtThongbao.setText("Số lượng không đúng");
            return false;
        }
        if(!ec_quyenSachTemp.checkNamXB()){
            txtThongbao.setText("Năm xuất bản không đúng");
            return false;
        }
        return true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSuaQuyenSach: {
                if (_isSua) {
                    _isSua = !_isSua;
                    btnSua.setText("Lưu");
                    setEnableEditText(0);


                } else {

                    if (checkInput()) {
                        _isSua = !_isSua;
                        btnSua.setText("Sửa");
                        setEnableEditText(1);
                        update();
                    }
                }
                break;
            }
            case R.id.btnThemQuyenSach: {
                if (checkInput()) {
                    insert();
                }
                break;
            }
            case R.id.btnHuyQuyenSach: {
                dismiss();
                break;
            }
            case R.id.btnXoaQuyenSach: {
                txtMa.setText(ec_quyenSach.get_MSSach());
                txtTen.setText(ec_quyenSach.get_TenSach());
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
        String kq = bus_quyenSach.UpdateData(ec_quyenSach, ec_quyenSachTemp);
        if (!kq.equalsIgnoreCase("1"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else {
            dismiss();
        }
    }

    private void insert() {
        String kq = bus_quyenSach.InseartData(ec_quyenSachTemp);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi thêm ");
        else {
            txtThongbao.setText("Thêm thành công ");
            listMa.add(ec_quyenSachTemp.get_MSSach());
        }

    }

    private void delete() {
        String kq = bus_quyenSach.DeleteData(ec_quyenSach);
        if (kq.equalsIgnoreCase("0"))
            txtThongbao.setText("Đã xảy ra lỗi khi xóa ");
        else {
            dismiss();
        }
    }
}
