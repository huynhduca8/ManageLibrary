package com.lhd.huynhduc.managelibrary.PQLNhanVien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhd.huynhduc.managelibrary.R;

/**
 * Created by huynhduc on 1/15/18.
 */

public class DangKyNVFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nv_dki,container,false);
    }
}
