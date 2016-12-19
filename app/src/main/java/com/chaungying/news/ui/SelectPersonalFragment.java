package com.chaungying.news.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chaungying.news.adapter.PersonalListAdapter;
import com.chaungying.news.bean.MyPersonalBean;
import com.chaungying.wuye3.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择人员碎片
 */
@ContentView(R.layout.fragment_news)
public class SelectPersonalFragment extends Fragment {


    // 列表
    @ViewInject(R.id.fragmentNews_list)
    private ListView listView;

    // 列表适配器
    private PersonalListAdapter adapter;

    private int tag;

    public void setTag(int tag) {
        this.tag = tag;
    }

    private List<MyPersonalBean> list = new ArrayList<>();

    public void setList(List<MyPersonalBean> list) {
        this.list = list;
        List<MyPersonalBean> tempList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (tag == list.get(i).getTag()) {
                tempList.add(list.get(i));
            }
        }
        adapter.setList(tempList);
        adapter.setListener(new PersonalListAdapter.updaStateListener() {
            @Override
            public void updateState(int tag) {
                if (upAlldata != null) {
                    upAlldata.updata(tag);
                }
            }
        });
        listView.setAdapter(adapter);
    }

    public void notifyAdapter(){
        adapter.notifyDataSetChanged();
    }

    private UpAllData upAlldata;

    public void setUpAlldata(UpAllData upAlldata) {
        this.upAlldata = upAlldata;
    }

    public interface UpAllData {
        void updata(int tag);
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
        adapter = new PersonalListAdapter(getContext());
        adapter.setList(list);
        listView.setAdapter(adapter);
    }
}
