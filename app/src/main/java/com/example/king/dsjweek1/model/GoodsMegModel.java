package com.example.king.dsjweek1.model;

import com.example.king.dsjweek1.contact.GoodsMegContact;
import com.example.king.dsjweek1.net.RetrofitUtils;
import com.example.king.dsjweek1.net.RetrofitUtilsCallBack;

public class GoodsMegModel implements GoodsMegContact.IGoodsMegModel {
    @Override
    public void getGoodsList(String api, String commodityId, RetrofitUtilsCallBack retrofitUtilsCallBack) {
        RetrofitUtils.getInstance().setGoodsMeg(api,commodityId,retrofitUtilsCallBack);
    }
}
