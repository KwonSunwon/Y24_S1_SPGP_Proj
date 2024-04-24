package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebHistoryItem;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class Scrap implements IGameObject{
    private long scrap;
    private long scrapPerSecond;
    private long scrapPerTouch;

    private ImageNumber scrapDisplay;
    private ImageNumber scrapPerSecondDisplay;

    private final int SCRAP_MODIFIER = 100;

    public Scrap() {
        scrapDisplay = new ImageNumber(R.mipmap.font1, 8.5f, 0.5f, 0.3f);
        scrapPerSecondDisplay = new ImageNumber(R.mipmap.font1, 8.5f, 1.0f, 0.3f);

        // Load scrap from save file

        scrap = (long)999999999; // if value is 100 that means player has 1 scrap, so 123 equals 1.23 scrap
        scrapPerTouch = 1;
        scrapPerSecond = 100000;
    }

    @Override
    public void update(float elapsedSeconds){
        float scrapAdder = scrapPerSecond * elapsedSeconds;
        scrap += (long) (scrapAdder * SCRAP_MODIFIER);
    }

    @Override
    public void draw(Canvas canvas){
        scrapDisplay.setNumber(scrap / SCRAP_MODIFIER);
        scrapDisplay.draw(canvas);
        scrapPerSecondDisplay.setNumber(scrapPerSecond / SCRAP_MODIFIER);
        scrapPerSecondDisplay.draw(canvas);
    }

    public boolean onTouch() {
        scrap += scrapPerTouch * SCRAP_MODIFIER;
        return true;
    }
}
