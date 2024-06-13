package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.MainScene;

public class ScrollObject extends Sprite implements IRecyclable {
    public ScrollObject(int mipmapId) {
        super(mipmapId);
        setPosition((float) Math.random() * 10, -5, 1, 1);
        dy = (float) Math.random() * 2 + 1;
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public void onRecycle() {
        setPosition((float) Math.random() * 10, 0, 1, 1);
        dy = (float) Math.random() * 2 + 1;
    }
}
