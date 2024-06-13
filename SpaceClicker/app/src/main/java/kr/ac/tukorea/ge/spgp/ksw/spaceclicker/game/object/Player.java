package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ITouchable;

import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.UpgradeInfo.UPGRADE_TYPE;

public class Player implements IGameObject, ITouchable {
    private static Player instance = null;

    public static long leftEventTime;

    private Scrap scrap;
    private SpaceShip spaceShip;

    static private ArrayList<UpgradeInfo> upgradeInfo;

    private int touchCount = 0;

    public enum gameMode{
        NORMAL,
        BATTLE,
        ASTEROID,
        END
    }

    private gameMode mode = gameMode.NORMAL;

    private Player() {
        scrap = new Scrap();
        spaceShip = new SpaceShip();

        upgradeInfo = UpgradeInfo.load();
        upgradeInfo.get(UPGRADE_TYPE.ANTENNA.ordinal());
    }

    static public Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    static public long getUpgradeLevel(UPGRADE_TYPE type){
        return upgradeInfo.get(type.ordinal()).getLevel();
    }

    public void setMode(gameMode mode){
        this.mode = mode;
    }

    public void getMinigameReward(long time) {
        scrap.addScrapTimeMultiply(time);
    }

    public void getBoxReward(long time) {
        scrap.addScrapTouchMultiply(time);
    }

    public void update(float elapsedSeconds) {
        if(mode == gameMode.NORMAL) {
            scrap.update(elapsedSeconds);
            spaceShip.update(elapsedSeconds);
        }
    }

    public void draw(Canvas canvas) {
        if(mode == gameMode.NORMAL) {
            scrap.draw(canvas);
            spaceShip.draw(canvas);
        }
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if(spaceShip.onTouch(event)) {
            switch (mode) {
                case NORMAL:
                    if(event.getAction() == MotionEvent.ACTION_DOWN)
                        scrap.addScrapFromTouch();
                    break;
                case BATTLE:
                    break;
                case ASTEROID:
                    break;
            }
            return true;
        }
        return false;
    }

    public void onUpgrade(UPGRADE_TYPE type){
        UpgradeInfo info = upgradeInfo.get(type.ordinal());
        if (!scrap.useScrap(info.getCost()))
            return;
        info.upgrade();
    }

    static public void setLeftEventTime(long time){
        leftEventTime = time;
    }
}
