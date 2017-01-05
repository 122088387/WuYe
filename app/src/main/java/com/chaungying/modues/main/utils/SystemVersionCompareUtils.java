package com.chaungying.modues.main.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.chaungying.common.bean.VersionBean;
import com.chaungying.common.constant.Const;
import com.chaungying.common.utils.L;
import com.chaungying.metting.view.ProgressUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 创建者：  王晓赛
 * 时  间： 2017/1/5
 * 判断是否为最新版本的工具类
 */

public class SystemVersionCompareUtils {

    public static String versionDesc = "";

    /**
     * 判断是否为最新版本
     *
     * @param context 上下文对象
     * @return true是 false否
     */
    public static void isNewVersion(final Context context) {
        ProgressUtil.show(context, "");
        RequestParams params = new RequestParams(Const.WuYe.URL_VERSION_CHECK);
//        ?ostype=android&optype=version
        params.addParameter("ostype", "android");
        params.addParameter("optype", "version");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
//                {"version":"1.0"}
                Gson gson = new Gson();
                VersionBean bean = gson.fromJson(result, VersionBean.class);
//                1.0.1
                versionDesc = bean.getDesc();
                if (!getLocaVersion(context).equals(bean.getVersion())) {
                    if (isNewVersionListener != null) {
                        isNewVersionListener.newVersionListener(false);
                    }
                } else {
                    if (isNewVersionListener != null) {
                        isNewVersionListener.newVersionListener(true);
                    }
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

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */

    private static String getLocaVersion(Context mContext) {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
//            this.getString(R.string.can_not_find_version_name)
            return "";
        }
    }

    public static IsNewVersionListener isNewVersionListener;

    //是否为最新版本的监听器
    public interface IsNewVersionListener {
        void newVersionListener(boolean isNew);
    }
}
