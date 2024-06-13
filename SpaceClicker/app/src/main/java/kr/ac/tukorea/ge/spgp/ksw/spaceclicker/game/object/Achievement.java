package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import android.content.Context;
import android.widget.Toast;

public class Achievement {
    private final String name;
    private final String description;
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

    public void checkAchievementUpgrade() {
        if (isAchieved) {
            return;
        }
        Player.getInstance();
        if (Player.getUpgradeLevel(UpgradeInfo.UPGRADE_TYPE.ANTENNA) == 1) {
            isAchieved = true;

            Toast toast = Toast.makeText(context, "업적 달성: " + name, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
