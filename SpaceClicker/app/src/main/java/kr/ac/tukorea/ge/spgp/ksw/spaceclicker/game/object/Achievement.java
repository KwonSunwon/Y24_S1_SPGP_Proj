package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.content.Context;

public class Achievement {
    private int name;
    private int description;
    private int condition;
    private boolean isAchieved;
    Context context;

    public Achievement(int name, int description, int condition) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.isAchieved = false;
        context = kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app.MainActivity.getContext();
    }

    public String getName() {
        return context.getString(name);
    }

    public String getDescription() {
        return context.getString(description);
    }

    public int getCondition() {
        return condition;
    }

    public boolean isAchieved() {
        return isAchieved;
    }

    public void setAchieved(boolean achieved) {
        isAchieved = achieved;
    }

    public boolean checkAchievement(int condition) {
        if( condition >= this.condition ) {
            isAchieved = true;
            return true;
        }
        return false;
    }
}
