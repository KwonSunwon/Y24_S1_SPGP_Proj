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
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app.MainActivity;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.AchievementManager;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.BgObjectGenerator;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.MiniGameEventGenerator;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.RandomBgObject;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Scrap;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.SpaceShip;

public class MainScene extends Scene{
    private Player player = Player.getInstance();

    public enum Layer {
        CONTROL,
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

        BgObjectGenerator bgObjectGenerator = new BgObjectGenerator();
        add(Layer.BACK_OBJECT, bgObjectGenerator);

        MiniGameEventGenerator miniGameEventGenerator = new MiniGameEventGenerator();
        add(Layer.UI, miniGameEventGenerator);

        AchievementManager achievementManager = AchievementManager.getInstance();
        add(Layer.CONTROL, achievementManager);
    }

    @Override
    protected void onPause() {

    }
}
