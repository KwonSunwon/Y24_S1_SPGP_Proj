package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ICircleCollidable;
import kr.ac.tukorea.ge.spgp.ksw.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.util.Circle;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.AsteroidScene;

public class Asteroid extends ScrollObject implements ICircleCollidable {
    private Random random = new Random();
    private Circle collisionCircle = new Circle();

    public Asteroid() {
        super(R.mipmap.asteroid_1, 2);
        init();
    }

    public void init(){
        if(random.nextBoolean()) {
            bitmap = BitmapPool.get(R.mipmap.asteroid_1);
        } else {
            bitmap = BitmapPool.get(R.mipmap.asteroid_2);
        }

        radius = (float) (random.nextInt(4) + 1) / 2;
        setPosition(x, y, radius);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

        collisionCircle.set(x, y, radius);

        if (y > 21) {
            Scene.top().remove(AsteroidScene.Layer.BACK_OBJECT, this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (BuildConfig.DEBUG) {
            Paint paint = new Paint();
            paint.setARGB(100, 255, 0, 0);
            canvas.drawCircle(collisionCircle.x, collisionCircle.y, collisionCircle.radius, paint);
        }
    }

    @Override
    public void onRecycle() {
        super.onRecycle();
        init();
    }

    @Override
    public Circle getCollisionCircle() {
        return collisionCircle;
    }
}
