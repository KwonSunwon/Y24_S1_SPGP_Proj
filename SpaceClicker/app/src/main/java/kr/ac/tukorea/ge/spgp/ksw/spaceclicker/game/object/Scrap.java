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

public class Scrap implements IGameObject {
    private Point position;
    private Paint paint;

    private long scrap;
    private long scrapPerTouch;
    private long scrapPerSecond;

    public Scrap() {
        // Load scrap from save file
        scrap = 0; // if value is 100 that means player has 1 scrap, so 123 equals 1.23 scrap
        scrapPerTouch = 1;
        scrapPerSecond = 1;

        position = new Point(2, 0);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(1);
    }

    @Override
    public void update(float elapsedSeconds){
        float scrapPerSecond = this.scrapPerSecond * elapsedSeconds;
        Log.d("Scrap", "Scrap per second: " + scrapPerSecond);
        scrap += scrapPerSecond * 100;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawText(GetScrap(), position.x, position.y, paint);
    }

    public String GetScrap() {
        return Long.toUnsignedString(scrap / 100);
    }

    public boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            scrap += scrapPerTouch * 100;
            return true;
        }
        return false;
    }
}
