package com.compy.check.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.R;
import com.compy.check.base.BaseActivity;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.CanShuBean;
import com.compy.check.bean.IFSListBean;
import com.compy.check.bean.IPBean;
import com.compy.check.bean.RechargBean;
import com.compy.check.bean.WalletMoneyBean;
import com.compy.check.net.BaseObserver;
import com.compy.check.utlis.CommonUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class WithdrawalActivity extends BaseActivity {
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtmy)
    TextView txtmy;

    @BindView(R.id.txttotle)
    TextView txttotle;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.edtamount)
    EditText edtamount;
    @BindView(R.id.btnall)
    Button btnall;
    @BindView(R.id.btnrechage)
    net.qiujuer.genius.ui.widget.Button btnrechage;
    @BindView(R.id.spinner_kinds)
    Spinner spinnerKinds;
    @BindView(R.id.edtcard)
    EditText edtcard;
    @BindView(R.id.edtname)
    EditText edtname;
    private BaseQuickAdapter<RechargBean, BaseViewHolder> mOneAdapter;
    String ip = "", tongdao = "", amount = "500";
    IFSListBean ifsListBean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_withawal;
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

        txttitle.setText("Withdrawal");

        ShowLoading();
        getUserMoney();
        initSpinner();
        getCanshu();
    }

    private void initSpinner() {
        ArrayList<String> strs = new ArrayList<>();
        RxHttp.get("http://pay.zxm88.net/v1/WebPayToPay/bankcode/list")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        if (baseBean.getHead().getCode() == 1) {
                            ifsListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), IFSListBean.class);

                            for (int i = 0; i < ifsListBean.getBody().getData().size(); i++) {
                                strs.add(ifsListBean.getBody().getData().get(i).getName() );
                            }
                            ArrayAdapter<String> strAdapter = new ArrayAdapter<>(WithdrawalActivity.this, android.R.layout.simple_list_item_1);
                            spinnerKinds.setAdapter(strAdapter);
                            strAdapter.addAll(strs);
                            strAdapter.notifyDataSetChanged();

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

    public void getUserMoney() {//读取列表
        RxHttp.get("/v1/wallets/" + SPUtils.getInstance().getString("id", "0") + "/balances")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        if (baseBean.getHead().getCode() == 1) {
                            WalletMoneyBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), WalletMoneyBean.class);

                            txttotle.setText(homeListBean.getBody().getData().getBalance() + "");

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

    public void getCanshu() {//读取平台参数
        RxHttp.get("/v1/admin/platform/parameters")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        if (baseBean.getHead().getCode() == 1) {
                            CanShuBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), CanShuBean.class);

                            tongdao = homeListBean.getBody().getData().getPayChannel();
                            if (tongdao.equals("911")) {
                                //linname.setVisibility(View.VISIBLE);
                                btnrechage.setEnabled(true);
                            }
                            getIP();

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

    public void getIP() {//客户端IP地址
        RxHttp.get("/v1/common/ip")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            IPBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), IPBean.class);

                            ip = homeListBean.getBody().getData();

                            btnrechage.setEnabled(true);

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
    public void postMoney() {//发起体现
        ShowLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        String json = createJsonString(map, "walletId", SPUtils.getInstance().getString("id", "0"));
        json = createJsonString(map, "amount", edtamount.getText().toString().trim());
        json = createJsonString(map, "clientIp", ip);
        json = createJsonString(map, "clientSn", CommonUtils.getDeviceID());
        json = createJsonString(map, "account", edtcard.getText().toString().trim());
        for (int i = 0; i < ifsListBean.getBody().getData().size(); i++) {
            if ( ifsListBean.getBody().getData().get(i).getName().equals(spinnerKinds.getSelectedItem())){
                json = createJsonString(map, "accountIFSC", ifsListBean.getBody().getData().get(i).getCode());
            }
        }

        json = createJsonString(map, "name", edtname.getText().toString().trim());
        System.out.println("json----"+json);
        RxHttp.postJson("http://pay.zxm88.net/v1/WebPayToPay/applyWithdraw")
                .setJsonParams(json)
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                          //  IPBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), IPBean.class);

                            Goto(WithdrawalRecordActivity.class);

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.imgback, R.id.btnall, R.id.btnrechage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgback:
                finish();
                break;
            case R.id.btnall:
                edtamount.setText(txttotle.getText().toString().replace("₦", ""));
                break;
            case R.id.btnrechage:
                if (edtname.getText().toString().length()==0||edtamount.getText().toString().length()==0||edtcard.getText().toString().length()==0
                ){
                    ToastUtils.showShort("Please input info");
                    return;
                }
                if (Double.parseDouble(edtamount.getText().toString().trim())<200){
                    ToastUtils.showShort("Withdrawal 200 at least");
                    return;
                }
                if (Double.parseDouble(edtamount.getText().toString().trim())>100000){
                    ToastUtils.showLong("One withdrawal cannot exceed 100000, please divide it into multiple withdrawals");
                    return;
                }
                postMoney();
                break;
        }
    }
}
