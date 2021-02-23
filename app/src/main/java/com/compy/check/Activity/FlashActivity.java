package com.compy.check.Activity;

import com.blankj.utilcode.util.SPUtils;
import com.compy.check.MainActivity;
import com.compy.check.R;
import com.compy.check.base.BaseActivity;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class FlashActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_flash;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fullScreen(true)
                .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
        .init();
    }

    @Override
    public void initView() {

        GotoStart();
    }
    private void GotoStart() {
        //查看是否有缓存的token     有：检查过期没    没有：跳转登录界面
        if (SPUtils.getInstance().getString("TOKEN", "").equals("")) {
            Goto(LoginActivity.class, FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_CLEAR_TASK);
            FlashActivity.this.finish();
        } else {
            Goto(MainActivity.class, FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_CLEAR_TASK);
            FlashActivity.this.finish();
        }
    }
}
