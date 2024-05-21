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

    private int crewUpgradeLevel;
    private int engineUpgradeLevel;
    private int designUpgradeLevel;

    private ImageNumber speedDisplay;
    private ImageNumber distanceDisplay;

    private Sprite speedIcon;
    private Sprite distanceIcon;
    private float distanceAdder = 0;

    public SpaceShip() {
        super(R.mipmap.spaceship);
        setPosition(4.5f, 8.f, 3.f, 3.f );

        // Load speed and distance from save file
        speed = 1;
        distance = 0;
        crewUpgradeLevel = 0;
        engineUpgradeLevel = 0;
        designUpgradeLevel = 0;

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
        crewUpgradeLevel = Player.getUpgradeLevel(Player.UpgradeType.SPEED_HIRE);
        engineUpgradeLevel = Player.getUpgradeLevel(Player.UpgradeType.SPEED_ENGINE);
        designUpgradeLevel = Player.getUpgradeLevel(Player.UpgradeType.SPEED_DESIGN);

        long speed = 1 + crewUpgradeLevel * 1 + engineUpgradeLevel * 5 + designUpgradeLevel * 10;
        distanceAdder += (speed * elapsedSeconds);
        if (distanceAdder >= 1) {
            distance += (long) distanceAdder;
            distanceAdder -= (long) distanceAdder;
        }
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
