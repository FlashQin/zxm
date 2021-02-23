package com.compy.check.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
import com.compy.check.bean.RechargBean;
import com.compy.check.bean.TeamDataBean;
import com.compy.check.bean.TeamListShouBean;
import com.compy.check.net.BaseObserver;
import com.gyf.immersionbar.ImmersionBar;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class TotleTeamContributionActivity extends BaseActivity {
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rec)
    RecyclerView rec;
    String url="";
    private BaseQuickAdapter<TeamListShouBean.BodyBean.DataBean, BaseViewHolder> mOneAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_totleteambution;
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

        txttitle.setText("Total Team Contribution");
        String status=getIntent().getStringExtra("type");
        switch (status){
            case "1":
                url="primary";
                break;

            case "2":
                url="secondary";
                break;
            case "3":
                url="recessive";
                break;
        }
        initAdapter();
        getDataList();
    }
    public void initAdapter() {


        rec.setLayoutManager(new LinearLayoutManager(TotleTeamContributionActivity.this));
        rec.addItemDecoration(new SpacesItemNoHeader(20));
        mOneAdapter = new BaseQuickAdapter<TeamListShouBean.BodyBean.DataBean, BaseViewHolder>(R.layout.item_recharge) {
            @Override
            protected void convert(BaseViewHolder helper, TeamListShouBean.BodyBean.DataBean item) {
//
//                TextView txtname = helper.itemView.findViewById(R.id.txtname);
//                txtname.setText(item.getName() + " ₦");
//                if (item.isIscheck() == true) {
//                    txtname.setBackgroundResource(R.drawable.shape_nobian_greenbac);
//                } else {
//                    txtname.setBackgroundResource(R.drawable.shape_whitebac_blackbian);
//                }

            }
        };
        rec.setAdapter(mOneAdapter);

        View inflate = LayoutInflater.from(TotleTeamContributionActivity.this).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);

    }
    public void getDataList() {//读取列表
        RxHttp.get("/v1/members/" + SPUtils.getInstance().getString("id", "0") + "/team-journals/"+url)
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            TeamListShouBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), TeamListShouBean.class);


                        }

                           // ToastUtils.showShort(baseBean.getHead().getMessage());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        //   HideLoading();
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imgback)
    public void onClick() {
        finish();
    }
}
