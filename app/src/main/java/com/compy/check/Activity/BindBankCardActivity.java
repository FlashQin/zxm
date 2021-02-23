package com.compy.check.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.compy.check.R;
import com.compy.check.base.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

import net.qiujuer.genius.ui.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindBankCardActivity extends BaseActivity {
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtname)
    TextView txtname;
    @BindView(R.id.txtphone)
    TextView txtphone;
    @BindView(R.id.txtbank)
    TextView txtbank;
    @BindView(R.id.txtifsc)
    TextView txtifsc;
    @BindView(R.id.btnmodiftinfo)
    Button btnmodiftinfo;
    @BindView(R.id.txtpassword)
    TextView txtpassword;
    @BindView(R.id.btnchangpass)
    Button btnchangpass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bandbackcard;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(toolbar) //可以为任意view，如果是自定义xml实现标题栏的话，标题栏根节点不能为RelativeLayout或者ConstraintLayout，以及其子类
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    public void initView() {

        txttitle.setText("Bind Bank Card");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    @OnClick({R.id.imgback, R.id.btnmodiftinfo, R.id.btnchangpass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgback:
                finish();
                break;
            case R.id.btnmodiftinfo:
                Goto(FillInfoActivity.class);
                break;
            case R.id.btnchangpass:
                Goto(ForgetFundPassActivity.class);

                break;
        }
    }
}
