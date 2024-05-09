package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Button;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;

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

        VertScrollBackground background = new VertScrollBackground(R.mipmap.bg_space, 1.f);
        add(Layer.BACKGROUND, background);

        Button upgrade1 = makeUpgradeButton(R.mipmap.up1, Player.UpgradeType.CLICK_ANTENNA, 2.25f, 3.f);
        add(Layer.UI, upgrade1);

        Button upgrade2 = makeUpgradeButton(R.mipmap.up2, Player.UpgradeType.SPEED_HIRE, 6.75f, 3.f);
        add(Layer.UI, upgrade2);

        Button upgrade3 = makeUpgradeButton(R.mipmap.up3, Player.UpgradeType.AUTO_SCRAP_ROBOT, 2.25f, 8.f);
        add(Layer.UI, upgrade3);

        Button upgrade4 = makeUpgradeButton(R.mipmap.up5, Player.UpgradeType.SPEED_ENGINE, 6.75f, 8.f);
        add(Layer.UI, upgrade4);

        Button upgrade5 = makeUpgradeButton(R.mipmap.up4, Player.UpgradeType.AUTO_SCRAP_RECYCLE, 2.25f, 13.f);
        add(Layer.UI, upgrade5);

        Button upgrade6 = makeUpgradeButton(R.mipmap.up6, Player.UpgradeType.SPEED_DESIGN, 6.75f, 13.f);
        add(Layer.UI, upgrade6);
    }

    @NonNull
    private static Button makeUpgradeButton(int mipmap, Player.UpgradeType upgradeType, float x, float y) {
        Button upgrade1 = new Button(mipmap) {
            @Override
            public boolean onTouch(MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_DOWN)
                    return false;
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                if (dstRect.contains(pts[0], pts[1])) {
                    Player.getInstance().onUpgrade(upgradeType);
                    return true;
                }
                return false;
            }
        };
        upgrade1.setPosition(x, y, 4.f, 4.f);
        return upgrade1;
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
