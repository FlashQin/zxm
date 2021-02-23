package com.compy.check.fragment;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.Activity.LVActivity;
import com.compy.check.Activity.TotleTeamContributionActivity;
import com.compy.check.R;
import com.compy.check.base.BaseFragment;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.TeamDataBean;
import com.compy.check.bean.UserMoneyDataBean;
import com.compy.check.net.BaseObserver;
import com.rxjava.rxlife.RxLife;

import net.qiujuer.genius.ui.widget.Button;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;


public class NewTeamFragment extends BaseFragment {

    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnshow)
    Button btnshow;
    @BindView(R.id.imgshe)
    ImageView imgshe;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.edta1)
    TextView edta1;
    @BindView(R.id.edta2)
    TextView edta2;
    @BindView(R.id.btna)
    Button btna;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    @BindView(R.id.edtb1)
    TextView edtb1;
    @BindView(R.id.edtb2)
    TextView edtb2;
    @BindView(R.id.btnb)
    Button btnb;
    @BindView(R.id.lin3)
    LinearLayout lin3;
    @BindView(R.id.edtc1)
    TextView edtc1;
    @BindView(R.id.edtc2)
    TextView edtc2;
    @BindView(R.id.btnc)
    Button btnc;
    @BindView(R.id.container1)
    ConstraintLayout container1;
    @BindView(R.id.rec)
    RecyclerView rec;
    String shareCode="";
    private BaseQuickAdapter<String, BaseViewHolder> mOneAdapter;

    @Override
    protected void initView(View view) {


        initAdapter();
        addFooterView();

    }

    @Override
    public void onResume() {
        super.onResume();
        ShowLoading();
        getMenberInfoData();
        getDataList();
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


                                shareCode=userMoneyDataBean.getBody().getData().getShareCode();
                            } catch (NullPointerException e) {

                            }

                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_team;
    }

    @Override
    protected void fetchData() {

    }

    public void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linearLayoutManager);
        // rec.addItemDecoration(new SpacesItemWithHeader(20));
        mOneAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_newteam, Arrays.asList("")) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {


            }
        };
        rec.setAdapter(mOneAdapter);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);
    }

    private void addFooterView() {

        View footerview = LayoutInflater.from(mActivity).inflate(R.layout.item_footer_newteam, (ViewGroup) rec.getParent(), false);

        mOneAdapter.addFooterView(footerview);


    }

    public void getDataList() {//读取列表
        RxHttp.get("/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/team")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                          HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            TeamDataBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), TeamDataBean.class);

                            edta1.setText(homeListBean.getBody().getData().getPrimaryMemberCount()+"");
                            edta2.setText(homeListBean.getBody().getData().getPrimaryShareAmount()+"");

                            edtb1.setText(homeListBean.getBody().getData().getSecondaryMemberCount()+"");
                            edtb2.setText(homeListBean.getBody().getData().getSecondaryShareAmount()+"");

                            edtc1.setText(homeListBean.getBody().getData().getRecessiveMemberCount()+"");
                            edtc2.setText(homeListBean.getBody().getData().getRecessiveShareAmount()+"");



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

    @OnClick({R.id.btnshow,R.id.btna, R.id.btnb, R.id.btnc})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnshow:

                shareContent();
                break;
            case R.id.btna:
                Goto(TotleTeamContributionActivity.class,"type","1");
                break;
            case R.id.btnb:
                Goto(TotleTeamContributionActivity.class,"type","2");
                break;
            case R.id.btnc:
                Goto(TotleTeamContributionActivity.class,"type","3");
                break;
        }
    }
    private void shareContent() {
        String link= "https://h5.zxm88.net/register?code="+shareCode;

        Intent share_intent = new Intent();

        share_intent.setAction(Intent.ACTION_SEND);

        share_intent.setType("text/plain");

        share_intent.putExtra(Intent.EXTRA_SUBJECT, "share");

        share_intent.putExtra(Intent.EXTRA_TEXT, link);

        share_intent = Intent.createChooser(share_intent, "share");

        startActivity(share_intent);
    }
}
