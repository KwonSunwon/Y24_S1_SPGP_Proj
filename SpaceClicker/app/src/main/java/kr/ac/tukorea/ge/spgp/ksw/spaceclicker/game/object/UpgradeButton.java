package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Button;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.UpgradeScene;

public class UpgradeButton extends Button {
    Player.UpgradeType upgradeType;
    int level;
    int cost;

    public UpgradeButton(int mipmap, Player.UpgradeType upgradeType, int cost) {
        super(mipmap);
        this.upgradeType = upgradeType;

        level = Player.getUpgradeLevel(upgradeType);
        this.cost = cost;
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if(event.getAction() != MotionEvent.ACTION_DOWN)
            return false;
        float[] pts = Metrics.fromScreen(event.getX(), event.getY());
        if(dstRect.contains(pts[0], pts[1])){
            Player.getInstance().onUpgrade(upgradeType, cost);
            cost += cost / 2;
            return true;
        }
        return false;
    }
}
