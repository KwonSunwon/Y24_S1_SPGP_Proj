package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene;

import kr.ac.tukorea.ge.spgp.ksw.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp.ksw.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.AsteroidGenerator;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.ImageNumber;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.MovablePlayer;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.util.CollisionManager;

public class AsteroidScene extends Scene {
    private final ImageNumber timer;
    private final MovablePlayer player;
    private long leftTime = 30000;
    private boolean winFlag = false;

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

        VertScrollBackground bg = new VertScrollBackground(R.mipmap.bg_space, 2.f);
        add(Layer.BACKGROUND, bg);

        player = new MovablePlayer();
        add(Layer.OBJECT, player);

        AsteroidGenerator asteroidGenerator = new AsteroidGenerator();
        add(Layer.CONTROL, asteroidGenerator);

        CollisionManager collisionManager = new CollisionManager(this);
        add(Layer.CONTROL, collisionManager);

        timer = new ImageNumber(5.5f, 1, 0.5f);
        timer.setNumber(leftTime);
        add(Layer.UI, timer);
    }

    @Override
    public void update(float elapsedSeconds) {
        if(player.introTimer == 2){
            if(leftTime > 0) {
                leftTime -= elapsedSeconds * 1000;
                if(leftTime < 0) {
                    leftTime = 0;
                    winFlag = true;
                    Scene.pop();
                }
            }
        }
        if(player.hp == 0){
            winFlag = false;
            Scene.pop();
        }
        timer.setNumber(leftTime);
        super.update(elapsedSeconds);

    }

    @Override
    public void onEnd(){
        Player.getInstance().setMode(Player.gameMode.NORMAL);
        if(winFlag)
            Player.getInstance().getMinigameReward(6000);
    }
}
