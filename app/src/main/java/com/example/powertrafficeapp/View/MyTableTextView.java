package com.example.powertrafficeapp.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by 小新 on 2017/7/28.
 */

public class MyTableTextView extends android.support.v7.widget.AppCompatTextView {
    Paint paint = new Paint();

    public MyTableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int color = Color.parseColor("#80b9f2");
        // 为边框设置颜色
        paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画TextView的4个边
        canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
        canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
        canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1, paint);
        canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1, paint);
    }
}