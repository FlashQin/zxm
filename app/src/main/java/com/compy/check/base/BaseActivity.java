package com.compy.check.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.coder.zzq.smartshow.dialog.LoadingDialog;
import com.compy.check.R;
import com.gyf.immersionbar.ImmersionBar;


import com.orhanobut.dialogplus.DialogPlus;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxKeyboardTool;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.Map;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import kotlin.jvm.internal.Intrinsics;


public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    private EditText editText;
    private CompositeDisposable disposable;

    private boolean isFullScreen = false;
    public DialogPlus dialog_shape;
    private boolean isAllowScreenRoate = false;
    private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConfigActivity();
        setContentView(this.getLayoutId());
        RxActivityTool.addActivity(this);
        ButterKnife.bind(this);

        disposable = new CompositeDisposable();
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
        //初始化沉浸式
        initImmersionBar();
        initView();
        initDate();
        smword();

    }

    public void smword() {
        ClassicsHeader.REFRESH_HEADER_PULLING = getString(R.string.refishpulldown);//"下拉可以刷新";
        ClassicsHeader.REFRESH_HEADER_REFRESHING = getString(R.string.refishpulldown);//"正在刷新...";
        ClassicsHeader.REFRESH_HEADER_LOADING = getString(R.string.refishtitle);//"正在加载...";
        ClassicsHeader.REFRESH_HEADER_RELEASE = getString(R.string.refishrelase);//"释放立即刷新";
        ClassicsHeader.REFRESH_HEADER_FINISH = getString(R.string.refishok);//"刷新完成";
        ClassicsHeader.REFRESH_HEADER_FAILED = getString(R.string.refishfail);//4"刷新失败";
        ClassicsHeader.REFRESH_HEADER_UPDATE = "\"'Last update at' M-d HH:mm\"";//"上次更新 M-d HH:mm";

        ClassicsFooter.REFRESH_FOOTER_PULLING = getString(R.string.loadmoretitle);//"上拉加载更多";
        ClassicsFooter.REFRESH_FOOTER_RELEASE = getString(R.string.loadmorerelasecontent);//"释放立即加载";
        ClassicsFooter.REFRESH_FOOTER_REFRESHING = getString(R.string.loadmoreloading);//"正在刷新...";
        ClassicsFooter.REFRESH_FOOTER_LOADING = getString(R.string.loadmoreloading);//"正在加载...";
        ClassicsFooter.REFRESH_FOOTER_FINISH = getString(R.string.loadmorehok);//"加载完成";
        ClassicsFooter.REFRESH_FOOTER_FAILED = getString(R.string.loadmorefail);//"加载失败";
        ClassicsFooter.REFRESH_FOOTER_NOTHING = getString(R.string.loadmorenodata);//"没有更多数据了";
    }


    public abstract void initImmersionBar();

    private final String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader((Reader) (new InputStreamReader(assetManager.open(fileName))));

            for (String line = bf.readLine(); line != null; line = bf.readLine()) {
                stringBuilder.append(line);
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        String var10000 = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(var10000, "stringBuilder.toString()");
        return var10000;
    }

    public void printLog(String e) {

        Log.d("------------------------------------------", e);

    }

    public String createJsonString(Map<String, Object> map, String key, Object value) {

        map.put(key, value);
        //将map集合转换为json字符串
        String result = JSON.toJSONString(map);
        return result;
    }

    /**
     * 显示键盘
     *
     * @param et 输入焦点
     */
    public void showInput(final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    public static String roundByScale(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        if (scale == 0) {
            return new DecimalFormat("0").format(v);
        }
        String formatStr = "0.";
        for (int i = 0; i < scale; i++) {
            formatStr = formatStr + "0";
        }
        return new DecimalFormat(formatStr).format(v);
    }

    public byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 富文本适配
     */
    private String getHtmlData(String bodyHTML) {
        //windowWidth = windowWidth/2;
        String css = "<style> img{max-width:100%;width:auto !important;height:auto !important;min-height:10px;} p{margin-top:0 !important;margin-bottom:0 !important;}</style>";
        String html = "<html><header>" + css + "</header><body style='margin:0;padding:0'>" + bodyHTML + "</body></html>";
        return html;
    }


    public void invokingBD(Context context, String addr) {

        //  com.baidu.BaiduMap这是高德地图的包名
        //调起百度地图客户端try {
        Intent intent = null;
        try {
            String uri = "intent://map/direction?origin=latlng:0,0|name:我的位置&destination=" + addr + "&mode=drivingion=" + "城市" + "&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";

            intent = Intent.getIntent(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (isInstallByread("com.baidu.BaiduMap")) {
            startActivity(intent); //启动调用
            Log.e("GasStation", "百度地图客户端已经安装");
        } else {
            Toast.makeText(context, "没有安装百度地图客户端", Toast.LENGTH_SHORT).show();
        }
    }

    public void invokingBDPostin(Context context, String addr, double lat, double lng) {

        //  com.baidu.BaiduMap这是高德地图的包名
        //调起百度地图客户端try {
        Intent intent = new Intent();

        String uri = "baidumap://map/navi?location=" + lat + "," + lng + "&src=andr.baidu.openAPIdemo";

        intent.setData(Uri.parse(uri));

        if (isInstallByread("com.baidu.BaiduMap")) {
            startActivity(intent); //启动调用
            Log.e("GasStation", "百度地图客户端已经安装");
        } else {
            Toast.makeText(context, "没有安装百度地图客户端", Toast.LENGTH_SHORT).show();
        }
    }

    public void invokingGD(Context context, String addr) {

        //  com.autonavi.minimap这是高德地图的包名
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("androidamap://navi?sourceApplication=应用名称&lat=" + "&dev=0"));
        intent.setPackage("com.autonavi.minimap");

        // Uri uri = Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=" + lat + "&slon=" + lng + "&sname=" + addr+ "&did=BGVIS2&dlat="+lat+"&dlon="+lng+"&dname="+addr+"&dev=0&t=0");
        intent.setData(Uri.parse("androidamap://poi?sourceApplication=softname&keywords=" + addr));
        // intent.setData(uri);
        if (isInstallByread("com.autonavi.minimap")) {
            startActivity(intent);
            Log.e("GasStation", "高德地图客户端已经安装");
        } else {
            Toast.makeText(context, "没有安装高德地图客户端", Toast.LENGTH_SHORT).show();
        }
    }

    public void invokingGDPostion(Context context, String addr, double lat, double lng) {

        //  com.autonavi.minimap这是高德地图的包名
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("androidamap://navi?sourceApplication=应用名称&lat=" + "&dev=0"));
        intent.setPackage("com.autonavi.minimap");

        Uri uri = Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=" + "" + "&slon=" + "" + "&sname=" + "" + "&did=BGVIS2&dlat=" + lat + "&dlon=" + lng + "&dname=" + addr + "&dev=0&t=0"
        );
        //  intent.setData(Uri.parse("androidamap://poi?sourceApplication=softname&keywords=" + addr));
        intent.setData(uri);
        if (isInstallByread("com.autonavi.minimap")) {
            startActivity(intent);
            Log.e("GasStation", "高德地图客户端已经安装");
        } else {
            Toast.makeText(context, "没有安装高德地图客户端", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 调用腾讯地图
     *
     * @param context 上下文对象s
     */
    public void invokeQQMap(Context context, String addr) {
        try {
            Uri uri = Uri.parse("qqmap://map/routeplan?type=drive" +
                    "&to=" + addr//终点的显示名称 必要参数
                    + "&tocoord="
                    + "&referer={你的应用名称}");
            Intent intent = new Intent();
            intent.setData(uri);

            context.startActivity(intent);
        } catch (Exception e) {
            //Logger.e(TAG, e.getMessage());
        }
    }

    public static void toTenCent(Context context, String type, String fromName, String fromcoord, String toName, String tocoord) {
        StringBuffer fromTo = new StringBuffer("qqmap://map/routeplan?type=");

        if (TextUtils.isEmpty(type))
            type = "drive";
        fromTo.append(type);

        if (!TextUtils.isEmpty(fromName))
            fromTo.append("&from=" + fromName);

        if (!TextUtils.isEmpty(fromcoord))
            fromTo.append("&fromcoord=" + fromcoord);

        if (!TextUtils.isEmpty(toName))
            fromTo.append("&to=" + toName);
        if (!TextUtils.isEmpty(tocoord))
            fromTo.append("&tocoord=" + tocoord);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        //将功能Scheme以URI的方式传入data
        intent.setData(Uri.parse(fromTo.toString()));
        context.startActivity(intent);

    }

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    public boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }


    private void ConfigActivity() {
        //是否全屏
        if (isFullScreen) {
            this.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        //是否允许旋转屏幕
        if (isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public void initDate() {
    }

    public void ShowLoading() {
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog().middle();
        loadingDialog.message("Loading...");
        loadingDialog.showInActivity(this);
    }

    public void HideLoading() {
        if (loadingDialog == null) return;
        loadingDialog.dismiss();
    }

    public void setAllowFullScreen(boolean allowFullScreen) {
        this.isFullScreen = allowFullScreen;
    }

    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

    public Context getContext() {
        return this;
    }

    public int getResouseColor(@ColorRes int colorId) {
        return getResources().getColor(colorId);
    }

    public Drawable getResouseDrawable(@DrawableRes int drawableId) {
        return getResources().getDrawable(drawableId);
    }

    public void Goto(Class<?> clz) {
        startActivity(new Intent(this, clz), null);
    }

    public void Goto(Intent intent) {
        startActivity(intent);
    }

    public void Goto(Context context, Class<?> clz) {
        context.startActivity(new Intent(context, clz), null);
    }

    public void Goto(Class<?> clz, String key, String value) {
        startActivity(new Intent(this, clz).putExtra(key, value), null);
    }

    public void Goto(Class<?> clz, String key, Serializable value) {
        startActivity(new Intent(this, clz).putExtra(key, value), null);
    }

    public void Goto(Class<?> clz, String key, int value, String key1, Serializable value1) {
        startActivity(new Intent(this, clz).putExtra(key, value).putExtra(key1, value1), null);
    }

    public void Goto(Class<?> clz, String key, String value, String key1, String value1) {
        startActivity(new Intent(this, clz).putExtra(key, value).putExtra(key1, value1), null);
    }

    public void Goto(Class<?> clz, String key, String value, String key1, int value1) {
        startActivity(new Intent(this, clz).putExtra(key, value).putExtra(key1, value1), null);
    }

    public void Goto(Class<?> clz, String key, int value, String key1, int value1) {
        startActivity(new Intent(this, clz).putExtra(key, value).putExtra(key1, value1), null);
    }

    public void Goto(Class<?> clz, String key, int value, String key1, int value1, String key2, String value2) {
        startActivity(new Intent(this, clz).putExtra(key, value).putExtra(key1, value1).putExtra(key2, value2), null);
    }

    public void Goto(Class<?> clz, String key, int value, String key1, int value1, String key2, int value2) {
        startActivity(new Intent(this, clz).putExtra(key, value).putExtra(key1, value1).putExtra(key2, value2), null);
    }

    public void Goto(Class<?> clz, String key0, String value0, String key1, String value1, String key2, String value2) {
        startActivity(new Intent(this, clz).putExtra(key0, value0).putExtra(key1, value1).putExtra(key2, value2), null);
    }

    public void Goto(Class<?> clz, String key0, int value0, String key1, String value1, String key2, int value2) {
        startActivity(new Intent(this, clz).putExtra(key0, value0).putExtra(key1, value1).putExtra(key2, value2), null);
    }

    public void Goto(Class<?> clz, String key0, String value0, String key1, String value1, String key2, String value2, String key3, String value3) {
        startActivity(new Intent(this, clz).putExtra(key0, value0).putExtra(key1, value1).putExtra(key2, value2).putExtra(key3, value3), null);
    }

    public void Goto(Class<?> clz, String key0, String value0, String key1, String value1, String key2, String value2, String key3, String value3, String key4, String value4) {
        startActivity(new Intent(this, clz).putExtra(key0, value0).putExtra(key1, value1).putExtra(key2, value2).putExtra(key3, value3).putExtra(key4, value4), null);
    }

    public void Goto(Class<?> clz, String key, int value) {
        startActivity(new Intent(this, clz).putExtra(key, value), null);
    }

    public void Goto(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void Goto(Class<?> clz, int flag) {
        startActivity(new Intent(this, clz).setFlags(flag), null);
    }

    public void Goto(Class<?> clz, int flag, String key, String value) {
        startActivity(new Intent(this, clz).setFlags(flag).putExtra(key, value), null);
    }

    public void Goto(Class<?> clz, Bundle bundle, int flag) {
        Intent intent = new Intent();
        intent.setFlags(flag);
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public String getToken() {
        return SPUtils.getInstance().getString("token");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            RxActivityTool.finishActivity();
        }
        return super.onKeyDown(keyCode, event);
    }

    //使editText点击外部时候失去焦点
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {//点击editText控件外部
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    assert v != null;
                    RxKeyboardTool.hideKeyboard(this, v);
                    if (editText != null) {
                        editText.clearFocus();
                    }
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            editText = (EditText) v;
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        HideLoading();
    }
}
