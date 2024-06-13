package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.sax.StartElementListener;
import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ITouchable;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class MovablePlayer extends Sprite implements ITouchable {
    private float smallTimer = 0;
    private float scale = 3.f;

    private float targetX, targetY;

    static final private float MAX_SPEED = 10.f;
    private boolean isTouched;

    public MovablePlayer() {
        super(R.mipmap.spaceship);

        setPosition(4.5f, 8.f, 3.f, 3.f);
    }

    @Override
    public void update(float elapsedSeconds) {
        if (smallTimer < 2) {
            smallTimer += elapsedSeconds;
            if (smallTimer > 2){
                smallTimer = 2;
                targetX = x;
                targetY = y;
            }
            scale = 3.f - smallTimer;
            setPosition(4.5f, 8.f + smallTimer, scale, scale);
            return;
        }

        setPosition(targetX, targetY, scale, scale);
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
}
