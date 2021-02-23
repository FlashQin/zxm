package com.compy.check.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemWithHeader;
import com.compy.check.base.BaseActivity;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.DanInfoBean;
import com.compy.check.bean.OderRecoderBean;
import com.compy.check.bean.UserMoneyDataBean;
import com.compy.check.net.BaseObserver;
import com.compy.check.utlis.UtilTool;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.rxjava.rxlife.RxLife;

import net.qiujuer.genius.ui.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class OrdergrabbingActivity extends BaseActivity {
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.txtmenberlevl)
    TextView txtmenberlevl;
    @BindView(R.id.txtorname)
    TextView txtorname;
    @BindView(R.id.btnrechage)
    Button btnrechage;
    @BindView(R.id.btnganble)
    Button btnganble;
    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txtassets)
    TextView txtassets;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.txttodayorder)
    TextView txttodayorder;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.txtYesterdayearnings)
    TextView txtYesterdayearnings;
    @BindView(R.id.txt4)
    TextView txt4;
    @BindView(R.id.txtTodayearnings)
    TextView txtTodayearnings;
    @BindView(R.id.txt5)
    TextView txt5;
    @BindView(R.id.txtYesteamears)
    TextView txtYesteamears;
    @BindView(R.id.txt6)
    TextView txt6;
    @BindView(R.id.txtTeambenftits)
    TextView txtTeambenftits;
    @BindView(R.id.txtbaifen)
    TextView txtbaifen;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.txtsummary)
    TextView txtsummary;
    private DialogPlus dialog_spec, dialog_data, dialog_fail, dialog_newguy, dialog_getout;
    int counter = 0, userLv = 0;
    private BaseQuickAdapter<OderRecoderBean.BodyBean.DataBean, BaseViewHolder> mOneAdapter;
    boolean isOk = false;
    String lv = "0";
    String resgerData = "";
    private boolean bitcoinMember = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_galbing;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(toolbar) //可以为任意view，如果是自定义xml实现标题栏的话，标题栏根节点不能为RelativeLayout或者ConstraintLayout，以及其子类
                .statusBarDarkFont(true)
                .init();
    }

    public void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrdergrabbingActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linearLayoutManager);
        rec.addItemDecoration(new SpacesItemWithHeader(20));
        mOneAdapter = new BaseQuickAdapter<OderRecoderBean.BodyBean.DataBean, BaseViewHolder>(R.layout.item_oderlist) {
            @Override
            protected void convert(BaseViewHolder helper, OderRecoderBean.BodyBean.DataBean item) {
                TextView txttime = helper.itemView.findViewById(R.id.txttime);
                TextView txtamount = helper.itemView.findViewById(R.id.txtamount);
                TextView txtbaifen = helper.itemView.findViewById(R.id.txtbaifen);
                TextView txttype = helper.itemView.findViewById(R.id.txttype);

                txttime.setText("Lv " + item.getLevel() + " ");
                txtamount.setText(item.getTradeAmount() + " ");

                txtbaifen.setText(item.getTradeIncome() + "");
                txttype.setText("Sucess");
                // String time = UtilTool.gettimenowNumber();
                if (String.valueOf(item.getId()).length() > 10) {
                    txttime.setText(String.valueOf(item.getId()).substring(0, 10));
                } else {
                    txttime.setText(String.valueOf(item.getId()));
                }


            }
        };
        rec.setAdapter(mOneAdapter);
        View inflate = LayoutInflater.from(OrdergrabbingActivity.this).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);
    }

    public void getMenberInfoData() {//读取个人概要数据:
        RxHttp.get("/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/info")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        if (baseBean.getHead().getCode() == 1) {
                            UserMoneyDataBean userMoneyDataBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), UserMoneyDataBean.class);

                            userLv = userMoneyDataBean.getBody().getData().getLevel();
                            txtassets.setText(userMoneyDataBean.getBody().getData().getBalance() + "");
                            txtTodayearnings.setText(userMoneyDataBean.getBody().getData().getTodayEarnings() + "");

                            txtYesterdayearnings.setText(userMoneyDataBean.getBody().getData().getYesterdayEarnings() + "");
                            txtTodayearnings.setText(userMoneyDataBean.getBody().getData().getTodayEarnings() + "");

                            txtYesteamears.setText(userMoneyDataBean.getBody().getData().getYesterdayShareAmount() + "");
                            txtTeambenftits.setText(userMoneyDataBean.getBody().getData().getTodayShareAmount() + "");


                            resgerData = userMoneyDataBean.getBody().getData().getRegisterDate();
                            bitcoinMember = userMoneyDataBean.getBody().getData().getBitcoinMember();
                            try {


                            } catch (NullPointerException e) {

                            }

                        } else

                            ToastUtils.showShort(baseBean.getHead().getMessage());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }

    public void getNoticeList() {//读取列表
        RxHttp.get("/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/orders?offset=0&limit=40")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        String content = "";
                        if (baseBean.getHead().getMessage().contains("There is no data")) {
                            btnganble.setEnabled(true);
                        }
                        if (baseBean.getHead().getCode() == 1) {
                            OderRecoderBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), OderRecoderBean.class);

                            mOneAdapter.setNewData(homeListBean.getBody().getData());
                            txttodayorder.setText(homeListBean.getBody().getData().size() + "");//可用余额
                            if (homeListBean.getBody().getData().size() >= 20) {
                                btnganble.setEnabled(false);
                            } else {
                                btnganble.setEnabled(true);
                            }
                        }

                        //  ToastUtils.showShort(baseBean.getHead().getMessage());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMenberInfoData();
    }

    @Override
    public void initView() {

        getList();
        initAdapter();
        txttitle.setText("Order grabbing");
        String info = getIntent().getStringExtra("info");
        String[] data = info.split("/");

        lv = data[0];
        txtsummary.setText(data[1]);
        txtbaifen.setText("Commission: " + data[2] + "% of the product price");
        txtmenberlevl.setText("Member   " + data[3]);
        txtorname.setText(data[4]);
        switch (data[0]) {
            case "0":
                img1.setBackgroundResource(R.drawable.benefit);
                break;
            case "1":
                img1.setBackgroundResource(R.drawable.lv1);
                break;
            case "2":
                img1.setBackgroundResource(R.drawable.lv2);
                break;
            case "3":
                img1.setBackgroundResource(R.drawable.lv3);
                break;
            case "4":
                img1.setBackgroundResource(R.drawable.lv4);
                break;
            case "5":
                img1.setBackgroundResource(R.drawable.lv5);
                break;
            case "6":
                img1.setBackgroundResource(R.drawable.lv6);
                break;
        }
        getNoticeList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imgback, R.id.btnrechage, R.id.btnganble})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgback:
                finish();
                break;
            case R.id.btnrechage:
                Goto(RechargeActivity.class);
                break;
            case R.id.btnganble:
                // ToastUtils.showLong(lv + resgerData);
                if (bitcoinMember == false) {

                    initFail("Please use Paxful Pay to upgrade first, then continue with the task. Normal withdrawal is available. Your funds are safe, Don't worry.");
                    return;
                }
                if (lv.equals("0") && UtilTool.getTimeRight(resgerData, UtilTool.gettimenow()) == false) {
                    //不用管用户等级，只要是0等级的商品，只有当天能刷
                    initNoticeGetout();//
                    return;
                }

                //这里是低等级不能刷高等级商品
                if (userLv == (Integer.parseInt(lv))) {
                    postXiadan();
                    initSpecDialog();
                } else {
                    initFail("");
                }
                break;
        }
    }

    public void postXiadan() {//读取列表
        RxHttp.postForm("/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/orders/" + lv)
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        //  HideLoading();
                        if (baseBean.getHead().getCode() == 1) {

                            isOk = true;

                        } else

                            ToastUtils.showShort(baseBean.getHead().getMessage());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        //   HideLoading();
                    }
                });
    }

    public void getInfo() {//读取列表
        RxHttp.get("v1/members/" + SPUtils.getInstance().getString("id", "0") + "/latest-order")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        //  HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            getMenberInfoData();
                            getNoticeList();
                            DanInfoBean danInfoBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), DanInfoBean.class);

                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    initSpecDialogPost(danInfoBean);
                                }
                            }, 500);


                        } else

                            ToastUtils.showShort(baseBean.getHead().getMessage());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        //   HideLoading();
                    }
                });
    }

    private void initSpecDialog() {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_searhganbling);
        dialog_spec = DialogPlus.newDialog(OrdergrabbingActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(false)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();

        NumberProgressBar numberProgressBar = viewHolder.getInflatedView().findViewById(R.id.numberbar3);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        numberProgressBar.setProgress(counter);
                        counter++;

                        if (counter >= 49) {
                            timer.cancel();
                            dialog_spec.dismiss();
                            counter = 0;
                            if (isOk == true) {
                                isOk = false;
                                getInfo();
                            }
                        }
                    }
                });

            }
        }, 100, 100);


        dialog_spec.show();
    }

    private void initSpecDialogPost(DanInfoBean danInfoBean) {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_odergangblingone);
        dialog_data = DialogPlus.newDialog(OrdergrabbingActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(false)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();

        TextView txtall = viewHolder.getInflatedView().findViewById(R.id.txtrorle);
        TextView txtreturn = viewHolder.getInflatedView().findViewById(R.id.txtreturn);
        TextView txtodernum = viewHolder.getInflatedView().findViewById(R.id.txtodernum);
        TextView txttime = viewHolder.getInflatedView().findViewById(R.id.txttime);
        TextView txtfan = viewHolder.getInflatedView().findViewById(R.id.txtfan);
        Button btnok = viewHolder.getInflatedView().findViewById(R.id.btnok);
        // String time = UtilTool.gettimenowNumber();
        txtall.setText(danInfoBean.getBody().getData().getTradeAmount() + "");
        txtreturn.setText(danInfoBean.getBody().getData().getTradeIncome() + "");
        if (String.valueOf(danInfoBean.getBody().getData().getId()).length() > 10) {
            txtodernum.setText(String.valueOf(danInfoBean.getBody().getData().getId()).substring(0, 10));
        } else {
            txtodernum.setText(String.valueOf(danInfoBean.getBody().getData().getId()));
        }


        txttime.setText(danInfoBean.getBody().getData().getBaseDate() + "");
        txtfan.setText("Commission: " + (roundByScale(danInfoBean.getBody().getData().getIncomeRate() * 100, 2)) + "% of the product price");
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_data.dismiss();
            }
        });
        dialog_data.show();
    }

    private void initFail(String content) {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_fail);
        dialog_fail = DialogPlus.newDialog(OrdergrabbingActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();

        Button btnok = viewHolder.getInflatedView().findViewById(R.id.btnok);
        TextView txtcontent = viewHolder.getInflatedView().findViewById(R.id.txtfan);
        if (content.length() > 0) {
            txtcontent.setText(content);
        }
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_fail.dismiss();
                Goto(RechargeActivity.class);
            }
        });

        dialog_fail.show();
    }

    private void initNoticeNewguy() {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_newguy);
        dialog_newguy = DialogPlus.newDialog(OrdergrabbingActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();

        Button btnok = viewHolder.getInflatedView().findViewById(R.id.btnok);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_newguy.dismiss();
                Goto(RechargeActivity.class);
            }
        });

        dialog_newguy.show();
    }

    private void initNoticeGetout() {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_newguy);
        dialog_getout = DialogPlus.newDialog(OrdergrabbingActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();

        Button btnok = viewHolder.getInflatedView().findViewById(R.id.btnok);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_getout.dismiss();
                Goto(RechargeActivity.class);
            }
        });

        dialog_getout.show();
    }

    public void getList() {//读取列表
        RxHttp.get("/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/order-journals?offset=" + 0 + "&limit=20")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        String content = "";

                        if (baseBean.getHead().getMessage().contains("There is no data")) {
                            if (resgerData.equals(UtilTool.gettimeYear())) {
                                initNoticeNewguy();
                            }
                        }


                        //  ToastUtils.showShort(baseBean.getHead().getMessage());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }
}
