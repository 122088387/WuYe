package com.chaungying.modues.main.ui;

import android.os.Bundle;
import android.widget.ListView;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.modues.main.adapter.DepartmentRankAdapter;
import com.chaungying.modues.main.bean.DepartmentRankBean;
import com.chaungying.wuye3.R;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/6
 */

@ContentView(R.layout.activity_personal_department_rank)
public class DepartmentRankActivity extends BaseActivity {

    @ViewInject(R.id.lv_department_rank)
    private ListView lv_department_rank;

    private DepartmentRankAdapter adapter;

    private List<DepartmentRankBean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setActionBar(getIntent().getStringExtra("title"), R.drawable.nav_return, 0);
        getData(getIntent().getIntExtra("index", 0),getIntent().getStringExtra("date"));
        adapter = new DepartmentRankAdapter(this);
        adapter.setList(list);
        lv_department_rank.setAdapter(adapter);
    }

    private void getData(int index,String date) {
        list.clear();
        ProgressUtil.show(this, "加载中...");
        RequestParams params = new RequestParams(Const.WuYe.URL_USER_GET_DEPARTMENT_RANK);
        params.addParameter("date", date);
        params.addParameter("userId", SPUtils.get(this, Const.SPDate.ID, -1));
        params.addParameter("type", index + 1);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                DepartmentRankBean rankBean = new Gson().fromJson(result, DepartmentRankBean.class);
                list.addAll(rankBean.getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                ProgressUtil.close();
            }
        });
    }
}