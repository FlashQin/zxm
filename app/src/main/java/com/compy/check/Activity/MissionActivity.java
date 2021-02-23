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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemNoHeader;
import com.compy.check.base.BaseActivity;
import com.compy.check.bean.RechargBean;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MissionActivity extends BaseActivity {

    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.txtdepostion)
    TextView txtdepostion;
    private BaseQuickAdapter<RechargBean, BaseViewHolder> mOneAdapter;
    List<RechargBean> rechargBeanList = new ArrayList<>();
    String[] strName = {"500", "1000", "3000", "5000", "10000", "20000", "30000", "50000"};

    int count=1;
    @Override
    public int getLayoutId() {

        return R.layout.activity_mission;
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

        txttitle.setText("Mission");
        initAdapter();
    }

    public void initAdapter() {
        for (int i = 0; i < 12; i++) {
            RechargBean bean = new RechargBean();
            if (i == 0) {
               // bean.setIscheck(true);
               // bean.setName(strName[i]);
            } else {
               // bean.setIscheck(false);
               // bean.setName(strName[i]);
            }
            rechargBeanList.add(bean);
        }

        rec.setLayoutManager(new LinearLayoutManager(MissionActivity.this));
        //rec.addItemDecoration(new SpacesItemNoHeader(20));
        mOneAdapter = new BaseQuickAdapter<RechargBean, BaseViewHolder>(R.layout.item_mission, rechargBeanList) {
            @Override
            protected void convert(BaseViewHolder helper, RechargBean item) {

               TextView txtname = helper.itemView.findViewById(R.id.txtxuhao);
               txtname.setText(count + "");
               count++;
                if (item.isIscheck() == true) {
                   // txtname.setBackgroundResource(R.drawable.shape_nobian_greenbac);
                } else {
                   // txtname.setBackgroundResource(R.drawable.shape_whitebac_blackbian);
                }
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (item.isIscheck() == false) {
                            for (int i = 0; i < rechargBeanList.size(); i++) {
                                if (rechargBeanList.get(i).getName().equals(item.getName())) {
                                    rechargBeanList.get(i).setIscheck(true);
                                } else {
                                    rechargBeanList.get(i).setIscheck(false);
                                }
                            }
                            mOneAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        };
        rec.setAdapter(mOneAdapter);

        View inflate = LayoutInflater.from(MissionActivity.this).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.imgback, R.id.txtdepostion})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgback:
                finish();
                break;
            case R.id.txtdepostion:
                break;
        }
    }
}
