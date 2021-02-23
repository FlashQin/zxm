package com.compy.check.net;

import rxhttp.wrapper.annotation.DefaultDomain;
import rxhttp.wrapper.annotation.Domain;

public class Url {

    @DefaultDomain()
    public static String baseUrl = "http://api.zxm88.net";//https://api.youchengchefu.com/
    // @DefaultDomain()
    //public static String baseUrl = "https://api.youpin.kaifa.fun/";//c/
    @Domain(name = "test")
    public static String update = "http://www.baidu.com/";

}