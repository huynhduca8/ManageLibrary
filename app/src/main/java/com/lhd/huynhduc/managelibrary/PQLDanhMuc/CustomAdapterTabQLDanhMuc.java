package com.lhd.huynhduc.managelibrary.PQLDanhMuc;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLLoaiSach.LoaiSachFragment;
import com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLDocGia.DocGiaFragment;
import com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLNhaXB.NhaXBFragment;
import com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLQuyenSach.QuyenSachDialogFragment;
import com.lhd.huynhduc.managelibrary.PQLDanhMuc.QLQuyenSach.QuyenSachFragment;

/**
 * Created by huynhduc on 1/15/18.
 */

public class CustomAdapterTabQLDanhMuc extends FragmentStatePagerAdapter {
    private String STab [] = {"Loại sách ","DM sách ","Độc giả ","Nhà XB "};

    public CustomAdapterTabQLDanhMuc(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new LoaiSachFragment();
            }
            case 1: {
                return new QuyenSachFragment();
            }
            case 2: {
                return new DocGiaFragment();
            }
            case 3: {
                return new NhaXBFragment();
            }
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return STab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return STab[position];
    }

}
