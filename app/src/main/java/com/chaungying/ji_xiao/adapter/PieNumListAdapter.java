package com.chaungying.ji_xiao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chaungying.ji_xiao.bean.PieNumListBean;
import com.chaungying.wuye3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/12
 */

public class PieNumListAdapter extends BaseAdapter {


    private List<PieNumListBean> list = new ArrayList<>();

    public void setList(List<PieNumListBean> list) {
        this.list = list;
    }

    private Context mContext;

    public PieNumListAdapter(Context mContext) {
        this.mContext = mContext;
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.pie_num_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getTitle());
        holder.num.setText(list.get(position).getNum() + "");
        if (list.get(position).isShowGrayLine()) {
            holder.pie_gray_line.setVisibility(View.VISIBLE);
            holder.pie_gray_line2.setVisibility(View.GONE);
        } else {
            holder.pie_gray_line.setVisibility(View.GONE);
            holder.pie_gray_line2.setVisibility(View.VISIBLE);
        }
        return convertView;
    }


    class ViewHolder {
        TextView title;
        TextView num;
        View pie_gray_line;
        View pie_gray_line2;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.pie_num_list_item_title);
            num = (TextView) view.findViewById(R.id.pie_num_list_item_num);
            pie_gray_line = view.findViewById(R.id.pie_gray_line);
            pie_gray_line2 = view.findViewById(R.id.pie_gray_line2);
        }
    }
}
