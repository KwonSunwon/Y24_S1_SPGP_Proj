package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import static kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app.MainActivity.getContext;

import android.graphics.Canvas;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class AchievementManager implements IGameObject {
    private static ArrayList<Achievement> achievements;

    static public ArrayList<Achievement> getAchievements() {
        if(achievements == null)
            init();
        return achievements;
    }

    static private void init() {
        achievements = new ArrayList<>();

        String[] names = getContext().getResources().getStringArray(R.array.achievement_names);
        String[] descriptions = getContext().getResources().getStringArray(R.array.achievement_descriptions);
        int[] isAchieved = getContext().getResources().getIntArray(R.array.achievement_is_achieved);

        for (int i = 0; i < names.length; i++) {
            achievements.add(new Achievement(names[i], descriptions[i], isAchieved[i] == 1));
        }
    }

    static public void checkAchievements() {
        for (Achievement achievement : achievements) {
            achievement.checkAchievement();
        }
    }

    static public void checkAchievement(int id) {
        if (!achievements.get(id).isAchieved()) {
            achievements.get(id).setAchieved(true);

            Toast.makeText(getContext(),
                    achievements.get(id).getName() + " : " + achievements.get(id).getDescription(),
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

    public static int getCount() {
        return achievements.size();
    }

    public static Achievement getItem(int position) {
        return achievements.get(position);
    }
}
