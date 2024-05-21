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
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.UpgradeButton;

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

        UpgradeButton upgrade1 = new UpgradeButton(R.mipmap.up1, Player.UpgradeType.CLICK_ANTENNA, 15);
        upgrade1.setPosition(2.25f, 3.f, 4.f, 4.f);
        add(Layer.UI, upgrade1);

        UpgradeButton upgrade2 = new UpgradeButton(R.mipmap.up2, Player.UpgradeType.SPEED_HIRE, 100);
        upgrade2.setPosition(6.75f, 3.f, 4.f, 4.f);
        add(Layer.UI, upgrade2);

        UpgradeButton upgrade3 = new UpgradeButton(R.mipmap.up3, Player.UpgradeType.AUTO_SCRAP_ROBOT, 50);
        upgrade3.setPosition(2.25f, 8.f, 4.f, 4.f);
        add(Layer.UI, upgrade3);

        UpgradeButton upgrade4 = new UpgradeButton(R.mipmap.up5, Player.UpgradeType.SPEED_ENGINE, 1000);
        upgrade4.setPosition(6.75f, 8.f, 4.f, 4.f);
        add(Layer.UI, upgrade4);

        UpgradeButton upgrade5 = new UpgradeButton(R.mipmap.up4, Player.UpgradeType.AUTO_SCRAP_RECYCLE, 100);
        upgrade5.setPosition(2.25f, 13.f, 4.f, 4.f);
        add(Layer.UI, upgrade5);

        UpgradeButton upgrade6 = new UpgradeButton(R.mipmap.up6, Player.UpgradeType.SPEED_DESIGN, 10000);
        upgrade6.setPosition(6.75f, 13.f, 4.f, 4.f);
        add(Layer.UI, upgrade6);
    }

    @Override
    public boolean onBackPressed() {
        Scene.pop();
        return true;
    }
}
