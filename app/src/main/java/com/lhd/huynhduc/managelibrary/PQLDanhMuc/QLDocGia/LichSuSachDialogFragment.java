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
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lhd.huynhduc.managelibrary.BUS.BUS_DocGia;
import com.lhd.huynhduc.managelibrary.Entity.EC_DocGia;
import com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLLoaiSach.LoaiSach_ListViewAdapter;
import com.lhd.huynhduc.managelibrary.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduc on 1/17/18.
 */

public class LichSuSachDialogFragment extends DialogFragment{
    int _isMuon = -1;
    String MaDG;
    String TenDG;
    TextView txtThongBao;
    TextView txtMa,txtTen;
    TextView txtTongLichSu;
    BUS_DocGia bus_docGia;
    Button btnDong;
    ListView lvLsSach;
    ScrollView scrollView;
    String[] lTitle = new String[]{"Lịch sử trả sách","Lịch sử mượn sách"};
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_lsmuontrasach, container, false);
        init(view);
        setupEventButton();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupDiaLog();
    }

    public void params(int _isMuon,String ma,String ten) {
        this.MaDG = ma;
        this.TenDG = ten;
        this._isMuon = _isMuon;

    }

    private void setupEventButton() {
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
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
        bus_docGia = new BUS_DocGia();
        scrollView = (ScrollView)v.findViewById(R.id.scrollView) ;
        txtThongBao = (TextView)v.findViewById(R.id.lblTieuDeLichSuSach);
        txtTongLichSu = (TextView)v.findViewById(R.id.txtTongLichSu);
        txtThongBao.setText(lTitle[_isMuon]);
        txtMa = (TextView) v.findViewById(R.id.txt_lssach_madocgia);
        txtTen = (TextView) v.findViewById(R.id.txt_lssach_tendocgia);
        txtMa.setText(this.MaDG);
        txtTen.setText(this.TenDG);

        btnDong = (Button)v.findViewById(R.id.btnDongLichSu);
        lvLsSach = (ListView)v.findViewById(R.id.lvLichSuSach);
        LichSuSach_ListViewAdapter lichSuSach_listViewAdapter = null;
        ArrayList<EC_DocGia.LichSuSach> arr_lichSuSaches = null;
        if(_isMuon == 1){
            arr_lichSuSaches = bus_docGia.getlsMuonSach(MaDG);


        }else{
            arr_lichSuSaches = bus_docGia.getlsTraSach(MaDG);


        }
        lichSuSach_listViewAdapter= new LichSuSach_ListViewAdapter(getContext(),
                R.layout.raw_docgia_lssach,
                R.id.txt_raw_lssach_maphieu,
                arr_lichSuSaches);
        txtTongLichSu.setText( "Tổng bản ghi "+lichSuSach_listViewAdapter.getCount());
        lvLsSach.setAdapter(lichSuSach_listViewAdapter);



    }


}
