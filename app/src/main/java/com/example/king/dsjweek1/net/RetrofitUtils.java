package com.example.king.dsjweek1.net;

import com.example.king.dsjweek1.api.Api;
import com.example.king.dsjweek1.api.ApiServer.GoodApiService;
import com.example.king.dsjweek1.api.ApiServer.GoodMegApiService;
import com.example.king.dsjweek1.entity.Goods;
import com.example.king.dsjweek1.entity.GoodsMegEntity;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static RetrofitUtils instance;
    private final OkHttpClient okHttpClient;
    public final Retrofit retrofit;

    private RetrofitUtils() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitUtils getInstance(){
        if (instance == null) {
            synchronized (RetrofitUtils.class){
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public void setGoods(String api, Map<String,String> params, final RetrofitUtilsCallBack retrofitUtilsCallBack){
        //模式：外观模式
        //设计模式：构建者模式
        //第二步 ,创建请求接口类对象，体现一个动态代理模式
        GoodApiService goodApiService = retrofit.create(GoodApiService.class);
        //第三步，创建请求对象
        Call<Goods> reqs = goodApiService.reqs(api, params);

        reqs.enqueue(new Callback<Goods>() {
            @Override
            public void onResponse(Call<Goods> call, Response<Goods> response) {
                Goods body = response.body();
                retrofitUtilsCallBack.success(body);
            }

            @Override
            public void onFailure(Call<Goods> call, Throwable t) {
                retrofitUtilsCallBack.failure("网络错误");
            }
        });
    }

    public void setGoodsMeg(String api, String commodityId, final RetrofitUtilsCallBack retrofitUtilsCallBack){
        //模式：外观模式
        //设计模式：构建者模式
        //第二步 ,创建请求接口类对象，体现一个动态代理模式
        GoodMegApiService goodMegApiService = retrofit.create(GoodMegApiService.class);
        Call<GoodsMegEntity> goodsMegEntityCall = goodMegApiService.megReg(api, commodityId);
        goodsMegEntityCall.enqueue(new Callback<GoodsMegEntity>() {
            @Override
            public void onResponse(Call<GoodsMegEntity> call, Response<GoodsMegEntity> response) {
                GoodsMegEntity body = response.body();
                retrofitUtilsCallBack.success(body);
            }

            @Override
            public void onFailure(Call<GoodsMegEntity> call, Throwable t) {
                retrofitUtilsCallBack.failure("网络错误");
            }
        });
    }

//    public void setGoods(Call reqs, final RetrofitUtilsCallBack retrofitUtilsCallBack) {
//        reqs.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                Object body = response.body();
//                retrofitUtilsCallBack.success(body);
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                retrofitUtilsCallBack.failure("网络错误");
//            }
//        });
//    }
}
