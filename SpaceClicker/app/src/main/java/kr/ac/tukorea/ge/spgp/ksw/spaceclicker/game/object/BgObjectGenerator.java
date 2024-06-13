package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.MainScene;

public class BgObjectGenerator implements IGameObject{
    private float interval;
    private float time = 0;

    private final Random random = new Random();

    public BgObjectGenerator() {
        interval = random.nextFloat() * 2 + 3;
    }

    @Override
    public void update(float elapsedSeconds) {
        time += elapsedSeconds;
        if (time > interval) {
            time = 0;
            interval = random.nextFloat() * 2 + 3;
            RandomBgObject bgObject = (RandomBgObject) RecycleBin.get(RandomBgObject.class);
            if(bgObject == null) {
                bgObject = new RandomBgObject();
            }
            Scene.top().add(MainScene.Layer.BACK_OBJECT, bgObject);
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
