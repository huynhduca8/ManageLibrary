package com.lhd.huynhduc.managelibrary.PQLBaoCao;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lhd.huynhduc.managelibrary.PQLNhanVien.DangKyNVFragment;
import com.lhd.huynhduc.managelibrary.PQLNhanVien.DoiMKNVFragment;
import com.lhd.huynhduc.managelibrary.PQLNhanVien.SuaNVFragment;
import com.lhd.huynhduc.managelibrary.PQLNhanVien.TTNVFragment;
import com.lhd.huynhduc.managelibrary.PQLNhanVien.XoaNVFragment;

/**
 * Created by huynhduc on 1/15/18.
 */

public class CustomAdapterTabQLBaoCao extends FragmentStatePagerAdapter {
    private String STab [] = {"Sách còn lại ","Sách mượn ","Sách trả ","Quá hạn"};

    public CustomAdapterTabQLBaoCao(FragmentManager supportFragmentManager, Context applicationContext) {
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
                return new BCSachConLaiFragment();
            }
            case 1: {
                return new BCSachMuonFragment();
            }
            case 2: {
                return new BCSachTraFragment();
            }
            case 3: {
                return new BCQuaHanFragment();
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
