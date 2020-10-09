package com.zinc.gaocyui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class Chapter1View extends View {
    private Paint paint;

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
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ff990000"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#ff004444"));
        super.onDraw(canvas);
        canvas.drawCircle(dpToPx(25.0f),dpToPx(25.0f),dpToPx(25.0f),paint);
    }

    protected float dpToPx(float dpValue) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return  (dpValue * metrics.density + 0.5f);
    }
}
