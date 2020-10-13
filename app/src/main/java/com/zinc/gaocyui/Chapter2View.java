package com.zinc.gaocyui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.zinc.ui2018.R;

public class Chapter2View extends View {
    private Paint paint;
    private Paint borderPaint;

    private int width;
    private int height;
    public static final String TAG = Chapter2View.class.getSimpleName();

    public Chapter2View(Context context) {
        this(context, null);
    }

    public Chapter2View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Chapter2View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        borderPaint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setColor(Color.parseColor("#ff990000"));
        paint.setStyle(Paint.Style.FILL);//Style 修改为画线模式
        paint.setStrokeWidth(dpToPx(4));//设置线条宽度

        borderPaint.setAntiAlias(true);//设置抗锯齿
        borderPaint.setColor(Color.parseColor("#ffffffff"));
        borderPaint.setStyle(Paint.Style.STROKE);//Style 修改为画线模式
        borderPaint.setStrokeWidth(dpToPx(4));//设置线条宽度

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
        super.onDraw(canvas);
        Shader shader0 = new LinearGradient(0, 0, width, height, Color.parseColor("#ffff0000"),
                Color.parseColor("#ff00ff00"), Shader.TileMode.CLAMP);//从左上角渐变到右下角
        Shader shader1 = new LinearGradient(width / 2, 0, width / 2, height, Color.parseColor("#ffff0000"),
                Color.parseColor("#ff00ff00"), Shader.TileMode.CLAMP);//从上到下渐变到右下角

        Shader shader2 = new RadialGradient(width / 2, height / 2, width / 4, Color.parseColor("#ffff0000"),
                Color.parseColor("#ff00ff00"), Shader.TileMode.CLAMP);
        Shader shader21 = new RadialGradient(width / 2, height / 2, width / 4, Color.parseColor("#ffff0000"),
                Color.parseColor("#ff00ff00"), Shader.TileMode.MIRROR);
        Shader shader22 = new RadialGradient(width / 2, height / 2, width / 4, Color.parseColor("#ffff0000"),
                Color.parseColor("#ff00ff00"), Shader.TileMode.REPEAT);

        Shader shader3 = new SweepGradient(width / 2, height / 2, Color.parseColor("#ffff0000"),
                Color.parseColor("#ff00ff00"));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flight);
        Shader shader4 = new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
//        paint.setShader(shader0);
//        paint.setShader(shader1);
//        paint.setShader(shader2);
//        paint.setShader(shader21);
//        paint.setShader(shader22);
//        paint.setShader(shader3);
//        paint.setShader(shader4);
//        canvas.drawCircle(width / 2, height / 2, width / 2, paint);

        paint.setTextSize(dpToPx(15));
        String text = "hello";
        canvas.drawText(text,width / 2, height / 2,paint);
        Rect rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        float measureText = paint.measureText(text);

        paint.setStyle(Paint.Style.STROKE);
        float strokeWidth = paint.getStrokeWidth();
        canvas.drawRect(width/2 +rect.left -strokeWidth,height/2 +rect.top -strokeWidth,width/2 +rect.right +strokeWidth,height/2 +rect.bottom +strokeWidth,paint);
        Log.d(TAG, "onDraw: rect="+rect+", measureText="+measureText);
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
