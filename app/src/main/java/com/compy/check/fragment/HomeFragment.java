package com.compy.check.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.Activity.OrdergrabbingActivity;
import com.compy.check.Activity.RechargeActivity;
import com.compy.check.Activity.WithdrawalActivity;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemWithHeader;
import com.compy.check.base.BaseFragment;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.HomeListBean;
import com.compy.check.bean.MessageWrap;
import com.compy.check.bean.NoticeListBean;
import com.compy.check.bean.UserMoneyDataBean;
import com.compy.check.net.BaseObserver;
import com.compy.check.utlis.UtilTool;
import com.gyf.immersionbar.ImmersionBar;
import com.marquee.dingrui.marqueeviewlib.MarqueeView;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.txt1num)
    TextView txt1num;
    @BindView(R.id.txt2num)
    TextView txt2num;
    @BindView(R.id.txt3num)
    TextView txt3num;
    int index = 0;
    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.imgshare)
    ImageView imgshare;
    @BindView(R.id.frame)
    FrameLayout frame;
    private BaseQuickAdapter<HomeListBean.BodyBean.DataBean, BaseViewHolder> mOneAdapter;
    private List<String> mItemList = new ArrayList<>();
    private int bannerHeight;
    MarqueeView marqueeView;
    //跑马灯数据
    List<String> messages = new ArrayList<>();
    TextView txtname, txttotle, txtcurreny, txtyesterday, txtincom, txttoday, txtlv;
    String shareCode = "";
    String resgerData = "";

    int[] pic = {R.drawable.benefit, R.drawable.lv1, R.drawable.lv2, R.drawable.lv3, R.drawable.lv4, R.drawable.lv5, R.drawable.lv6};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one_home;
    }


    @Override
    protected void fetchData() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();

        getMenberInfoData();
        getNoticeList();
    }

    @Override
    protected void initView(View view) {
        ShowLoading();
        getDataList();
        //  ImmersionBar.with(this).titleBar(R.id.toolbar).init();
        final LinearLayoutManager[] linearLayoutManager = {new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false)};
        mRv.setLayoutManager(linearLayoutManager[0]);
        mRv.addItemDecoration(new SpacesItemWithHeader(20));
        mOneAdapter = new BaseQuickAdapter<HomeListBean.BodyBean.DataBean, BaseViewHolder>(R.layout.item_one) {
            @Override
            protected void convert(BaseViewHolder helper, HomeListBean.BodyBean.DataBean item) {

                TextView txtlv = helper.itemView.findViewById(R.id.txtlv);
                TextView txtsummary = helper.itemView.findViewById(R.id.txtsummary);
                TextView txtarea = helper.itemView.findViewById(R.id.txtarea);
                TextView txtbaifen = helper.itemView.findViewById(R.id.txtbaifen);
                ImageView img = helper.itemView.findViewById(R.id.img_goods);
                txtsummary.setText(item.getSummary());
                txtlv.setText("Member " + item.getName());
                txtarea.setText(String.valueOf(item.getBalanceLowerLimit()).replace(".0", "") + "₦");
                txtbaifen.setText(roundByScale(item.getIncomeRate() * 100, 2) + "%");
                img.setBackgroundResource(pic[index]);
                index++;
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String info = item.getName().replace("level ", "") + "/" + item.getSummary() + "/" + roundByScale(item.getIncomeRate() * 100, 2) + "/" + item.getName() + "/" + txtarea.getText().toString().trim();
                        Goto(OrdergrabbingActivity.class, "info", info);
                    }
                });
            }
        };
        // mOneAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRv.setAdapter(mOneAdapter);
        addHeaderView();
        addFooterView();


    }

    private void addHeaderView() {

        View headView = LayoutInflater.from(mActivity).inflate(R.layout.item_homeheader, (ViewGroup) mRv.getParent(), false);
        ConstraintLayout constraintLayout = headView.findViewById(R.id.container);
        ImageView imgrechage = headView.findViewById(R.id.imgrechagre);
        ImageView imgwith = headView.findViewById(R.id.imgwith);
        txtname = headView.findViewById(R.id.txt10);
        txttotle = headView.findViewById(R.id.txttotle);
        txtcurreny = headView.findViewById(R.id.txtcurrency);
        txtyesterday = headView.findViewById(R.id.txtyestday);
        txtincom = headView.findViewById(R.id.txtincome);
        txttoday = headView.findViewById(R.id.txtToday);
        txtlv = headView.findViewById(R.id.txtlevl);
        marqueeView = headView.findViewById(R.id.marrr);
        mOneAdapter.addHeaderView(headView);


        // marqueeView.setTextColor(0xffad43ae);
        ViewGroup.LayoutParams bannerParams = constraintLayout.getLayoutParams();
        ViewGroup.LayoutParams titleBarParams = mToolbar.getLayoutParams();
        bannerHeight = bannerParams.height - titleBarParams.height - ImmersionBar.getStatusBarHeight(getActivity());


        imgrechage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Goto(RechargeActivity.class);
            }
        });
        imgwith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Goto(WithdrawalActivity.class);
            }
        });


    }

    private void addFooterView() {


        View footerview = LayoutInflater.from(mActivity).inflate(R.layout.item_footer_home, (ViewGroup) mRv.getParent(), false);
        ConstraintLayout constraintLayout = footerview.findViewById(R.id.container3);

        mOneAdapter.addFooterView(footerview);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageWrap(1, ""));

            }
        });

    }

    @Override
    protected void setListener() {
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy <= bannerHeight) {
                    mToolbar.setVisibility(View.GONE);
                    float alpha = (float) totalDy / bannerHeight;
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(getActivity(), R.color.colorPrimary), alpha));
                } else {
                    mToolbar.setVisibility(View.VISIBLE);
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(getActivity(), R.color.colorPrimary), 1));
                }
            }
        });
//        mOneAdapter.setOnLoadMoreListener(() -> new Handler().postDelayed(() -> {
//            mOneAdapter.addData(addData());
//            if (mItemList.size() == 100) {
//                mOneAdapter.loadMoreEnd();
//            } else {
//                mOneAdapter.loadMoreComplete();
//            }
//        }, 2000), mRv);

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

                            try {
                                txtname.setText("Hi " + userMoneyDataBean.getBody().getData().getName());
                                txttotle.setText(userMoneyDataBean.getBody().getData().getBalance() + "");
                                txt1num.setText(userMoneyDataBean.getBody().getData().getTodayEarnings() + "");
                                txtcurreny.setText(userMoneyDataBean.getBody().getData().getReward() + "");
                                txt2num.setText(userMoneyDataBean.getBody().getData().getReward() + "");
                                txtyesterday.setText(userMoneyDataBean.getBody().getData().getYesterdayEarnings() + "");
                                txt3num.setText(userMoneyDataBean.getBody().getData().getYesterdayEarnings() + "");
                                txtincom.setText(userMoneyDataBean.getBody().getData().getCumulativeEarnings() + "");
                                txttoday.setText(userMoneyDataBean.getBody().getData().getTodayEarnings() + "");
                                txtlv.setText("LV" + userMoneyDataBean.getBody().getData().getLevel() + "");

                                shareCode = userMoneyDataBean.getBody().getData().getShareCode();
                                resgerData = userMoneyDataBean.getBody().getData().getRegisterDate();
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

    public void getDataList() {//读取列表
        index = 0;
        RxHttp.get("/v1/levels")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        //  HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            HomeListBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), HomeListBean.class);

                            mOneAdapter.setNewData(homeListBean.getBody().getData());


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

    public void getNoticeList() {//读取列表
        RxHttp.get("/v1/wallets/latest-withdraws")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        String content = "";
                        if (baseBean.getHead().getCode() == 1) {
                            NoticeListBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), NoticeListBean.class);

                            if (homeListBean.getBody().getData().size() == 0) {
                                return;
                            }
                            for (int i = 0; i < homeListBean.getBody().getData().size(); i++) {
                                messages.add(homeListBean.getBody().getData().get(i).getName() + " " + "withdraw" + " " + String.valueOf(homeListBean.getBody().getData().get(i).getAmount()).replace(".0", "") + " ₦ " + "sucess;     ");
                            }
                            for (int i = 0; i < messages.size(); i++) {
                                content = content + "  " + messages.get(i);
                            }
                            marqueeView.setContent(content);

                        } else

                            ToastUtils.showShort(baseBean.getHead().getMessage());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }

    @OnClick(R.id.imgshare)
    public void onClick() {
        shareContent();
    }

    private void shareContent() {
        String link = "https://h5.zxm88.net/register?code=" + shareCode;
        //http://h5.at2000.net/index?code=6pEJeagAwv
        Intent share_intent = new Intent();

        share_intent.setAction(Intent.ACTION_SEND);

        share_intent.setType("text/plain");

        share_intent.putExtra(Intent.EXTRA_SUBJECT, "share");

        share_intent.putExtra(Intent.EXTRA_TEXT, link);

        share_intent = Intent.createChooser(share_intent, "share");

        startActivity(share_intent);
    }
}
