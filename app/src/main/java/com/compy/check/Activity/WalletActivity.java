package com.compy.check.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.compy.check.bean.HomeListBean;
import com.compy.check.bean.RechargBean;
import com.compy.check.bean.WalletListBean;
import com.compy.check.bean.WalletMoneyBean;
import com.compy.check.net.BaseObserver;
import com.gyf.immersionbar.ImmersionBar;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class WalletActivity extends BaseActivity {

    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txttotle)
    TextView txttotle;
    @BindView(R.id.imgrechagre)
    ImageView imgrechagre;
    @BindView(R.id.imgwith)
    ImageView imgwith;
    @BindView(R.id.relwith)
    RelativeLayout relwith;
    @BindView(R.id.rec)
    RecyclerView rec;
    private BaseQuickAdapter<WalletListBean.BodyBean.DataBean, BaseViewHolder> mOneAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(toolbar) //可以为任意view，如果是自定义xml实现标题栏的话，标题栏根节点不能为RelativeLayout或者ConstraintLayout，以及其子类
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserMoney();
    }

    @Override
    public void initView() {

        txttitle.setText("Wallet");
        initAdapter();
        getData();
    }

    public void initAdapter() {


        rec.setLayoutManager(new LinearLayoutManager(WalletActivity.this));
       // rec.addItemDecoration(new SpacesItemNoHeader(20));
        mOneAdapter = new BaseQuickAdapter<WalletListBean.BodyBean.DataBean, BaseViewHolder>(R.layout.item_walletcoderr) {
            @Override
            protected void convert(BaseViewHolder helper, WalletListBean.BodyBean.DataBean item) {


                TextView txtname = helper.itemView.findViewById(R.id.txttime);
                TextView txtamount = helper.itemView.findViewById(R.id.txtamount);

                txtname.setText(item.getSummary());

                switch (item.getSide()) {
                    case "+":
                        txtamount.setText("+" + item.getAmount() + "");

                        txtamount.setTextColor(getColor(R.color.colorjia));
                        break;
                    case "-":
                        txtamount.setText("-" + item.getAmount() + "");
                        txtamount.setTextColor(getColor(R.color.colorjian));

                        break;

                }

            }
        };
        rec.setAdapter(mOneAdapter);

        View inflate = LayoutInflater.from(WalletActivity.this).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.imgback, R.id.imgrechagre, R.id.imgwith})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgback:
                finish();
                break;
            case R.id.imgrechagre:
                Goto(RechargeActivity.class);
                break;
            case R.id.imgwith:
                Goto(WithdrawalActivity.class);
                break;
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

    public void getData() {//读取列表
        RxHttp.get("/v1/wallets/" + SPUtils.getInstance().getString("id", "0") + "/balance-journals?offset=0&limit=20")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            WalletListBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), WalletListBean.class);

                            mOneAdapter.addData(homeListBean.getBody().getData());

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
