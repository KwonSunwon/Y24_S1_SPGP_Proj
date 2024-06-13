package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.graphics.Paint;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.MainScene;

public class ScrollObject extends Sprite implements IRecyclable {
    protected float speed = 0;

    public ScrollObject(int mipmapId, float speed) {
        super(mipmapId);
        setPosition((float) Math.random() * 10, -5, 1, 1);
        this.speed = speed;
        dy = (float) Math.random() * 2 + this.speed;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public void onRecycle() {
        setPosition((float) Math.random() * 10, -5, 1, 1);
        dy = (float) Math.random() * 2 + this.speed;
    }
}
