package com.example.king.dsjweek1.model;

import com.example.king.dsjweek1.contact.GoodsContact;
import com.example.king.dsjweek1.entity.Goods;
import com.example.king.dsjweek1.net.GoodApiService;
import com.example.king.dsjweek1.net.RetrofitUtils;
import com.example.king.dsjweek1.net.RetrofitUtilsCallBack;

import java.util.Map;

import retrofit2.Call;

public class GoodsModel implements GoodsContact.IGoodsModel {

    @Override
    public void getGoodsList(String api, Map<String, String> params, RetrofitUtilsCallBack retrofitUtilsCallBack) {

//        GoodApiService goodApiService = RetrofitUtils.getInstance().retrofit.create(GoodApiService.class);
//        //第三步，创建请求对象
//        Call reqs = goodApiService.reqs(api, params);
//
//        RetrofitUtils.getInstance().setGoods(reqs,retrofitUtilsCallBack);
        RetrofitUtils.getInstance().setGoods(api,params,retrofitUtilsCallBack);

    }
}
