package com.hotfix.xyjian.mvpfragment.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Xyjian on 2018/4/14.
 */

public class DemoView  extends View{
    private  final String TAG=getClass().getSimpleName();
    private Paint mPaint=new Paint();
    private int mWidth;
    private int mHeight;
    public DemoView(Context context) {
        super(context);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        Log.d(TAG,"<sizeWidth>"+sizeWidth+"<modeWidth>"+modeWidth+"<sizeHeight>"+sizeHeight+"<modeHeight>"+modeHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;

        Log.d(TAG,"<mWidth>"+mWidth+"<mHeight>"+mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    private void initPaints(){
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG,"onDraw");
        canvas.drawColor(Color.BLUE);
//        canvas.drawPoint(80,80,mPaint);
//        canvas.drawLine(90,90,90,200,mPaint);
//        canvas.drawRect(90,220,150,290,mPaint);
//        canvas.drawRoundRect(90,300,200,400,10,5,mPaint);
//
//        canvas.drawCircle(250,80,50,mPaint);
        canvas.translate(mWidth/4,mHeight/4);
        canvas.drawArc(0,0,80,60,0,90,true,mPaint);
        canvas.rotate(90);
        mPaint.setColor(Color.RED);
        canvas.drawArc(0,0,80,60,0,90,true,mPaint);
    }
}
