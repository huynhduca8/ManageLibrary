package com.lhd.huynhduc.managelibrary.PQLNhanVien;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by huynhduc on 1/15/18.
 */

public class CustomAdapterTabQLNhanVien extends FragmentStatePagerAdapter {
    private String STab [] = {"Thông tin ","Đăng ký ","Sửa đổi ","Xóa ","Đổi MK "};

    public CustomAdapterTabQLNhanVien(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new TTNVFragment();
            }
            case 1: {
                return new DangKyNVFragment();
            }
            case 2: {
                return new SuaNVFragment();
            }
            case 3: {
                return new XoaNVFragment();
            }
            case 4: {
                return new DoiMKNVFragment();
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
