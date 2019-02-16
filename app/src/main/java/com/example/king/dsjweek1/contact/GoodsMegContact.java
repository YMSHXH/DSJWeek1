package com.example.king.dsjweek1.contact;

import com.example.king.dsjweek1.net.RetrofitUtilsCallBack;

import java.util.Map;

public interface GoodsMegContact {
    interface IGoodsMegModel{
        void getGoodsList(String api,String commodityId, RetrofitUtilsCallBack retrofitUtilsCallBack);
    }

    interface IGoodsMegView{
        void failure(String msg);//服务器请求失败：断网，服务器宕机等等因素
        void success(Object o);
    }
}
