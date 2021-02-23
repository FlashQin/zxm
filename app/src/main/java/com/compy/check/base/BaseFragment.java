package com.compy.check.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.coder.zzq.smartshow.dialog.LoadingDialog;
import com.compy.check.R;
import com.gyf.immersionbar.ImmersionBar;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import kotlin.jvm.internal.Intrinsics;


public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    private Unbinder unBinder;
    private LoadingDialog loadingDialog;
    protected View statusBarView;
    protected Toolbar toolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //statusBarView = view.findViewById(R.id.status_bar_view);
        toolbar = view.findViewById(R.id.toolbar);
        fitsLayoutOverlap();

        initView(view);

        initData();
        setListener();

    }



    private final String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader((Reader)(new InputStreamReader(assetManager.open(fileName))));

            for(String line = bf.readLine(); line != null; line = bf.readLine()) {
                stringBuilder.append(line);
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        String var10000 = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(var10000, "stringBuilder.toString()");
        return var10000;
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
    public  byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetchData();
    }

    protected void initData() {

    }

    /**
     * 设置监听
     */
    protected void setListener() {

    }
    public void ShowLoading() {
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog().middle();
        loadingDialog.showInFragment(this);
    }

    public void HideLoading() {
        if (loadingDialog == null) return;
        loadingDialog.dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }



    protected abstract void initView(View view);

    protected abstract int getLayoutId();

    protected abstract void fetchData();

    public void Goto(Class<?> clz) {
        startActivity(new Intent(getContext(), clz), null);
    }

    public void Goto(Class<?> clz, int flag) {
        startActivity(new Intent(getContext(), clz).setFlags(flag), null);
    }

    public void Goto(Class<?> clz, String key, int value) {
        startActivity(new Intent(getContext(), clz).putExtra(key, value), null);
    }

    public void Goto(Class<?> clz, String key, String value) {
        startActivity(new Intent(getContext(), clz).putExtra(key, value), null);
    }

    public void Goto(Class<?> clz, String key, String value, String key1, String value1) {
        startActivity(new Intent(getContext(), clz).putExtra(key, value).putExtra(key1, value1), null);
    }

    public void Goto(Class<?> clz, String key, String value, String key1, int value1) {
        startActivity(new Intent(getContext(), clz).putExtra(key, value).putExtra(key1, value1), null);
    }

    public void Goto(Class<?> clz, String key, String value, String key1, String value1, String key2, String value2) {
        startActivity(new Intent(getContext(), clz).putExtra(key, value).putExtra(key1, value1).putExtra(key2, value2), null);
    }

    public void Goto(Class<?> clz, String key, int value, String key1, int value1, String key2, String value2) {
        startActivity(new Intent(getContext(), clz).putExtra(key, value).putExtra(key1, value1).putExtra(key2, value2), null);
    }
    public void Goto(Class<?> clz, String key, String value, String key1, String value1, String key2, String value2, String key3, String value3) {
        startActivity(new Intent(getContext(), clz).putExtra(key, value).putExtra(key1, value1).putExtra(key2, value2).putExtra(key3, value3), null);
    }
    public  String createJsonString(Map<String, Object> map, String key, Object value) {

        map.put(key, value);
        //将map集合转换为json字符串
        String result = JSON.toJSONString(map);
        return result;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    private void fitsLayoutOverlap() {
//        if (statusBarView != null) {
//            ImmersionBar.setStatusBarView(this, statusBarView);
//        } else {
        ImmersionBar.setTitleBar(this, toolbar);
//        }
    }
}
