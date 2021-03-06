package com.zinc.gaocyui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.zinc.ui2018.R;

public class Chapter1View extends View {
    private Paint paint;
    private Paint borderPaint;
    private Path p1;

    private int width;
    private int height;
    public static final String TAG = Chapter1View.class.getSimpleName();

    public Chapter1View(Context context) {
        this(context, null);
    }

    public Chapter1View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Chapter1View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        borderPaint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setColor(Color.parseColor("#ff990000"));
        paint.setStyle(Paint.Style.STROKE);//Style 修改为画线模式
        paint.setStrokeWidth(dpToPx(4));//设置线条宽度
//        paint.setStyle(Paint.Style.FILL);//Style 修改为填充，默认
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);//Style 修改为填充

        borderPaint.setAntiAlias(true);//设置抗锯齿
        borderPaint.setColor(Color.parseColor("#ffffffff"));
        borderPaint.setStyle(Paint.Style.STROKE);//Style 修改为画线模式
        borderPaint.setStrokeWidth(dpToPx(4));//设置线条宽度

        p1 = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize((int) dpToPx(100), widthMeasureSpec),
                getDefaultSize((int) dpToPx(100), heightMeasureSpec));
    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        Log.d(TAG, "onSizeChanged: w=" + pxToDp(w) + ",h=" + pxToDp(h));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#ff004444"));
        super.onDraw(canvas);
//        canvas.drawCircle(width / 2, height / 2, width / 2, paint);//画圆

//        canvas.drawRect(10, 10, width -10,height -10, paint);//画矩形

//        canvas.drawRoundRect(10.0f, 10.0f, (float)(width -10),(float)(height -10), 50.0f,50.0f,paint);//画矩形

//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawArc(10.0f, 10.0f, (float)(width -10),(float)(height -10), 20,140,true,paint);
//        canvas.drawArc(10.0f, 10.0f, (float)(width -10),(float)(height -10), -110, 100,false,paint);

//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(10.0f, 10.0f, (float)(width -10),(float)(height -10), -110, 100,false,paint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flight);
        canvas.drawBitmap(bitmap,0,0,paint);

//        paint.setTextSize(dpToPx(10));
//        canvas.drawText("This is a flight",width/2,height/2,paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        rotate(canvas,paint,100,100);
        rotate(canvas,paint,60,160);
        canvas.restore();
//
        /*rotate(canvas,paint,60);
        rotate(canvas,paint,80);
        rotate(canvas,paint,120);*/

    }

    private void rotate(Canvas canvas,Paint paint,int degree,int rotateDegree){
        canvas.rotate(-rotateDegree,width/2,height/2);
        canvas.drawArc(10.0f, 10.0f, (float)(width -10),(float)(height -10), 0, degree,true,paint);
        canvas.drawArc(10.0f, 10.0f, (float)(width -10),(float)(height -10), 0, degree,true,borderPaint);
        canvas.rotate(rotateDegree,width/2,height/2);
//        canvas.translate(0,10);
    }

    protected float dpToPx(float dpValue) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (dpValue * metrics.density + 0.5f);
    }

    protected float pxToDp(float pxValue) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (pxValue / metrics.density + 0.5f);
    }
}
