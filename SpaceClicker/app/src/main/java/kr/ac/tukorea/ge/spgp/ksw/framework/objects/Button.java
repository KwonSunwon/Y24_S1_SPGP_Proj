package kr.ac.tukorea.ge.spgp.ksw.framework.objects;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.ITouchable;

public class Button extends Sprite implements ITouchable {

    public Button(int mipmapId) {
        super(mipmapId);
    }

    public boolean onTouch(MotionEvent event) {
        return false;
    }
}