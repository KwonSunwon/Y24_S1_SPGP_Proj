package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Scrap;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.SpaceShip;

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

        VertScrollBackground bg = new VertScrollBackground(R.mipmap.bg_space, 1.f);
        add(Layer.BACKGROUND, bg);

        SpaceShip spaceShip = new SpaceShip();
        add(Layer.OBJECT, spaceShip);

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
