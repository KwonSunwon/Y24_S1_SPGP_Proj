package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.util;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ICircleCollidable;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.res.Sound;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.MovablePlayer;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.AsteroidScene;

public class CollisionManager implements IGameObject {
    private final Scene scene;

    public CollisionManager(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void update(float elapsedSeconds) {
        MovablePlayer player = (MovablePlayer) scene.objectsAt(AsteroidScene.Layer.OBJECT).get(0);
        if(player.isInjured())
            return;

        ArrayList<IGameObject> asteroids = scene.objectsAt(AsteroidScene.Layer.BACK_OBJECT);
        for (int i = asteroids.size() - 1; i >= 0; i--) {
            IGameObject asteroid = asteroids.get(i);
            if(CollisionHelper.collides(player, (ICircleCollidable) asteroid)) {
                player.hit();
                Sound.playEffect(R.raw.shoot_destroy_1);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
