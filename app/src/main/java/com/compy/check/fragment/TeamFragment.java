package com.compy.check.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.Activity.LVActivity;
import com.compy.check.Activity.MissionActivity;
import com.compy.check.Activity.OrdergrabbingActivity;
import com.compy.check.Activity.TotleTeamContributionActivity;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemNoHeader;
import com.compy.check.adapter.SpacesItemWithHeader;
import com.compy.check.base.BaseFragment;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.HomeListBean;
import com.compy.check.bean.TeamDataBean;
import com.compy.check.net.BaseObserver;
import com.compy.check.utlis.ClipUtils;
import com.rxjava.rxlife.RxLife;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;


public class TeamFragment extends BaseFragment {

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txtlv1)
    TextView txtlv1;
    @BindView(R.id.txtlv2)
    TextView txtlv2;
    @BindView(R.id.txtlv3)
    TextView txtlv3;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.txtcode)
    TextView txtcode;
    @BindView(R.id.txtcopycode)
    TextView txtcopycode;
    @BindView(R.id.txt4)
    TextView txt4;
    @BindView(R.id.txtlink)
    TextView txtlink;
    @BindView(R.id.txtcopylink)
    TextView txtcopylink;
    @BindView(R.id.linbar)
    LinearLayout linbar;
    @BindView(R.id.rec)
    RecyclerView rec;
    private BaseQuickAdapter<String, BaseViewHolder> mOneAdapter;

    @Override
    protected void initView(View view) {

        initAdapter();
        addHeaderView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one3_service;
    }

    @Override
    protected void fetchData() {

    }

    public void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linearLayoutManager);
        rec.addItemDecoration(new SpacesItemWithHeader(20));
        mOneAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_team_list, Arrays.asList("", "", "", "")) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Goto(LVActivity.class);
                    }
                });
            }
        };
        rec.setAdapter(mOneAdapter);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);
    }

    private void addHeaderView() {

        View headView = LayoutInflater.from(mActivity).inflate(R.layout.item_homeheader3, (ViewGroup) rec.getParent(), false);

        ConstraintLayout conmission = headView.findViewById(R.id.containermission);
        ConstraintLayout conlist = headView.findViewById(R.id.containerlist);

        conmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Goto(MissionActivity.class);
            }
        });
        conlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Goto(TotleTeamContributionActivity.class);
            }
        });
        mOneAdapter.addHeaderView(headView);


    }

    @OnClick({R.id.txtcopycode, R.id.txtcopylink})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtcopycode:
                ClipUtils.copyText(getActivity(),
                        txtcode.getText().toString(), "copy succ");
                break;
            case R.id.txtcopylink:
                ClipUtils.copyText(getActivity(),
                        txtlink.getText().toString(), "copy succ");
                break;
        }
    }
}
