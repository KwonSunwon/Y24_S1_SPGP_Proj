package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.util.Log;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp.ksw.framework.res.Sound;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.AchievementManager;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.BgObjectGenerator;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.MiniGameEventGenerator;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;

public class MainScene extends Scene{
    private final MiniGameEventGenerator miniGameEventGenerator;
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

        miniGameEventGenerator = new MiniGameEventGenerator();
        add(Layer.UI, miniGameEventGenerator);

        AchievementManager achievementManager = AchievementManager.getInstance();
        add(Layer.CONTROL, achievementManager);

        Sound.playMusic(R.raw.bg);
    }

    @Override
    public void onPause() {
        super.onPause();
        Player.getInstance().save();
        Sound.pauseMusic();
    }

    @Override
    public void onResume() {
        super.onResume();
        Sound.resumeMusic();
    }
}
