package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ITouchable;

public class Player implements IGameObject, ITouchable {
    public enum UpgradeType {
        CLICK_ANTENNA,
        AUTO_SCRAP_ROBOT,
        AUTO_SCRAP_RECYCLE,
        SPEED_HIRE,
        SPEED_ENGINE,
        SPEED_DESIGN,
    }
    private static Player instance = null;

    private Scrap scrap;
    private SpaceShip spaceShip;
    private AchievementManager achievement;

    public HashMap<UpgradeType, Integer> UpgradeLevel = new HashMap<>();

    private Player() {
        scrap = new Scrap();
        spaceShip = new SpaceShip();
        achievement = new AchievementManager();

        for(int i = 0; i < UpgradeType.values().length; i++){
            UpgradeType type = UpgradeType.values()[i];
            UpgradeLevel.put(type, 0);
        }
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    static public int getUpgradeLevel(UpgradeType type){
        if (!getInstance().UpgradeLevel.containsKey(type))
            return 0;
        return getInstance().UpgradeLevel.get(type);
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

    public void onUpgrade(UpgradeType type, int cost){
        if (!scrap.useScrap(cost))
            return;
        achievement.checkAchievement(type.ordinal());
        UpgradeLevel.put(type, UpgradeLevel.get(type) + 1);
    }
}
