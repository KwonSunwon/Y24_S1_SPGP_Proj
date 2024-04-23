package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Scrap;

public class MainScene extends Scene{
    enum Layer {
        BACKGROUND,
        OBJECT,
        UI,
        END
    }

    private Scrap scrap;

    public MainScene() {
        if(BuildConfig.DEBUG)
            Log.d("MainScene", "MainScene constructor");

        initLayers(Layer.END);

        scrap = new Scrap();
        add(Layer.UI, scrap);
    }

    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    public boolean onTouch(MotionEvent event) {
        return scrap.onTouch(event);
    }
}
