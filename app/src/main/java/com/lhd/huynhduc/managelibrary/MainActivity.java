package com.lhd.huynhduc.managelibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.lhd.huynhduc.managelibrary.BUS.BUS_DocGia;
import com.lhd.huynhduc.managelibrary.BUS.BUS_NhanVien;
import com.lhd.huynhduc.managelibrary.Class.Variable;
import com.lhd.huynhduc.managelibrary.PQLBaoCao.CustomAdapterTabQLBaoCao;
import com.lhd.huynhduc.managelibrary.PQLDanhMuc.CustomAdapterTabQLDanhMuc;
import com.lhd.huynhduc.managelibrary.PQLMuonTra.CustomAdapterTabQLMuonTra;
import com.lhd.huynhduc.managelibrary.PQLNhanVien.CustomAdapterTabQLNhanVien;

import java.nio.charset.Charset;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout mTablayout;
    ViewPager mViewPager;
    Toolbar toolbar;
    boolean _isKhoiTaoTab = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void khoiTaoTab(){
        mViewPager = null;
        mTablayout = null;
        LinearLayout linearLayout  = null;
        linearLayout = (LinearLayout)findViewById(R.id.include_tab);
        linearLayout.setVisibility(View.VISIBLE); //set hiện layout

        mViewPager = (ViewPager) findViewById(R.id.pager_update);
        mTablayout = (TabLayout)findViewById(R.id.tab_update);
        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.setTabTextColors(
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.white)
        );// Set  màu trắng chữ lúc chọn và không chọn
        mTablayout.setSelectedTabIndicatorColor(getResources().getColor( R.color.colorAccent));//set màu chân lúc

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void hienTab(int _i){
        if(_i == 1){
            toolbar.setTitle(getResources().getString(R.string.txt_qldanhmuc));
            mViewPager.setAdapter(new CustomAdapterTabQLDanhMuc(getSupportFragmentManager(),getApplicationContext()));
            int lIcon[] = new int[]{R.drawable.img_title_loaisach,
                    R.drawable.img_title_danhmucsach,
                    R.drawable.img_title_docgia,
                    R.drawable.img_title_nhaxb};
            for (int i = 0; i < mTablayout.getTabCount(); i++) {
                mTablayout.getTabAt(i).setIcon(lIcon[i]);
            }
        }

        else if(_i == 2) {
            toolbar.setTitle(getResources().getString(R.string.txt_qlimtsach));
            mViewPager.setAdapter(new CustomAdapterTabQLMuonTra(getSupportFragmentManager(), getApplicationContext()));
            int lIcon[] = new int[]{R.drawable.img_title_dsmuon,
                    R.drawable.img_title_tuychinh,
                    R.drawable.img_title_dstra,
                    R.drawable.img_title_dsphat};
            for (int i = 0; i < mTablayout.getTabCount(); i++) {
                mTablayout.getTabAt(i).setIcon(lIcon[i]);
            }
        }

        else if(_i == 3) {
            toolbar.setTitle(getResources().getString(R.string.txt_baocaotk));
            mViewPager.setAdapter(new CustomAdapterTabQLBaoCao(getSupportFragmentManager(), getApplicationContext()));
            int lIcon[] = new int[]{R.drawable.img_title_sachconlai,
                    R.drawable.img_title_sachmuon,
                    R.drawable.img_title_sachtra,
                    R.drawable.img_title_quahan
            };
            for (int i = 0; i < mTablayout.getTabCount(); i++) {
                mTablayout.getTabAt(i).setIcon(lIcon[i]);
            }

        }
        else if(_i == 4) {
            toolbar.setTitle(getResources().getString(R.string.txt_nhanvien));
            mViewPager.setAdapter(new CustomAdapterTabQLNhanVien(getSupportFragmentManager(), getApplicationContext()));
            int lIcon[] = new int[]{R.drawable.img_title_thongtinnv,
                    R.drawable.img_title_dkinv,
                    R.drawable.img_title_suadoinv,
                    R.drawable.img_title_xoanv,
                    R.drawable.img_title_doimk};
            for (int i = 0; i < mTablayout.getTabCount(); i++) {
                mTablayout.getTabAt(i).setIcon(lIcon[i]);
            }

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        if(_isKhoiTaoTab){
            _isKhoiTaoTab = !_isKhoiTaoTab;
            khoiTaoTab();
        }
        int id = item.getItemId();
        if (id == R.id.nav_ql_danhmuc) {

            hienTab(1);
        } else if (id == R.id.nav_ql_muontra) {
            hienTab(2);
        } else if (id == R.id.nav_ql_baocao) {
            hienTab(3);
        } else if (id == R.id.nav_ql_nhanvien) {
            hienTab(4);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class GetData extends AsyncTask<String,Void,String>{
        ProgressDialog pd;
        Context mContext;
        public GetData(Context c){
            mContext = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(mContext);
            pd.setCancelable(true);
            pd.setTitle("Đang tải...");
            pd.show();
        }
        //1
        @Override
        protected String doInBackground(String... strings) {

            HTTPDataHandle httpDataHandle = new HTTPDataHandle();

            String data = "";
            return data;
        }
        //2
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        //3
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Hoan thanh
            pd.dismiss();
        }

    }

}
