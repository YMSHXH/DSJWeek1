package com.example.king.dsjweek1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.king.dsjweek1.R;
import com.example.king.dsjweek1.adapter.GoodsAdapter;
import com.example.king.dsjweek1.api.Api;
import com.example.king.dsjweek1.common.Commons;
import com.example.king.dsjweek1.contact.GoodsContact;
import com.example.king.dsjweek1.entity.Co_Goods;
import com.example.king.dsjweek1.entity.Goods;
import com.example.king.dsjweek1.greendao.Co_GoodsDao;
import com.example.king.dsjweek1.greendao.DaoSession;
import com.example.king.dsjweek1.myview.SearchView;
import com.example.king.dsjweek1.presenter.GoodsPresenter;
import com.example.king.dsjweek1.utils.GreendaoUtils;
import com.example.king.dsjweek1.utils.NetWorkUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements SearchView.SearchCallBack,
        GoodsContact.IGoodsView,
        XRecyclerView.LoadingListener,
        GoodsAdapter.GoodsADCallBack {

    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.xrecy)
    XRecyclerView xrecy;
    private GoodsPresenter goodsPresenter;
    private List<Goods.ResultBean> list;
    private GoodsAdapter goodsAdapter;
    private int page = 1;
    private Map<String, String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        //初始化数据库
        GreendaoUtils.getInstance().initGreenDao(this,Commons.DB_NNAME);

        if (!NetWorkUtils.isNetWork(this)) {
            Co_GoodsDao daoSession = GreendaoUtils.getInstance().getDaoSession().getCo_GoodsDao();//数据库
            List<Goods.ResultBean> result = new ArrayList<>();
            //查询数据库
            List<Co_Goods> co_goods = daoSession.loadAll();
            for (Co_Goods co_good : co_goods) {
                Goods.ResultBean resultBean = new Goods.ResultBean();
                resultBean.setCommodityId(co_good.getCommodityId());
                resultBean.setCommodityName(co_good.getCommodityName());
                resultBean.setMasterPic(co_good.getMasterPic());
                resultBean.setPrice(co_good.getPrice());
                resultBean.setSaleNum(co_good.getSaleNum());
                result.add(resultBean);
            }
            System.out.println("查询==="+ co_goods.size());
            goodsAdapter.setList(result);
            return;
        }

        goodsPresenter = new GoodsPresenter(this);
        params = new HashMap<>();
        list = new ArrayList<>();
        params.put("keyword", "手机");
        params.put("page", 1 + "");
        params.put("count", 10 + "");
        goodsPresenter.setGoodsList(Api.GOODS_API, params);

    }

    private void initView() {
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        searchView.setSearchCallBack(this);

        //设置xrecy样式
        xrecy.setLayoutManager(new GridLayoutManager(this,2));
        goodsAdapter = new GoodsAdapter(this);
        xrecy.setAdapter(goodsAdapter);

        //刷新加载
        xrecy.setLoadingListener(this);

        //点击事件
        goodsAdapter.setGoodsADCallBack(this);
    }

    @Override
    public void onRefresh() {
        params.put("page",1 + "");
        goodsPresenter.setGoodsList(Api.GOODS_API, params);
        xrecy.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        params.put("page",page + "");
        goodsPresenter.setGoodsList(Api.GOODS_API, params);
        xrecy.loadMoreComplete();
    }

    @Override
    public void getGoodsName(String name) {
        if (name != null && !"".equals(name)) {
            page = 1;
            params.put("keyword",name);
            params.put("page",page + "");
            goodsPresenter.setGoodsList(Api.GOODS_API, params);
        } else {
            Toast.makeText(this, "输入商品信息为空,请重新输入", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(Object o) {
        Goods goods = (Goods) o;
        //Toast.makeText(this,goods.getMessage(),Toast.LENGTH_SHORT).show();

        //List<Co_Goods> co_goods = new ArrayList<>();

        list = goods.getResult();

        if (page == 1) {
            goodsAdapter.setList(list);
        } else {
            goodsAdapter.addList(list);
        }
        page ++;
        int i = 1;
        //添加数据库
        Co_Goods co_goods = new Co_Goods();
        for (Goods.ResultBean resultBean : list) {
            Co_GoodsDao daoSession = GreendaoUtils.getInstance().getDaoSession().getCo_GoodsDao();//数据库
            co_goods.setId(i);
            co_goods.setCommodityId(resultBean.getCommodityId());
            co_goods.setCommodityName(resultBean.getCommodityName());
            co_goods.setMasterPic(resultBean.getMasterPic());
            co_goods.setPrice(resultBean.getPrice());
            co_goods.setSaleNum(resultBean.getSaleNum());
            daoSession.insertOrReplace(co_goods);
            System.out.println("添加==="+ co_goods.getCommodityName());
            i++;
        }
        Co_GoodsDao daoSession = GreendaoUtils.getInstance().getDaoSession().getCo_GoodsDao();//数据库
        List<Goods.ResultBean> result = new ArrayList<>();
        //查询数据库
        List<Co_Goods> co = daoSession.loadAll();
        System.out.println("查询2==="+ co.size());


    }


//    @Override
//    public void success(Goods goods) {
//        //Toast.makeText(this,goods.getMessage(),Toast.LENGTH_SHORT).show();
//        list = goods.getResult();
//        if (page == 1) {
//            goodsAdapter.setList(list);
//        } else {
//            goodsAdapter.addList(list);
//        }
//        page ++;
//    }

    @Override
    public void getCommodityId(String position) {
        EventBus.getDefault().postSticky(position);
        startActivity(new Intent(MainActivity.this,GoodsMegActivity.class));
    }
}
