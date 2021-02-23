package com.compy.check.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemWithHeader;
import com.compy.check.base.BaseFragment;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.MessageWrap;
import com.compy.check.bean.OderRecoderBean;
import com.compy.check.bean.UserMoneyDataBean;
import com.compy.check.net.BaseObserver;
import com.compy.check.utlis.UtilTool;
import com.gyf.immersionbar.ImmersionBar;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;


public class TwoFragment extends BaseFragment {

    @BindView(R.id.txttitle2)
    TextView txttitle2;
    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txtRemaining)
    TextView txtRemaining;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.txtTodaynum)
    TextView txtTodaynum;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.linbar)
    LinearLayout linbar;
    //    @BindView(R.id.container3)
//    ConstraintLayout container3;
    @BindView(R.id.smallLabel)
    SmartRefreshLayout smallLabel;
    int page = 0;
    private BaseQuickAdapter<OderRecoderBean.BodyBean.DataBean, BaseViewHolder> mOneAdapter;

    @Override
    protected void initView(View view) {
        ImmersionBar.with(this)
                .titleBar(linbar) //可以为任意view，如果是自定义xml实现标题栏的话，标题栏根节点不能为RelativeLayout或者ConstraintLayout，以及其子类
                .statusBarDarkFont(true)
                .init();
        initAdapter();
        getMenberInfoData();

        //  addFooterView();//顶部数据都在  info接口里面
        smallLabel.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                // page++;
                getNoticeList();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one2_service;
    }

    @Override
    protected void fetchData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        page=0;
        getNoticeList();
    }

    public void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
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
                //String time = UtilTool.gettimenowNumber();
                txtbaifen.setText(item.getTradeAmount() + " ");
                txttime.setText(item.getBaseDate() + " ");
                if (String.valueOf(item.getId()).length()>10) {
                    txtamount.setText(String.valueOf(item.getId()).substring(0, 10));
                }else {
                    txtamount.setText(String.valueOf(item.getId()));
                }

                txttype.setText(item.getTradeIncome() + " ");

            }
        };
        rec.setAdapter(mOneAdapter);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);
    }

    private void addFooterView() {

        View footerview = LayoutInflater.from(mActivity).inflate(R.layout.item_footer_home2, (ViewGroup) rec.getParent(), false);
        mOneAdapter.addFooterView(footerview);


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

                                txtRemaining.setText(userMoneyDataBean.getBody().getData().getBalance() + "");//可用余额


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
        RxHttp.get("/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/order-journals?offset=" + page + "&limit=20")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        String content = "";

                        if (baseBean.getHead().getCode() == 1) {
                            OderRecoderBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), OderRecoderBean.class);


                            txtTodaynum.setText(homeListBean.getBody().getData().size() + "");//可用余额

                            if (page == 0) {

                                mOneAdapter.setNewData(homeListBean.getBody().getData());

                            } else {
                                mOneAdapter.addData(homeListBean.getBody().getData());
                            }
                            page = page + 20;


                        } else {
                            smallLabel.setNoMoreData(true);
                        }
                        smallLabel.finishLoadMore();
                        //  ToastUtils.showShort(baseBean.getHead().getMessage());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }

//    @OnClick(R.id.container3)
//    public void onClick() {
//        EventBus.getDefault().post(new MessageWrap(0, ""));
//    }
}
