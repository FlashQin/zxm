package com.compy.check.Activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.compy.check.bean.DanInfoBean;
import com.compy.check.bean.PayListRecorder;
import com.compy.check.net.BaseObserver;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.qiujuer.genius.ui.widget.Button;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class RechageRecordActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener {
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
    @BindView(R.id.smallLabel)
    SmartRefreshLayout smallLabel;
    private BaseQuickAdapter<PayListRecorder.BodyBean.DataBean, BaseViewHolder> mOneAdapter;
    private DialogPlus dialog_spec;
    int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rechagerecord;
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

        txttitle.setText("Recharge records");
        smallLabel.setOnLoadMoreListener(this);
        smallLabel.setOnRefreshListener(this);



        initAdapter();
        getDataLiST();
    }

    public void initAdapter() {


        rec.setLayoutManager(new LinearLayoutManager(RechageRecordActivity.this));
        rec.addItemDecoration(new SpacesItemNoHeader(20));
        mOneAdapter = new BaseQuickAdapter<PayListRecorder.BodyBean.DataBean, BaseViewHolder>(R.layout.item_recharrecoder) {
            @Override
            protected void convert(BaseViewHolder helper, PayListRecorder.BodyBean.DataBean item) {

                TextView txtname = helper.itemView.findViewById(R.id.txttime);
                TextView txtamount = helper.itemView.findViewById(R.id.txtamount);
                TextView txttype = helper.itemView.findViewById(R.id.txttype);
//                JSONObject json1 = JSONObject.parseObject(JSONObject.toJSONString(item.getCreatedAt().getDate()));
               String time1 = "";
//                for (Map.Entry<String, Object> entry : json1.entrySet()) {
//
//                    time1=time1+entry.getValue().toString();
//                }
//                JSONObject json2 = JSONObject.parseObject(JSONObject.toJSONString(item.getCreatedAt().getTime()));
              String time2 = "";
//                for (Map.Entry<String, Object> entry2 : json2.entrySet()) {
//
//                    time2=time2+entry2.getValue().toString();
//                }
                time1=item.getCreatedAt().getDate().getMonth()+"-"+item.getCreatedAt().getDate().getDay()+"-"+item.getCreatedAt().getDate().getYear();
                time2=item.getCreatedAt().getTime().getHour()+":"+item.getCreatedAt().getTime().getMinute()+":"+item.getCreatedAt().getTime().getSecond();

                txtname.setText(time1+" "+time2);
                txtamount.setText(item.getAmount()+"N");

                switch (item.getStatus()){
                    case 0:
                        txttype.setText("Pending review");
                        break;
                    case 1:
                        txttype.setText("Sucess");
                        break;
                    case 3:
                        txttype.setText("Fail");
                        break;
                }

            }
        };
        rec.setAdapter(mOneAdapter);



    }

    public void getDataLiST() {//客户端IP地址
        ShowLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        String json = createJsonString(map, "type", -1);
        RxHttp.get("/v1/wallets/" + SPUtils.getInstance().getString("id", "0") + "/recharge-journals?limit=10")
                .setJsonParams(json)
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            PayListRecorder homeListBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), PayListRecorder.class);


                            mOneAdapter.setNewData(homeListBean.getBody().getData());


                        }

                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        HideLoading();
//                        View inflate = LayoutInflater.from(RechageRecordActivity.this).inflate(R.layout.layout_empty_home2, null);
//                        mOneAdapter.setEmptyView(inflate);
                    }
                });
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getDataLiST();
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

    private void initSpecDialogPost(DanInfoBean danInfoBean) {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_info_rechar);
        dialog_spec = DialogPlus.newDialog(RechageRecordActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(false)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();

        TextView txtall = viewHolder.getInflatedView().findViewById(R.id.txtmoney);
        TextView txtreturn = viewHolder.getInflatedView().findViewById(R.id.txtfee);
        TextView txtodernum = viewHolder.getInflatedView().findViewById(R.id.txtreceive);
        TextView txttime = viewHolder.getInflatedView().findViewById(R.id.txtname);
        TextView txtfan = viewHolder.getInflatedView().findViewById(R.id.txtifs);
        TextView txtno = viewHolder.getInflatedView().findViewById(R.id.txtno);
        Button btnok = viewHolder.getInflatedView().findViewById(R.id.btnrechage);
        // String time = UtilTool.gettimenowNumber();
        txtall.setText(danInfoBean.getBody().getData().getTradeAmount() + "");
        txtreturn.setText(danInfoBean.getBody().getData().getTradeIncome() + "");

        txttime.setText(danInfoBean.getBody().getData().getBaseDate() + "");
        txtfan.setText("Commission: " + (roundByScale(danInfoBean.getBody().getData().getIncomeRate() * 100, 2)) + "% of the product price");
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_spec.dismiss();
            }
        });
        dialog_spec.show();
    }
}
