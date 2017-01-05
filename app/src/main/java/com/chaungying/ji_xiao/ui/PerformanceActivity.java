package com.chaungying.ji_xiao.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.chaungying.BaseActivity;
import com.chaungying.qiandao.ui.ManagerTongJiActivity;
import com.chaungying.wuye3.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王晓赛 or 2016/9/19
 *         <p>
 *         包含四重情况
 */

@ContentView(R.layout.activity_performance)
public class PerformanceActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {


//    String[] titleStr = {"签到分析", "绩效排名", "投诉咨询", "用车情况", "完成率分析", "维修派工-时间分析"};

    @ViewInject(R.id.per_ji_xiao_rank)
    private LinearLayout per_ji_xiao_rank;
    @ViewInject(R.id.per_finish_rate)
    private LinearLayout per_finish_rate;
    @ViewInject(R.id.per_time_anay)
    private LinearLayout per_time_anay;
    @ViewInject(R.id.per_singin_anay)
    private LinearLayout per_singin_anay;
    @ViewInject(R.id.per_complaining_advisory)
    private LinearLayout per_complaining_advisory;
    @ViewInject(R.id.per_user_car)
    private LinearLayout per_user_car;
    @ViewInject(R.id.ll_kao_qin_fen_xi)
    private LinearLayout ll_kao_qin_fen_xi;

    private List<LinearLayout> ll_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setActionBar("工作绩效", R.drawable.nav_return, 0);

        ll_list.add(per_ji_xiao_rank);
        ll_list.add(per_finish_rate);
        ll_list.add(per_time_anay);
        ll_list.add(per_singin_anay);
        ll_list.add(ll_kao_qin_fen_xi);
        ll_list.add(per_complaining_advisory);
        ll_list.add(per_user_car);
        for (int i = 0; i < ll_list.size(); i++) {
            ll_list.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.per_singin_anay://日趋势分析
                openActivty(this, PerSignActivity.class, null, null);
                break;
            case R.id.per_ji_xiao_rank://绩效排名
                openActivty(this, PerRepairDispatchActivity.class, null, null);
                break;
            case R.id.per_complaining_advisory://投诉咨询
                openActivty(this, PerComplaintsConsultActivity.class, null, null);
                break;
            case R.id.per_user_car://用车
                openActivty(this, PerUserCarActivity.class, null, null);
                break;
            case R.id.per_finish_rate://完成率分析  饼状图
                openActivty(this, PieChartAnalyActivity.class, null, null);
                break;
            case R.id.per_time_anay://维修派工-时间分析  折线图
                openActivty(this, LineChartAnalyActivity.class, null, null);
                break;
            case R.id.ll_kao_qin_fen_xi://签到分析
                openActivty(this, ManagerTongJiActivity.class, null, null);
                break;

        }
    }

    @Event(value = R.id.title_back)
    private void back(View view) {
        finish();
    }
}
