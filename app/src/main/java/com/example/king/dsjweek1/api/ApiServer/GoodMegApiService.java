package com.example.king.dsjweek1.api.ApiServer;

import com.example.king.dsjweek1.entity.GoodsMegEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface GoodMegApiService {
    @GET
    Call<GoodsMegEntity> megReg(@Url String api, @Query("commodityId")String commodityId);
}
