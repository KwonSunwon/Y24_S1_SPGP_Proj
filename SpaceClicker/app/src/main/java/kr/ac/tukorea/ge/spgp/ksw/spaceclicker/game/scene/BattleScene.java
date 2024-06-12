package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.BattleGauge;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;

public class BattleScene extends Scene {
    private final BattleGauge battleGauge;
    private final int length;

    private int touchCount = 0;
    private float enemyTouchCount = 0;

    private int enemyRate;

    private float timer;
    private boolean isWin;

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

        length = 7;
        battleGauge = new BattleGauge(1, R.color.battle_gauge1, R.color.battle_gauge2, length);
        battleGauge.setPosition(1, 8);
        add(Layer.UI, battleGauge);

        this.enemyRate = enemyRate;

        Sprite player = new Sprite(R.mipmap.spaceship);
        player.setPosition(4.5f, 12, 3, 3);
        add(Layer.OBJECT, player);

        int enemyImage;
        if(enemyRate <= 6)
            enemyImage = R.mipmap.enemy_1;
        else
            enemyImage = R.mipmap.enemy_2;
        Sprite enemy = new Sprite(enemyImage);
        enemy.setPosition(4.5f, 4, 3, 3);
        add(Layer.OBJECT, enemy);

        timer = 10;
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

        enemyTouchCount += enemyRate * elapsedSeconds;
        battleGauge.setValues(touchCount, enemyTouchCount);

        if(touchCount - enemyTouchCount > (length / 2) + 2){
            enemyTouchCount = touchCount - (length / 2) - 2;
        }
        else if(enemyTouchCount - touchCount > (length / 2) + 2){
            // Player lose
            isWin = false;
            Scene.pop();
        }

        timer -= elapsedSeconds;
        if(timer <= 0){
            isWin = true;
            Scene.pop();
        }
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchCount += 1;
        }
        return true;
    }

    @Override
    public void onEnd(){
        Player.setMode(Player.gameMode.NORMAL);
        if(isWin)
            Player.getInstance().getMinigameReward(600);
    }
}
