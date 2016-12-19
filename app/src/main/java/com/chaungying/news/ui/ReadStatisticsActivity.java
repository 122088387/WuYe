package com.chaungying.news.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Const;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.news.adapter.NewsFragmentAdapter;
import com.chaungying.news.bean.ReadDetailsBean;
import com.chaungying.wuye3.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
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
 * 时  间： 2016/12/14
 */

@ContentView(R.layout.activity_read_statistics)
public class ReadStatisticsActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {


    private long activityId;

    // Tab布局
    @ViewInject(R.id.readList_tabLayout1)
    private SlidingTabLayout tabLayout;

    // ViewPager
    @ViewInject(R.id.readList_viewPager)
    private ViewPager viewPager;

    private ArrayList<Fragment> datas = new ArrayList<>();

    private ReadListFragment readListFragment;
    private ReadListFragment readListFragment1;

    // 碎片适配器
    private NewsFragmentAdapter adapter;

    private List<ReadDetailsBean.DataBean> readList = new ArrayList<>();
    private List<ReadDetailsBean.DataBean> unReadList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        getData();
    }

    private void initView() {
        setActionBar("阅读情况", R.drawable.nav_return, 0);
        activityId = getIntent().getLongExtra("activityId", -1);

        tabLayout.setOnTabSelectListener(this);
        // 添加数据
        readListFragment = new ReadListFragment();
        readListFragment1 = new ReadListFragment();
        datas.add(readListFragment);
        datas.add(readListFragment1);

        // 配置ViewPager
        adapter = new NewsFragmentAdapter(getSupportFragmentManager(), datas);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);

        tabLayout.setViewPager(viewPager, new String[]{"已阅读", "未阅读"});
    }

    private void getData() {
        ProgressUtil.show(this, "");
        readList.clear();
        unReadList.clear();
        RequestParams params = new RequestParams(Const.WuYe.URL_SHOW_READ_CHAR_LIST);
        params.addParameter("activityId", activityId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ReadDetailsBean bean = new Gson().fromJson(result, ReadDetailsBean.class);
                List<ReadDetailsBean.DataBean> list = bean.getData();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getState() == 0) {//已读
                        readList.add(list.get(i));
                    } else if (list.get(i).getState() == 1) {//未读
                        unReadList.add(list.get(i));
                    }

                }
                readListFragment.setReadList(readList);
                readListFragment1.setReadList(unReadList);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabLayout.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelect(int position) {
        viewPager.setCurrentItem(position, true);
    }

    @Override
    public void onTabReselect(int position) {

    }
}
