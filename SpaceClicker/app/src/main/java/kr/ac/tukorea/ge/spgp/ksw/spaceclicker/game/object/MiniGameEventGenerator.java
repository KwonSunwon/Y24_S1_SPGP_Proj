package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ITouchable;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.BattleScene;

public class MiniGameEventGenerator implements IGameObject, ITouchable {
    private Sprite eventButton = new Sprite(R.mipmap.plus_icon);
    private float x;

    private Random random = new Random();

    private int interval;
    private float time;
    private MiniGameType nextMiniGameType;

    enum MiniGameType{
        BATTLE,
        ASTEROID,
        END
    }

    public MiniGameEventGenerator() {
        x = -1;
        eventButton.setPosition(x, 0, 1, 1);

        interval = random.nextInt(120) + 120;
        time = 0;
        int type = random.nextInt(2);
        nextMiniGameType = MiniGameType.values()[type];

        if(BuildConfig.DEBUG)
            interval = 1;
    }

    private boolean buttonMovement() {
        if (x != 1.f) {
            x += 0.05f;
            if (x >= 1.f) {
                x = 1.f;
            }
            eventButton.setPosition(x, 0, 1, 1);
            return false;
        }
        return true;
    }

    @Override
    public void update(float elapsedSeconds) {
        time += elapsedSeconds;
        if (time > interval) {
            // Mini Game Icon Move
            if(buttonMovement()) {
//                interval = random.nextInt(120) + 120;
                interval = 10;
                time = 0;
                int type = random.nextInt(2);
                nextMiniGameType = MiniGameType.values()[type];
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        eventButton.draw(canvas);
    }

    @Override
    public boolean onTouch(MotionEvent event){
        if(event.getAction() != MotionEvent.ACTION_DOWN) return false;
        float[] pts = Metrics.fromScreen(event.getX(), event.getY());
        if (eventButton.getDstRect().contains(pts[0], pts[1])) {
            // Mini Game Scene Push
            if(BuildConfig.DEBUG)
                Log.d("MiniGameEventGenerator", "MiniGameType: " + nextMiniGameType.toString());
            x = -1;
            eventButton.setPosition(x, 0, 1, 1);
            if(BuildConfig.DEBUG)
                nextMiniGameType = MiniGameType.BATTLE;
            switch (nextMiniGameType) {
                case BATTLE:
                    Scene.push(new BattleScene(random.nextInt(10) + 3));
                    return true;
                case ASTEROID:
                    return true;
            }
            return true;
        }
        return false;
    }
}
