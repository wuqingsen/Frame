package com.freexiaoyu.cloud.ui.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.freexiaoyu.cloud.R;
import com.freexiaoyu.cloud.bean.MessageEvent;
import com.freexiaoyu.cloud.utils.OSUtils;
import com.freexiaoyu.cloud.utils.StatusBarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(provideContentViewId());//布局
        ButterKnife.bind(this);
        mContext=this;
        initView();
        initData();
        initListener();
        if (OSUtils.getSystem() == OSUtils.SYS_MIUI) {
            StatusBarUtils.setMiuiStatusBarDarkMode(BaseActivity.this, false);
        } else if (OSUtils.getSystem() == OSUtils.SYS_FLYME) {
            StatusBarUtils.setMeizuStatusBarDarkIcon(BaseActivity.this, false);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                //getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }


    protected abstract int provideContentViewId();//用于引入布局文件
    /***
     * 在初始化布局之前操作
     */
    protected abstract void init();

    /***
     * 布局初始化完成后控件初始化
     */
    protected abstract void initView();
    /***
     * 在初始化布局之前操作
     */
    protected abstract void initData();
    /***
     * 事件
     */
    protected abstract void initListener();


    protected abstract  void messageHandler(MessageEvent event);

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        messageHandler(event);
    }


    protected void toolBar(Boolean isBlack, Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        if (isBlack) {
            toolbar.setNavigationIcon(R.drawable.title_bar_back_btn_white);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

}
