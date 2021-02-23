package com.compy.check.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.compy.check.R;
import com.compy.check.base.BaseActivity;
import com.compy.check.utlis.ProgressWebView;
import com.gyf.immersionbar.ImmersionBar;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.orhanobut.dialogplus.DialogPlus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WebActivity extends BaseActivity {

    @BindView(R.id.webview)
    ProgressWebView webview;
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.txttitle)
    TextView txttitle;
    @BindView(R.id.txtmore)
    TextView txtmore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private DialogPlus dialog_spec;
    private Object mAgentWeb;


    @Override
    public int getLayoutId() {
        return R.layout.activity_event;
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


        String name=getIntent().getStringExtra("name");
        if (name!=null){
            txttitle.setText(name);
        }else {
            txttitle.setText("Pay");
        }

       // String mData = getIntent().getStringExtra("json");
//        mAgentWeb = AgentWeb.with(this)
//                .setAgentWebParent(layoutWeb, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
//                .useDefaultIndicator()
//                .createAgentWeb()
//                .ready()
//                .go("file:///android_asset/" + mData);
         initWebView();


    }

    private void initWebView() {

        //1. Url的地址

        //2. 要提交的数据
        String mData = getIntent().getStringExtra("json");
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setAppCacheEnabled(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
        System.out.println("qqqqqqqqqqqqqqqqqqqqqq     "+mData);
        webview.loadUrl(mData);

      //  webview.loadUrl(mData);//本地模板

    }

    @OnClick({R.id.imgback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgback:
                onBackPressed();
                break;


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
