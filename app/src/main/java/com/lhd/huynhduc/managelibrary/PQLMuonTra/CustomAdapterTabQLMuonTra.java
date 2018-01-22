package com.lhd.huynhduc.managelibrary.PQLMuonTra;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lhd.huynhduc.managelibrary.PQLMuonTra.QLDSMuon.DSMuonFragment;

/**
 * Created by huynhduc on 1/15/18.
 */

public class CustomAdapterTabQLMuonTra extends FragmentStatePagerAdapter {
    private String STab [] = {"DS mượn","Tùy chỉnh","DS trả ","DS phạt "};

    public CustomAdapterTabQLMuonTra(FragmentManager supportFragmentManager, Context applicationContext) {
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
                return new DSMuonFragment();
            }
            case 1: {
                return new TuyChinhPFragment();
            }
            case 2: {
                return new DSTraFragment();
            }
            case 3:{
                return new DSPhatFragment();
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
