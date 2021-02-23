package com.compy.check.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemNoHeader;
import com.compy.check.base.BaseActivity;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.CanShuBean;
import com.compy.check.bean.IPBean;
import com.compy.check.bean.NewPayBean;
import com.compy.check.bean.PayLinkeBean;
import com.compy.check.bean.RechargBean;
import com.compy.check.bean.WalletMoneyBean;
import com.compy.check.net.BaseObserver;
import com.compy.check.utlis.CommonUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class RechargeActivity extends BaseActivity {
    @BindView(R.id.txtmy)
    TextView txtmy;

    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.txtv)
    TextView txtv;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.btnrechage)
    Button btnrechage;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttotle)
    TextView txttotle;
    @BindView(R.id.edtname)
    EditText edtname;
    @BindView(R.id.linname)
    LinearLayout linname;
    @BindView(R.id.edtemail)
    EditText edtemail;
    @BindView(R.id.linemail)
    LinearLayout linemail;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.imgmy)
    TextView imgmy;
    @BindView(R.id.edtamount)
    EditText edtamount;
    @BindView(R.id.btnall)
    Button btnall;
    @BindView(R.id.btnrechage910)
    Button btnrechage910;
    @BindView(R.id.btnrechagenew)
    Button btnrechagenew;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.txttab1)
    TextView txttab1;
    @BindView(R.id.txttab2)
    TextView txttab2;
    @BindView(R.id.lintab)
    LinearLayout lintab;
    @BindView(R.id.btncreat)
    net.qiujuer.genius.ui.widget.Button btncreat;
    @BindView(R.id.lintab1)
    LinearLayout lintab1;
    @BindView(R.id.lintab2)
    LinearLayout lintab2;
    private BaseQuickAdapter<RechargBean, BaseViewHolder> mOneAdapter;
    List<RechargBean> rechargBeanList = new ArrayList<>();
    String[] strName = {"5000", "20000", "100000", "150000", "200000", "250000"};

    String ip = "", tongdao = "910", amount = "500";

    @Override
    public int getLayoutId() {

        return R.layout.activity_recharge;
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

        txttitle.setText("Recharge");
        String money = getIntent().getStringExtra("money") == null ? "" : getIntent().getStringExtra("money");
        if (money.length() > 0) {
            edtamount.setText(money);
        } else {
            edtamount.setText(strName[0]);
        }
        initAdapter();
        ShowLoading();
        //getCanshu();
       getIP();
        edtamount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                rechargBeanList.clear();
                for (int j = 0; j < strName.length; j++) {
                    RechargBean bean = new RechargBean();
                    bean.setIscheck(false);
                    bean.setName(strName[j]);
                    rechargBeanList.add(bean);
                }
                mOneAdapter.setNewData(rechargBeanList);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (edtamount.getText().toString().length() == 0) {
                    //edtamount.setText("5000");
                }
            }
        });
    }

    public void initAdapter() {
        for (int i = 0; i < strName.length; i++) {
            RechargBean bean = new RechargBean();
            if (i == 0) {
                bean.setIscheck(true);

                bean.setName(strName[i]);
            } else {
                bean.setIscheck(false);
                bean.setName(strName[i]);
            }
            rechargBeanList.add(bean);

        }

        rec.setLayoutManager(new GridLayoutManager(RechargeActivity.this, 2));
        rec.addItemDecoration(new SpacesItemNoHeader(20));
        mOneAdapter = new BaseQuickAdapter<RechargBean, BaseViewHolder>(R.layout.item_recharge, rechargBeanList) {
            @Override
            protected void convert(BaseViewHolder helper, RechargBean item) {

                TextView txtname = helper.itemView.findViewById(R.id.txtname);
                txtname.setText(item.getName() + " ₦");
                if (item.isIscheck() == true) {
                    amount = item.getName();
                    txtname.setBackgroundResource(R.drawable.shape_nobian_greenbac);
                    txtname.setSelected(true);
                } else {
                    txtname.setSelected(false);
                    txtname.setBackgroundResource(R.drawable.shape_whitebac_blackbian);
                }
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtamount.setText(item.getName());
                        if (item.isIscheck() == false) {
                            for (int i = 0; i < rechargBeanList.size(); i++) {
                                if (rechargBeanList.get(i).getName().equals(item.getName())) {
                                    rechargBeanList.get(i).setIscheck(true);
                                } else {
                                    rechargBeanList.get(i).setIscheck(false);
                                }
                            }
                            mOneAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        };
        rec.setAdapter(mOneAdapter);

        View inflate = LayoutInflater.from(RechargeActivity.this).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getUserMoney();
    }

    @OnClick({R.id.imgback, R.id.btnrechage, R.id.btnrechage910, R.id.btnrechagenew, R.id.txttab1, R.id.txttab2, R.id.btncreat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgback:
                finish();
                break;
            case R.id.btnrechage:
                tongdao = "911";
                if (edtname.getText().toString().length() == 0) {

                    ToastUtils.showShort("please input your name");
                    return;
                }
                if (edtemail.getText().toString().length() == 0) {
                    ToastUtils.showShort("please input your email");
                    return;
                }
                if (!edtemail.getText().toString().contains("@")) {
                    ToastUtils.showShort("please input right email");
                    return;
                }
                if (edtamount.getText().toString().trim().length() != 0) {
                    double money = Double.parseDouble(edtamount.getText().toString().trim());
                    if (money < 200) {

                        ToastUtils.showShort("Recharg 200 at least");
                        return;
                    }
                    if (money > 200000) {

                        ToastUtils.showShort("Recharg 200000 most");
                        return;
                    }
                }
                getPayLink();
                break;
            case R.id.btnrechage910:
                tongdao = "910";
                if (edtname.getText().toString().length() == 0) {

                    ToastUtils.showShort("please input your name");
                    return;
                }
                if (edtemail.getText().toString().length() == 0) {
                    ToastUtils.showShort("please input your email");
                    return;
                }

                if (!edtemail.getText().toString().contains("@")) {
                    ToastUtils.showShort("please input right email");
                    return;
                }
                if (edtamount.getText().toString().trim().length() != 0) {
                    double money = Double.parseDouble(edtamount.getText().toString().trim());
                    if (money < 200) {

                        ToastUtils.showShort("Recharg 200 at least");
                        return;
                    }
                    if (money > 200000) {

                        ToastUtils.showShort("Recharg 200000 most");
                        return;
                    }
                }
                getPayLink();
                break;
            case R.id.btnrechagenew:
                if (edtname.getText().toString().length() == 0) {

                    ToastUtils.showShort("please input your name");
                    return;
                }
                if (edtemail.getText().toString().length() == 0) {
                    ToastUtils.showShort("please input your email");
                    return;
                }

                if (!edtemail.getText().toString().contains("@")) {
                    ToastUtils.showShort("please input right email");
                    return;
                }
                if (edtamount.getText().toString().trim().length() != 0) {
                    double money = Double.parseDouble(edtamount.getText().toString().trim());
                    if (money < 200) {

                        ToastUtils.showShort("Recharg 200 at least");
                        return;
                    }
                    if (money > 200000) {

                        ToastUtils.showShort("Recharg 200000 most");
                        return;
                    }
                }
                getNewPayLink();
                break;
            case R.id.txttab1:
                txttab1.setBackgroundResource(R.drawable.drawable_txttab1);
                txttab2.setBackgroundResource(R.drawable.drawable_txttab2);
                txttab1.setTextColor(getResouseColor(R.color.white));
                txttab2.setTextColor(getResouseColor(R.color.black));
                lintab1.setVisibility(View.VISIBLE);
                lintab2.setVisibility(View.GONE);
                break;
            case R.id.txttab2:
                txttab2.setBackgroundResource(R.drawable.drawable_txttab1);
                txttab1.setBackgroundResource(R.drawable.drawable_txttab2);
                txttab2.setTextColor(getResouseColor(R.color.white));
                txttab1.setTextColor(getResouseColor(R.color.black));
                lintab2.setVisibility(View.VISIBLE);
                lintab1.setVisibility(View.GONE);
                break;
            case R.id.btncreat:
                Goto(WebActivity.class, "json", "https://paxful.com/register?locale=en&r=xgQPBARgjYp","name","Create new account");

                break;
        }
    }

    //邮箱验证
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@{gmail}.{com}$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }

    public void getUserMoney() {//读取列表
        RxHttp.get("/v1/wallets/" + SPUtils.getInstance().getString("id", "0") + "/balances")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
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
                            if (tongdao.equals("910")) {
                                linname.setVisibility(View.VISIBLE);
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
                            btnrechage910.setEnabled(true);

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

    public void getPayLink() {//获取这个支付链接
        ShowLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        String json = createJsonString(map, "walletId", SPUtils.getInstance().getString("id", "0"));
        json = createJsonString(map, "amount", amount);
        json = createJsonString(map, "clientIp", ip);
        json = createJsonString(map, "channel", tongdao);
        json = createJsonString(map, "clientSn", CommonUtils.getDeviceID());
        json = createJsonString(map, "email", edtemail.getText().toString().trim());//910，911通道毕传
        // if (tongdao.equals("910")) {
        json = createJsonString(map, "contactName", edtname.getText().toString().trim());//910通道毕传
        // }
        System.out.println("------------" + json);
        RxHttp.postJson("http://pay.zxm88.net/v2/WebPayToPay/preRecharge")
                .setJsonParams(json)
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            PayLinkeBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), PayLinkeBean.class);
                            Map<String, Object> map = new HashMap<String, Object>();
                            String json = createJsonString(map, "amount", homeListBean.getBody().getData().getAmount());
                            json = createJsonString(map, "appId", homeListBean.getBody().getData().getAppId());
                            json = createJsonString(map, "applyDate", homeListBean.getBody().getData().getApplyDate());
                            json = createJsonString(map, "channel", homeListBean.getBody().getData().getChannel());
                            json = createJsonString(map, "clientIp", homeListBean.getBody().getData().getClientIp());
                            json = createJsonString(map, "clientSn", homeListBean.getBody().getData().getClientSn());
                            json = createJsonString(map, "notifyUrl", homeListBean.getBody().getData().getNotifyUrl());
                            json = createJsonString(map, "outOrderNo", homeListBean.getBody().getData().getOutOrderNo());
                            json = createJsonString(map, "sign", homeListBean.getBody().getData().getSign());
                            json = createJsonString(map, "userId", homeListBean.getBody().getData().getUserId());
                            json = createJsonString(map, "email", homeListBean.getBody().getData().getEmail());
                            if (tongdao.equals("910")) {
                                json = createJsonString(map, "contactName", edtname.getText().toString());
                            }
                            //  json = createJsonString(map, "contactName", edtname.getText().toString());
                            json = "http://h5.zxm88.net/pay.html?channel=" + homeListBean.getBody().getData().getChannel() + "&data=" + json;

                            System.out.println("111111111///" + json);
                            Goto(WebActivity.class, "json", json);

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

    public void getNewPayLink() {//获取这个支付链接
        ShowLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        String json = createJsonString(map, "fiatAmount", Double.parseDouble(amount));
        json = createJsonString(map, "email", edtemail.getText().toString().trim());//910，911通道毕传
        json = createJsonString(map, "name", edtname.getText().toString().trim());//910通道毕传
        System.out.println("------------" + json);
        RxHttp.postJson("http://pay.zxm88.net/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/bitcoin/paxful")
                .setJsonParams(json)
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            NewPayBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), NewPayBean.class);
                            Goto(WebActivity.class, "json", homeListBean.getBody().getData().toString());

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
}
