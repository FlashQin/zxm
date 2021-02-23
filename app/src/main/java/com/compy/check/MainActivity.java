package com.compy.check;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.compy.check.Activity.OrdergrabbingActivity;
import com.compy.check.Activity.RechargeActivity;
import com.compy.check.base.BaseActivity;
import com.compy.check.bean.BaseBean;
import com.compy.check.bean.HomeListBean;
import com.compy.check.bean.MenberDataBean;
import com.compy.check.bean.MenberInfoBean;
import com.compy.check.bean.MessageWrap;
import com.compy.check.bean.NoticeListBean;
import com.compy.check.bean.ResigerBean;
import com.compy.check.bean.UpDataBean;
import com.compy.check.fragment.FourFragment;
import com.compy.check.fragment.HomeFragment;
import com.compy.check.fragment.MyFragment;
import com.compy.check.fragment.NewTeamFragment;
import com.compy.check.fragment.TeamFragment;
import com.compy.check.fragment.TwoFragment;
import com.compy.check.net.BaseObserver;
import com.compy.check.utlis.ACache;
import com.compy.check.utlis.CommonUtils;
import com.compy.check.utlis.UtilTool;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.rxjava.rxlife.RxLife;
import com.vondear.rxtool.RxActivityTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rxhttp.wrapper.param.RxHttp;

public class MainActivity extends BaseActivity {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_category)
    LinearLayout llCategory;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.ll_proudct)
    LinearLayout llProudct;
    private HomeFragment homeFourFragment;
    private TwoFragment categoryFourFragment;
    private NewTeamFragment serviceFourFragment;
    private FourFragment mineFourFragment;
    private MyFragment myFragment;
    private DialogPlus dialog_update;
    int currtPage = 1;
    BaseDownloadTask singleTask;

    private int downloadId1;
    public String singleFileSaveName;
    private DialogPlus dialog_fail;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        // ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true).init();
        selectedFragment(0);
        tabSelected(llHome);
        checkVision();

        if (UtilTool.getnotice( UtilTool.gettimenow(),"2021-02-15 00:00:00") == true) {
            initNotice();
            System.out.println("第N次");
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        getMenberInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageWrap page) {

        switch (page.getType()) {
            case 0:

                selectedFragment(page.getType());
                tabSelected(llHome);
                break;
            case 1:
                selectedFragment(page.getType());
                tabSelected(llCategory);
                break;
        }
    }

    public void downLoad(Button btnyes, String url) {//
        String destPath = getExternalCacheDir() + "/" + "zxm.apk";
        File file = new File(destPath);
        if (file.exists()) {
            file.delete();
        }
        long length = new File(destPath).length(); //已下载的文件长度
        RxHttp.get(url.replace("https", "http"))
                .setRangeHeader(length)  //设置开始下载位置，结束位置默认为文件末尾
                .asDownload(destPath, progress -> {
                    //下载进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已下载的字节大小
                    long totalSize = progress.getTotalSize();     //要下载的总字节大小
                    System.out.println("下载进度-------------" + currentProgress);
                    System.out.println("当前已下载的字节大小-------------" + currentSize);
                    System.out.println("要下载的总字节大小-------------" + totalSize);
                    // progressBar.setProgress(currentProgress);
                    btnyes.setText("Load...(" + currentProgress + "%)");
                }, AndroidSchedulers.mainThread()) //指定主线程回调
                .subscribe(s -> { //s为String类型
                    //下载成功，处理相关逻辑
                    dialog_update.dismiss();
                    install(destPath);
                }, throwable -> {
                    //下载失败，处理相关逻辑
                    btnyes.setText("Fail");
                });
    }

    private void install(String filePath) {
        Log.i(TAG, "开始执行安装: " + filePath);
        File apkFile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.w(TAG, "版本大于 N ，开始使用 fileProvider 进行安装");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    this
                    , "com.compy.check.fileprovider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Log.w(TAG, "正常进行安装");
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }

    @Override
    public void initImmersionBar() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_home, R.id.ll_category, R.id.ll_service, R.id.ll_proudct, R.id.ll_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
                tabSelected(llHome);
                // ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true).init();
                break;
            case R.id.ll_category:
                selectedFragment(1);
                tabSelected(llCategory);
                //ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true).init();
                //  ImmersionBar.with(this).keyboardEnable(true).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn1).init();
                break;
            case R.id.ll_service:
                selectedFragment(2);
                tabSelected(llService);
                //ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true).init();
                break;
            case R.id.ll_proudct:
                selectedFragment(3);
                tabSelected(llProudct);
                // ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true).init();
                break;
            case R.id.ll_mine:
                selectedFragment(4);
                tabSelected(llMine);
                // ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true).init();
                break;
        }
    }

    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (homeFourFragment == null) {
                    homeFourFragment = new HomeFragment();
                    transaction.add(R.id.content, homeFourFragment);
                } else {
                    transaction.show(homeFourFragment);
                }
                break;
            case 1:
                if (categoryFourFragment == null) {
                    categoryFourFragment = new TwoFragment();
                    transaction.add(R.id.content, categoryFourFragment);
                } else {
                    transaction.show(categoryFourFragment);
                }
                break;
            case 2:
                if (serviceFourFragment == null) {
                    serviceFourFragment = new NewTeamFragment();
                    transaction.add(R.id.content, serviceFourFragment);
                } else {
                    transaction.show(serviceFourFragment);
                }
                break;
            case 3:
                if (mineFourFragment == null) {
                    mineFourFragment = new FourFragment();
                    transaction.add(R.id.content, mineFourFragment);
                } else {
                    transaction.show(mineFourFragment);
                }
                break;
            case 4:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.content, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeFourFragment != null) {
            transaction.hide(homeFourFragment);
        }
        if (categoryFourFragment != null) {
            transaction.hide(categoryFourFragment);
        }
        if (serviceFourFragment != null) {
            transaction.hide(serviceFourFragment);
        }
        if (mineFourFragment != null) {
            transaction.hide(mineFourFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }

    private void tabSelected(LinearLayout linearLayout) {
        llHome.setSelected(false);
        llCategory.setSelected(false);
        llService.setSelected(false);
        llMine.setSelected(false);
        llProudct.setSelected(false);
        linearLayout.setSelected(true);
    }

    public void getMenberInfo() {//读取会员信息,为安全起见, 系统只提供一部分会员信息, 并不提供所有的属性定义
        RxHttp.get("/v1/members/" + SPUtils.getInstance().getString("id", "0"))
                .asObject(BaseBean.class)
                .subscribeOn(Schedulers.io())
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        HideLoading();
                        if (baseBean.getHead().getCode() == 1) {
                            MenberInfoBean menberInfoBean = JSONObject.parseObject(JSONObject.toJSONString(baseBean), MenberInfoBean.class);


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
    public void onBackPressed() {
        CommonUtils.doubleClickExitApp();
    }

    private void checkVision() {

        RxHttp.get("/v1/common/version")
                .asObject(BaseBean.class)
                .as(RxLife.asOnMain(this))
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onNext(BaseBean BaseMsgBean) {
                        if (BaseMsgBean.getHead().getCode() == 1) {
                            UpDataBean homeListBean = JSONObject.parseObject(JSONObject.toJSONString(BaseMsgBean), UpDataBean.class);

                            String code = "" + CommonUtils.getVersionName(MainActivity.this);
                            if (code.equals(homeListBean.getBody().getData().getVersion())) {//无更新

                                // initSpecDialogNoNew();
                            } else {//更新

                                initDialogUpdate(homeListBean);

                            }


                        } else {
                            //initSpecDialogNoNew();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        // initSpecDialogNoNew();
                    }
                });
    }

    //规格参数dialog
    public void initDialogUpdate(UpDataBean upDataBean) {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_update);
        dialog_update = DialogPlus.newDialog(MainActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(false)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();
        Button btnyes = viewHolder.getInflatedView().findViewById(R.id.btnyes);
        Button btnno = viewHolder.getInflatedView().findViewById(R.id.btnno);
        // progressBar = viewHolder.getInflatedView().findViewById(R.id.progress_bar);
        TextView txttitle = viewHolder.getInflatedView().findViewById(R.id.txt);
        TextView txtcontent = viewHolder.getInflatedView().findViewById(R.id.txtcontent);

        txttitle.setText("New version " + upDataBean.getBody().getData().getVersion());
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upDataBean.getBody().getData().getStatus() == 0) {
                    RxActivityTool.finishAllActivity();
                    //根据进程ID，杀死该进程
                    android.os.Process.killProcess(android.os.Process.myPid());
                    //退出应用程序
                    System.exit(0);
                } else {

                    dialog_update.dismiss();
                }
            }
        });
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnyes.getText().toString().equals("Yes") || btnyes.getText().toString().equals("Fail")) {
                    btnyes.setText("Load...");

                    //downLoad(btnyes,upDataBean.getBody().getData().getDownload());
                    StrtDown(upDataBean.getBody().getData().getDownload(), btnyes);
                }


            }
        });


        dialog_update.show();
    }

    public void StrtDown(String url, Button textView) {
        downloadId1 = createDownloadTask(url, textView).start();
    }

    private BaseDownloadTask createDownloadTask(String url, Button textView) {
        final ViewHolder tag;

        boolean isDir = false;

        String path = getExternalCacheDir() + "/" + "zxm.apk";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }

        // tag = new ViewHolder(new WeakReference<>(this), progressBar1, null, speedTv1, 1);

        //tag.setFilenameTv(filenameTv1);


        return FileDownloader.getImpl().create(url.replace("https", "http"))
                .setPath(path, isDir)
                .setCallbackProgressTimes(3000)
                .setMinIntervalUpdateSpeed(400)
                .setListener(new FileDownloadSampleListener() {

                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.pending(task, soFarBytes, totalBytes);
                        // ((ViewHolder) task.getTag()).updatePending(task);
                        // textView.setText("");
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.progress(task, soFarBytes, totalBytes);
                        Double number = (Double.valueOf(soFarBytes) / Double.valueOf(totalBytes)) * 100;
                        System.out.println("DownLoad..." + soFarBytes + "/" + totalBytes + "/" + number);
                        textView.setText("Load.." + roundByScale(number, 1) + "%");
//                        ((ViewHolder) task.getTag()).updateProgress(soFarBytes, totalBytes,
//                                task.getSpeed());
                        if (totalBytes == -1) {
                            // chunked transfer encoding data
                            // progressBar.setIndeterminate(true);
                        } else {
                            //  progressBar.setMax(totalBytes);
                            //  progressBar.setProgress(soFarBytes);
                        }
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                        //  ToastUtils.showShort(e.toString());
                        StrtDown(url, textView);
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                        super.connected(task, etag, isContinue, soFarBytes, totalBytes);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.paused(task, soFarBytes, totalBytes);
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);
                        // ToastUtils.showShort("OK");
                        install(path);
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        super.warn(task);
                    }
                });
    }

    private void initNotice() {
        ViewHolder viewHolder = new ViewHolder(R.layout.dilog_homenotice);
        dialog_fail = DialogPlus.newDialog(MainActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setContentBackgroundResource(R.color.transparent)
                .setOverlayBackgroundResource(R.color.dialog_overlay_bg)
                .create();

        net.qiujuer.genius.ui.widget.Button btnok = viewHolder.getInflatedView().findViewById(R.id.btnok);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_fail.dismiss();

            }
        });

        dialog_fail.show();
    }
}