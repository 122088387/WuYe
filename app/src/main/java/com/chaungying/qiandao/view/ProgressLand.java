package com.chaungying.qiandao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.chaungying.wuye3.R;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/11/28
 */

public class ProgressLand extends View {

    //Colors (with defaults)
    private int barColor = 0xAA000000;
    private int rimColor = 0x00FFFFFF;

    private int barLength = 50;
    private int barWidth = 5;
    private int rimLength = 50;
    private int rimWidth = 4;

    //Animation
    //The amount of degrees per second
    private float spinSpeed = 230.0f;

    private double barSpinCycleTime = 920/*460*/;

    //Rectangles
    private RectF barBounds = new RectF();
    private RectF rimBounds = new RectF();

    public ProgressLand(Context context) {
        this(context, null);
    }

    public ProgressLand(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        parseAttributes(context.obtainStyledAttributes(attrs, R.styleable.TongJiProgress));
        setupBounds(rimLength, rimWidth);
        setupPaints();
    }

    public void setProgress(float progress) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        barBounds = new RectF(paddingLeft, paddingTop,
                rimLength * progress - paddingRight, rimWidth - paddingBottom + rimWidth);
    }

    //Paints
    private Paint barPaint = new Paint();
    private Paint rimPaint = new Paint();

    private void setupPaints() {
        barPaint.setColor(barColor);
        barPaint.setAntiAlias(true);
        barPaint.setStyle(Paint.Style.FILL);

        rimPaint.setColor(rimColor);
        rimPaint.setAntiAlias(true);
        rimPaint.setStyle(Paint.Style.FILL);
    }

    public ProgressLand(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Parse the attributes passed to the view from the XML
     *
     * @param a the attributes to parse
     */
    private void parseAttributes(TypedArray a) {
        // We transform the default_png values from DIP to pixels
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        barWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, barWidth, metrics);
        rimWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rimWidth, metrics);
        barWidth = (int) a.getDimension(R.styleable.TongJiProgress_tongJi_barWidth, barWidth);
        barLength = (int) a.getDimension(R.styleable.TongJiProgress_tongJi_barLength, barLength);
        rimWidth = (int) a.getDimension(R.styleable.TongJiProgress_tongJi_rimWidth, rimWidth);

        float baseSpinSpeed =
                a.getFloat(R.styleable.TongJiProgress_tongJi_spinSpeed, spinSpeed / 360.0f);
        spinSpeed = baseSpinSpeed * 360;

        barSpinCycleTime =
                a.getInt(R.styleable.TongJiProgress_tongJi_barSpinCycleTime, (int) barSpinCycleTime);


        barColor = a.getColor(R.styleable.TongJiProgress_tongJi_barColor, barColor);

        rimColor = a.getColor(R.styleable.TongJiProgress_tongJi_rimColor, rimColor);
        // Recycle
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            return;
        }
        canvas.drawRect(rimBounds, rimPaint);
        canvas.drawRect(barBounds, barPaint);
    }

    /**
     * Set the bounds of the component
     */
    private void setupBounds(int layout_width, int layout_height) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        rimBounds = new RectF(paddingLeft, paddingTop,
                layout_width - paddingRight, layout_height - paddingBottom + barWidth);

        barBounds = new RectF(paddingLeft, paddingTop,
                layout_width * ((float) barLength / rimLength) - paddingRight, layout_height - paddingBottom + barWidth);
    }

}
