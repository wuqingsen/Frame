package com.freexiaoyu.cloud;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.freexiaoyu.cloud.bean.MessageEvent;
import com.freexiaoyu.cloud.ui.base.BaseActivity;
import com.freexiaoyu.cloud.utils.Camera;
import com.freexiaoyu.cloud.utils.DES3Utils;
import com.freexiaoyu.cloud.utils.PermissionsChecker;
import com.freexiaoyu.cloud.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private final static int RESULT_CODE_PERMISSIONS = 0x001;
    private final static int RESULT_CODE_CAMERA = 0x002;
    private PermissionsChecker mPermissionsChecker; // 权限检测器
    //打开音频 相机 相册 定位
    String[] perms = {"android.permission.RECORD_AUDIO"
            , "android.permission.CAMERA"
            , "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_FINE_LOCATION"};
    @BindView(R.id.btn_submit)
    Button btn_submit;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(perms)) {
            ActivityCompat.requestPermissions(MainActivity.this, perms, RESULT_CODE_PERMISSIONS);
        }
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPermissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void initListener() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Camera.startCamera(mContext, RESULT_CODE_CAMERA);
                String par="mobile=123456password=123123a";
                String token= DES3Utils.DESEncryption(par);
                Log.e("aaaaa",token);
                String token2=DES3Utils.DESDecryption(token);
                Log.e("BBBB",token2);
            }
        });
    }

    @Override
    protected void messageHandler(MessageEvent event) {

    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case RESULT_CODE_PERMISSIONS:
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!cameraAccepted) {
                    ToastUtil.show(mContext, "缺少主要权限, 无法运行");
                    finish();
                }
                break;
        }
    }

}
