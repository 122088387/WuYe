package com.chaungying.qiandao.adapter;

import android.content.Context;
import android.content.Intent;

import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.common.utils.T;
import com.chaungying.qiandao.ui.TongJiActivity;
import com.chaungying.qiandao.view.SinginListFragment;
import com.chaungying.site_repairs.view.PullToRefreshLayout;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class TongJiListener implements PullToRefreshLayout.OnRefreshListener {
    Context mContext;
    private final String ACTION_NAME = "刷新数据";
    private final static int REFRESH_SUCCESS = 0;
    private final static int REFRESH_FAIL = 1;

    private int refreshPage = 2;

    public TongJiListener(Context context) {
        mContext = context;
    }
    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
//		Message msg = new Message();
//		msg.what = REFRESH_SUCCESS;
//        RequestParams params = new RequestParams(Const.WuYe.URL_SIGN_STATISTICAL);//再次请求数据
//        params.setConnectTimeout(5 * 1000);
//        params.addParameter("page", TongJiActivity.refreshPage++);
//        params.addParameter("row", TongJiActivity.refreshPageNum);
//        params.addParameter("userId", SPUtils.get(mContext, Const.SPDate.ID, 4512));
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                T.showShort(mContext, "刷新成功");
//                Intent mIntent = new Intent(ACTION_NAME);
//                mIntent.putExtra("json", result);
//                //发送广播
//                mContext.sendBroadcast(mIntent);
//                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                ex.printStackTrace();
//                T.showShort(mContext, "刷新失败");
//                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
        // 模拟联网加载5秒  下拉刷新操作
//		new Handler()
//		{
//			@Override
//			public void handleMessage(Message msg)
//			{
//				// 千万别忘了告诉控件刷新完毕了哦！
//				pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//			}
//		}.sendEmptyMessageDelayed(0, 0);
    }

    /**
     * 上拉加载数据
     *
     * @param pullToRefreshLayout
     */
    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
        // 加载操作
        RequestParams params = new RequestParams(Const.WuYe.URL_SIGN_STATISTICAL);//再次请求报修记录列表
        params.setConnectTimeout(5 * 1000);
        params.addParameter("page", refreshPage++);
        params.addParameter("row", TongJiActivity.refreshPageNum);
        if (TongJiActivity.userId == 0) {
            params.addParameter("memberId", SPUtils.get(mContext, Const.SPDate.ID, 4512));
        } else {
            params.addParameter("memberId", TongJiActivity.userId);
        }
        //是个人名片跳转过来的话 会加一个日期参数，否则就是签到中的统计跳过来不用加参数
        if (SinginListFragment.card != null && SinginListFragment.card.equals("card")) {
            params.addParameter("signInDate", SinginListFragment.signInDate);
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Intent mIntent = new Intent(ACTION_NAME);
                mIntent.putExtra("json", result);
                //发送广播
                mContext.sendBroadcast(mIntent);
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                T.showShort(mContext, "加载错误");
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


//        new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                // 千万别忘了告诉控件加载完毕了哦！
//                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//            }
//        }.sendEmptyMessageDelayed(0, 5000);
    }

}
