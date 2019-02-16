package com.example.king.dsjweek1.myview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.king.dsjweek1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchView extends ConstraintLayout {

    @BindView(R.id.cls)
    ImageView cls;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.tv_find)
    TextView tvFind;
    private SearchCallBack searchCallBack;
    private Unbinder bind;

    public void setSearchCallBack(SearchCallBack searchCallBack) {
        this.searchCallBack = searchCallBack;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_search, this, true);
        bind = ButterKnife.bind(this, view);
    }

    @OnClick({R.id.cls, R.id.ed_search, R.id.tv_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cls:
                break;
            case R.id.ed_search:
                break;
            case R.id.tv_find:
                String name = edSearch.getText().toString();
                searchCallBack.getGoodsName(name);
                break;
        }
    }

    public interface SearchCallBack{
        void getGoodsName(String name);
    }


}



