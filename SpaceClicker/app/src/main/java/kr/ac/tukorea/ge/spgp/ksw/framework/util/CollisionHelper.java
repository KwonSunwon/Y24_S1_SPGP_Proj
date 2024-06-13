package kr.ac.tukorea.ge.spgp.ksw.framework.util;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ICircleCollidable;

public class CollisionHelper {
    public static boolean collides(IBoxCollidable obj1, IBoxCollidable obj2) {
        RectF r1 = obj1.getCollisionRect();
        RectF r2 = obj2.getCollisionRect();

        if (r1.left > r2.right) return false;
        if (r1.top > r2.bottom) return false;
        if (r1.right < r2.left) return false;
        if (r1.bottom < r2.top) return false;

        return true;
    }

    public static boolean collides(ICircleCollidable obj1, ICircleCollidable obj2){
        Circle c1 = obj1.getCollisionCircle();
        Circle c2 = obj2.getCollisionCircle();

        float dx = c1.x - c2.x;
        float dy = c1.y - c2.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        return distance < c1.radius + c2.radius;
    }

    public static boolean collides(IBoxCollidable rectObj, ICircleCollidable circleObj){
        RectF r = rectObj.getCollisionRect();
        Circle c = circleObj.getCollisionCircle();

        float closestX = Math.max(r.left, Math.min(c.x, r.right));
        float closestY = Math.max(r.top, Math.min(c.y, r.bottom));

        float dx = c.x - closestX;
        float dy = c.y - closestY;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        return distance < c.radius;
    }
}
