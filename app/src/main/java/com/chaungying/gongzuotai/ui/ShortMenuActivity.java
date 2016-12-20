package com.chaungying.gongzuotai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.modues.main.adapter.ShorMenuAdapter;
import com.chaungying.modues.main.bean.WindowBtnBean;
import com.chaungying.modues.main.view.FillGridView;
import com.chaungying.news.ui.ReleaseNewsActivity;
import com.chaungying.qiandao.ui.ManagerTongJiActivity;
import com.chaungying.qiandao.ui.TongJiActivity;
import com.chaungying.wuye3.R;
import com.chaungying.zixunjieda.ui.ZiXunJieDaConfigActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/2
 */
@ContentView(R.layout.page_view)
public class ShortMenuActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    @ViewInject(R.id.short_menu_grid_view)
    private FillGridView gridView;

    private ShorMenuAdapter myAdapter;

    private ArrayList<WindowBtnBean.ShortcutsBean> beanList = new ArrayList<>();

    @ViewInject(R.id.ll_colose_menu)
    private LinearLayout ll_colose_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        beanList = (ArrayList<WindowBtnBean.ShortcutsBean>) getIntent().getExtras().getSerializable("list");

        myAdapter = new ShorMenuAdapter(this);
        myAdapter.setData_list(beanList);
        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener(this);
        ll_colose_menu.setOnClickListener(this);
    }

    /**
     * item的点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = (TextView) view.findViewById(R.id.tv1);
        String title = textView.getText().toString();
        switch (view.getId()) {
            case 158://发布报修
                Intent intent1 = new Intent(ShortMenuActivity.this, ZiXunJieDaConfigActivity.class);
                intent1.putExtra("appId", 104);
                intent1.putExtra("isShort", true);
                intent1.putExtra("title", title);
                startActivity(intent1);
                break;
            case 159://发布派工
                Intent intent2 = new Intent(ShortMenuActivity.this, ZiXunJieDaConfigActivity.class);
                intent2.putExtra("appId", 106);
                intent2.putExtra("isShort", true);
                intent2.putExtra("title", title);
                startActivity(intent2);
                break;
            case 160://考勤打卡
                int isAdmin = (int) SPUtils.get(this, Const.SPDate.IS_ADMIN, -1);
                if (isAdmin == 1) {//不是管理人员
                    openActivty(this, TongJiActivity.class, null, null);
                } else {
                    openActivty(this, ManagerTongJiActivity.class, null, null);
                }
                break;
            case 165://发布新闻
                Intent intent3 = new Intent(ShortMenuActivity.this, ReleaseNewsActivity.class);
                startActivity(intent3);
                break;
            case 166://请假
                Intent intent4 = new Intent(ShortMenuActivity.this, ZiXunJieDaConfigActivity.class);
                intent4.putExtra("appId", 164);
                intent4.putExtra("isShort", true);
                intent4.putExtra("title", title);
                startActivity(intent4);
                break;
            case 167:
                Intent intent5 = new Intent();
                intent5.putExtra("appId", 167);
                setResult(RESULT_OK, intent5);
                finish();
                break;
        }
        finish();
        overridePendingTransition(R.anim.short_menu_activity_in, R.anim.short_menu_activity_out);
    }

    @Override
    public void onClick(View view) {
        finish();
        overridePendingTransition(R.anim.short_menu_activity_in, R.anim.short_menu_activity_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.short_menu_activity_in, R.anim.short_menu_activity_out);
    }
}
