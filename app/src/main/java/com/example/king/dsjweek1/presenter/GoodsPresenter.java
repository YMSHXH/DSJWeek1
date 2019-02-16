package com.example.king.dsjweek1.presenter;

import com.example.king.dsjweek1.contact.GoodsContact;
import com.example.king.dsjweek1.entity.Goods;
import com.example.king.dsjweek1.model.GoodsModel;
import com.example.king.dsjweek1.net.RetrofitUtilsCallBack;

import java.lang.ref.WeakReference;
import java.util.Map;

public class GoodsPresenter {

    public GoodsContact.IGoodsView iGoodsView;
    public GoodsModel goodsModel;
    public WeakReference<GoodsContact.IGoodsView> weakReference;

    public GoodsPresenter(GoodsContact.IGoodsView iGoodsView) {
        this.weakReference = new WeakReference<>(iGoodsView);
        this.goodsModel = new GoodsModel();
        this.iGoodsView = weakReference.get();
    }

    public void setGoodsList(String api, Map<String, String> params){
        goodsModel.getGoodsList(api, params, new RetrofitUtilsCallBack() {
            @Override
            public void failure(String msg) {
                if (iGoodsView != null){
                    iGoodsView.failure(msg);
                }
            }

            @Override
            public void success(Object o) {
                if (iGoodsView != null){
                    iGoodsView.success(o);
                }
            }

//            @Override
//            public void success(Goods goods) {
//                if (iGoodsView != null){
//                    iGoodsView.success(goods);
//                }
//            }
        });
    }

    //解绑
    public void dettach(){
        if (weakReference != null){
            //晴空对象
            weakReference.clear();
            weakReference = null;
            iGoodsView = null;
        }
    }
}
