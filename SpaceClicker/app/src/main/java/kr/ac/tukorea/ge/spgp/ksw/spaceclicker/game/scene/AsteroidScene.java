package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.MovablePlayer;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;

public class AsteroidScene extends Scene {
    private enum Layer {
        CONTROL,
        BACKGROUND,
        BACK_OBJECT,
        OBJECT,
        UI,
        END
    }

    public AsteroidScene() {
        super();

        initLayers(Layer.END);

        MovablePlayer player = new MovablePlayer();
        add(Layer.OBJECT, player);
    }
}
