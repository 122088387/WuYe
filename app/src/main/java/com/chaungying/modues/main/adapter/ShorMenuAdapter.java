package com.chaungying.modues.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaungying.modues.main.bean.WindowBtnBean;
import com.chaungying.wuye3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/1
 */

public class ShorMenuAdapter extends BaseAdapter {

    private List<WindowBtnBean.ShortcutsBean> data_list = new ArrayList<>();
    private Context mContext;


    public ShorMenuAdapter(Context context) {
        mContext = context;
    }

    public void setData_list(List<WindowBtnBean.ShortcutsBean> data_list) {
        this.data_list = data_list;
    }

    @Override
    public int getCount() {
        return data_list.size();
    }

    @Override
    public Object getItem(int position) {
        return data_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.btn_item, null);
            view.setId(data_list.get(position).getId());
            TextView tv = (TextView) view.findViewById(R.id.tv1);
            tv.setText(data_list.get(position).getName());
            ImageView iv = (ImageView) view.findViewById(R.id.iv1);
            if (data_list.get(position).getIcoUrl() != null && !data_list.get(position).getIcoUrl().equals("")) {
                Picasso.with(mContext).load(data_list.get(position).getIcoUrl()).error(R.drawable.default_png).into(iv);
            }
        }
        return view;
    }
}
