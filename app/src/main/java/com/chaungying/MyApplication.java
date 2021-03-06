package com.chaungying;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;
import com.chaungying.common.utils.GlideImageLoader;
import com.chaungying.common.utils.L;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.imagepicker.ImagePicker;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.vmeet.netsocket.bean.SocketObj;
import com.vmeet.netsocket.data.Set;

import org.litepal.LitePalApplication;
import org.xutils.x;

import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

/**
 * @anthor 王晓赛 or 2016/6/22
 */
public class MyApplication extends MultiDexApplication {
    public static MyApplication application;

    public static MyApplication getApplication() {
        return application;
    }

    public static Set MSGSET = new Set("MSGSET");
    public static Set LOGSET = new Set("LOGSET");
    public static Set BASE = new Set("BASE");
    public static Set LOCALSET = new Set("LOCALSET");
    public static Set RSSSET = new Set("RSSSET");
    public static Set ORDERSET = new Set("ORDERSET");
    public static Set BIZSET = new Set("BIZSET");

    public static SocketObj getSocketObj() {
        return socketObj;
    }

    public static void setSocketObj(SocketObj socketObj) {
        MyApplication.socketObj = socketObj;
    }

    public static Set CHARTSET = new Set("CHARTSET");
    public static SocketObj socketObj;
    public static SharedPreferences.Editor editor;
    public static SharedPreferences sharedPreferences;
    public static String approotPath;
    public static HashMap<String, String> userState = new HashMap<String, String>();

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        x.Ext.init(this);
        x.Ext.setDebug(true);
        SDKInitializer.initialize(this);
        // setDebugMode 设置调试模式
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        LitePalApplication.initialize(this);
//        NoHttp.initialize(this);
        //打印Log日志
        L.isDebug = true;
        approotPath = Environment.getExternalStorageDirectory()
                + "/wuye/";
        socketObj = new SocketObj();
        socketObj.setContext(this);
        sharedPreferences = getSharedPreferences("wuye", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Logger.init("WuYe3");
        //二维码扫描初始化
        ZXingLibrary.initDisplayOpinion(this);
        //仿微信的图片选择器初始化
        initImagePicker();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(false);                      //显示拍照按钮
        imagePicker.setCrop(false);                             //是否裁剪
        imagePicker.setMultiMode(false);                        //是否多选
    }

    /**
     * Gson初始化-去乱码
     */
    public static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation().create();


    private static MyApplication WuYeApp;

    /**
     * 单例模式获取app
     *
     * @return
     */
    public static MyApplication Initialize() {
        if (WuYeApp == null) {
            WuYeApp = new MyApplication();
        }
        return WuYeApp;
    }

}
