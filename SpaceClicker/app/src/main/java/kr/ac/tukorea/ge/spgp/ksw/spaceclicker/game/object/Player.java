package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.view.MotionEvent;

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

    private Player() {
        scrap = new Scrap();
        spaceShip = new SpaceShip();
        achievement = new AchievementManager();
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void update(float elapsedSeconds) {
        scrap.update(elapsedSeconds);
        spaceShip.update(elapsedSeconds);
    }

    public void draw(Canvas canvas) {
        scrap.draw(canvas);
        spaceShip.draw(canvas);
    }

    public Scrap getScrap() {
        return scrap;
    }

    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if(spaceShip.onTouch(event)) {
            scrap.addScrapFromTouch();
            return true;
        }
        return false;
    }

    public void onUpgrade(UpgradeType type){
        switch (type) {
            case CLICK_ANTENNA:
                scrap.addAntennaUpgradeLevel();
                achievement.checkAchievement(0);
                break;
            case AUTO_SCRAP_ROBOT:
                scrap.addRobotUpgradeLevel();
                achievement.checkAchievement(1);
                break;
            case AUTO_SCRAP_RECYCLE:
                scrap.addRecycleUpgradeLevel();
                achievement.checkAchievement(2);
                break;
            case SPEED_HIRE:
                spaceShip.addCrewUpgradeLevel();
                achievement.checkAchievement(3);
                break;
            case SPEED_ENGINE:
                spaceShip.addEngineUpgradeLevel();
                achievement.checkAchievement(4);
                break;
            case SPEED_DESIGN:
                spaceShip.addDesignUpgradeLevel();
                achievement.checkAchievement(5);
                break;
        }
    }
}
