package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Button;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Scrap;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.SpaceShip;

public class MainScene extends Scene{
    private SpaceShip spaceShip;
    private Scrap scrap;

    enum Layer {
        BACKGROUND,
        OBJECT,
        UI,
        BUTTON,
        END
    }

    public MainScene() {
        if(BuildConfig.DEBUG)
            Log.d("MainScene", "MainScene constructor");

        initLayers(Layer.END);

        VertScrollBackground bg = new VertScrollBackground(R.mipmap.bg_space, 1.f);
        add(Layer.BACKGROUND, bg);

        spaceShip = new SpaceShip();
        add(Layer.OBJECT, spaceShip);

        scrap = new Scrap();
        add(Layer.UI, scrap);

        Button upgradeButton = new Button(R.mipmap.plus_icon) {
            @Override
            public boolean onTouch(MotionEvent event) {
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                if( dstRect.contains(pts[0], pts[1])){
                    Scene.push(new UpgradeScene());
                    return true;
                }
                return false;
            }
        };
        upgradeButton.setPosition(2.5f, 15.f, 4.5f, 1.f);
        add(Layer.BUTTON, upgradeButton);
    }

    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    public boolean onTouch(MotionEvent event) {
        return super.onTouch(event);
    }
}
