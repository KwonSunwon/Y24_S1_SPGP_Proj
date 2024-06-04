package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import static kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app.MainActivity.getContext;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp.ksw.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;

public class AchievementManager implements IGameObject {
    private static AchievementManager instance = null;
    private static ArrayList<Achievement> achievements;

    static final int ACHIEVEMENT_COUNT = 20;

    public static AchievementManager getInstance() {
        if (instance == null) {
            instance = new AchievementManager();
        }
        return instance;
    }

    private AchievementManager() {
        init();
    }

    private void init() {
        String[] names = getContext().getResources().getStringArray(R.array.achievement_names);
        String[] descriptions = getContext().getResources().getStringArray(R.array.achievement_descriptions);

        achievements = new ArrayList<>(ACHIEVEMENT_COUNT);

        for(int i = 0; i < getCount(); i++){
            achievements.add(new Achievement(names[i], descriptions[i], false));

            Log.d("AchievementManager", "init: " + names[i] + " : " + descriptions[i]);
        }
        getAchievedList();
    }

    private void getAchievedList() {
        SharedPreferences achievedList = getContext().getSharedPreferences("AchievedList", 0);
        SharedPreferences.Editor editor = achievedList.edit();

        String achievedListString = achievedList.getString("AchievedList", "");
        if (achievedListString.isEmpty()) {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < getCount(); i++) {
                jsonArray.put(0);
            }
            editor.putString("AchievedList", jsonArray.toString());
            editor.apply();
        }

        // String to JSONArray and set isAchieved
        try {
            JSONArray jsonArray = new JSONArray(achievedListString);
            for (int i = 0; i < jsonArray.length(); i++) {
                achievements.get(i).setAchieved(jsonArray.getInt(i) == 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkAchievements() {
        for (Achievement achievement : achievements) {
            achievement.checkAchievement();
        }
    }

    public void checkAchievement(int id) {
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

    public int getCount() {
        return ACHIEVEMENT_COUNT;
    }

    public Achievement getItem(int position) {
        return achievements.get(position);
    }

    public void reset(){
        for (Achievement achievement : achievements) {
            achievement.setAchieved(false);
        }
        SharedPreferences achievedList = getContext().getSharedPreferences("AchievedList", 0);
        SharedPreferences.Editor editor = achievedList.edit();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < getCount(); i++) {
            jsonArray.put(0);
        }
        editor.putString("AchievedList", jsonArray.toString());
        editor.apply();
    }
}
