package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.util.Log;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class Scrap implements IGameObject {
    // TODO
    // Split Scrap class into two classes: Scrap and ScrapDisplay

    private long scrap;
    private long scrapPerSecond;
    private long scrapPerTouch;

    private int AntennaUpgradeLevel;
    private int RobotUpgradeLevel;
    private int RecycleUpgradeLevel;

    private ImageNumber scrapDisplay;
    private ImageNumber scrapPerSecondDisplay;

    private Sprite scrapIcon;
    private Sprite plusIcon;

    private final int SCRAP_MODIFIER = 100;
    private float scrapAdder = 0;

    public Scrap() {
        scrapDisplay = new ImageNumber(R.mipmap.font1, 8.f, 0.5f, 0.3f);
        scrapPerSecondDisplay = new ImageNumber(R.mipmap.font1, 8.f, 1.0f, 0.3f);

        scrapIcon = new Sprite(R.mipmap.scrap_icon);
        scrapIcon.setPosition(8.5f, 0.7f, 0.6f, 0.5f);

        plusIcon = new Sprite(R.mipmap.plus_icon);
        plusIcon.setPosition(8.5f, 1.2f, 0.4f, 0.4f);

        // Load scrap from save file

        scrap = (long)9999999_99; // if value is 100 that means player has 1 scrap, so 123 equals 1.23 scrap
        scrapPerTouch = 100;
        scrapPerSecond = 0;

        AntennaUpgradeLevel = 0;
        RobotUpgradeLevel = 0;
        RecycleUpgradeLevel = 0;
    }

    @Override
    public void update(float elapsedSeconds){
        RobotUpgradeLevel = Player.getUpgradeLevel(Player.UpgradeType.AUTO_SCRAP_ROBOT);
        RecycleUpgradeLevel = Player.getUpgradeLevel(Player.UpgradeType.AUTO_SCRAP_RECYCLE);

        scrapPerSecond = (RobotUpgradeLevel * 100) + (RecycleUpgradeLevel * 500);
        scrapAdder += scrapPerSecond * elapsedSeconds;
        if(scrapAdder >= 1_00){
            scrap += (long)scrapAdder;
            scrapAdder -= (long)scrapAdder;
        }
        scrapDisplay.setNumber(scrap / SCRAP_MODIFIER);
        scrapPerSecondDisplay.setNumber(scrapPerSecond / SCRAP_MODIFIER);
    }

    @Override
    public void draw(Canvas canvas){
        scrapDisplay.draw(canvas);
        scrapPerSecondDisplay.draw(canvas);

        scrapIcon.draw(canvas);
        plusIcon.draw(canvas);
    }

    public boolean addScrapFromTouch() {
        AntennaUpgradeLevel = Player.getUpgradeLevel(Player.UpgradeType.CLICK_ANTENNA);

        scrap += scrapPerTouch;
        scrap += AntennaUpgradeLevel * 50;
        return true;
    }

    public boolean useScrap(long scrap) {
        long modeScrap = scrap * SCRAP_MODIFIER;
        if (this.scrap < modeScrap) {
            return false;
        }
        this.scrap -= modeScrap;
        return true;
    }
}
