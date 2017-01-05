package com.chaungying.qiandao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chaungying.qiandao.bean.ManagerTongJiBean;
import com.chaungying.round_malls.utils.BigDecimalUtils;
import com.chaungying.wuye3.R;

import java.util.ArrayList;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/11/28
 */

public class FullRankAdapter extends BaseAdapter {
    private ArrayList<ManagerTongJiBean.DataBean> list = new ArrayList<>();
    private Context mContext;

    public void setList(ArrayList<ManagerTongJiBean.DataBean> list) {
        this.list = list;
    }


    public FullRankAdapter(Context context) {
        mContext = context;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_full_rank_item, null, false);
            holderP = new ViewHolderP(convertView);
            convertView.setTag(holderP);
        } else {
            holderP = (ViewHolderP) convertView.getTag();
        }
        ManagerTongJiBean.DataBean bean = list.get(position);
        holderP.full_rank_num_item.setText(position + 1 + "");
        holderP.full_rank_name_item.setText(bean.getUserName());
        holderP.full_rank_counts_item.setText(BigDecimalUtils.rounding1(Double.parseDouble(bean.getCounts()) / 60 + ""));
        return convertView;
    }

    class ViewHolderP {
        TextView full_rank_num_item;
        TextView full_rank_name_item;
        TextView full_rank_counts_item;

        ViewHolderP(View view) {

            full_rank_num_item = (TextView) view.findViewById(R.id.full_rank_num_item);
            full_rank_name_item = (TextView) view.findViewById(R.id.full_rank_name_item);
            full_rank_counts_item = (TextView) view.findViewById(R.id.full_rank_counts_item);
        }
    }
}
