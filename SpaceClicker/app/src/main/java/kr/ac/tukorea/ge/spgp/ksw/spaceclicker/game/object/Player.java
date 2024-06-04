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

    private Scrap scrap;
    private SpaceShip spaceShip;

    static private ArrayList<UpgradeInfo> upgradeInfo;

    private Player() {
        scrap = new Scrap();
        spaceShip = new SpaceShip();

        upgradeInfo = UpgradeInfo.load();
        upgradeInfo.get(UPGRADE_TYPE.ANTENNA.ordinal());
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    static public long getUpgradeLevel(UPGRADE_TYPE type){
        return upgradeInfo.get(type.ordinal()).getLevel();
    }

    public void update(float elapsedSeconds) {
        scrap.update(elapsedSeconds);
        spaceShip.update(elapsedSeconds);
    }

    public void draw(Canvas canvas) {
        scrap.draw(canvas);
        spaceShip.draw(canvas);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if(spaceShip.onTouch(event)) {
            scrap.addScrapFromTouch();
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
}
