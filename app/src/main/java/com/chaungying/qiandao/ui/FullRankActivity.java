package com.chaungying.qiandao.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chaungying.BaseActivity;
import com.chaungying.common.view.GrayLineView;
import com.chaungying.modues.main.ui.PersonalCardActivity;
import com.chaungying.qiandao.adapter.FullRankAdapter;
import com.chaungying.qiandao.bean.ManagerTongJiBean;
import com.chaungying.wuye3.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/11/28
 * 完整排行榜
 */

@ContentView(R.layout.activity_full_rank)
public class FullRankActivity extends BaseActivity {

    @ViewInject(R.id.lv_full_rank)
    private ListView lv_full_rank;

    private FullRankAdapter adapter;

    private ArrayList<ManagerTongJiBean.DataBean> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        setActionBar("排名", R.drawable.nav_return, 0);
        list = (ArrayList<ManagerTongJiBean.DataBean>) getIntent().getExtras().getSerializable("list");
        adapter = new FullRankAdapter(this);
        adapter.setList(list);
        lv_full_rank.setAdapter(adapter);
        lv_full_rank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                int userId = list.get(position).getUserId();
                String userName = list.get(position).getUserName();
                bundle.putInt("userId", userId);
                bundle.putString("userName",userName);
                openActivty(FullRankActivity.this, PersonalCardActivity.class, bundle, null);
            }
        });

        GrayLineView grayLineView = new GrayLineView(this);
        grayLineView.setLineHeight(1);
        grayLineView.setLineColor(getResources().getColor(R.color.huise3));
        lv_full_rank.addFooterView(grayLineView);
    }
}
