package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import static kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app.MainActivity.getContext;

import android.content.SharedPreferences;
import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.UpgradeInfo.UPGRADE_TYPE;

public class Scrap implements IGameObject {
    // TODO
    // Split Scrap class into two classes: Scrap and ScrapDisplay

    private long scrap;
    private long scrapPerSecond;
    private long scrapPerTouch;

    private long AntennaUpgradeLevel;
    private long RobotUpgradeLevel;
    private long RecycleUpgradeLevel;

    private ImageNumber scrapDisplay;
    private ImageNumber scrapPerSecondDisplay;

    private Sprite scrapIcon;
    private Sprite plusIcon;

    private final int SCRAP_MODIFIER = 100;
    private float scrapAdder = 0;

    public Scrap() {
        scrapDisplay = new ImageNumber(8.f, 0.5f, 0.3f);
        scrapPerSecondDisplay = new ImageNumber(8.f, 1.0f, 0.3f);

        scrapIcon = new Sprite(R.mipmap.scrap_icon);
        scrapIcon.setPosition(8.5f, 0.7f, 0.6f, 0.5f);

        plusIcon = new Sprite(R.mipmap.plus_icon);
        plusIcon.setPosition(8.5f, 1.2f, 0.4f, 0.4f);

        // Load scrap from save file

        scrap = 9999999_99; // if value is 100 that means player has 1 scrap, so 123 equals 1.23 scrap
        scrapPerTouch = 1_00;
        scrapPerSecond = 0;

        SharedPreferences scrapSave = getContext().getSharedPreferences("scrap", 0);
        scrap = scrapSave.getLong("scrap", 0);

        SharedPreferences scrapPerTouchSave = getContext().getSharedPreferences("scrapPerTouch", 0);
        scrapPerTouch = scrapPerTouchSave.getLong("scrapPerTouch", 1_00);

        SharedPreferences scrapPerSecondSave = getContext().getSharedPreferences("scrapPerSecond", 0);
        scrapPerSecond = scrapPerSecondSave.getLong("scrapPerSecond", 0);
    }

    public void save() {
        SharedPreferences scrapSave = getContext().getSharedPreferences("scrap", 0);
        SharedPreferences.Editor editor = scrapSave.edit();
        editor.putLong("scrap", scrap);
        editor.apply();

        SharedPreferences scrapPerTouchSave = getContext().getSharedPreferences("scrapPerTouch", 0);
        editor = scrapPerTouchSave.edit();
        editor.putLong("scrapPerTouch", scrapPerTouch);
        editor.apply();

        SharedPreferences scrapPerSecondSave = getContext().getSharedPreferences("scrapPerSecond", 0);
        editor = scrapPerSecondSave.edit();
        editor.putLong("scrapPerSecond", scrapPerSecond);
        editor.apply();
    }

    @Override
    public void update(float elapsedSeconds){
        RobotUpgradeLevel = Player.getUpgradeLevel(UPGRADE_TYPE.ROBOT);
        RecycleUpgradeLevel = Player.getUpgradeLevel(UPGRADE_TYPE.RECYCLE);

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
        AntennaUpgradeLevel = Player.getUpgradeLevel(UPGRADE_TYPE.ANTENNA);

        scrap += scrapPerTouch;
        scrap += AntennaUpgradeLevel * 50;
        return true;
    }

    public void addScrapTimeMultiply(long time) {
        if(scrapPerSecond == 0) scrap += 100;
        scrap += scrapPerSecond * time;
    }
    public void addScrapTouchMultiply(long touch) {
        scrap += scrapPerTouch * touch;
    }

    public boolean useScrap(long scrap) {
        long modeScrap = scrap * SCRAP_MODIFIER;
        if (this.scrap < modeScrap) {
            return false;
        }
        this.scrap -= modeScrap;
        return true;
    }

    public boolean useScrapWithOutModifier(long scrap) {
        if (this.scrap < scrap) {
            return false;
        }
        this.scrap -= scrap;
        return true;
    }
}
