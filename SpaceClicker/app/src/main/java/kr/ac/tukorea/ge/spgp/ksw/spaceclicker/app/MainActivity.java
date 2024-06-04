package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import kr.ac.tukorea.ge.spgp.ksw.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.AchievementManager;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.MainScene;

public class MainActivity extends GameActivity {
    public static Context getContext() {
        return activity.getApplicationContext();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MainScene().push();

        AchievementManager.init();
    }

    public void onUpgradeButtonClick(View view) {
        Log.d("MainActivity", "onUpgradeClick");
        onPause();
        startActivity(new Intent(this, UpgradeActivity.class));
    }

    public void onAchievementButtonClick(View view) {
        Log.d("MainActivity", "onAchievementClick");
        onPause();
        startActivity(new Intent(this, AchievementActivity.class));
    }
}