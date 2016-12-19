package com.chaungying.qiandao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.chaungying.BaseActivity;
import com.chaungying.news.adapter.NewsFragmentAdapter;
import com.chaungying.qiandao.view.LeaveListFragment;
import com.chaungying.qiandao.view.SinginListFragment;
import com.chaungying.wuye3.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * @author 王晓赛 or 2016/7/28
 */

@ContentView(R.layout.activity_qian_dao_tong_ji)
public class TongJiActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {
    // Tab布局
    @ViewInject(R.id.tongjiList_tabLayout1)
    private SlidingTabLayout tabLayout;


    // ViewPager
    @ViewInject(R.id.tongjiList_viewPager)
    private ViewPager viewPager;

    private ArrayList<Fragment> datas = new ArrayList<>();


    private SinginListFragment singinListFragment;
    private LeaveListFragment leaveListFragment;


    NewsFragmentAdapter newsFragmentAdapter;


    //查询某个人得Id
    public static int userId;

    //刷新每次的个数
    public static int refreshPageNum = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setActionBar(R.string.signin_statistical, R.drawable.nav_return, 0);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            userId = bundle.getInt("userId");
        }
        initTabView();
    }

    private void initTabView() {
        tabLayout.setOnTabSelectListener(this);

        // 添加数据
        singinListFragment = new SinginListFragment();
        leaveListFragment = new LeaveListFragment();
        datas.add(singinListFragment);
        datas.add(leaveListFragment);

        // 配置ViewPager
        newsFragmentAdapter = new NewsFragmentAdapter(getSupportFragmentManager(), datas);
        viewPager.setAdapter(newsFragmentAdapter);
        viewPager.setOnPageChangeListener(this);
        tabLayout.setViewPager(viewPager, new String[]{"签到", "请假"});
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {
        viewPager.setCurrentItem(position, true);
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


    @Event(R.id.title_back)
    private void back(View view) {
        finish();
    }

    /**
     * 取消广播
     */
    @Override
    public void onStop() {
        super.onStop();
    }

}
