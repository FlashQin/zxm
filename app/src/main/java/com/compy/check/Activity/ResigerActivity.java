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
import com.compy.check.utlis.CommonTimer;
import com.compy.check.utlis.CommonUtils;
import com.compy.check.utlis.UtilTool;
import com.gyf.immersionbar.ImmersionBar;
import com.rxjava.rxlife.RxLife;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;


public class ResigerActivity extends BaseActivity {


    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.etusername)
    EditText etusername;
    @BindView(R.id.edtsms)
    EditText edtsms;
    @BindView(R.id.btncode)
    Button btncode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.imglook)
    ImageView imglook;
    @BindView(R.id.edtrepass)
    EditText edtrepass;
    @BindView(R.id.edsharetcode)
    EditText edsharetcode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    boolean islook = false;

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(toolbar) //可以为任意view，如果是自定义xml实现标题栏的话，标题栏根节点不能为RelativeLayout或者ConstraintLayout，以及其子类
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_newresiger;
    }

    @Override
    public void initView() {
        txttitle.setText("Register");
        String time= SPUtils.getInstance().getString("reopt", "0");
        long sen=UtilTool.getTimeMISO(time,UtilTool.gettimenow());
        if (sen>0){
            new CommonTimer(sen * 1000 + 200, 1000, btncode).start();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imgback, R.id.btncode, R.id.imglook, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgback:
                finish();
                break;
            case R.id.btncode:
                if (etPhone.getText().toString().trim().length() < 8) {
                    ToastUtils.showShort("please enter right phone number");
                    return;
                }
                ShowLoading();
                // Map<String, Object> map = new HashMap<String, Object>();
                // String json = createJsonString(map, "mobile", etPhone.getText().toString().trim());
                //System.out.println("----------" + json);
                RxHttp.postForm("/v1/mobiles/" + etPhone.getText().toString().trim() + "/auth-code")
                        // .setJsonParams(json)
                        .asObject(BaseBean.class)
                        .subscribeOn(Schedulers.io())
                        .as(RxLife.asOnMain(this))
                        .subscribe(new BaseObserver<BaseBean>() {
                            @Override
                            public void onNext(BaseBean baseBean) {
                                HideLoading();
                                if (baseBean.getHead().getCode() == 1) {
                                    new CommonTimer(150 * 1000 + 200, 1000, btncode).start();
                                    ToastUtils.showShort("Send sucess");
                                    SPUtils.getInstance().put("reopt", UtilTool.gettimenow());

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
            case R.id.imglook:
                if (islook == false) {
                    islook = true;
                    imglook.setBackgroundResource(R.drawable.mima_2);
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    islook = false;
                    imglook.setBackgroundResource(R.drawable.mima_1);
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.btn_login:
                if (etPhone.getText().toString().length() == 0) {
                    ToastUtils.showShort("please input your phone number");
                    return;
                }
                if (etPassword.getText().toString().length() == 0) {
                    ToastUtils.showShort("please input your password");
                    return;
                }
                if (etusername.getText().toString().length() == 0) {
                    ToastUtils.showShort("please input your username");
                    return;
                }
                if (edsharetcode.getText().toString().length() == 0) {
                    ToastUtils.showShort("please input your shareCode");
                    return;
                }
                if (!(etPassword.getText().toString().equals(edtrepass.getText().toString()))) {
                    ToastUtils.showShort("please check your password");
                    return;
                }
                if (edtsms.getText().toString().length() == 0) {
                    ToastUtils.showShort("please input your sms code");
                    return;
                }
                ShowLoading();
               // postResiger();
               checkCode();

                break;
        }
    }

    public void postResiger() {


        Map<String, Object> map = new HashMap<String, Object>();
        String json = createJsonString(map, "name", etusername.getText().toString().trim());
        json = createJsonString(map, "password", etPassword.getText().toString().trim());
        json = createJsonString(map, "mobile", etPhone.getText().toString().trim());
        json = createJsonString(map, "shareCode", edsharetcode.getText().toString().trim());
        json = createJsonString(map, "version", CommonUtils.getVersionName(ResigerActivity.this));
        System.out.println("----------" + json);

        RxHttp.postJson("/v1/members/sign-up")
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
                            ACache.get(Utils.getApp()).put("USER_BEAN", GsonUtils.toJson(resigerBean));
                            SPUtils.getInstance().put("id", resigerBean.getBody().getData().getId() + "");
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

    }

    public void checkCode() {
        RxHttp.get("/v1/mobiles/" + etPhone.getText().toString().trim() + "/auth-code/" + edtsms.getText().toString().trim())
                // .setJsonParams(json)
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        // HideLoading();
                        if (baseBean.getHead().getCode() == 1) {

                            postResiger();

                        } else {
                            HideLoading();
                            ToastUtils.showShort(baseBean.getHead().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        HideLoading();
                    }
                });
    }
}
