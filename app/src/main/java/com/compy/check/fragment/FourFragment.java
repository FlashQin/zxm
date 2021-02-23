package com.compy.check.fragment;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.Activity.InterestRecordActivity;
import com.compy.check.Activity.ProudtTypeActivity;
import com.compy.check.Activity.RechargeActivity;
import com.compy.check.Activity.WithdrawalActivity;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemWithHeader;
import com.compy.check.base.BaseFragment;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.LiCaiListBean;
import com.compy.check.bean.LicaiSuccBean;
import com.compy.check.bean.UserMoneyDataBean;
import com.compy.check.net.BaseObserver;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.rxjava.rxlife.RxLife;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class FourFragment extends BaseFragment {


    @BindView(R.id.linbar)
    LinearLayout linbar;
    @BindView(R.id.txt10)
    TextView txt10;
    @BindView(R.id.txtlevl)
    TextView txtlevl;
    @BindView(R.id.txt11)
    TextView txt11;
    @BindView(R.id.txttotle)
    TextView txttotle;
    @BindView(R.id.txt12)
    TextView txt12;
    @BindView(R.id.txtcurrency)
    TextView txtcurrency;
    @BindView(R.id.txtyestday)
    TextView txtyestday;
    @BindView(R.id.txt13)
    TextView txt13;
    @BindView(R.id.txtincome)
    TextView txtincome;
    @BindView(R.id.txt14)
    TextView txt14;
    @BindView(R.id.txtToday)
    TextView txtToday;
    @BindView(R.id.txt15)
    TextView txt15;
    @BindView(R.id.container1)
    ConstraintLayout container1;
    @BindView(R.id.ddddd)
    ImageView ddddd;
    @BindView(R.id.marrr)
    MarqueeView marrr;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.txtto)
    TextView txtto;
    @BindView(R.id.txtin)
    TextView txtin;
    @BindView(R.id.txtintesnum)
    TextView txtintesnum;
    @BindView(R.id.txttotl)
    TextView txttotl;
    @BindView(R.id.txttotlenum)
    TextView txttotlenum;
    @BindView(R.id.txtyest)
    TextView txtyest;
    @BindView(R.id.txtyestdaynum)
    TextView txtyestdaynum;
    @BindView(R.id.ty)
    TextView ty;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.btnout)
    Button btnout;
    @BindView(R.id.imgrechagre)
    ImageView imgrechagre;
    @BindView(R.id.imgwith)
    ImageView imgwith;
    @BindView(R.id.relwith)
    RelativeLayout relwith;
    private DialogPlus dialog_spec;
    private BaseQuickAdapter<LiCaiListBean, BaseViewHolder> mOneAdapter;

    double userMoney=0;
    String[] typeString = {"A", "B", "C", "D"};
    String[] dayString = {"2.5", "3.5", "4.5", "5"};
    String[] daynumString = {"7", "30", "90", "180"};
    String[] allString = {"17.5", "105", "405", "900"};
    List<LiCaiListBean> liCaiListBeans = new ArrayList<>();

    @Override
    protected void initView(View view) {
        ImmersionBar.with(this)
                .titleBar(linbar) //可以为任意view，如果是自定义xml实现标题栏的话，标题栏根节点不能为RelativeLayout或者ConstraintLayout，以及其子类
                .statusBarDarkFont(true)
                .init();

        for (int i = 0; i < typeString.length; i++) {
            LiCaiListBean bean = new LiCaiListBean();
            bean.setType(typeString[i]);
            bean.setDay(dayString[i]);
            bean.setDaynum(daynumString[i]);
            bean.setAll(allString[i]);
            liCaiListBeans.add(bean);
        }
        initAdapter();


    }

    @Override
    public void onResume() {
        super.onResume();
        getMenberInfoData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one4_service;
    }

    @Override
    protected void fetchData() {

    }

    public void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linearLayoutManager);
        rec.addItemDecoration(new SpacesItemWithHeader(20));
        mOneAdapter = new BaseQuickAdapter<LiCaiListBean, BaseViewHolder>(R.layout.item_homefoure_list, liCaiListBeans) {
            @Override
            protected void convert(BaseViewHolder helper, LiCaiListBean item) {
                TextView txttype = helper.itemView.findViewById(R.id.txttype);
                TextView txtday = helper.itemView.findViewById(R.id.txtoneday);
                TextView txtdaynum = helper.itemView.findViewById(R.id.txtdaynum);
                TextView txtall = helper.itemView.findViewById(R.id.txtall);
                TextView txtdespoin = helper.itemView.findViewById(R.id.txtdepost);
                txttype.setText("" + item.getType());
                txtday.setText("One day: Interest rate  +" + item.getDay() + "%");
                txtdaynum.setText("[Set]   " + item.getDaynum() + " days");
                txtall.setText("+" + item.getAll() + "%");
                txtdespoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        initSpecDialog(item);
                    }
                });
            }
        };
        rec.setAdapter(mOneAdapter);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);
    }


    private void initSpecDialog(LiCaiListBean item) {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_typeabcd);
        dialog_spec = DialogPlus.newDialog(getActivity())
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(false)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();
        Button btnno = viewHolder.getInflatedView().findViewById(R.id.btnno);
        Button btnyes = viewHolder.getInflatedView().findViewById(R.id.btnyes);
        TextView txttype1 = viewHolder.getInflatedView().findViewById(R.id.txttype);
        TextView txtdata = viewHolder.getInflatedView().findViewById(R.id.txtdata);
        EditText edtinput = viewHolder.getInflatedView().findViewById(R.id.edtinput);
        TextView txtretun = viewHolder.getInflatedView().findViewById(R.id.txtreturn);
        txttype1.setText("Type " + item.getType());
        txtdata.setText("One Day +" + item.getDay() + "%(Set " + item.getDaynum() + " days)");
        txtretun.setText("Expected return: " + item.getAll()+"%");
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_spec.dismiss();
            }
        });
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String money=edtinput.getText().toString();
                if (money.length()==0){
                    money="0";
                }
                if (userMoney<Double.parseDouble(money)){
                    ToastUtils.showShort("you just can buy "+userMoney);
                    return;
                }
                if (Double.parseDouble(money) >= 100000) {
                    dialog_spec.dismiss();
                    buyLicai(item.getDaynum(), edtinput.getText().toString().trim());
                }else {
                    ToastUtils.showShort("You need buy 100000 least");
                }
            }
        });
        dialog_spec.show();
    }




    @OnClick({R.id.imgrechagre, R.id.imgwith, R.id.btnout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgrechagre:
                Goto(RechargeActivity.class);
                break;
            case R.id.imgwith:
                Goto(WithdrawalActivity.class);
                break;
            case R.id.btnout:
                Goto(ProudtTypeActivity.class);
                break;
        }
    }

    public void getMenberInfoData() {//读取个人概要数据:
        RxHttp.get("/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/info")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            UserMoneyDataBean userMoneyDataBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), UserMoneyDataBean.class);

                            try {


                                txt10.setText("Hi " + userMoneyDataBean.getBody().getData().getName());
                                txttotle.setText(userMoneyDataBean.getBody().getData().getBalance() + "");
                                txtcurrency.setText(userMoneyDataBean.getBody().getData().getReward() + "");
                                txtyestday.setText(userMoneyDataBean.getBody().getData().getYesterdayEarnings() + "");
                                txtincome.setText(userMoneyDataBean.getBody().getData().getCumulativeEarnings() + "");
                                txtToday.setText(userMoneyDataBean.getBody().getData().getTodayEarnings() + "");
                                txtlevl.setText("LV" + userMoneyDataBean.getBody().getData().getLevel() + "");
                                userMoney=userMoneyDataBean.getBody().getData().getAvailableBalance();

                            } catch (NullPointerException e) {

                            }

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

    public void buyLicai(String contract, String amount) {//读取个人概要数据:
        ShowLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        String json = createJsonString(map, "contract", contract);
        json = createJsonString(map, "amount", Integer.parseInt(amount));
        System.out.println("json---"+json);
        RxHttp.postJson("/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/finances")
                .setJsonParams(json)
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            LicaiSuccBean userMoneyDataBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), LicaiSuccBean.class);

                            Goto(ProudtTypeActivity.class);
                            ToastUtils.showShort("buy succ");
                            //buy   succ
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
