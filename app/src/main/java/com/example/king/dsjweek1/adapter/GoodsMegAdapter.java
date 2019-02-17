package com.example.king.dsjweek1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.king.dsjweek1.R;
import com.example.king.dsjweek1.entity.GoodsMegEntity;
import com.github.chrisbanes.photoview.PhotoView;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsMegAdapter extends RecyclerView.Adapter<GoodsMegAdapter.GoodMegVH> {
    private Context context;
    private GoodsMegEntity.ResultBean result;
    private BannerCallBack bannerCallBack;

    public void setBannerCallBack(BannerCallBack bannerCallBack) {
        this.bannerCallBack = bannerCallBack;
    }

    public GoodsMegAdapter(Context context, GoodsMegEntity.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public GoodMegVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_goods_meg, viewGroup, false);
        GoodMegVH goodMegVH = new GoodMegVH(inflate);
        return goodMegVH;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodMegVH goodMegVH, int i) {
        String picture = result.getPicture();
        final String[] split = picture.split("\\,");
        final List<String> image = new ArrayList<>();
        for (String s : split) {
            image.add(s);
        }

        goodMegVH.xbanner.setData(image,null);
        goodMegVH.xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(context).load(image.get(position)).into((ImageView) view);
            }
        });
        goodMegVH.xbanner.stopAutoPlay();
        goodMegVH.xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                //跳转
                bannerCallBack.setPosition(image.get(position));
            }
        });

        goodMegVH.detailsName.setText(result.getCommodityName());
        goodMegVH.detailsPrice.setText("￥:"+result.getPrice());
        goodMegVH.detailsWebView.loadData(result.getDetails(),"text/html; charset=UTF-8", null);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class GoodMegVH extends RecyclerView.ViewHolder {
        @BindView(R.id.xbanner)
        XBanner xbanner;
        @BindView(R.id.details_price)
        TextView detailsPrice;
        @BindView(R.id.details_name)
        TextView detailsName;
        @BindView(R.id.details_webView)
        WebView detailsWebView;
        public GoodMegVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface BannerCallBack{
        void setPosition(String image);
    }
}
