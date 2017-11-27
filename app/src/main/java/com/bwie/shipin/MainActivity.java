package com.bwie.shipin;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

public class MainActivity extends AppCompatActivity {
    private PlayerView play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 沉浸式
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.top_bg_color);//通知栏所需颜色
        }

        setContentView(R.layout.activity_main);
//        ImmersiveStatusbar.getInstance().Immersive(getWindow(), getActionBar());
//        ActionBar supportActionBar = getSupportActionBar();
//        if(supportActionBar!=null){
//        }

        //初始化数据
        initData();
    }
    /**
     * 获取数据
     */
    private void initData() {
        String url ="http://2449.vod.myqcloud.com/2449_bfbbfa3cea8f11e5aac3db03cda99974.f20.mp4";
        play = new PlayerView(this)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(url);
        play.startPlay();
    }
    //当你离开播放界面的时候视频停止播放
    @Override
    protected void onStop() {
        super.onStop();
        play.stopPlay();
    }
    /**
     * 沉浸式
     * @param on
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
