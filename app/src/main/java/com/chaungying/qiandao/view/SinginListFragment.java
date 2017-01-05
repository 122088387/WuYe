package com.chaungying.qiandao.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.common.utils.T;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.qiandao.adapter.TongJiAdapter;
import com.chaungying.qiandao.adapter.TongJiListener;
import com.chaungying.qiandao.bean.TongJiBean;
import com.chaungying.site_repairs.view.PullToRefreshLayout;
import com.chaungying.site_repairs.view.PullableListView1;
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
 * 签到中签到列表碎片
 */
@ContentView(R.layout.fragment_singin_list)
public class SinginListFragment extends Fragment {


    //判断是否是个人名片页面跳转过来的
    public static  String card;

    //查询的月份
    public static String signInDate;

    //查询某个人得Id
    public static int userId;

    //刷新的页数
    public int refreshPage = 1;
    //刷新每次的个数
    public static int refreshPageNum = 10;

    //支持刷新的布局
    @ViewInject(R.id.refresh_view)
    private PullToRefreshLayout layout;
    //支持刷新的listView
    @ViewInject(R.id.lv_tong_ji)
    private PullableListView1 lvTongJi;

    private final int UPDATA = 0;

    //获取到的签到数据
    ArrayList<TongJiBean.DataBean> list = new ArrayList<TongJiBean.DataBean>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATA:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    TongJiAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        createFragment();
        return view;
    }

    private void createFragment() {
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            card = bundle.getString("card");
            signInDate = bundle.getString("signInDate");
            userId = bundle.getInt("userId");
        }
        getSigInData();
        adapter = new TongJiAdapter(getActivity());
        adapter.setList(list);
        lvTongJi.setAdapter(adapter);
        layout.setOnRefreshListener(new TongJiListener(getActivity()));
//        layout.setCanPullDown(false);
        //注册加载数据之后的广播接收器
        registerBoradcastReceiver();

    }

    /**
     * 广播接收器
     */
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(ACTION_NAME);
        //注册广播
        getActivity().registerReceiver(receiverRefresh, myIntentFilter);
    }

    /**
     * 定义接收 刷新数据的广播接收器
     */
    private BroadcastReceiver receiverRefresh = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_NAME)) {
                jsonToBean(intent.getStringExtra("json"));
            }
        }
    };

    /**
     * 将result json串变为对象 添加到集合中，更新适配器
     *
     * @param result
     */
    public void jsonToBean(String result) {
        Gson gson = new Gson();
        TongJiBean bean = gson.fromJson(result, TongJiBean.class);
        List<TongJiBean.DataBean> dataBeanList = (ArrayList<TongJiBean.DataBean>) bean.getData();
        //将刷新出来的数据集合添加进去
        if (list != null) {
            if (dataBeanList != null && dataBeanList.size() > 0) {
                T.showShort(getActivity(), "加载成功");
                list.addAll(dataBeanList);
                setBeanTag();
            } else {
                T.showShort(getActivity(), "没有更多数据");
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 给数据设置标志
     */
    private void setBeanTag() {
        for (int position = 0; position < list.size(); position++) {
            if (position == 0) {
                list.get(position).setShow(true);
            }
            if (position > 0) {
                TongJiBean.DataBean bean1 = list.get(position);
                TongJiBean.DataBean upBean = list.get(position - 1);
                if (!(bean1.getSignInDate() == null ? "" : bean1.getSignInDate()).equals(upBean.getSignInDate())) {
                    bean1.setShow(true);
                } else {
                    bean1.setShow(false);
                }
            }
        }
    }

    /**
     * 接收刷新数据的标志
     */
    private final String ACTION_NAME = "刷新数据";

    private void getSigInData() {
        list.clear();
        ProgressUtil.show(getActivity(), "加载中...");
        RequestParams pamas = new RequestParams(Const.WuYe.URL_SIGN_STATISTICAL);
        pamas.setConnectTimeout(5 * 1000);
//        page=页号（默认1）&row=行数（默认50）&memberId=用户id
        pamas.addParameter("page", refreshPage);
        pamas.addParameter("row", refreshPageNum);
        //是个人名片跳转过来的话 会加一个日期参数，否则就是签到中的统计跳过来不用加参数
        if (card != null && card.equals("card")) {
            pamas.addParameter("signInDate", signInDate);
        }
        if (userId == 0) {
            pamas.addParameter("memberId", SPUtils.get(getActivity(), Const.SPDate.ID, -1));
        } else {
            pamas.addParameter("memberId", userId);
        }
        pamas.addParameter("districtId", SPUtils.get(getContext(), Const.SPDate.USER_DISTRICT_ID, ""));
        x.http().post(pamas, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                TongJiBean bean = gson.fromJson(result, TongJiBean.class);
                adapter.setIsAdmin(bean.getIsAdmin());
                list.addAll(bean.getData());
                if (list != null) {
                    setBeanTag();
                    handler.sendEmptyMessage(UPDATA);
                } else {
                    T.showShort(getActivity(), "暂无数据");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                ProgressUtil.hide();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiverRefresh);
        refreshPage = 0;
        if (list != null) {
            list.clear();
        }
    }
}
