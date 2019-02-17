package com.example.king.dsjweek1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.king.dsjweek1.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PhoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pho_view);
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void getCommodityId(String imageUrl) {
        Toast.makeText(this,imageUrl,Toast.LENGTH_SHORT).show();
    }
}
