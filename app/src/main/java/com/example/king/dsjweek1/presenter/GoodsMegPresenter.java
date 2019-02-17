package com.example.king.dsjweek1.presenter;

import com.example.king.dsjweek1.contact.GoodsMegContact;
import com.example.king.dsjweek1.model.GoodsMegModel;
import com.example.king.dsjweek1.net.RetrofitUtilsCallBack;

import java.lang.ref.WeakReference;

public class GoodsMegPresenter {

    public GoodsMegContact.IGoodsMegView iGoodsMegView;
    public GoodsMegModel goodsMegModel;
    public WeakReference<GoodsMegContact.IGoodsMegView> weakReference;

    public GoodsMegPresenter(GoodsMegContact.IGoodsMegView iGoodsMegView) {
        this.goodsMegModel = new GoodsMegModel();
        this.weakReference = new WeakReference<>(iGoodsMegView);
        this.iGoodsMegView = weakReference.get();
    }

    public void setGoodsMegList(String api, String commodityId){
        goodsMegModel.getGoodsList(api, commodityId, new RetrofitUtilsCallBack() {
            @Override
            public void failure(String msg) {
                if (iGoodsMegView != null){
                    iGoodsMegView.failure(msg);
                }
            }

            @Override
            public void success(Object o) {
                if (iGoodsMegView != null){
                    iGoodsMegView.success(o);
                }

            }
        });
    }

    //解绑
    public void dettach(){
        if (weakReference != null){
            //晴空对象
            weakReference.clear();
            weakReference = null;
            iGoodsMegView = null;
        }
    }
}
