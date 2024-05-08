package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Button;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class UpgradeScene extends Scene {
    enum Layer {
        BACKGROUND,
        UI,
        END
    }

    public UpgradeScene() {
        if(BuildConfig.DEBUG)
            Log.d("UpgradeScene", "UpgradeScene constructor");

        initLayers(Layer.END);

        Button upgrade1 = new Button(R.mipmap.km) {
            @Override
            public boolean onTouch(MotionEvent event) {
                if(event.getAction() != MotionEvent.ACTION_DOWN)
                    return false;
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                if (dstRect.contains(pts[0], pts[1])) {
                    Log.d("UpgradeScene", "upgrade1 touched");

                    return true;
                }
                return false;
            }
        };
        upgrade1.setPosition(2.5f, 15.f, 4.5f, 1.f);
        add(Layer.UI, upgrade1);
    }


    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public boolean onBackPressed() {
        Scene.pop();
        return true;
    }
}
