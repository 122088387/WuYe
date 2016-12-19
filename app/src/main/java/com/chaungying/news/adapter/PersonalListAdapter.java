package com.chaungying.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chaungying.news.bean.MyPersonalBean;
import com.chaungying.wuye3.R;

import java.util.ArrayList;
import java.util.List;

public class PersonalListAdapter extends BaseAdapter {

    private Context context;

    public PersonalListAdapter(Context context) {
        this.context = context;
    }

    private List<MyPersonalBean> list = new ArrayList<>();

    public void setList(List<MyPersonalBean> list) {
        this.list = list;
    }

    private updaStateListener listener;

    public void setListener(updaStateListener listener) {
        this.listener = listener;
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
        final Holder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_personal_list, viewGroup, false);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.name.setText(list.get(i).getName());
        holder.checkBox.setChecked(list.get(i).isCheck());
        holder.checkBox.setClickable(false);
        holder.item_personal_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isCheck()) {
                    holder.checkBox.setChecked(false);
                    list.get(i).setCheck(false);
                } else {
                    holder.checkBox.setChecked(true);
                    list.get(i).setCheck(true);
                }
                if (listener != null) {
                    listener.updateState(list.get(i).getTag());
                }
            }
        });
        return view;
    }

    class Holder {
        TextView name;
        CheckBox checkBox;
        RelativeLayout item_personal_rl;

        public Holder(View view) {
            name = (TextView) view.findViewById(R.id.item_personal_tv);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            item_personal_rl = (RelativeLayout) view.findViewById(R.id.item_personal_rl);
        }
    }

    public interface updaStateListener {
        void updateState(int tag);
    }
}
