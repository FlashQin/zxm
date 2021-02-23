package com.compy.check.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemNoHeader;
import com.compy.check.base.BaseActivity;
import com.compy.check.bean.RechargBean;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LVActivity extends BaseActivity {
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
    private BaseQuickAdapter<RechargBean, BaseViewHolder> mOneAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_lv;
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

        txttitle.setText("Lv");
        initAdapter();
    }
    public void initAdapter() {


        rec.setLayoutManager(new GridLayoutManager(LVActivity.this, 2));
        rec.addItemDecoration(new SpacesItemNoHeader(20));
        mOneAdapter = new BaseQuickAdapter<RechargBean, BaseViewHolder>(R.layout.item_recharge) {
            @Override
            protected void convert(BaseViewHolder helper, RechargBean item) {

                TextView txtname = helper.itemView.findViewById(R.id.txtname);
                txtname.setText(item.getName() + " ₦");
                if (item.isIscheck() == true) {
                    txtname.setBackgroundResource(R.drawable.shape_nobian_greenbac);
                } else {
                    txtname.setBackgroundResource(R.drawable.shape_whitebac_blackbian);
                }

            }
        };
        rec.setAdapter(mOneAdapter);

        View inflate = LayoutInflater.from(LVActivity.this).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);

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
