package com.compy.check.net;

import android.content.Intent;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.compy.check.MainActivity;
import com.compy.check.bean.BaseBean;
import com.compy.check.utlis.ACache;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

public class AuthInterceptor implements Interceptor {
    //token刷新时间
    private static volatile long SESSION_KEY_REFRESH_TIME = 0;
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        Response response = chain.proceed(request);
//        ResponseBody responseBody = response.body();
//        BufferedSource source = responseBody.source();
//        source.request(Long.MAX_VALUE); // Buffer the entire body.
//        Buffer buffer = source.buffer();
//        Charset UTF8 = Charset.forName("UTF-8");
//        String data = buffer.clone().readString(UTF8);//采用复制的方式读取，否则会导致请求异常!
//        //这判断根据你们后台的具体返回数据进行判断。(我们后台会返回token 失效,我以此进行判断，你们也可以让后台返回特定的code进行判断)
//        BaseBean baseSocketBean = new Gson().fromJson(data, BaseBean.class);



       // if (baseSocketBean.getHead().getCount().equals("3")) {
            //EventBus.getDefault().post(new BaseEvent());//这里发送一个通知到baseActivity去处理响应的跳转逻辑
//            ACache.get(Utils.getApp()).remove("USER_BEAN");
//            SPUtils.getInstance().remove("TOKEN");
//            Utils.getApp().startActivity(new Intent(Utils.getApp(), MainActivity.class));
      //  }
        //以下是官方得 token失效处理办法
//        String code = response.header("token_code");
//        if ("-1".equals(code)) { //token 失效  1、这里根据自己的业务需求写判断条件
//            return handleTokenInvalid(chain, request);
//        }
        return chain.proceed(request);
    }

    //处理token失效问题
    private Response handleTokenInvalid(Chain chain, Request request) throws IOException {
        HashMap<String, String> mapParam = new HashMap<>();
        RequestBody body = request.body();
        if (body instanceof FormBody) {
            FormBody formBody = (FormBody) body;
            for (int i = 0; i < formBody.size(); i++) {
                mapParam.put(formBody.name(i), formBody.value(i));  //2、保存参数
            }
        }
        //同步刷新token
        String requestTime = mapParam.get("request_time");  //3、发请求前需要add("request_time",System.currentTimeMillis())
        boolean success = refreshToken(requestTime);
        Request newRequest = null;
        if (success) { //刷新成功，重新签名
            //  mapParam.put("token", User.get().getToken()); //4、拿到最新的token,重新发起请求
//            newRequest = RxHttp.postForm(request.url().toString())
//                    .add(mapParam) //添加参数
//                    .buildRequest();
        } else {
            newRequest = request;
        }
        return chain.proceed(newRequest);
    }

    //刷新token
    private boolean refreshToken(Object value) {
        long requestTime = 0;
        try {
            requestTime = Integer.parseInt(value.toString());
        } catch (Exception ignore) {
        }
        //请求时间小于token刷新时间，说明token已经刷新，则无需再次刷新
        if (requestTime <= SESSION_KEY_REFRESH_TIME) return true;
        synchronized (this) {
            //再次判断是否已经刷新
            if (requestTime <= SESSION_KEY_REFRESH_TIME) return true;
            try {
                //获取到最新的token，这里需要同步请求token,千万不能异步  5、根据自己的业务修改
                String token = RxHttp.postForm("/refreshToken/...")
                        .execute(SimpleParser.get(String.class));

                SESSION_KEY_REFRESH_TIME = System.currentTimeMillis() / 1000;
                // User.get().setToken(token); //保存最新的token
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }
}
