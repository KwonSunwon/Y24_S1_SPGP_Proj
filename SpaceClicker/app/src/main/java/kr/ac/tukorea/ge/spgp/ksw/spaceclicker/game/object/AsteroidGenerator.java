package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.AsteroidScene;

public class AsteroidGenerator implements IGameObject {
    private final static float MiN_INTERVAL = 0;

    private float interval;
    private float time = 0;

    private final Random random = new Random();

    public AsteroidGenerator() {
        interval = 2;
    }

    @Override
    public void update(float elapsedSeconds) {
        time += elapsedSeconds;
        if (time > interval) {
            time = 0;
            interval = random.nextFloat() * 2 + MiN_INTERVAL;
            Asteroid asteroid = (Asteroid) RecycleBin.get(Asteroid.class);
            if(asteroid == null) {
                asteroid = new Asteroid();
            }
            Scene.top().add(AsteroidScene.Layer.BACK_OBJECT, asteroid);
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
