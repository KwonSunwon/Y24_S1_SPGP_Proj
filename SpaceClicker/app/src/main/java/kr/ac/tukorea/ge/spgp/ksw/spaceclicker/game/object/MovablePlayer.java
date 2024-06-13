package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ITouchable;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class MovablePlayer extends Sprite implements ITouchable, IBoxCollidable {
    public float introTimer = 0;
    private float scale = 3.f;

    private float targetX, targetY;

    private boolean isTouched;

    public int hp = 3;
    private float injuredTime = 0;
    private Paint paint = new Paint();

    public MovablePlayer() {
        super(R.mipmap.spaceship);

        setPosition(4.5f, 8.f, 3.f, 3.f);
    }

    @Override
    public void update(float elapsedSeconds) {
        if (introTimer < 2) {
            introTimer += elapsedSeconds;
            if (introTimer > 2){
                introTimer = 2;
                targetX = x;
                targetY = y;
            }
            scale = 3.f - introTimer;
            setPosition(4.5f, 8.f + introTimer, scale, scale);
            return;
        }
        setPosition(targetX, targetY, scale, scale);
        if(injuredTime > 0){
            injuredTime -= elapsedSeconds;
        }
    }

    @Override
    public void draw(Canvas canvas){
        if(injuredTime > 0){
            // blink player
            paint.setAlpha((int)(255 * Math.sin(injuredTime * 40)));
        }
        else
            paint.setAlpha(255);
        canvas.drawBitmap(bitmap, null, dstRect, paint);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        float[] pts;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pts = Metrics.fromScreen(event.getX(), event.getY());
                if(dstRect.contains(pts[0], pts[1])) {
                    isTouched = true;
                    targetX = pts[0];
                    targetY = pts[1];
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(isTouched){
                    pts = Metrics.fromScreen(event.getX(), event.getY());
                    targetX = pts[0];
                    targetY = pts[1];
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                isTouched = false;
                return true;
        }
        return false;
    }

    @Override
    public RectF getCollisionRect() {
        return getDstRect();
    }

    public boolean isInjured(){
        return injuredTime > 0;
    }

    public void hit() {
        hp--;
        injuredTime = 0.5f;
    }
}
