package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Debug;
import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.BattleGauge;

public class BattleScene extends Scene {
    private final BattleGauge battleGauge;

    private int touchCount = 0;
    private float enemyTouchCount = 0;

    private int enemyRate;

    public enum Layer {
        CONTROL,
        BACKGROUND,
        BACK_OBJECT,
        OBJECT,
        UI,
        END
    }

    public BattleScene(int enemyRate) {
        initLayers(Layer.END);

        battleGauge = new BattleGauge(1, R.color.battle_gauge1, R.color.battle_gauge2, 7);
        battleGauge.setPosition(1, 8);
        add(Layer.UI, battleGauge);

        this.enemyRate = enemyRate;

        Log.d("BattleScene", "BattleScene constructor" + " enemyRate: " + enemyRate);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

        enemyTouchCount += 11 * elapsedSeconds;
        battleGauge.setValues(touchCount, enemyTouchCount);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchCount += 1;
        }
        return true;
    }
}
