package com.chaungying.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.news.adapter.NewsFragmentAdapter;
import com.chaungying.news.bean.MyPersonalBean;
import com.chaungying.news.bean.PersonalBean;
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
 * 时  间： 2016/12/15
 * 选择人员
 */

@ContentView(R.layout.activity_select_personal)
public class SelectPersonalActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    // Tab布局
    @ViewInject(R.id.personalList_tabLayout1)
    private SlidingTabLayout tabLayout;


    // ViewPager
    @ViewInject(R.id.personalList_viewPager)
    private ViewPager viewPager;

    @ViewInject(R.id.title_menu)
    private TextView title_menu;
    @ViewInject(R.id.title_back)
    private ImageView title_back;

    // 碎片适配器
    private NewsFragmentAdapter adapter;

    private ArrayList<Fragment> datas = new ArrayList<>();

    private SelectPersonalFragment fragmentFull;
    private SelectPersonalFragment fragmentDepartment;
    private SelectPersonalFragment fragmentRole;
    private SelectPersonalFragment fragmentPerson;


    private List<MyPersonalBean> list = new ArrayList<>();

    //上传数据的pushType标识
    private int pushType;
    private String ids = "";
    private String names = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        createView();
        getData();
    }

    private void getData() {
        ProgressUtil.show(this, "加载中...");
        RequestParams params = new RequestParams(Const.WuYe.URL_SELECTED_MEMBERS);
        params.addParameter("districtId", SPUtils.get(this, Const.SPDate.USER_DISTRICT_ID, ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                PersonalBean bean = new Gson().fromJson(result, PersonalBean.class);
                MyPersonalBean personalBean1 = new MyPersonalBean();
                personalBean1.setName("全部人员");
                personalBean1.setId(-1);
                personalBean1.setTag(1);
                list.add(personalBean1);
                for (int i = 0; i < bean.getDepartments().size(); i++) {
                    MyPersonalBean personalBean = new MyPersonalBean();
                    personalBean.setName(bean.getDepartments().get(i).getName());
                    personalBean.setId(bean.getDepartments().get(i).getId());
                    personalBean.setTag(2);
                    list.add(personalBean);
                }
                for (int i = 0; i < bean.getRoles().size(); i++) {
                    MyPersonalBean personalBean = new MyPersonalBean();
                    personalBean.setName(bean.getRoles().get(i).getName());
                    personalBean.setId(bean.getRoles().get(i).getId());
                    personalBean.setTag(3);
                    list.add(personalBean);
                }
                for (int i = 0; i < bean.getMembers().size(); i++) {
                    MyPersonalBean personalBean = new MyPersonalBean();
                    personalBean.setName(bean.getMembers().get(i).getUserName());
                    personalBean.setId(bean.getMembers().get(i).getId());
                    personalBean.setTag(4);
                    list.add(personalBean);
                }
                fragmentFull.setList(list);
                fragmentDepartment.setList(list);
                fragmentRole.setList(list);
                fragmentPerson.setList(list);
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


    public void updataState1(int tag) {
        pushType = tag;
        ids = "";
        names = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTag() != tag) {
                list.get(i).setCheck(false);
            } else if (list.get(i).isCheck()) {
                ids += list.get(i).getId() + ",";
                names += list.get(i).getName() + ",";
            }
        }
        for (int i = 0; i < datas.size(); i++) {
            ((SelectPersonalFragment) datas.get(i)).notifyAdapter();
        }
    }

    /**
     * 初始化
     */
    private void createView() {
        // 初始化Title
        setActionBarText(R.string.select_personal, R.drawable.nav_return, R.string.queding);

        tabLayout.setOnTabSelectListener(this);

        // 添加数据
        fragmentFull = new SelectPersonalFragment();
        fragmentDepartment = new SelectPersonalFragment();
        fragmentRole = new SelectPersonalFragment();
        fragmentPerson = new SelectPersonalFragment();

        datas.add(fragmentFull);
        datas.add(fragmentDepartment);
        datas.add(fragmentRole);
        datas.add(fragmentPerson);
        for (int i = 0; i < datas.size(); i++) {
            ((SelectPersonalFragment) datas.get(i)).setUpAlldata(new SelectPersonalFragment.UpAllData() {
                @Override
                public void updata(int tag) {
                    updataState1(tag);
                }
            });
            ((SelectPersonalFragment) datas.get(i)).setTag(i + 1);
        }

        // 配置ViewPager
        adapter = new NewsFragmentAdapter(getSupportFragmentManager(), datas);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(this);
        tabLayout.setViewPager(viewPager, new String[]{"全部", "部门", "角色", "人员"});
        //点击确定的时候
        title_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("pushType", pushType);
                if (ids.length() > 0) {
                    intent.putExtra("ids", ids.substring(0, ids.length() - 1));
                }
//                intent.putExtra("names", names.substring(0, names.length()-1));
                String tempName = pushType == 1 ? "全部" : pushType == 2 ? "部门" : pushType == 3 ? "角色" : pushType == 4 ? "人员" : "选择人员";
                intent.putExtra("names", tempName);
                setResult(RESULT_OK, intent);
                finish();
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
        viewPager.setCurrentItem(position, false);
    }

    @Override
    public void onTabReselect(int position) {

    }
}
