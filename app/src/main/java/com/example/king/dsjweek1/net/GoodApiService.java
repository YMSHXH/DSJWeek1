package com.example.king.dsjweek1.net;

import com.example.king.dsjweek1.entity.Goods;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface GoodApiService {

    @GET
    Call<Goods> reqs (@Url String api, @QueryMap Map<String,String> params);
}
