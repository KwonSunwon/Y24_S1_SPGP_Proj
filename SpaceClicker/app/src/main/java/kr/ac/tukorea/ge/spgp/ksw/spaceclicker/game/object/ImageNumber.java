package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.BuildConfig;

public class ImageNumber implements IGameObject {
    private final Bitmap bitmap;
    private final float right, top, dstCharWidth, dstCharHeight;
    private final float dstOffset;
    private final Rect srcRect = new Rect();
    private final RectF dstRect = new RectF();
    private final int srcCharWidth, srcCharHeight;
    private final int srcOffset;
    private long number, displayNumber;

    public ImageNumber(int mipmapId, float right, float top, float width) {
        this.bitmap = BitmapPool.get(mipmapId);
        this.right = right;
        this.top = top;
        this.dstCharWidth = width;
        this.srcCharWidth = 20;
        this.srcCharHeight = 20;
        this.dstCharHeight = dstCharWidth * srcCharHeight / srcCharWidth;
        this.srcOffset = 20;
        this.dstOffset = 0.2f;
    }

    public void setNumber(long number) {
        this.number = this.displayNumber = number;
    }

    @Override
    public void update(float elapsedSeconds) {
        long diff = number - displayNumber;
        if (diff == 0) return;
        if (-10 < diff && diff < 0) {
            displayNumber--;
        } else if (0 < diff && diff < 10) {
            displayNumber++;
        } else {
            displayNumber += diff / 10;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(BuildConfig.DEBUG){
            Paint paint = new Paint();
            paint.setColor(0x80FF0000);
            canvas.drawRect(right, top, right + dstCharWidth, top + dstCharHeight, paint);
        }

        long value = this.displayNumber;
        float x = right;
        while (value > 0) {
            int digit = (int)(value % 10);
            srcRect.set(digit * srcCharWidth + srcOffset, srcOffset, (digit + 1) * srcCharWidth + srcOffset, srcCharHeight + srcOffset);
            x -= dstCharWidth;
            dstRect.set(x, top, x + dstCharWidth + dstOffset, top + dstCharHeight + dstOffset);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            value /= 10;
        }
    }
}
