package com.chaungying.news.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chaungying.news.adapter.ReadListAdapter;
import com.chaungying.news.bean.ReadDetailsBean;
import com.chaungying.wuye3.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 已读列表碎片
 *
 */
@ContentView(R.layout.fragment_read)
public class ReadListFragment extends Fragment {

    // 列表
    @ViewInject(R.id.fragmentRead_list)
    private ListView listView;

    // 列表适配器
    private ReadListAdapter adapter;

    // 数据
    List<ReadDetailsBean.DataBean> ReadList = new ArrayList<>();

    public void setReadList(List<ReadDetailsBean.DataBean> readList) {
        ReadList.clear();
        ReadList.addAll(readList);
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        createFragment();
        return view;
    }


    /**
     * 初始化
     */
    private void createFragment() {
        // 初始化列表
        adapter = new ReadListAdapter(getContext());
        adapter.setDatas(ReadList);
        listView.setAdapter(adapter);
    }
}
