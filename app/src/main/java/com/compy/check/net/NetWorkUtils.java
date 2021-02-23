package com.compy.check.net;

import com.blankj.utilcode.util.SPUtils;

import java.net.Socket;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import rxhttp.wrapper.param.DeleteRequest;
import rxhttp.wrapper.param.GetRequest;
import rxhttp.wrapper.param.PostRequest;
import rxhttp.wrapper.param.PutRequest;
import rxhttp.wrapper.param.RxHttp;

public class NetWorkUtils {

    private static OkHttpClient okHttpClient;

    public static void initNetWork() {
        //Cache cache = new Cache(Utils.getApp().getCacheDir(), 10 * 1024 * 1024);
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(25, TimeUnit.SECONDS)
                .readTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                 // .cache(cache)
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
               // .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .addInterceptor(new AuthInterceptor())
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    return chain.proceed(request);
                })
                .build();

        RxHttp.init(okHttpClient, true);
        RxHttp.setOnParamAssembly(param -> {
            //根据不同请求添加不同参数
            if (param instanceof GetRequest) {

            } else if (param instanceof PostRequest) {

            } else if (param instanceof PutRequest) {

            } else if (param instanceof DeleteRequest) {

            }
            String token = SPUtils.getInstance().getString("TOKEN", "");
            System.out.println("tokennn/"+token);
            if (!token.equals("")) {
                return param.addHeader("token", token)
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
//                        .addHeader("Connection", "keep-alive")
                        .addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
            } else {
                return param.addHeader("Content-Type", "application/json;charset=UTF-8")
//                        .addHeader("Connection", "keep-alive")
                        .addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
            }
        });
    }

    //信任所有的服务器,返回true
    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    //获取这个SSLSocketFactory
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {}
        return ssfFactory;
    }

    public static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
    }

}
