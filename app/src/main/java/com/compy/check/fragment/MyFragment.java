package com.compy.check.fragment;


import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.compy.check.Activity.BindBankCardActivity;
import com.compy.check.Activity.CustomerServiceActivity;
import com.compy.check.Activity.HowMakeMoneyActivity;
import com.compy.check.Activity.Introductionctivity;
import com.compy.check.Activity.LoginActivity;
import com.compy.check.Activity.MemberSystemActivity;
import com.compy.check.Activity.RechageRecordActivity;
import com.compy.check.Activity.RechargeActivity;
import com.compy.check.Activity.WalletActivity;
import com.compy.check.Activity.WithdrawalActivity;
import com.compy.check.Activity.WithdrawalRecordActivity;
import com.compy.check.R;
import com.compy.check.adapter.SpacesItemNoHeader;
import com.compy.check.base.BaseFragment;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.MineListBean;
import com.compy.check.bean.UserMoneyDataBean;
import com.compy.check.net.BaseObserver;
import com.compy.check.utlis.ACache;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.rxjava.rxlife.RxLife;
import com.sunfusheng.marqueeview.MarqueeView;

import net.qiujuer.genius.kit.handler.Run;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;


public class MyFragment extends BaseFragment {

    @BindView(R.id.linbar)
    LinearLayout linbar;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.txtmine)
    TextView txtmine;
    @BindView(R.id.txt10)
    TextView txt10;
    @BindView(R.id.txtlevel)
    TextView txtlevel;
    @BindView(R.id.imgrechagre)
    ImageView imgrechagre;
    @BindView(R.id.imgwith)
    ImageView imgwith;
    @BindView(R.id.relwith)
    RelativeLayout relwith;
    @BindView(R.id.txtid)
    TextView txtid;
    @BindView(R.id.txtyour)
    TextView txtyour;
    @BindView(R.id.container1)
    ConstraintLayout container1;
    @BindView(R.id.ddddd)
    ImageView ddddd;
    @BindView(R.id.marrr)
    MarqueeView marrr;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.txttotlenum)
    TextView txttotlenum;
    private BaseQuickAdapter<MineListBean, BaseViewHolder> mOneAdapter;
    private DialogPlus dialog_spec;
    List<MineListBean> mineList = new ArrayList<>();
    String[] strname = {"Member system",  "Withdrawal records", "Recharge records", "Wallet", "Introduction", "Sign out"};
    int[] strpic = {R.mipmap.anniu_1,  R.mipmap.anniu_3, R.mipmap.anniu_4, R.drawable.anniu_5, R.mipmap.anniu_6, R.mipmap.anniu_7};

    @Override
    protected void initView(View view) {
        ImmersionBar.with(this)
                .titleBar(linbar) //可以为任意view，如果是自定义xml实现标题栏的话，标题栏根节点不能为RelativeLayout或者ConstraintLayout，以及其子类
                .statusBarDarkFont(true)
                .init();

        for (int i = 0; i < strname.length; i++) {
            MineListBean bean = new MineListBean();
            bean.setName(strname[i]);
            bean.setIcon(strpic[i]);
            mineList.add(bean);
        }
        initAdapter();


    }

    @Override
    public void onResume() {
        super.onResume();
        getMenberInfoData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void fetchData() {

    }

    public void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linearLayoutManager);
        rec.addItemDecoration(new SpacesItemNoHeader(5));
        mOneAdapter = new BaseQuickAdapter<MineListBean, BaseViewHolder>(R.layout.item_mylist) {
            @Override
            protected void convert(BaseViewHolder helper, MineListBean item) {

                TextView txtname = helper.itemView.findViewById(R.id.txtname);
                TextView txtn = helper.itemView.findViewById(R.id.txtn);
                ImageView img = helper.itemView.findViewById(R.id.img3);
                txtname.setText(item.getName());
                img.setBackgroundResource(item.getIcon());
                if (item.getName().equals("Wallet")){
                    txtn.setVisibility(View.VISIBLE);
                }else {
                    txtn.setVisibility(View.INVISIBLE);
                }

            }
        };
        mOneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        Goto(MemberSystemActivity.class);
                        break;
//                    case 1:
//                        Goto(BindBankCardActivity.class);
//                        break;
                    case 1:
                        Goto(WithdrawalRecordActivity.class);
                        break;
                    case 2:
                        Goto(RechageRecordActivity.class);
                        break;
//                    case 4:
//                        Goto(ChangeNameActivity.class);
//                        break;
                    case 3:
                        Goto(WalletActivity.class);
                        break;
                    case 4:
                        Goto(Introductionctivity.class);
                        break;
                    case 5:

                        initSpecDialog();
                        break;

                }
            }
        });
        rec.setAdapter(mOneAdapter);
        mOneAdapter.setNewData(mineList);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_empty_home2, null);
        mOneAdapter.setEmptyView(inflate);
        addFooterView();
    }

    private void addFooterView() {

        View footerview = LayoutInflater.from(mActivity).inflate(R.layout.item_footer_mine, (ViewGroup) rec.getParent(), false);
        ConstraintLayout conmake = footerview.findViewById(R.id.container3);
        ConstraintLayout conservice = footerview.findViewById(R.id.container4);
        ConstraintLayout conshare = footerview.findViewById(R.id.container5);
        conmake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Goto(HowMakeMoneyActivity.class);
            }
        });
        conservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Goto(CustomerServiceActivity.class);
            }
        });
        conshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Goto(HowMakeMoneyActivity.class);
            }
        });
        mOneAdapter.addFooterView(footerview);


    }

    private void initSpecDialog() {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_exit);
        dialog_spec = DialogPlus.newDialog(getActivity())
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(false)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();
        Button btnno = viewHolder.getInflatedView().findViewById(R.id.btnno);
        Button btnyes = viewHolder.getInflatedView().findViewById(R.id.btnyes);

        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_spec.dismiss();
            }
        });
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_spec.dismiss();
                ToastUtils.showShort("Exit Sucess");
                Run.onUiAsync(() -> {
                    ACache.get(Utils.getApp()).remove("USER_BEAN");
                    SPUtils.getInstance().remove("TOKEN");
                    SPUtils.getInstance().remove("id");
                });
                // RxBus.getDefault().post("", "Exit");
                Goto(LoginActivity.class, FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().finish();

            }
        });
        dialog_spec.show();
    }

    @OnClick({R.id.imgrechagre, R.id.imgwith})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgrechagre:
                Goto(RechargeActivity.class);
                break;
            case R.id.imgwith:
                Goto(WithdrawalActivity.class);
                break;
        }
    }
    public void getMenberInfoData(){//读取个人概要数据:
        RxHttp.get("/v1/members/"+ SPUtils.getInstance().getString("id", "0")+"/info")
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            UserMoneyDataBean userMoneyDataBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), UserMoneyDataBean.class);

                            try {


                                txt10.setText("Hi " + userMoneyDataBean.getBody().getData().getName());
                                txttotlenum.setText(userMoneyDataBean.getBody().getData().getBalance() + "");

                                txtlevel.setText("Membership level: LV" + userMoneyDataBean.getBody().getData().getLevel() + "");
                                txtid.setText("ID:"+userMoneyDataBean.getBody().getData().getId());

                            }catch (NullPointerException e){

                            }

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
}
