package com.chaungying.address.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chaungying.BaseActivity;
import com.chaungying.address.adapter.DepartmentAdapter;
import com.chaungying.address.bean.DataBean;
import com.chaungying.address.bean.GardenContactBean;
import com.chaungying.address.bean.PersonListBean;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.common.utils.T;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.wuye3.R;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王晓赛 or 2016/8/26
 */
@ContentView(R.layout.activity_department)
public class DepartmentActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    @ViewInject(R.id.lv_contact)
    ListView listView;

    DepartmentAdapter departmentAdapter;

    private int id;//第一个界面传过来的id

    private GardenContactBean gardenContactBean;

    List<DataBean> tempList = new ArrayList<DataBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setActionBar("通讯录", R.drawable.nav_return, 0);
        id = getIntent().getIntExtra("id", 0);//默认id是0
        tempList = DataSupport.where("pId=?", id + "").find(DataBean.class);

        departmentAdapter = new DepartmentAdapter(this);
        departmentAdapter.setList(tempList);
        listView.setAdapter(departmentAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 在级别列表页面请求数据，判断下个界面是否有人员列表
     *
     * @param departmentId
     */
    private void getData(final String departmentId) {
        ProgressUtil.show(this, "加载中...");
        RequestParams params = new RequestParams(Const.WuYe.URL_ADDRESS_ALL_CONTANCTS);
        params.addParameter("districtId", SPUtils.get(this, Const.SPDate.USER_DISTRICT_ID, ""));
        params.addParameter("departmentId", departmentId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                PersonListBean personListBean = gson.fromJson(result, PersonListBean.class);
                int size = personListBean.getData().size();
                if (size == 0) {
                    T.showShort(DepartmentActivity.this, "暂无人员列表");
                } else {
                    Intent intent = new Intent(DepartmentActivity.this, PersonListActivity.class);
                    intent.putExtra("departmentId", departmentId);
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
                ProgressUtil.close();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DataBean bean = departmentAdapter.getList().get(position);
        if (DataSupport.where("pId=?", bean.getId() + "").find(DataBean.class).size() == 0) {
            //如果没有下一级部门，则请求人员列表
            getData(bean.getId() + "");
        } else {
            Intent intent = new Intent(DepartmentActivity.this, DepartmentActivity.class);
            intent.putExtra("id", bean.getId());
            startActivity(intent);
        }
//        String isShowMembers = gardenContactBean.getIsShowMembers();
//        String isNextUrlParam = gardenContactBean.getIsNextUrlParam();
//        String requestUrl = gardenContactBean.getRequestUrl();
//        String isShowMembersParam = gardenContactBean.getIsShowMembersParam();
//        String paramKey = gardenContactBean.getParamKey();
//        int val = gardenContactBean.getData().get(position).getVal();
//
//        if (isShowMembers.equals("1")) {//不是人员列表
//
//            if (isNextUrlParam.equals("1")) {//不拼接
//
//            } else if (isNextUrlParam.equals("0")) {//拼接
//
//            }
//            Intent intent = new Intent(this, DepartmentActivity.class);
//            intent.putExtra("requestUrl", requestUrl);
//            startActivity(intent);
//        } else if (isShowMembers.equals("0")) {//是人员列表
//            if (isNextUrlParam.equals("1")) {//不拼接
//
//            } else if (isNextUrlParam.equals("0")) {//拼接
//
//            }
//            //跳转到人员列表页
//            Intent intent = new Intent(this, PersonListActivity.class);
//            intent.putExtra("requestUrl", requestUrl);
//            startActivity(intent);
//        }
//        if (isShowMembersParam.equals("0") || isShowMembers.equals("0")) {//存起来
//            SPUtils.remove(this, Const.SpAddress.ADDRESS_KEY);
//            SPUtils.put(this, Const.SpAddress.ADDRESS_KEY, parmasStr + "-" + paramKey + ":" + val);
//        } else if (isShowMembersParam.equals("1")) {//不存
//
//        }
    }
}
