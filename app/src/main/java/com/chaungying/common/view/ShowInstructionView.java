package com.chaungying.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chaungying.wuye3.R;

/**
 * @author 王晓赛 or 2016/7/18
 */
public class ShowInstructionView extends FrameLayout {
    private TextView textView;

    public ShowInstructionView(Context context) {
        this(context, null);
    }

    public ShowInstructionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShowInstructionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.show_instruction_view, this);
        textView = (TextView) findViewById(R.id.show_text);
    }


    public void setText(String showStr) {
        textView.setText(showStr);
    }

    /**
     * 设置展示文本的样式
     *
     * @param color           颜色
     * @param size            字体大小
     * @param backgroundColor 字体背景颜色
     */
    public void setTextViewType(int color, int size, int backgroundColor) {
        textView.setTextSize(size);
        textView.setTextColor(color);
        textView.setBackgroundColor(backgroundColor);
    }
}
