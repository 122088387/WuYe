package com.chaungying.ji_xiao.ui;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Const;
import com.chaungying.common.view.DownPopWindowPerView;
import com.chaungying.ji_xiao.bean.JobHeader;
import com.chaungying.ji_xiao.bean.JobPer;
import com.chaungying.ji_xiao.bean.JobPerCon;
import com.chaungying.ji_xiao.bean.LineChartBean;
import com.chaungying.ji_xiao.bean.PerRepairDispatchBean;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.site_repairs.view.PullToRefreshLayout;
import com.chaungying.wuye3.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作绩效  折线图 分析
 *
 * @author 王晓赛 or 2016/12/7 修改
 */
@ContentView(R.layout.activity_line_chart)
public class LineChartAnalyActivity extends BaseActivity implements OnChartValueSelectedListener {

    protected String[] mParties = new String[]{"", "", "", "", "", "", "", ""};

    /**
     * 头部标题
     */
    @ViewInject(R.id.job_add_ll)
    private LinearLayout job_add_ll;


    /////////////////图表//////////////////
    @ViewInject(R.id.lineChart1)
    private LineChart mLineChart;
    @ViewInject(R.id.lineChart2)
    private LineChart mLineChart2;
    @ViewInject(R.id.lineChart3)
    private LineChart mLineChart3;

    @ViewInject(R.id.month_or_year)
    private TextView month_or_year;
    @ViewInject(R.id.month_or_year2)
    private TextView month_or_year2;
    @ViewInject(R.id.month_or_year3)
    private TextView month_or_year3;


    ArrayList<Integer> colors1 = new ArrayList<Integer>();
    ArrayList<Integer> colors2 = new ArrayList<Integer>();
    ArrayList<String> lables = new ArrayList<>();
    /**
     * 数据集合
     */
    private List<JobPerCon> jobs = new ArrayList<JobPerCon>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    break;
                case 2://点击某项之后请求的数据
                    Bundle bundle = msg.getData();
                    JobPer jobPer = (JobPer) bundle.getSerializable("bean");
                    ArrayList<JobPerCon> list = jobPer.data;
                    JobPerCon jobPerCon = new JobPerCon();
                    jobPerCon.name = "合计：";
                    jobPerCon.total = jobPer.totalSum;
                    jobPerCon.finish = jobPer.finishSum;
                    jobPerCon.percent = jobPer.percentSum;
                    list.add(jobPerCon);
                    break;
                case 3:
                    PerRepairDispatchBean perRepairDisBean = (PerRepairDispatchBean) msg.getData().getSerializable("bean");
                    break;
                case 5:
                    lineChartBean = (LineChartBean) msg.getData().getSerializable("bean");
                    itemcount = lineChartBean.getRList().size();
//                    setData(mLineChart, "报修数量分析");
//                    setData(mLineChart2, "派工数量分析");
//                    setData(mLineChart3, "维修数量分析");
                    setData(mLineChart, "1");
                    setData(mLineChart2, "2");
                    setData(mLineChart3, "3");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 19) {
            //沉浸式
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        x.view().inject(this);//注入Vie我和事件
        setActionBar("时间分析", R.drawable.nav_return, 0);
        mLineChart.setTag(1);
        mLineChart2.setTag(2);
        mLineChart3.setTag(3);
        initView(mLineChart);
        initView(mLineChart2);
        initView(mLineChart3);
        initDate();
    }

    private int itemcount = 12;

    private void initView(LineChart mLineChart) {
        mLineChart.setDescription("");
        mLineChart.setExtraOffsets(5, 10, 5, 5);
        mLineChart.setOnChartValueSelectedListener(this);
        mLineChart.setDrawGridBackground(false);

        // enable touch gestures
        mLineChart.setTouchEnabled(true);

        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        // mLineChart.setScaleXEnabled(true);
        // mLineChart.setScaleYEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(true);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setEnabled(false);


        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mLineChart.getAxisRight().setEnabled(false);

        Legend l = mLineChart.getLegend();
        l.setEnabled(false);
    }

    private void setData(LineChart mLineChart, String title) {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<>();
        final List<LineChartBean.RListBean> listR = lineChartBean.getRList();
        final List<LineChartBean.TListBean> listT = lineChartBean.getTList();
        final List<LineChartBean.WListBean> listW = lineChartBean.getWList();

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinValue(0);
        xAxis.setTypeface(CharBase.mTfLight);
        xAxis.setAxisMaxValue(itemcount + 1);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        boolean monthOrYear;
        if (title.equals("1")) {
            for (int i = 0; i < listR.size(); i++) {
                yVals.add(new Entry(i + 1, listR.get(i).getData()));
                xVals.add(listR.get(i).getDate());
            }
            xAxis.setValueFormatter(new AxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    String str = "";
                    if (value < itemcount + 1 && value != 0) {
                        str = listR.get((int) (value - 1)).getDate();
                        //判断是月份还是日期
                        if (ShowNum('-', str) == 1) {//月份
                            month_or_year.setText(listR.get(0).getDate() + "至" + listR.get(listR.size() - 1).getDate());
                            month_or_year2.setText(listR.get(0).getDate() + "至" + listR.get(listR.size() - 1).getDate());
                            month_or_year3.setText(listR.get(0).getDate() + "至" + listR.get(listR.size() - 1).getDate());
                        } else {
                            month_or_year.setText("本月");
                            month_or_year2.setText("本月");
                            month_or_year3.setText("本月");
                        }
                        str = str.substring(str.indexOf("-") + 1);
                    }
                    return str;
                }

                @Override
                public int getDecimalDigits() {
                    return 0;
                }
            });
        } else if (title.equals("2")) {
            for (int i = 0; i < listT.size(); i++) {
                yVals.add(new Entry(i + 1, listT.get(i).getData()));
                xVals.add(listT.get(i).getDate());
            }
            xAxis.setValueFormatter(new AxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    String str = "";
                    if (value < itemcount + 1 && value != 0) {
                        str = listT.get((int) (value - 1)).getDate();
                        str = str.substring(str.indexOf("-") + 1);
                    }
                    return str;
                }

                @Override
                public int getDecimalDigits() {
                    return 0;
                }
            });
        } else if (title.equals("3")) {
            for (int i = 0; i < listW.size(); i++) {
                yVals.add(new Entry(i + 1, listW.get(i).getData()));
                xVals.add(listW.get(i).getDate());
            }
            xAxis.setValueFormatter(new AxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    String str = "";
                    if (value < itemcount + 1 && value != 0) {
                        str = listW.get((int) (value - 1)).getDate();
                        str = str.substring(str.indexOf("-") + 1);
                    }
                    return str;
                }

                @Override
                public int getDecimalDigits() {
                    return 0;
                }
            });
        }

        LineDataSet dataSet = new LineDataSet(yVals, title);

        dataSet.setColors(new int[]{getResources().getColor(R.color.colorPrimary)});
        dataSet.setCircleColor(getResources().getColor(R.color.colorPrimary));
        dataSet.setLineWidth(1f);
        dataSet.setCircleRadius(3f);
        dataSet.setDrawCircleHole(true);
        dataSet.setValueTextSize(9f);
        dataSet.setDrawFilled(true);


        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataSet);

        LineData data = new LineData(dataSets);
        mLineChart.setData(data);

        mLineChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

    }

    //判断某个字符再字符串中出现的次数
    private int ShowNum(char str, String fullStr) {
        int num = 0;
        for (int i = 0; i < fullStr.length(); i++) {
            if (str == fullStr.charAt(i)) {
                num++;
            }
        }
        return num;
    }

    /**
     * 初始化数据
     */
    private void initDate() {
        requestScreen();
        request();
    }

    /**
     * 请求员工绩效筛选头
     */
    private void requestScreen() {
        RequestParams params = new RequestParams(Const.WuYe.URL_GET_REPAIR_WORK_PERFORMANCE_FILTRATE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JobHeader header = null;
                try {
                    header = new Gson().fromJson(result, JobHeader.class);
                } catch (Exception e) {// Json转换异常
                    e.printStackTrace();
                }
                if (header != null && header.getRespCode() == 1001) {
                    List<JobHeader.ItemsBean> dataBean = header.getItems();
                    DownPopWindowPerView downPopWindowPerView = new DownPopWindowPerView(LineChartAnalyActivity.this);
                    downPopWindowPerView.setItemsBeanList(dataBean);
                    downPopWindowPerView.setHandler(handler);
                    job_add_ll.addView(downPopWindowPerView);
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
            }
        });
    }


    LineChartBean lineChartBean;

    /**
     * 刚进入界面请求数据  请求员工筛选默认的总数据
     */
    private void request() {
        ProgressUtil.show(this, "加载中...");
        //对于图表数据的请求
        RequestParams params1 = new RequestParams(Const.WuYe.URL_REPAIR_LINE_CHARTS_LIST);
//        statistics=统计类型 1按月 2按日
        params1.addParameter("statistics", 1);
        x.http().post(params1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                lineChartBean = gson.fromJson(result, LineChartBean.class);
                itemcount = lineChartBean.getRList().size();
                setData(mLineChart, "1");
                setData(mLineChart2, "2");
                setData(mLineChart3, "3");
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
     * 刷新
     */
    private PullToRefreshLayout.OnRefreshListener onRefreshListener = new PullToRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            jobs.clear();
            request();
//            new GetDataTask().execute();
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

        }
    };

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }


    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
//            pullToRefreshLayout.refreshFinish(0);

            super.onPostExecute(result);
        }
    }

    /**
     * 返回
     *
     * @param v
     */
    @Event(value = R.id.title_back)
    private void back(View v) {
        finish();
    }

}
