package com.chaungying.modues.main.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.common.utils.T;
import com.chaungying.common.utils.date.DateStyle;
import com.chaungying.common.utils.date.DateUtil;
import com.chaungying.ji_xiao.ui.CharBase;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.modues.main.adapter.RepairAnalysisAdapter;
import com.chaungying.modues.main.bean.PersonalCardBean;
import com.chaungying.modues.main.bean.RepairAnalysisBean;
import com.chaungying.modues.main.spinner_view.NiceSpinner;
import com.chaungying.qiandao.ui.TongJiActivity;
import com.chaungying.round_malls.utils.BigDecimalUtils;
import com.chaungying.wuye3.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/5
 * 个人卡片
 */

@ContentView(R.layout.activity_personal_card)
public class PersonalCardActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {


    @ViewInject(R.id.lv_repair_analysis)
    private ListView analysisList;

    //本月平均工时
    @ViewInject(R.id.avgWorks)
    private TextView avgWorks;

    //部门排名
    @ViewInject(R.id.departmentrRank)
    private TextView departmentrRank;
    //出勤天数
    @ViewInject(R.id.cqDays)
    private RelativeLayout cqDays;
    //正常打卡
    @ViewInject(R.id.zcdk)
    private RelativeLayout zcdk;
    //早退
    @ViewInject(R.id.earlyPeople)
    private RelativeLayout earlyPeople;
    //迟到
    @ViewInject(R.id.tardinessPeople)
    private RelativeLayout tardinessPeople;
    //请假
    @ViewInject(R.id.absenteeismPeople)
    private RelativeLayout absenteeismPeople;

    List<RelativeLayout> rl_list = new ArrayList<>();
    //天数标题
    List<TextView> tianShuTitle = new ArrayList<>();
    //天数
    List<TextView> tianShu = new ArrayList<>();

    String[] tilteStr = {"出勤天数", "正常打卡", "早退", "迟到", "请假"};
    List<Integer> tianshuNum = new ArrayList<>();


    List<RepairAnalysisBean> list = new ArrayList<>();
    String[] baoxiuTitle = {"本月报修量/部门排名", "本月抢单量/部门排名", "本月维修量/部门排名"};
    List<Integer> baoxiuTotal = new ArrayList<>();
    List<Integer> baoxiuCurrent = new ArrayList<>();
    ;
    //传入到查询所有签到的日期
    private String signInDate;

    private RepairAnalysisAdapter adapter;

//    @ViewInject(R.id.person_card_date_btn)
//    private TextView showDate;

    @ViewInject(R.id.pieChart1)
    private PieChart mpieChart;

    ArrayList<Integer> colors1 = new ArrayList<Integer>();
    protected String[] mParties = new String[]{"", "", "", "", "", "", "", ""};
    ArrayList<String> lables = new ArrayList<>();

    @ViewInject(R.id.nice_spinner)
    private NiceSpinner nice_spinner;

    //存储日期的集合
    private List<String> dateList = new ArrayList();

    PersonalCardBean cardBean;

    private int userId = 0;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initColors();
        initView(mpieChart);
        initView();
        //获取数据
        String date = DateUtil.DateToString(new Date(), DateStyle.YYYY_MM);
        getDate(date);
    }


    private void initColors() {
        colors1.add(Color.rgb(0, 223, 199));
        colors1.add(Color.rgb(46, 179, 248));

        lables.clear();
        lables.add("完成数量");
        lables.add("未完成数量");
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userId = bundle.getInt("userId");
            name = bundle.getString("userName");
        }
        setActionBar((name == null || name.equals("") ? name : name + "-") + "个人卡片", R.drawable.nav_return, 0);
        //出勤迟到等
        rl_list.add(cqDays);
        rl_list.add(zcdk);
        rl_list.add(earlyPeople);
        rl_list.add(tardinessPeople);
        rl_list.add(absenteeismPeople);


        for (int i = 0; i < rl_list.size(); i++) {
            TextView title = (TextView) rl_list.get(i).findViewById(R.id.tian_shu_title);
            TextView titleNum = (TextView) rl_list.get(i).findViewById(R.id.tian_shu);
            tianShuTitle.add(title);
            tianShu.add(titleNum);
        }


        //报修分析
        adapter = new RepairAnalysisAdapter(this);
        list = new ArrayList<>();
        for (int i = 0; i < baoxiuTitle.length; i++) {
            RepairAnalysisBean bean = new RepairAnalysisBean();
            bean.setName(baoxiuTitle[i]);
            list.add(bean);
        }
        adapter.setList(list);
        analysisList.setAdapter(adapter);

        String date = DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_CN);
        dateList.add(date);
        for (int i = 1; i < 12; i++) {
            date = DateUtil.addMonth(date, -1);
            dateList.add(date);
        }
        List<String> dataset = new LinkedList<>(dateList);
        nice_spinner.attachDataSource(dataset);
        nice_spinner.setOnItemSelectedListener(this);
    }

    private void initView(PieChart mPieChart) {
        //饼状
        mPieChart.setUsePercentValues(true);
        //没有该方法
//        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        //拖拽转动时减速
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        mPieChart.setDescription("");
        //没有该方法
//        mPieChart.setCenterTextTypeface(mTfLight);
//        mPieChart.setCenterText(generateCenterSpannableText());

        mPieChart.setDrawHoleEnabled(false);
        mPieChart.setHoleColor(Color.WHITE);

        //洞 Hole
        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(0);

        //设置中间圆洞的弧度
        mPieChart.setHoleRadius(0f);
        //设置圆洞外半透明的圆弧
        mPieChart.setTransparentCircleRadius(0f);

        // 绘制中间的文字
        mPieChart.setDrawCenterText(false);

        mPieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        Legend l = mPieChart.getLegend();
        l.setEnabled(false);

    }

    private void setData(PieChart mPieChart, ArrayList<Integer> colors, int... num) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < num.length; i++) {
            entries.add(new PieEntry((float) (num[i]), mParties[i % mParties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        //设置两片之间的空白间隙
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(colors1);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(CharBase.mTfLight);
        mPieChart.setData(data);
        //从哪个位置开始扇形动画
        mPieChart.setRotationAngle(270);

        // undo all highlights
        mPieChart.highlightValues(null);

        mPieChart.invalidate();

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String date = dateList.get(i);
        date = date.replace('年', '-');
        date = date.substring(0, date.indexOf("月"));
        getDate(date);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * 获取数据
     *
     * @param date
     */
    private void getDate(final String date) {
        signInDate = date;
        ProgressUtil.show(this, "加载中...");
        RequestParams params = new RequestParams(Const.WuYe.URL_USER_GET_PERSONAL_CARD);
        params.addParameter("date", date);
        if (userId != 0) {
            params.addParameter("userId", userId);
        } else {
            params.addParameter("userId", SPUtils.get(this, Const.SPDate.ID, -1));
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                cardBean = new Gson().fromJson(result, PersonalCardBean.class);
                if (cardBean != null) {
                    adapter.setDate(date);
                    upData();
                } else {
                    T.showShort(PersonalCardActivity.this, "获取数据失败");
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

    /**
     * 更新数据
     */
    private void upData() {
        tianshuNum.clear();
        tianshuNum.add(cardBean.getCqDays());
        tianshuNum.add(cardBean.getZcdk());
        tianshuNum.add(cardBean.getEarlyPeople());
        tianshuNum.add(cardBean.getTardinessPeople());
        tianshuNum.add(cardBean.getAbsenteeismPeople());

        avgWorks.setText("本月平均工时：" + BigDecimalUtils.rounding(cardBean.getAvgWorks()));
        departmentrRank.setText("部门内排名：" + cardBean.getDepartmentrRank());
        for (int i = 0; i < rl_list.size(); i++) {
            tianShuTitle.get(i).setText(tilteStr[i]);
            if (i < 2) {
                tianShu.get(i).setText(tianshuNum.get(i) + "天");
            } else {
                tianShu.get(i).setText(tianshuNum.get(i) + "次");
            }
        }

        baoxiuTotal.clear();
        baoxiuCurrent.clear();

        baoxiuTotal.add(cardBean.getRepairNum());
        baoxiuCurrent.add(cardBean.getRepairRank());
        baoxiuTotal.add(cardBean.getQdNum());
        baoxiuCurrent.add(cardBean.getQdRank());
        baoxiuTotal.add(cardBean.getWxNum());
        baoxiuCurrent.add(cardBean.getWxRank());


        for (int i = 0; i < list.size(); i++) {
            list.get(i).setTotal(baoxiuTotal.get(i));
            list.get(i).setCurrent(baoxiuCurrent.get(i));
            adapter.notifyDataSetChanged();
        }
        if (cardBean.getNoFinishNum() == 0 && cardBean.getFinishNum() == 0) {
            setData(mpieChart, colors1, 1, 0);
        } else {
            setData(mpieChart, colors1, cardBean.getFinishNum(), cardBean.getNoFinishNum());
        }
    }

    @Event(value = R.id.personal_sigin)
    private void personal_sigin(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("card", "card");
        bundle.putString("signInDate", signInDate);
        bundle.putInt("userId", userId);
        openActivty(this, TongJiActivity.class, bundle, null);
    }

}
