package kr.ac.tukorea.ge.spgp.ksw.spaceclicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.view.View;

public class GameView extends View {
    private final Activity activity;

    public GameView(Context context) {
        super(context);
        this.activity = (Activity)context;

        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(0.1f);
        borderPaint.setColor(Color.RED);

        setFullScreen(); // default behavior?
    }

    public void setFullScreen() {
        int flags = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        setSystemUiVisibility(flags);
    }

    private static final float SCREEN_WIDTH = 9.0f;
    private static final float SCREEN_HEIGHT = 16.0f;
    private Matrix transform = new Matrix();
    private float transformScale = 1;
    private final RectF borderRect = new RectF(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    private final Paint borderPaint;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float view_ratio = (float)w / (float)h;
        float game_ratio = SCREEN_WIDTH / SCREEN_HEIGHT;

        if (view_ratio > game_ratio) {
            float scale = (float)h / SCREEN_HEIGHT;
            transform.setTranslate((w - h * game_ratio) / 2, 0);
            transform.preScale(scale, scale);
        } else {
            float scale = (float)w / SCREEN_WIDTH;
            transform.setTranslate(0, (h - w / game_ratio) / 2);
            transform.preScale(scale, scale);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.concat(transform);
        canvas.scale(transformScale, transformScale);
        canvas.drawRect(borderRect, borderPaint);
        canvas.restore();
    }
}
