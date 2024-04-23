package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebHistoryItem;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class Scrap extends ImageNumber{
    private long scrap;
    private long scrapPerTouch;
    private long scrapPerSecond;

    private final int SCRAP_MODIFIER = 100;

    public Scrap() {
        super(R.mipmap.font1, 8.5f, 0.5f, 0.3f);

        // Load scrap from save file

        scrap = (long)999999999; // if value is 100 that means player has 1 scrap, so 123 equals 1.23 scrap
        scrapPerTouch = 1;
        scrapPerSecond = 100000;
    }

    @Override
    public void update(float elapsedSeconds){
        float scrapPerSecond = this.scrapPerSecond * elapsedSeconds;
        Log.d("Scrap", "Scrap per second: " + scrapPerSecond);
        scrap += (long) (scrapPerSecond * SCRAP_MODIFIER);
    }

    @Override
    public void draw(Canvas canvas){
        super.setNumber(scrap / SCRAP_MODIFIER);
        super.draw(canvas);
    }

    public String GetScrap() {
        return Long.toUnsignedString(scrap / SCRAP_MODIFIER);
    }

    public boolean onTouch() {
        scrap += scrapPerTouch * SCRAP_MODIFIER;
        return true;
    }
}
