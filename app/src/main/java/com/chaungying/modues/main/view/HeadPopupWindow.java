package com.chaungying.modues.main.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chaungying.wuye3.R;

/**
 * @author 王晓赛 or 2016/8/26
 */
public class HeadPopupWindow extends PopupWindow implements View.OnClickListener {


    private TextView pictures;
    private TextView photo_album;
    private TextView cancle;
    private View mMenuView;
    private Activity mContext;

    public static final int SELECT_PHONE = 0x0050;
    public static final int TAKE_PICTURE = 0x0060;

    public HeadPopupWindow(Activity context) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.select_head_pop_view, null);
        pictures = (TextView) mMenuView.findViewById(R.id.pictures);
        photo_album = (TextView) mMenuView.findViewById(R.id.photo_album);
        cancle = (TextView) mMenuView.findViewById(R.id.cancle);

        pictures.setOnClickListener(this);
        photo_album.setOnClickListener(this);
        cancle.setOnClickListener(this);

        //设置按钮监听
//        btn_pick_photo.setOnClickListener(itemsOnClick);
//        btn_take_photo.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x22000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.rl_pop).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
//                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pictures:
                //拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mContext.startActivityForResult(intent, TAKE_PICTURE);
                dismiss();
                break;
            case R.id.photo_album:
                //相册
                Intent local = new Intent();
                local.setType("image/*");
                local.setAction(Intent.ACTION_GET_CONTENT);
                mContext.startActivityForResult(local, SELECT_PHONE);
                dismiss();
                break;
            case R.id.cancle:
                //取消
                dismiss();
                break;
        }
    }
}