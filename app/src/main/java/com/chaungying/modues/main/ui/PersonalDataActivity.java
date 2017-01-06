package com.chaungying.modues.main.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.common.utils.T;
import com.chaungying.common.utils.file.AsyncTaskForUpLoadFiles;
import com.chaungying.common.utils.file.Bimp;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.modues.main.view.HeadPopupWindow;
import com.chaungying.wuye3.R;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.squareup.picasso.Picasso;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.chaungying.modues.main.view.SetFragment.PULODATE_HEAD;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/9
 */

@ContentView(R.layout.activity_personal_data)
public class PersonalDataActivity extends BaseActivity {


    @ViewInject(R.id.touxiang)
    private ImageView touxiang;

    @ViewInject(R.id.rl_touxiang)
    private RelativeLayout rl_touxiang;

    @ViewInject(R.id.xingming)
    private TextView name;
    @ViewInject(R.id.dianhua)
    private TextView phone;
    @ViewInject(R.id.gongsi_title)
    private TextView gongsi_title;
    @ViewInject(R.id.gongsi)
    private TextView company;

    public static final int SELECT_PHONE = 0x0050;
    public static final int TAKE_PICTURE = 0x0060;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setActionBar("个人资料", R.drawable.nav_return, 0);

        String path = (String) SPUtils.get(this, Const.SPDate.HEAD_URL, "");
        if (path != null && !"".equals(path)) {
            Picasso.with(this).load(path).error(R.drawable.person_touxiang).into(touxiang);
        }

        name.setText(SPUtils.get(this, Const.SPDate.USER_NAME, "") + "");
        phone.setText(SPUtils.get(this, Const.SPDate.LONGING_NAME, "") + "");
        if ((int) SPUtils.get(this, Const.SPDate.IS_WUYE_OR_YEZHU, 1) == 1) {
            //物业人员
            gongsi_title.setText("园区");
            company.setText(SPUtils.get(this, Const.SPDate.USER_DISTRICT_NAME, "") + "");
        } else if ((int) SPUtils.get(this, Const.SPDate.IS_WUYE_OR_YEZHU, 1) == 2) {
            //业主人员
            gongsi_title.setText("公司");
            company.setText(SPUtils.get(this, Const.SPDate.YE_ZHU_COMPANY, "") + "");
        }
    }

    @Event(value = R.id.touxiang)
    private void touxiang(View view) {
//        HeadPopupWindow headPopupWindow = new HeadPopupWindow(this);
//        //设置layout在PopupWindow中显示的位置
//        headPopupWindow.showAtLocation((this).findViewById(R.id.main),
//                Gravity.FILL | Gravity.CENTER_HORIZONTAL, 0, 0);
        showDialog();

    }

    @Event(value = R.id.rl_touxiang)
    private void rl_touxiang(View view) {

//        HeadPopupWindow headPopupWindow = new HeadPopupWindow(this);
//        //设置layout在PopupWindow中显示的位置
//        headPopupWindow.showAtLocation((this).findViewById(R.id.main),
//                Gravity.FILL | Gravity.CENTER_HORIZONTAL, 0, 0);

        showDialog();


    }

    public void showDialog() {
        final String[] stringItems = {"拍照", "相册"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
//            dialog.title("选择群消息提醒方式\r\n(该群在电脑的设置:接收消息并提醒)")//
//                    .titleTextSize_SP(14.5f)//
//                    .show();
        dialog.isTitleShow(false).show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //拍照
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, TAKE_PICTURE);
                } else if (position == 1) {
                    //相册
//                    Intent local = new Intent();
//                    local.setType("image/*");
//                    local.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(local, SELECT_PHONE);
                    Intent intent = new Intent(PersonalDataActivity.this, ImageGridActivity.class);
                    startActivityForResult(intent, HeadPopupWindow.SELECT_PHONE);
                }
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case HeadPopupWindow.SELECT_PHONE:
//                    Uri originalUri = data.getData();
//                    String[] proj = {MediaStore.Images.Media.DATA};
//                    //好像是android多媒体数据库的封装接口，具体的看Android文档
//                    Cursor cursor = managedQuery(originalUri, proj, null, null, null);
//                    //按我个人理解 这个是获得用户选择的图片的索引值
//                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                    //将光标移至开头 ，这个很重要，不小心很容易引起越界
//                    cursor.moveToFirst();
//                    String path = cursor.getString(column_index);
//                    upLoadHeadImg(path);
                    break;
                case HeadPopupWindow.TAKE_PICTURE:
                    Bundle bundle = data.getExtras();
                    // 获取相机返回的数据，并转换为Bitmap图片格式，这是缩略图
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    String sdcardPathDir = Const.SAVE_MEDAR.PIC_PATH;
                    File fileDir = new File(sdcardPathDir);
                    if (!fileDir.exists()) {
                        fileDir.mkdirs();
                    }
                    // 是否有headImg文件
                    File file = new File(sdcardPathDir, System.currentTimeMillis() + ".JPG");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String tempPath = Bimp.saveBitmap(bitmap, file.getPath());
                    upLoadHeadImg(tempPath);
                    break;
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {//注意：选择图片库中的返回码resultCode已经存在
            //添加图片返回
            if (data != null && requestCode == HeadPopupWindow.SELECT_PHONE) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images.size() > 0) {
                    upLoadHeadImg(images.get(0).path);
                }
            }
        }
    }

    //存储上传头像的临时文件夹
    private File tempFile;

    /**
     * 上传头像
     */
    private void upLoadHeadImg(String filePath) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", SPUtils.get(this, Const.SPDate.ID, -1) + "");
        Map<String, File> files = new HashMap<String, File>();

        //压缩图片并保存到临时文件夹
        Bitmap bitmap1 = Bimp.revitionImageSize(filePath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHssmm");
        String pathSD = Bimp.saveBitmap(bitmap1, sdf.format(new Date()));
        tempFile = new File(pathSD);
        files.put(pathSD, tempFile);

        ProgressUtil.show(this, "上传中...");
        new AsyncTaskForUpLoadFiles(Const.WuYe.URL_USER_UPLOAD_HEAD_IMAGE,
                params,
                files,
                "multipartFile",
                handler.obtainMessage(PULODATE_HEAD)).execute();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PULODATE_HEAD:
                    Bundle bundle = msg.getData();
                    int respCode = bundle.getInt("respCode");
                    if (respCode == 1001) {
                        //将新的头像地址保存到本地
                        SPUtils.remove(PersonalDataActivity.this, Const.SPDate.HEAD_URL);
                        SPUtils.put(PersonalDataActivity.this, Const.SPDate.HEAD_URL, bundle.getString("data"));
                        Picasso.with(PersonalDataActivity.this).load(bundle.getString("data")).into(touxiang);
                    } else {
                        T.showShort(PersonalDataActivity.this, "上传头像失败");
                    }
                    ProgressUtil.close();
                    break;
            }
        }
    };

}
