package com.chaungying.qiandao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chaungying.qiandao.bean.TongJiBean;
import com.chaungying.wuye3.R;

import java.util.ArrayList;

/**
 * @author 王晓赛 or 2016/7/28
 *         <p>
 *         签到统计的适配器
 **/
public class TongJi2Adapter extends BaseAdapter {

    private ArrayList<TongJiBean.DataBean> list = new ArrayList<TongJiBean.DataBean>();
    private Context mContext;

    public void setList(ArrayList<TongJiBean.DataBean> list) {
        this.list = list;
    }

    public void addDatas(ArrayList<TongJiBean.DataBean> list) {
        this.list.addAll(list);
    }

    public TongJi2Adapter(Context context) {
        mContext = context;
    }

    private int isAdmin;

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderP holderP = null;
        if (convertView == null) {
            holderP = new ViewHolderP();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_qian_dao_tong_ji2_item, null, false);
            holderP.tvDateTime = (TextView) convertView.findViewById(R.id.tv_date_time);
            holderP.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);
            holderP.tv_wifi1 = (TextView) convertView.findViewById(R.id.tv_wifi1);
            convertView.setTag(holderP);
        } else {
            holderP = (ViewHolderP) convertView.getTag();
        }

        TongJiBean.DataBean bean = list.get(position);
        holderP.tvDateTime.setText("开始时间：" + bean.getCreateTime());
        holderP.tvAddress.setText("结束时间：" + bean.getSignInAddress());
        holderP.tv_wifi1.setText("请假原因：" + bean.getWifiName());
        return convertView;
    }

    class ViewHolderP {
        TextView tvDateTime;
        TextView tvAddress;
        TextView tv_wifi1;
    }
}
