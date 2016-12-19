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
public class TongJiAdapter extends BaseAdapter {

    private ArrayList<TongJiBean.DataBean> list = new ArrayList<TongJiBean.DataBean>();
    private Context mContext;

    public void setList(ArrayList<TongJiBean.DataBean> list) {
        this.list = list;
    }

    public void addDatas(ArrayList<TongJiBean.DataBean> list) {
        this.list.addAll(list);
    }

    public TongJiAdapter(Context context) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_qian_dao_tong_ji_item, null, false);
            holderP.tvDateHead = (TextView) convertView.findViewById(R.id.tv_date_head);
            holderP.tvDateTime = (TextView) convertView.findViewById(R.id.tv_date_time);
            holderP.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);
            holderP.tv_wifi1 = (TextView) convertView.findViewById(R.id.tv_wifi1);
            holderP.tvWifiInfo = (TextView) convertView.findViewById(R.id.tv_wifi);
            holderP.tv_singin_state = (TextView) convertView.findViewById(R.id.tv_singin_state);
            convertView.setTag(holderP);
        } else {
            holderP = (ViewHolderP) convertView.getTag();
        }

        TongJiBean.DataBean bean = list.get(position);
        if (bean.getShow()) {
            holderP.tvDateHead.setVisibility(View.VISIBLE);
        } else {
            holderP.tvDateHead.setVisibility(View.GONE);
        }

        holderP.tvDateHead.setText(bean.getSignInDate());
        holderP.tvDateTime.setText(bean.getCreateTime());
        holderP.tvAddress.setText(bean.getSignInAddress());
//        if (isAdmin == 0) {
//            holderP.tv_wifi1.setText("姓名：");
//            holderP.tvWifiInfo.setText(bean.getUserName());
//        } else {
        holderP.tv_wifi1.setText("网络：");
        holderP.tvWifiInfo.setText(bean.getWifiName() == null || bean.getWifiName().equals("(null)") ? "" : bean.getWifiName());
        if (bean.getFlag() == 0) {
            holderP.tv_singin_state.setText("签到");
        } else if (bean.getFlag() == 1) {
            holderP.tv_singin_state.setText("签退");
        } else if (bean.getFlag() == 2) {
            holderP.tv_singin_state.setText("请假");
        }

//        }

        return convertView;
    }

    class ViewHolderP {
        TextView tvDateHead;
        TextView tvDateTime;
        TextView tvAddress;
        TextView tvWifiInfo;
        TextView tv_wifi1;
        TextView tv_singin_state;
    }
}
