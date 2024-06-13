package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.databinding.UpgradeLayoutBinding;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Achievement;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.AchievementManager;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;

import static kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.UpgradeInfo.UPGRADE_TYPE;

public class UpgradeActivity extends AppCompatActivity {
    private Player player = Player.getInstance();
    private UpgradeLayoutBinding biding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biding = UpgradeLayoutBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());
    }

    public void onUpgradeClick(View v){
        int id = v.getId();
        if(id == R.id.upgradeAntenna){
            player.onUpgrade(UPGRADE_TYPE.ANTENNA);
            AchievementManager.getInstance().getItem(UPGRADE_TYPE.ANTENNA.ordinal()).checkAchievementUpgrade();
        } else if(id == R.id.upgradeRobot) {
            player.onUpgrade(UPGRADE_TYPE.ROBOT);
            AchievementManager.getInstance().getItem(UPGRADE_TYPE.ROBOT.ordinal()).checkAchievementUpgrade();
        } else if(id == R.id.upgradeEngine) {
            player.onUpgrade(UPGRADE_TYPE.ENGINE);
            AchievementManager.getInstance().getItem(UPGRADE_TYPE.ENGINE.ordinal()).checkAchievementUpgrade();
        } else if(id == R.id.upgradeCrew) {
            player.onUpgrade(UPGRADE_TYPE.CREW);
            AchievementManager.getInstance().getItem(UPGRADE_TYPE.CREW.ordinal()).checkAchievementUpgrade();
        } else if(id == R.id.upgradeRecycle) {
            player.onUpgrade(UPGRADE_TYPE.RECYCLE);
            AchievementManager.getInstance().getItem(UPGRADE_TYPE.RECYCLE.ordinal()).checkAchievementUpgrade();
        } else if(id == R.id.upgradeDesign) {
            player.onUpgrade(UPGRADE_TYPE.DESIGN);
            AchievementManager.getInstance().getItem(UPGRADE_TYPE.DESIGN.ordinal()).checkAchievementUpgrade();
        }
        AchievementManager.getInstance().saveAchievedList();
    }

    public void onBackClick(View v){
        finish();
    }
}
