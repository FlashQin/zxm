package com.compy.check.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemNoHeader;
import com.compy.check.base.BaseActivity;
import com.compy.check.bean.MenberSystemBean;
import com.compy.check.bean.RechargBean;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberSystemActivity extends BaseActivity {
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
    private BaseQuickAdapter<MenberSystemBean, BaseViewHolder> mOneAdapter;
    String[] strlv={"LV1","LV2","LV3","LV4","LV5","LV6"};
    String[] stramout={"5000","20000","100000","300000","500000","1000000"};
    String[] strcontent1={"1,You can withdraw order commission on sign up day\n" +
            "2,The number of orders that can be swiped is 20\n" +
            "3,The promotion rebate is 12%/8%/5%","1,Each withdraw limit is 2000000\n" +
            "2,The number of orders that can be swiped is 20\n" +
            "3,The promotion rebate is 12%/8%/5%","1,Withdraw no limit\n" +
            "2,The number of orders that can be swiped is 20\n" +
            "3,The promotion rebate is 12%/8%/5%","1,Withdraw no limit\n" +
            "2,The number of orders that can be swiped is 20\n" +
            "3,The promotion rebate is 12%/8%/5%","1,Withdraw no limit\n" +
            "2,The number of orders that can be swiped is 20\n" +
            "3,The promotion rebate is 12%/8%/5%","1,Withdraw no limit\n" +
            "2,The number of orders that can be swiped is 20\n" +
            "3,The promotion rebate is 12%/8%/5%"};
    String[] strlcontent2={"Each order you can get 0.6% commission rate","Each order you can get 0.65% commission rate","Each order you can get 0.65% commission rate","Each order you can get 0.7% commission rate","Each order you can get 0.7% commission rate","Each order you can get 0.72% commission rate"};

    List<MenberSystemBean> list=new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_membersystem;
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

        txttitle.setText("Member System");
        for (int i = 0; i < strlv.length; i++) {
            MenberSystemBean bean=new MenberSystemBean();
            bean.setLv(strlv[i]);
            bean.setAmount(stramout[i]);
            bean.setContentone(strcontent1[i]);
            bean.setContenttwo(strlcontent2[i]);
            list.add(bean);
        }
        initAdapter();
        addHeaderView();
    }
    private void addHeaderView() {

        View headerview = LayoutInflater.from(MemberSystemActivity.this).inflate(R.layout.header_menbersystem, (ViewGroup) rec.getParent(), false);
        mOneAdapter.addHeaderView(headerview);
    }
    public void initAdapter() {
        rec.setLayoutManager(new LinearLayoutManager(MemberSystemActivity.this));
        rec.addItemDecoration(new SpacesItemNoHeader(5));
        mOneAdapter = new BaseQuickAdapter<MenberSystemBean, BaseViewHolder>(R.layout.item_menbersystem,list) {
            @Override
            protected void convert(BaseViewHolder helper, MenberSystemBean item) {

                TextView txt1 = helper.itemView.findViewById(R.id.txt1);
                TextView txt2 = helper.itemView.findViewById(R.id.txt2);
                TextView txt3 = helper.itemView.findViewById(R.id.txt3);
                TextView txt8 = helper.itemView.findViewById(R.id.txt8);
                Button txtup = helper.itemView.findViewById(R.id.txtup);
                txt1.setText(item.getLv());
                txt2.setText(item.getContentone());
                txt3.setText(item.getContenttwo());
                txt8.setText(item.getAmount()+"N");

                txtup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Goto(RechargeActivity.class,"money",item.getAmount());
                    }
                });

            }
        };
        rec.setAdapter(mOneAdapter);

        View inflate = LayoutInflater.from(MemberSystemActivity.this).inflate(R.layout.layout_empty_home2, null);
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
