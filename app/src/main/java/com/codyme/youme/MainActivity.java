package com.codyme.youme;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    //顶部栏
    private View barMain;

    //底部导航栏
    private View navMain;

    private Button btnNavTour;
    private Button btnNavGoods;
    private Button btnNavMsg;
    private Button btnNavShare;
    private Button btnNavUser;

    private FragmentManager mFragmentManager;

    //事件监听器
    private View.OnClickListener mClickListener;

    private TourFragment fragmentTour;
    private GoodFragment fragmentGoods;
    private MsgFragment fragmentMsg;
    private ShareFragment fragmentShare;
    private UserFragment fragmentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化组件
        initUtils();
        initViews();

        switchTo(0);
    }

    private void initUtils() {
        mFragmentManager = getSupportFragmentManager();

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_bar_friends:
                        break;

                    case R.id.btn_bar_search:
                        break;

                    case R.id.btn_bar_add:
                        showPopupMenu(MainActivity.this, v);
                        break;

                    case R.id.btn_nav_tour:
                        switchTo(0);
                        break;

                    case R.id.btn_nav_goods:
                        switchTo(1);
                        break;

                    case R.id.btn_nav_msg:
                        switchTo(2);
                        break;

                    case R.id.btn_nav_share:
                        switchTo(3);
                        break;

                    case R.id.btn_nav_user:
                        switchTo(4);
                        break;

                }
            }
        };
    }

    private void initViews() {

        barMain = findViewById(R.id.bar_main);

        navMain = findViewById(R.id.nav_main);

        btnNavTour = (Button) navMain.findViewById(R.id.btn_nav_tour);
        btnNavGoods = (Button) navMain.findViewById(R.id.btn_nav_goods);
        btnNavMsg = (Button) navMain.findViewById(R.id.btn_nav_msg);
        btnNavShare = (Button) navMain.findViewById(R.id.btn_nav_share);
        btnNavUser = (Button) navMain.findViewById(R.id.btn_nav_user);

        barMain.findViewById(R.id.btn_bar_search).setOnClickListener(mClickListener);
        barMain.findViewById(R.id.btn_bar_add).setOnClickListener(mClickListener);

        btnNavTour.setOnClickListener(mClickListener);
        btnNavGoods.setOnClickListener(mClickListener);
        btnNavMsg.setOnClickListener(mClickListener);
        btnNavShare.setOnClickListener(mClickListener);
        btnNavUser.setOnClickListener(mClickListener);

    }

    private void switchTo(int id) {

        resetNavBtns();

        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();

        switch (id){
            case 0:
                btnNavTour.setSelected(true);
                if(fragmentTour == null){
                    fragmentTour = new TourFragment();
                    mTransaction.show(fragmentTour);
                }
                mTransaction.replace(R.id.container_main, fragmentTour);
                break;
            case 1:
                btnNavGoods.setSelected(true);
                if(fragmentGoods == null){
                    fragmentGoods = new GoodFragment();
                    mTransaction.show(fragmentGoods);
                }
                mTransaction.replace(R.id.container_main, fragmentGoods);
                break;
            case 2:
                btnNavMsg.setSelected(true);
                if(fragmentMsg == null){
                    fragmentMsg = new MsgFragment();
                    mTransaction.show(fragmentMsg);
                }
                mTransaction.replace(R.id.container_main, fragmentMsg);
                break;
            case 3:
                btnNavShare.setSelected(true);
                if(fragmentShare == null){
                    fragmentShare = new ShareFragment();
                    mTransaction.show(fragmentShare);
                }
                mTransaction.replace(R.id.container_main, fragmentShare);
                break;
            case 4:
                btnNavUser.setSelected(true);
                if(fragmentUser == null){
                    fragmentUser = new UserFragment();
                    mTransaction.show(fragmentUser);
                }
                mTransaction.replace(R.id.container_main, fragmentUser);
                break;
        }

        mTransaction.commit();
    }

    private void resetNavBtns() {
        btnNavTour.setSelected(false);
        btnNavGoods.setSelected(false);
        btnNavMsg.setSelected(false);
        btnNavShare.setSelected(false);
        btnNavUser.setSelected(false);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showPopupMenu(final Context context, View ancher) {
        PopupMenu popupMenu = new PopupMenu(context, ancher);
        //引入菜单资源
        popupMenu.inflate(R.menu.menu_add);

        //菜单项的监听
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_add_share:
                        break;

                    case R.id.menu_scan_QR:
                        break;

                    case R.id.menu_my_QR:
                        break;

                    case R.id.menu_add_friend:
                        break;
                }
                return true;
            }
        });

        //使用反射，强制显示菜单图标
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popupMenu);
            mHelper.setForceShowIcon(true);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        //显示PopupMenu
        popupMenu.show();
    }
}
