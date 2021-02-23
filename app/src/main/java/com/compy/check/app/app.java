package com.compy.check.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.bumptech.glide.Glide;

import com.coder.zzq.smartshow.core.SmartShow;
import com.compy.check.net.NetWorkUtils;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


import java.util.Objects;

import io.reactivex.plugins.RxJavaPlugins;


public class app extends Application {

    public Context mContext;


    static {//使用static代码段可以防止内存泄漏

        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                //开始设置全局的基本参数（可以被下面的DefaultRefreshHeaderCreator覆盖）
//                layout.autoRefresh();
                layout.setEnableLoadMore(true);
                layout.setEnableFooterTranslationContent(true);
                layout.setEnableHeaderTranslationContent(true);
                layout.setDisableContentWhenRefresh(true);//是否在刷新的时候禁止列表的操作
                layout.setDisableContentWhenLoading(true);//是否在加载的时候禁止列表的操作
            }
        });

        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
                layout.setEnableHeaderTranslationContent(true);
                return new ClassicsHeader(context);
            }
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableFooterTranslationContent(true);
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        //refWatcher = LeakCanary.install(this);

        SmartShow.init(this);
        //logger初始化
        Logger.addLogAdapter(new AndroidLogAdapter());
        //RxJava全局报错捕获
        RxJavaPlugins.setErrorHandler(throwable -> Log.e("err", Objects.requireNonNull(throwable.getMessage())));
        //UtilCode初始化
     //   Utils.init(this);
        //初始化RxHttp
        NetWorkUtils.initNetWork();
        //CrashReport.initCrashReport(getApplicationContext(), "8ce22b980c", false);
        FileDownloader.setupOnApplicationOnCreate(this)
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                ))
                .commit();
    }


    /**
     * 返回当前程序版本名
     */
    public String getAppVersionName() {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
}
