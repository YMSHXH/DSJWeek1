package com.example.king.dsjweek1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.king.dsjweek1.R;
import com.example.king.dsjweek1.adapter.GoodsMegAdapter;
import com.example.king.dsjweek1.api.Api;
import com.example.king.dsjweek1.contact.GoodsMegContact;
import com.example.king.dsjweek1.entity.GoodsMegEntity;
import com.example.king.dsjweek1.presenter.GoodsMegPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsMegActivity extends AppCompatActivity
        implements GoodsMegContact.IGoodsMegView {

    @BindView(R.id.recy)
    RecyclerView recy;
    private String position;
    private GoodsMegPresenter goodsMegPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_meg);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        initView();
        initData();

    }

    private void initView() {
        recy.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initData() {
        goodsMegPresenter = new GoodsMegPresenter(this);
        goodsMegPresenter.setGoodsMegList(Api.GOODS_MESSAGE_API, position);
    }


    @Subscribe(sticky = true)
    public void getCommodityId(String position) {
        this.position = position;
        //Toast.makeText(this,position,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(Object o) {
        GoodsMegEntity goodsMegEntity = (GoodsMegEntity) o;
        Toast.makeText(this, goodsMegEntity.getMessage(), Toast.LENGTH_SHORT).show();
        GoodsMegEntity.ResultBean result = goodsMegEntity.getResult();
        //设置适配器
        GoodsMegAdapter goodsMegAdapter = new GoodsMegAdapter(this,result);
        recy.setAdapter(goodsMegAdapter);

    }
}
