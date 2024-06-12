package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.util.Log;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.util.Gauge;

public class BattleGauge extends Gauge implements IGameObject {
    float x, y;
    float value;
    float length;
    private float sub;

    public BattleGauge(float width, int color1, int color2, float length) {
        super(width, color1, color2);
        this.length = length;

        value = length / 2;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setValues(float value1, float value2) {
        sub = value1 - value2;
    }

    @Override
    public void update(float elapsedSeconds) {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, x + length, y, bgPaint);
        if (value > 0) {
            float finalValue = value + sub;
            if(value + sub > length)
                finalValue = length;
            else if(value + sub < 0)
                finalValue = 0;

            canvas.drawLine(x, y, x + finalValue, y, fgPaint);
        }
    }
}
