package com.chaungying.modues.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chaungying.modues.main.bean.DepartmentRankBean;
import com.chaungying.wuye3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/5
 */

public class DepartmentRankAdapter extends BaseAdapter {

    private String date;
    private List<DepartmentRankBean.DataBean> list = new ArrayList<>();

    public void setList(List<DepartmentRankBean.DataBean> list) {
        this.list = list;
    }

    private Context mContext;

    public void setDate(String date) {
        this.date = date;
    }

    public DepartmentRankAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.personal_department_rank_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.department_rank_num_item.setText(i + 1 + "");
        holder.department_rank_name_item.setText(list.get(i).getUserName());
        holder.department_rank_counts_item.setText(list.get(i).getUserNum() + "");
        return view;
    }


    class ViewHolder {
        TextView department_rank_num_item;
        TextView department_rank_name_item;
        TextView department_rank_counts_item;

        ViewHolder(View view) {

            department_rank_num_item = (TextView) view.findViewById(R.id.department_rank_num_item);
            department_rank_name_item = (TextView) view.findViewById(R.id.department_rank_name_item);
            department_rank_counts_item = (TextView) view.findViewById(R.id.department_rank_counts_item);
        }
    }
}
