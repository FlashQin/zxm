package com.compy.check.Activity;


import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.compy.check.MainActivity;
import com.compy.check.R;
import com.compy.check.base.BaseActivity;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.ResigerBean;
import com.compy.check.net.BaseObserver;
import com.compy.check.utlis.ACache;
import com.compy.check.utlis.CommonUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.rxjava.rxlife.RxLife;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;


public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.imglook)
    ImageView imglook;
    @BindView(R.id.tvresiger)
    TextView tvresiger;

    boolean islook=false;
    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(toolbar) //可以为任意view，如果是自定义xml实现标题栏的话，标题栏根节点不能为RelativeLayout或者ConstraintLayout，以及其子类
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imglook, R.id.btn_login, R.id.tvresiger, R.id.tv_forget_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imglook:
                if (islook==false){
                    islook=true;
                    imglook.setBackgroundResource(R.drawable.mima_2);
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    islook=false;
                    imglook.setBackgroundResource(R.drawable.mima_1);
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.btn_login:
                if (etPhone.getText().toString().length() == 0) {
                    ToastUtils.showShort("Please input your phoneNumber");
                    return;
                }
                if (etPassword.getText().toString().length() == 0) {
                    ToastUtils.showShort("Please input your password");
                    return;
                }
                ShowLoading();
                Map<String, Object> map = new HashMap<String, Object>();
                String json = createJsonString(map, "password", etPassword.getText().toString().trim());
                json = createJsonString(map, "mobile", etPhone.getText().toString().trim());
                json = createJsonString(map, "version", CommonUtils.getVersionName(LoginActivity.this));
                System.out.println("----------" + json);
                RxHttp.postJson("/v1/members/sign-in")
                        .setJsonParams(json)
                        .asObject(BaseBean.class)
                        .subscribeOn(Schedulers.io())
                        .as(RxLife.asOnMain(this))
                        .subscribe(new BaseObserver<BaseBean>() {
                            @Override
                            public void onNext(BaseBean baseBean) {
                                HideLoading();
                                if (baseBean.getHead().getCode() == 1) {
                                    ResigerBean resigerBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), ResigerBean.class);
                                    SPUtils.getInstance().put("TOKEN", resigerBean.getBody().getData().getToken());
                                    SPUtils.getInstance().put("id", resigerBean.getBody().getData().getId() + "");
                                    ACache.get(Utils.getApp()).put("USER_BEAN", GsonUtils.toJson(resigerBean));
                                    finish();
                                    Goto(MainActivity.class);


                                } else


                                    ToastUtils.showShort(baseBean.getHead().getMessage());
                            }


                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                HideLoading();
                            }
                        });
                break;
            case R.id.tvresiger:
                Goto(ResigerActivity.class);
                break;
            case R.id.tv_forget_password:
                Goto(ForgetFundPassActivity.class);
                break;
        }
    }
}
