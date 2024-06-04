package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.content.Context;

public class Achievement {
    private String name;
    private String description;
    private boolean isAchieved;
    Context context;

    public Achievement(String name, String description, boolean isAchieved) {
        this.name = name;
        this.description = description;
        this.isAchieved = isAchieved;
        context = kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app.MainActivity.getContext();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAchieved() {
        return isAchieved;
    }

    public void setAchieved(boolean achieved) {
        isAchieved = achieved;
    }

    public boolean checkAchievement() {
        return false;
    }
}
