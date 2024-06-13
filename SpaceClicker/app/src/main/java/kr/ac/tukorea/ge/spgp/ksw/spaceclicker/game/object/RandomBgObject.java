package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ITouchable;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.MainScene;

public class RandomBgObject extends Sprite implements IRecyclable, ITouchable {
    public RandomBgObject() {
        super(R.mipmap.crate1);
        // Set random x position
        setPosition((float) Math.random() * 10, -5, 1, 1);
        // Set random speed
        dy = (float) Math.random() * 2 + 1;
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        if (y > 21) {
            Scene.top().remove(MainScene.Layer.BACK_OBJECT, this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void onRecycle() {
        setPosition((float) Math.random() * 10, 0, 1, 1);
        // Set random speed
        dy = (float) Math.random() * 2 + 1;
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            float[] pts = Metrics.fromScreen(event.getX(), event.getY());
            if (getDstRect().contains(pts[0], pts[1])) {
                Scene.top().remove(MainScene.Layer.BACK_OBJECT, this);
                Player.getInstance().getBoxReward(60);
                return true;
            }
        }
        return false;
    }
}
