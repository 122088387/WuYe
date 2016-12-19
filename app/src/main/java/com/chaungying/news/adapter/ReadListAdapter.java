package com.chaungying.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chaungying.news.bean.ReadDetailsBean;
import com.chaungying.wuye3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 阅读的碎片
 */
public class ReadListAdapter extends BaseAdapter {

    private Context context;

    // 数据
    List<ReadDetailsBean.DataBean> datas = new ArrayList<>();

    public ReadListAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ReadDetailsBean.DataBean> data) {
        datas = data;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_read_list, viewGroup, false);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        // 标题
        holder.name.setText(datas.get(i).getUserName());
        return view;
    }

    class Holder {
        TextView name;

        public Holder(View view) {
            name = (TextView) view.findViewById(R.id.item_readList_topLabel);
        }
    }
}
