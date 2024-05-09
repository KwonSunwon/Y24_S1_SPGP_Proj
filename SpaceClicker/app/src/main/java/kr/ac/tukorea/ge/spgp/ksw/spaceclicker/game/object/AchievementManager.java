package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.graphics.Canvas;
import android.widget.Toast;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class AchievementManager implements IGameObject {
    private final ArrayList<Integer> achievementNames = new ArrayList<Integer>(){{
        add(R.string.achievement1_name);
        add(R.string.achievement2_name);
        add(R.string.achievement3_name);
        add(R.string.achievement4_name);
        add(R.string.achievement5_name);
        add(R.string.achievement6_name);
        add(R.string.achievement7_name);
        add(R.string.achievement8_name);
        add(R.string.achievement9_name);
        add(R.string.achievement10_name);
        add(R.string.achievement11_name);
        add(R.string.achievement12_name);
        add(R.string.achievement13_name);
        add(R.string.achievement14_name);
        add(R.string.achievement15_name);
        add(R.string.achievement16_name);
        add(R.string.achievement17_name);
        add(R.string.achievement18_name);
        add(R.string.achievement19_name);
        add(R.string.achievement20_name);
    }};
    private final ArrayList<Integer> achievementDescriptions = new ArrayList<Integer>(){{
        add(R.string.achievement1_desc);
        add(R.string.achievement2_desc);
        add(R.string.achievement3_desc);
        add(R.string.achievement4_desc);
        add(R.string.achievement5_desc);
        add(R.string.achievement6_desc);
        add(R.string.achievement7_desc);
        add(R.string.achievement8_desc);
        add(R.string.achievement9_desc);
        add(R.string.achievement10_desc);
        add(R.string.achievement11_desc);
        add(R.string.achievement12_desc);
        add(R.string.achievement13_desc);
        add(R.string.achievement14_desc);
        add(R.string.achievement15_desc);
        add(R.string.achievement16_desc);
        add(R.string.achievement17_desc);
        add(R.string.achievement18_desc);
        add(R.string.achievement19_desc);
        add(R.string.achievement20_desc);
    }};
    public static ArrayList<Achievement> achievements = new ArrayList<>();

    public AchievementManager() {
        // add achievements
        for (int i = 0; i < 20; i++) {
            Achievement newAchievement = new Achievement(achievementNames.get(i), achievementDescriptions.get(i), 1);
            achievements.add(newAchievement);
        }
    }

    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    public void checkAchievements() {
        for (Achievement achievement : achievements) {
            achievement.checkAchievement(1);
        }
    }

    public void checkAchievement(int idx) {
        if (!achievements.get(idx).isAchieved()) {
            achievements.get(idx).setAchieved(true);

            Toast.makeText(kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app.MainActivity.getContext(),
                    achievements.get(idx).getName() + " : " + achievements.get(idx).getDescription(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void update(float elapsedSeconds) {
        checkAchievements();
    }

    @Override
    public void draw(Canvas canvas) {
        // Do nothing
    }
}
