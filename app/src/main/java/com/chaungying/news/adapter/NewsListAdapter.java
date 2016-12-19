package com.chaungying.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaungying.news.bean.NewsItem;
import com.chaungying.news.ui.ReadStatisticsActivity;
import com.chaungying.wuye3.R;

import java.util.ArrayList;

/**
 * @author 种耀淮 在2016年07月26日19:35创建
 */
public class NewsListAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<NewsItem> datas = new ArrayList<>();

    public NewsListAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<NewsItem> data) {
        datas = data;
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setDate(datas.get(i).getCreatTime());
            if (i == 0) {
                datas.get(i).setDisPlayDate("error");
            } else {
                datas.get(i).setDisPlayDate(datas.get(i - 1).getCreatTime());
            }
        }
        notifyDataSetChanged();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_news_list, viewGroup, false);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        // 标题
        if (datas.get(i).getState() == 1) {//未读
            holder.title.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            holder.title.setTextColor(context.getResources().getColor(R.color.gray));
        }
        holder.title.setText(datas.get(i).getActivityName());
        // 来源
        holder.source.setText("来源：" + datas.get(i).getSource());
        holder.comments.setText("阅读量：" + datas.get(i).getReadeds() + "/" + (datas.get(i).getReadeds() + datas.get(i).getUnreads()) + " >");
        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadStatisticsActivity.class);
                intent.putExtra("activityId", datas.get(i).getId());
                context.startActivity(intent);
            }
        });
        // 加载图片
        if (datas.get(i).getImageAddr() != null && datas.get(i).getImageAddr().length() > 0) {
            Glide.with(context).load(datas.get(i).getImageAddr()).error(R.drawable.news).into(holder.image);
        } else {
            Glide.with(context).load(R.drawable.news).into(holder.image);
        }
        return view;
    }

    class Holder {
        ImageView image;
        TextView title, source,
                comments,
                topLabel;

        public Holder(View view) {
            image = (ImageView) view.findViewById(R.id.item_newsList_iv);
            title = (TextView) view.findViewById(R.id.item_newsList_title);
            source = (TextView) view.findViewById(R.id.item_newsList_source);
            comments = (TextView) view.findViewById(R.id.item_newsList_comments);
            topLabel = (TextView) view.findViewById(R.id.item_newsList_topLabel);
        }
    }
}
