package com.chaungying.qiandao.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chaungying.BaseFragment;
import com.chaungying.common.bean.FillListBean;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.gongzuotai.ui.MsgListDetailsActivity;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.qiandao.adapter.TongJi2Adapter;
import com.chaungying.qiandao.bean.TongJiBean;
import com.chaungying.qiandao.ui.TongJiActivity;
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
 * 签到中请假列表碎片
 */
@ContentView(R.layout.fragment_leave_list)
public class LeaveListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    // 列表
    @ViewInject(R.id.lv_leave_list)
    private ListView listView;

    private FillListBean fillListBean;

    private TongJi2Adapter tongJiAdapter;

    private List<List<FillListBean.DataBean>> list = new ArrayList<>();

    //获取到的签到数据
    ArrayList<TongJiBean.DataBean> list1 = new ArrayList<TongJiBean.DataBean>();

    private static final int SINGIN_APP_ID = 164;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        createFragment();
        return view;
    }

    private void createFragment() {
        // 初始化列表
        tongJiAdapter = new TongJi2Adapter(getContext());
        tongJiAdapter.setList(list1);
        listView.setAdapter(tongJiAdapter);
        listView.setOnItemClickListener(this);
        getDataList();
    }

    private void getDataList() {
        list1.clear();
        ProgressUtil.show(getActivity(), "加载中...");
        RequestParams params = new RequestParams(Const.WuYe.URL_LIST);
        params.addParameter("appId", SINGIN_APP_ID);//签到中请假、补假的appId
        if (TongJiActivity.userId == 0) {
            params.addParameter("userId", SPUtils.get(getActivity(), Const.SPDate.ID, -1));
        } else {
            params.addParameter("userId", TongJiActivity.userId);
        }
        params.addParameter("districtId", SPUtils.get(getContext(), Const.SPDate.USER_DISTRICT_ID, ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                fillListBean = gson.fromJson(result, FillListBean.class);
                list = fillListBean.getData();
                for (int i = 0; i < list.size(); i++) {
                    ArrayList<FillListBean.DataBean> itemList = (ArrayList<FillListBean.DataBean>) list.get(i);
                    TongJiBean.DataBean bean = new TongJiBean.DataBean();
                    for (int j = 0; j < itemList.size(); j++) {
                        if (j == 0) {
                            bean.setCreateTime(itemList.get(j).getValue());
                        } else if (j == 1) {
                            bean.setSignInAddress(itemList.get(j).getValue());
                        } else if (j == 2) {
                            bean.setWifiName(itemList.get(j).getValue());
                        }
                    }
                    bean.setLogicId(itemList.get(0).getLogicId());
                    list1.add(bean);
                }
                tongJiAdapter.notifyDataSetChanged();
                ProgressUtil.close();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("appId", SINGIN_APP_ID);
        bundle.putInt("logicId", list1.get(position).getLogicId());
        openActivty(getActivity(), MsgListDetailsActivity.class, bundle, null);
    }
}
