package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app;

import android.os.Bundle;

import kr.ac.tukorea.ge.spgp.ksw.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.MainScene;

public class MainActivity extends GameActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MainScene().push();
    }
}