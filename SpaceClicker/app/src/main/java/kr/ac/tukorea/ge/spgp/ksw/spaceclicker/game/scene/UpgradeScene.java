package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.util.Log;

import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;

public class UpgradeScene extends Scene {

    public UpgradeScene() {
        if(BuildConfig.DEBUG)
            Log.d("UpgradeScene", "UpgradeScene constructor");
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
