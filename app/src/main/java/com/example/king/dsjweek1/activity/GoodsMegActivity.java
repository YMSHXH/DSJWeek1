package com.example.king.dsjweek1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.king.dsjweek1.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GoodsMegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_meg);
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void getCommodityId(String position){
        Toast.makeText(this,position,Toast.LENGTH_SHORT).show();
    }
}
