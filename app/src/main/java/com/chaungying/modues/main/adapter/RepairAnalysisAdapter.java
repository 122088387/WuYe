package com.chaungying.modues.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chaungying.modues.main.bean.RepairAnalysisBean;
import com.chaungying.modues.main.ui.DepartmentRankActivity;
import com.chaungying.wuye3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/5
 */

public class RepairAnalysisAdapter extends BaseAdapter {

    private String date;
    private List<RepairAnalysisBean> list = new ArrayList<>();

    public void setList(List<RepairAnalysisBean> list) {
        this.list = list;
    }

    private Context mContext;

    public void setDate(String date) {
        this.date = date;
    }

    public RepairAnalysisAdapter(Context mContext) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.personal_card_analysis_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.title.setText(list.get(i).getName());
        holder.proportion.setText(list.get(i).getTotal() + "/" + list.get(i).getCurrent());
        holder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DepartmentRankActivity.class);
                intent.putExtra("index", i);
                String str = list.get(i).getName();
                str = str.substring(str.indexOf('月') + 1, str.indexOf("/"));//报修量
                intent.putExtra("title", str);
                intent.putExtra("date", date);
                mContext.startActivity(intent);
            }
        });
        return view;
    }


    class ViewHolder {
        TextView title;
        TextView proportion;
        TextView right;


        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.analysis_title);
            proportion = (TextView) view.findViewById(R.id.analysis_proportion);
            right = (TextView) view.findViewById(R.id.analysis_right);
        }
    }
}
