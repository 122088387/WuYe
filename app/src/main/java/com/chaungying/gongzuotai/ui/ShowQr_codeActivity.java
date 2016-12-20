package com.chaungying.gongzuotai.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaungying.BaseActivity;
import com.chaungying.wuye3.R;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/20
 * <p>
 * 显示二维码界面
 */

@ContentView(R.layout.activity_show_qrcode)
public class ShowQr_codeActivity extends BaseActivity {

    @ViewInject(R.id.qr_code_imagview)
    private ImageView qrImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setActionBar("二维码", R.drawable.nav_return, 0);

        initQrView();
    }

    private void initQrView() {
        String textContent = "服务器请求";
        if (TextUtils.isEmpty(textContent)) {
            Toast.makeText(this, "您的输入为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
        qrImageView.setImageBitmap(mBitmap);
    }
}
