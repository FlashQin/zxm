package com.compy.check.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
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
import com.compy.check.bean.LiCaiRecorderListBean;
import com.compy.check.bean.LicaiSuccBean;
import com.compy.check.bean.RechargBean;
import com.compy.check.net.BaseObserver;
import com.gyf.immersionbar.ImmersionBar;
import com.rxjava.rxlife.RxLife;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class InterestRecordActivity extends BaseActivity {
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
    private BaseQuickAdapter<LiCaiRecorderListBean.BodyBean.DataBean, BaseViewHolder> mOneAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_interestrecord;
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

        txttitle.setText("Interest Access Record");
        initAdapter();
    }
    public void initAdapter() {


        rec.setLayoutManager(new GridLayoutManager(InterestRecordActivity.this, 2));
        rec.addItemDecoration(new SpacesItemNoHeader(20));
        mOneAdapter = new BaseQuickAdapter<LiCaiRecorderListBean.BodyBean.DataBean, BaseViewHolder>(R.layout.item_recharge) {
            @Override
            protected void convert(BaseViewHolder helper, LiCaiRecorderListBean.BodyBean.DataBean item) {

                TextView txtname = helper.itemView.findViewById(R.id.txtname);
               // txtname.setText(item.getName() + " ₦");
//                if (item.isIscheck() == true) {
//                    txtname.setBackgroundResource(R.drawable.shape_nobian_greenbac);
//                } else {
//                    txtname.setBackgroundResource(R.drawable.shape_whitebac_blackbian);
//                }

            }
        };
        rec.setAdapter(mOneAdapter);

        View inflate = LayoutInflater.from(InterestRecordActivity.this).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);

    }
    public void getdata(){//读取个人概要数据:
        Map<String, Object> map = new HashMap<String, Object>();
//        String json = createJsonString(map, "contract",contract);
//        json = createJsonString(map, "amount",amount);
        RxHttp.postJson("/v1/members/"+ SPUtils.getInstance().getString("id", "0")+"/finances?offset=0&limit=20")
               // .setJsonParams(json)
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            LiCaiRecorderListBean userMoneyDataBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), LiCaiRecorderListBean.class);

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
