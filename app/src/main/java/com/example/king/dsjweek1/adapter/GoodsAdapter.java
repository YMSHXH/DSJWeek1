package com.example.king.dsjweek1.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.king.dsjweek1.R;
import com.example.king.dsjweek1.entity.Goods;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Url;

public class GoodsAdapter extends XRecyclerView.Adapter<GoodsAdapter.GoodsAdapterVH> {

    private Context context;
    private List<Goods.ResultBean> list;
    private GoodsADCallBack goodsADCallBack;

    public void setGoodsADCallBack(GoodsADCallBack goodsADCallBack) {
        this.goodsADCallBack = goodsADCallBack;
    }

    public GoodsAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    //刷新
    public void setList(List<Goods.ResultBean> list) {
        if (context != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    //加载
    public void addList(List<Goods.ResultBean> list) {
        if (context != null) {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods, viewGroup, false);
        GoodsAdapterVH goodsAdapterVH = new GoodsAdapterVH(view);
        return goodsAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsAdapterVH goodsAdapterVH, int i) {
        final Goods.ResultBean resultBean = list.get(i);
        goodsAdapterVH.serTitle.setText(resultBean.getCommodityName());
        goodsAdapterVH.serPrice.setText("￥:"+resultBean.getPrice());
        goodsAdapterVH.serSum.setText(resultBean.getSaleNum());
        Uri uri = Uri.parse(resultBean.getMasterPic());
        goodsAdapterVH.serImg.setImageURI(uri);
        //圆角
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(15);
        goodsAdapterVH.serImg.getHierarchy().setRoundingParams(roundingParams);
        //跳转监听
        goodsAdapterVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsADCallBack.getCommodityId(resultBean.getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GoodsAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.ser_img)
        SimpleDraweeView serImg;
        @BindView(R.id.ser_title)
        TextView serTitle;
        @BindView(R.id.ser_price)
        TextView serPrice;
        @BindView(R.id.ser_sum)
        TextView serSum;
        public GoodsAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface GoodsADCallBack{
        void getCommodityId(String position);
    }
}
