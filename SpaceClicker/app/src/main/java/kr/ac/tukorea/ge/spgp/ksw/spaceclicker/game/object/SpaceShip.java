package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class SpaceShip extends Sprite {
    public SpaceShip() {
        super(R.mipmap.spaceship);
        setPosition(4.5f, 8.f, 3.f, 3.f );
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
