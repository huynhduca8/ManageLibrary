package com.lhd.huynhduc.managelibrary;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by huynhduc on 1/18/18.
 */

public class AdapterHelper {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void update(ArrayAdapter arrayAdapter, ArrayList<Object> listOfObject){
        arrayAdapter.clear();
        for (Object object : listOfObject){
            arrayAdapter.add(object);
        }
    }
}
