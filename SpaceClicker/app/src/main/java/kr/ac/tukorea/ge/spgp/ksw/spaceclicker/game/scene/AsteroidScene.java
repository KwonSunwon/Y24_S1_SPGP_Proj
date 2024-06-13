package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.AsteroidGenerator;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.MovablePlayer;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.util.CollisionManager;

public class AsteroidScene extends Scene {
    public enum Layer {
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

        VertScrollBackground bg = new VertScrollBackground(R.mipmap.bg_space, 1.f);
        add(Layer.BACKGROUND, bg);

        MovablePlayer player = new MovablePlayer();
        add(Layer.OBJECT, player);

        AsteroidGenerator asteroidGenerator = new AsteroidGenerator();
        add(Layer.CONTROL, asteroidGenerator);

        CollisionManager collisionManager = new CollisionManager(this);
        add(Layer.CONTROL, collisionManager);
    }
}
