package com.yarolegovich.colorwheelshader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yarolegovich on 4/1/18.
 */

public class ColorWheelView extends View {

    private static final int DEFAULT_BRIGHTNESS = 224;

    private float centerX;
    private float centerY;
    private float radius;

    private Paint huePaint;
    private Paint saturationPaint;
    private Paint brightnessOverlayPaint;

    public ColorWheelView(Context context) {
        super(context);
    }

    public ColorWheelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ColorWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    {
        huePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        saturationPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        brightnessOverlayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        brightnessOverlayPaint.setColor(Color.BLACK);
        brightnessOverlayPaint.setAlpha(brightnessToAlpha(DEFAULT_BRIGHTNESS));
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(w, h) * 0.48f;
        centerX = w * 0.5f;
        centerY = h * 0.5f;
        recomputeShader();
    }

    @Override protected void onDraw(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, radius, huePaint);
        canvas.drawCircle(centerX, centerY, radius, saturationPaint);
        canvas.drawCircle(centerX, centerY, radius, brightnessOverlayPaint);
    }

    private void recomputeShader() {
        Shader hueShader = new SweepGradient(centerX, centerY,
            new int[] {
                Color.RED, Color.MAGENTA, Color.BLUE, Color.CYAN,
                Color.GREEN, Color.YELLOW, Color.RED},
            new float[] {
                0.000f, 0.166f, 0.333f, 0.499f,
                0.666f, 0.833f, 0.999f});
        huePaint.setShader(hueShader);

        Shader satShader = new RadialGradient(centerX, centerY, radius,
            Color.WHITE, 0x00FFFFFF,
            Shader.TileMode.CLAMP);
        saturationPaint.setShader(satShader);
    }

    public void setBrightness(int brightness) {
        brightnessOverlayPaint.setAlpha(brightnessToAlpha(brightness));
        invalidate();
    }

    private int brightnessToAlpha(int brightness) {
        return 255 - brightness;
    }
}
