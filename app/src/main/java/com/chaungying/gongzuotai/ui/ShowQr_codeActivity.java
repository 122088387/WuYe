package com.chaungying.gongzuotai.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.wuye3.R;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/20
 * <p>
 * 显示二维码界面
 */

@ContentView(R.layout.activity_show_qrcode)
public class ShowQr_codeActivity extends BaseActivity {

    public static final int UP_DATA_DATE = 0x0001;

    // 日期
    @ViewInject(R.id.signIn_date)
    private TextView dateTv;

    // 时间
    @ViewInject(R.id.signIn_time)
    private TextView timeTv;

    // 秒
    @ViewInject(R.id.signIn_time_seconds)
    private TextView secondsTv;

    @ViewInject(R.id.qr_code_imagview)
    private ImageView qrImageView;

    SimpleDateFormat formatterDatePoint = new SimpleDateFormat("yyyy.MM.dd");
    SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
    SimpleDateFormat formatterSeconds = new SimpleDateFormat("ss");

    /**
     * Handler线程
     */
    private Handler handler = new Handler() {
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatterDatePoint = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatterSeconds = new SimpleDateFormat("ss");

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UP_DATA_DATE:// 更新时间
                    // 发送延时消息给自己
                    handler.sendEmptyMessageDelayed(UP_DATA_DATE, 1000);
                    // 获取时间
                    Date curDate = new Date(System.currentTimeMillis());
                    // 设置日期
                    dateTv.setText(formatterDatePoint.format(curDate));
                    // 设置时间
                    timeTv.setText(formatterTime.format(curDate));
                    // 设置秒
                    secondsTv.setText(formatterSeconds.format(curDate));
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setActionBar("二维码", R.drawable.nav_return, 0);

        initQrView();
    }

    private void initQrView() {

        handler.sendEmptyMessage(UP_DATA_DATE);// 发送消息更改时间控件

        String textContent = "user_id:" + SPUtils.get(this, Const.SPDate.ID, -1);
        Bitmap mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
        qrImageView.setImageBitmap(mBitmap);


    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(UP_DATA_DATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.removeMessages(UP_DATA_DATE);
        handler.sendEmptyMessage(UP_DATA_DATE);// 发送消息更改时间控件
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(UP_DATA_DATE);
    }
}
