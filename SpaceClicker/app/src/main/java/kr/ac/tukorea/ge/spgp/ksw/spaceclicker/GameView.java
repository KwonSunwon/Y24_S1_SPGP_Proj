package kr.ac.tukorea.ge.spgp.ksw.spaceclicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class GameView extends View {
    private final Activity activity;

    private Scrap scrap = new Scrap();
    private RectF touchSpace = new RectF(1, 1, 8, 15);

    public GameView(Context context) {
        super(context);
        this.activity = (Activity)context;

        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(0.1f);
        borderPaint.setColor(Color.RED);

        setFullScreen(); // default behavior?

        scrap.Init();
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
    private Matrix inverse = new Matrix();
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
        transform.invert(inverse);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.concat(transform);
        canvas.drawRect(borderRect, borderPaint);
        canvas.drawRect(touchSpace, borderPaint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float[] touchPoint = ChangeScreenPointToGamePoint(event.getX(), event.getY());
        if (touchSpace.contains(touchPoint[0], touchPoint[1])) {
            scrap.AddScrapFromClick();
        }
        Log.d("GameView Touch", "Touch at " + touchPoint[0] + ", " + touchPoint[1] + " scrap: " + scrap.GetScrap());
        return super.onTouchEvent(event);
    }

    @NonNull
    private float[] ChangeScreenPointToGamePoint(float x, float y) {
        float[] touchPoint = new float[] { x, y };
        inverse.mapPoints(touchPoint, 0, touchPoint, 0, 1);
        return touchPoint;
    }
}
