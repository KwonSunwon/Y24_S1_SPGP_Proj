package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ITouchable;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class SpaceShip extends Sprite{
    private long speed;
    private long distance;

    private ImageNumber speedDisplay;
    private ImageNumber distanceDisplay;

    private Sprite speedIcon;
    private Sprite distanceIcon;

    public SpaceShip() {
        super(R.mipmap.spaceship);
        setPosition(4.5f, 8.f, 3.f, 3.f );

        // Load speed and distance from save file
        speed = 100;
        distance = 0;

        speedDisplay = new ImageNumber(R.mipmap.font1, 8f, 1.5f, 0.3f);
        distanceDisplay = new ImageNumber(R.mipmap.font1, 7.8f, 2.f, 0.5f);

        speedIcon = new Sprite(R.mipmap.speed_icon);
        speedIcon.setPosition(8.5f, 1.7f, 0.5f, 0.5f);

        distanceIcon = new Sprite(R.mipmap.km);
        distanceIcon.setPosition(8.5f, 2.35f, 1.f, 0.4f);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        distance += (long) (speed * elapsedSeconds);
        speedDisplay.setNumber(speed);
        distanceDisplay.setNumber(distance);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        distanceDisplay.draw(canvas);
        speedDisplay.draw(canvas);
        distanceIcon.draw(canvas);
        speedIcon.draw(canvas);
    }

    public boolean onTouch(MotionEvent event) {
        float[] pts = Metrics.fromScreen(event.getX(), event.getY());
        return dstRect.contains(pts[0], pts[1]);
    }
}
