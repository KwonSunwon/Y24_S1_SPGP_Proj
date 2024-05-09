package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Achievement;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.AchievementManager;

public class AchievementScene extends Scene {
    enum Layer {
        BACKGROUND,
        OBJECT,
        UI,
        END
    }

    AchievementManager achievementManager = new AchievementManager();

    public AchievementScene() {
        initLayers(Layer.END);
    }
}
