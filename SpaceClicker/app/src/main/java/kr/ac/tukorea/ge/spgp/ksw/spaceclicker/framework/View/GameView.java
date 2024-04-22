package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.Game.framework.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.graphics.Matrix;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.Game.framework.Object.Scrap;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.Game.framework.Interface.GameObject;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class GameView extends View implements Choreographer.FrameCallback{
    private final Activity activity;

    private Scrap scrap = new Scrap();
    private RectF touchSpace = new RectF(1, 1, 8, 15);
    private Bitmap backgroudBitmap;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public GameView(Context context) {
        super(context);
        this.activity = (Activity)context;

        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(0.1f);
        borderPaint.setColor(Color.RED);

        setFullScreen(); // default behavior?

        scrap.Init();
        gameObjects.add(scrap);

        backgroudBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.space_backgroud_x4);
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

        Metrics.onSize(w, h);
    }

    private long previousNanos = 0;
    private float elapsedSeconds = 0;
    private void scheduleUpdate() {
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long nanos) {
        long elapsedNanos = nanos - previousNanos;
        elapsedSeconds = elapsedNanos / 1_000_000_000f;
        if (previousNanos != 0) {
            update();
        }
        invalidate();
        if (isShown()) {
            scheduleUpdate();
        }
        previousNanos = nanos;
    }
    private void update() {
        // Scene Update
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        DrawBackground(canvas);

        canvas.save();
        canvas.concat(transform);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(1);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(scrap.GetScrap(), 2, 0, paint);
        canvas.restore();

        DrawDebugBox(canvas);
    }

    private void DrawBackground(Canvas canvas) {
        Rect srcRect = new Rect(700, 0, getWidth() / 2 + 700, getHeight() / 2);
        Rect dstRect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawBitmap(backgroudBitmap, srcRect, dstRect, null);
    }

    private void DrawDebugBox(Canvas canvas) {
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

        invalidate();
        return super.onTouchEvent(event);
    }

    @NonNull
    private float[] ChangeScreenPointToGamePoint(float x, float y) {
        float[] touchPoint = new float[] { x, y };
        inverse.mapPoints(touchPoint, 0, touchPoint, 0, 1);
        return touchPoint;
    }
}
