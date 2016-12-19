package com.chaungying.qiandao.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.date.DateStyle;
import com.chaungying.common.utils.date.DateUtil;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.qiandao.bean.ManagerTongJiBean;
import com.chaungying.qiandao.view.ProgressWheel;
import com.chaungying.wuye3.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/11/28
 * <p>
 * 如果是管理人员  进入此界面
 */
@ContentView(R.layout.activity_manager_tong_ji)
public class ManagerTongJiActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.manager_tong_ji_progress)
    private ProgressWheel progressWheel;

    @ViewInject(R.id.tong_ji_progressbar1)
    private ProgressBar pb1;
    @ViewInject(R.id.tong_ji_progressbar2)
    private ProgressBar pb2;
    @ViewInject(R.id.tong_ji_progressbar3)
    private ProgressBar pb3;
    @ViewInject(R.id.tong_ji_progressbar4)
    private ProgressBar pb4;

    ManagerTongJiBean bean;

    private int totalPeople;

    @ViewInject(R.id.tv_percentage)
    private TextView tv_percentage;

    @ViewInject(R.id.rl_1)
    private LinearLayout rl;
    @ViewInject(R.id.rl_2)
    private LinearLayout r2;
    @ViewInject(R.id.rl_3)
    private LinearLayout r3;

    @ViewInject(R.id.search_full_rank)
    private TextView searchFullRank;

    List<LinearLayout> rl_list = new ArrayList<>();

    @ViewInject(R.id.date_left)
    private ImageView dateLeft;

    @ViewInject(R.id.date_right)
    private ImageView dateRight;

    @ViewInject(R.id.tv_date)
    private TextView dateStr;

    private Date date = new Date();

    //记录更新天数差值
    private int updateNum;

    //是否第一次请求数据
    private boolean isFirstGetDate = true;

    private int[] imags = {R.drawable.first_rank, R.drawable.second_rank, R.drawable.three_rank};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        initData(DateUtil.getDateFull(date));

    }

    private void initData(final String date) {
        ProgressUtil.show(this, "加载中...");
        RequestParams params = new RequestParams(Const.WuYe.URL_SIGN_IN_WORK);
        params.addParameter("date", date);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                bean = new Gson().fromJson(result, ManagerTongJiBean.class);
                upData();
                dateStr.setText(date);
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

    /**
     * 更新所有数据
     */
    private void upData() {
        totalPeople = bean.getTotalPeople();
        tv_percentage.setText(bean.getSignInPeople() + "/" + totalPeople);
        progressWheel.setProgress(bean.getSignInPeople() / (float) totalPeople);

        pb1.setMax(totalPeople);
        pb2.setMax(totalPeople);
        pb3.setMax(totalPeople);
        pb4.setMax(totalPeople);

        pb1.setProgress(bean.getSignInPeople());
        pb2.setProgress(bean.getEarlyPeople());
        pb3.setProgress(bean.getTardinessPeople());
        pb4.setProgress(bean.getAbsenteeismPeople());

        if (isFirstGetDate) {
            isFirstGetDate = !isFirstGetDate;
            List<ManagerTongJiBean.DataBean> dataList = bean.getData();
            Collections.sort(dataList);

            for (int i = 0; i < rl_list.size(); i++) {
                TextView tv = (TextView) rl_list.get(i).findViewById(R.id.tv_ranking);
                ImageView iv = (ImageView) rl_list.get(i).findViewById(R.id.iv_ranking);
                ImageView head = (ImageView) rl_list.get(i).findViewById(R.id.iv_head_view);
                tv.setText(dataList.get(i).getUserName());
                iv.setImageResource(imags[i]);
                if (dataList.get(i).getPortrait() != null && !"".equals(dataList.get(i).getPortrait())) {
                    Picasso.with(this).load(dataList.get(i).getPortrait()).error(R.drawable.qian_dao_tong_ji_head).into(head);
                }
            }
        }
    }

    private void initView() {
        setActionBar("签到统计", R.drawable.nav_return, 0);
        progressWheel.setBarColor(getResources().getColor(R.color.colorPrimary));
        rl_list.add(rl);
        rl_list.add(r2);
        rl_list.add(r3);
        searchFullRank.setOnClickListener(this);
        dateLeft.setOnClickListener(this);
        dateRight.setOnClickListener(this);
        dateStr.setText(DateUtil.DateToString(date, DateStyle.YYYY_MM_DD));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_full_rank:
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (ArrayList) bean.getData());
                openActivty(this, FullRankActivity.class, bundle, null);
                break;
            case R.id.date_left:
                updateNum--;
                String temp = DateUtil.DateToString(DateUtil.addDay(date, updateNum), DateStyle.YYYY_MM_DD);
                initData(temp);
                break;
            case R.id.date_right:
                updateNum++;
                String temp1 = DateUtil.DateToString(DateUtil.addDay(date, updateNum), DateStyle.YYYY_MM_DD);
                initData(temp1);
                break;
        }
    }
}
