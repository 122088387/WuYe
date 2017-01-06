package com.chaungying.news.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chaungying.BaseActivity;
import com.chaungying.common.constant.Base;
import com.chaungying.common.constant.Const;
import com.chaungying.common.ui.PhotoActivity;
import com.chaungying.common.utils.SPUtils;
import com.chaungying.common.utils.T;
import com.chaungying.common.utils.file.AsyncTaskForUpLoadFiles;
import com.chaungying.common.utils.file.Bimp;
import com.chaungying.metting.view.ProgressUtil;
import com.chaungying.news.view.CameraViewOfNews;
import com.chaungying.wuye3.R;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.chaungying.common.utils.file.Bimp.saveBitmap;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/15
 * 发布新闻
 */

@ContentView(R.layout.activity_release_news)
public class ReleaseNewsActivity extends BaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    @ViewInject(R.id.release_news_title)
    private EditText title;

    @ViewInject(R.id.radio_group)
    private RadioGroup radio_group;

    @ViewInject(R.id.release_news_content)
    private EditText content;

    @ViewInject(R.id.send_who)
    private TextView send_who;

    @ViewInject(R.id.title_menu)
    private TextView title_menu;

    @ViewInject(R.id.rl_select_person)
    private RelativeLayout rl_select_person;

    private static final int RETURN_PUSH_TYPE = 0X0023;
    private static final int PULODATE_HEAD = 0X0024;
    private static final int SELECT_PHONE = 0X0025;

    private int pushType;
    private String ids = "";
    private String names = "";

    private int activityType = 0;//提交的类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        setActionBarText(R.string.release_news_notice, R.drawable.nav_return, R.string.tijiao);
        cameraViewVar.setHandler(handler);
        rl_select_person.setOnClickListener(this);
        title_menu.setOnClickListener(this);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.select_news:
                        activityType = 0;
                        break;
                    case R.id.select_notice:
                        activityType = 1;
                        break;
                }
            }
        });
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CameraViewOfNews.CAMERA_PIC:
                    //打开相机，并处理图片
                    // 利用系统自带的相机应用:拍照
                    bmp = msg.getData().getParcelableArrayList("bmp");
                    methodRequiresTwoPermission();
                    break;
                case CameraViewOfNews.PHOTO_ALBUM_PIC:
                    bmp = msg.getData().getParcelableArrayList("bmp");
                    //相册选择照片
                    selectPhoto();
                    break;
                case PULODATE_HEAD:
                    int respCode = msg.getData().getInt("respCode");
                    ProgressUtil.close();
                    if (respCode == 1001) {
                        T.showShort(ReleaseNewsActivity.this, "提交成功");
                        finish();
                    } else {
                        T.showShort(ReleaseNewsActivity.this, "提交失败");
                    }
                    break;
            }
        }
    };

    /////////////////////////对相机界面的控制////////////////////////////
    @ViewInject(R.id.camera_view)
    CameraViewOfNews cameraViewVar;

    public List<String> drr = new ArrayList<String>();
    public List<Bitmap> bmp = new ArrayList<Bitmap>();
    private static final int TAKE_PICTURE = 0;
    private Uri photoUri;

    /**
     * 相机拍照
     */
    public void photo() {
        try {
            //设置启动相机意图
            Intent openCameraIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);

            String sdcardState = Environment.getExternalStorageState();
            String sdcardPathDir = Const.SAVE_MEDAR.PIC_PATH;
            File file = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                // 有sd卡，是否有myImage文件夹
                File fileDir = new File(sdcardPathDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                // 是否有headImg文件
                file = new File(sdcardPathDir, System.currentTimeMillis() + ".JPG");
            }
            if (file != null) {
                photoUri = Uri.fromFile(file);
                if (!file.exists()) {
                    file.createNewFile();
                }
                drr.add(photoUri.getPath());
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////////相册选择图片////////////////
    public void selectPhoto() {
//        Intent local = new Intent();
//        local.setType("image/*");
//        local.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(local, SELECT_PHONE);
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, SELECT_PHONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:// 拍照
//                if (drr.size() < 6 && resultCode == -1) {// 拍照 对图片的限制为五张，目前无限制
                if (resultCode == -1) {
                    //对图片处理之后放入到内存卡中
                    Bitmap bitmap1 = Bimp.revitionImageSize(drr.get(drr.size() - 1));
                    PhotoActivity.bitmap.add(bitmap1);
                    //将图片切为圆角的
                    Bitmap bitmap2 = Bimp.createFramedPhoto(480, 480, bitmap1, 0);
                    bmp.add(bitmap2);
                    cameraViewVar.gridviewInit(drr);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHssmm");
                    String pathSD = saveBitmap(bitmap1, sdf.format(new Date()));
                    files1.put(pathSD, new File(pathSD));
                    filePathList.add(pathSD);
                    cameraViewVar.setFilePathList(filePathList);
                    cameraViewVar.setFileList(files1);

                }
                break;
            case RETURN_PUSH_TYPE:
                if (data != null) {
                    pushType = data.getIntExtra("pushType", 0);
                    ids = data.getStringExtra("ids");
                    names = data.getStringExtra("names");
                    if (names != null && !"".equals(names)) {
                        send_who.setText(names);
                    }
                }
                break;
            case SELECT_PHONE://选择照片的请求code
                if (data != null) {
//                    Uri originalUri = data.getData();
//                    if(originalUri!=null){
//                        String uriStr=originalUri.toString();
//                        String path=uriStr.substring(10,uriStr.length());
//                        if(path.startsWith("com.sec.android.gallery3d")){
//                            return;
//                        }
//                    }
//                    String[] proj = {MediaStore.Images.Media.DATA};
//                    //好像是android多媒体数据库的封装接口，具体的看Android文档
//                    Cursor cursor = managedQuery(originalUri, proj, null, null, null);
//                    //按我个人理解 这个是获得用户选择的图片的索引值
//                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                    //将光标移至开头 ，这个很重要，不小心很容易引起越界
//                    cursor.moveToFirst();
//                    String path = cursor.getString(column_index);
//                    drr.add(path);
                    if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {//注意：选择图片库中的返回码resultCode已经存在
                        //添加图片返回
                        if (data != null) {
                            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                            if (images.size() > 0) {
                                drr.add(images.get( 0).path);
                            }
                        }
                    }
                    //对图片处理之后放入到内存卡中
                    Bitmap bitmap1 = Bimp.revitionImageSize(drr.get(drr.size() - 1));
                    PhotoActivity.bitmap.add(bitmap1);
                    //将图片切为圆角的
                    Bitmap bitmap2 = Bimp.createFramedPhoto(480, 480, bitmap1, 0);
                    bmp.add(bitmap2);
                    cameraViewVar.gridviewInit(drr);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHssmm");
                    String pathSD = saveBitmap(bitmap1, sdf.format(new Date()));
                    files1.put(pathSD, new File(pathSD));
                    filePathList.add(pathSD);
                    cameraViewVar.setFilePathList(filePathList);
                    cameraViewVar.setFileList(files1);
                }
                break;
        }
    }

    //上传的文件map
    Map<String, File> files1 = new HashMap<String, File>();
    public List<String> filePathList = new ArrayList<String>();

    /**
     * 照片图片的上传数据设置
     *
     * @param path
     */

    private void setData(List<String> path) {
        if (path.size() > 0) {
//            for (int i = 0; i < path.size(); i++) {
//                Bitmap bitmap1 = Bimp.revitionImageSize(path.get(i));
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHssmm");
//                String pathSD = saveBitmap(bitmap1, sdf.format(new Date()));
//                files1.put(pathSD, new File(pathSD));
//            }
            Map<String, String> params = new HashMap<String, String>();
            params.put("districtId", (String) SPUtils.get(this, Const.SPDate.USER_DISTRICT_ID, ""));
            String tempTitle = title.getText().toString();
            if (tempTitle.equals("")) {
                T.showShort(this, "标题和内容不能为空");
                return;
            }
            params.put("title", tempTitle);
            String tempContent = content.getText().toString();
            if (tempContent.equals("")) {
                T.showShort(this, "标题和内容不能为空");
                return;
            }
            params.put("content", tempContent);
            params.put("memberId", SPUtils.get(this, Const.SPDate.ID, -1) + "");
            if (pushType == 0) {
                T.showShort(this, "请选择人员");
                return;
            }
            params.put("pushType", pushType + "");//1全部 2 部门 3角色 4人员
            if (pushType == 1) {//全部 传 ""
                params.put("values", "");//选中的id数组
            } else {
                params.put("values", ids);//选中的id数组
            }
            params.put("activityType", activityType + "");// 0新闻 1公告
            ProgressUtil.show(this, "提交中...");
            new AsyncTaskForUpLoadFiles(
                    Const.WuYe.URL_PUSH_ACTIVITY_BY_PHONE
                    , params, files1, "images", handler.obtainMessage(PULODATE_HEAD)).execute();
        } else {
            RequestParams params = new RequestParams(Const.WuYe.URL_PUSH_ACTIVITY_BY_PHONE2);
            params.addParameter("districtId", (String) SPUtils.get(this, Const.SPDate.USER_DISTRICT_ID, ""));
            String tempTitle = title.getText().toString();
            if (tempTitle.equals("")) {
                T.showShort(this, "标题和内容不能为空");
                return;
            }
            params.addParameter("title", tempTitle);
            String tempContent = content.getText().toString();
            if (tempContent.equals("")) {
                T.showShort(this, "标题和内容不能为空");
                return;
            }
            params.addParameter("content", tempContent);
            params.addParameter("memberId", SPUtils.get(this, Const.SPDate.ID, -1) + "");
            if (pushType == 0) {
                T.showShort(this, "请选择人员");
                return;
            }
            params.addParameter("pushType", pushType + "");//1全部 2 部门 3角色 4人员
            if (pushType == 1) {//全部 传 ""
                params.addParameter("values", "");//选中的id数组
            } else {
                params.addParameter("values", ids);//选中的id数组
            }
            params.addParameter("activityType", activityType);// 0新闻 1公告

            ProgressUtil.show(this, "提交中...");
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Base base = new Gson().fromJson(result, Base.class);
                    if (base.respCode == 1001) {
                        T.showShort(ReleaseNewsActivity.this, base.respMsg);
                        finish();
                    } else {
                        T.showShort(ReleaseNewsActivity.this, base.respMsg);
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    ProgressUtil.close();
                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_select_person:
                openActivtyForResult(this, SelectPersonalActivity.class, null, RETURN_PUSH_TYPE);
                break;
            case R.id.title_menu:
                setData(drr);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PhotoActivity.bitmap.clear();
    }

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;


    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permission, do the thing!
            photo();
        } else {
            // Do not have permissions, request them now  // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                    REQUEST_CAMERA_PERM, perms);
        }
    }


    /**
     * EsayPermissions接管权限处理逻辑
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions  结果转发给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 授予权限
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        Toast.makeText(getContext(), "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    /**
     * 拒绝权限
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Toast.makeText(getContext(), "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "拍照需要在设置页面找到“权限管理->相机->允许”进行操作 ，请点击“确定”进入设置页面去操作。")
                    .setTitle("权限申请")
                    .setPositiveButton("确定")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(REQUEST_CAMERA_PERM)
                    .build()
                    .show();
        }
    }
}
