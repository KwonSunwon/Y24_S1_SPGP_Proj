package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;

public class Scrap implements IGameObject {
    private long scrap;
    private long scrapPerTouch;
    private long scrapPerSecond;

    public void Init() {
        // Load scrap from save file
        scrap = 0;
        scrapPerTouch = 1;
        scrapPerSecond = 0;
    }

    @Override
    public void update(float elapsedSeconds){

    }

    @Override
    public void draw(Canvas canvas){

    }

    public void AddScrapFromClick() {
        scrap += scrapPerTouch;
    }

    public void AddScrapFromSecond() {
        scrap += scrapPerSecond;
    }

    public String GetScrap() {
        return Long.toUnsignedString(scrap);
    }
}
