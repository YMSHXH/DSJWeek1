package com.example.king.dsjweek1.net;

import com.example.king.dsjweek1.entity.Goods;

public interface RetrofitUtilsCallBack {
    void failure(String msg);//服务器请求失败：断网，服务器宕机等等因素
    void success(Object o);
}
