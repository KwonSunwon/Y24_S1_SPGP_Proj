package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

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
    }

    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    public boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if(IsClickUpgradeButton(event)){
                new UpgradeScene().push();
                return true;
            }
            else if(spaceShip.onTouch(event))
                return scrap.onTouch();
        }
        return false;
    }

    private RectF upgradeButtonRect = new RectF(0.f, 15.f, 4.5f, 16.f);
    private boolean IsClickUpgradeButton(MotionEvent event) {
        float[] pts = Metrics.fromScreen(event.getX(), event.getY());
        return upgradeButtonRect.contains(pts[0], pts[1]);
    }
}
