package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ITouchable;

public class Player implements IGameObject, ITouchable {
    private static Player instance = null;

    private Scrap scrap;
    private SpaceShip spaceShip;

    private Player() {
        scrap = new Scrap();
        spaceShip = new SpaceShip();
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
}
