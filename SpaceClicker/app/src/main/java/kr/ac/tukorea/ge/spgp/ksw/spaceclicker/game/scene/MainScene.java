package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Button;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.BgObjectGenerator;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.MiniGameEventGenerator;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.RandomBgObject;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Scrap;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.SpaceShip;

public class MainScene extends Scene{
    private Player player = Player.getInstance();

    public enum Layer {
        BACKGROUND,
        BACK_OBJECT,
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

        add(Layer.OBJECT, player);

        Button upgradeButton = new Button(R.mipmap.upgrade_button) {
            @Override
            public boolean onTouch(MotionEvent event) {
                if(event.getAction() != MotionEvent.ACTION_DOWN)
                    return false;
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                if( dstRect.contains(pts[0], pts[1])){
                    Scene.push(new UpgradeScene());
                    return true;
                }
                return false;
            }
        };
        upgradeButton.setPosition(2.25f, 13.5f, 4.f, 1.5f);
        add(Layer.UI, upgradeButton);

        Button achievementButton = new Button(R.mipmap.achievement_button) {
            @Override
            public boolean onTouch(MotionEvent event) {
                if(event.getAction() != MotionEvent.ACTION_DOWN)
                    return false;
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                if( dstRect.contains(pts[0], pts[1])){
                    Scene.push(new AchievementScene());
                    return true;
                }
                return false;
            }
        };
        achievementButton.setPosition(6.75f, 13.5f, 4.f, 1.5f);
        add(Layer.UI, achievementButton);

        BgObjectGenerator bgObjectGenerator = new BgObjectGenerator();
        add(Layer.BACK_OBJECT, bgObjectGenerator);

        MiniGameEventGenerator miniGameEventGenerator = new MiniGameEventGenerator();
        add(Layer.UI, miniGameEventGenerator);
    }
}
