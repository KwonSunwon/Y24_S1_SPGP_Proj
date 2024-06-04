package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.databinding.AchievementItemBinding;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.databinding.AchievementLayoutBinding;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Achievement;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.AchievementManager;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;

public class AchievementActivity extends AppCompatActivity {
    private AchievementLayoutBinding biding;

    private Player player = Player.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biding = AchievementLayoutBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());

        biding.achievementList.setAdapter(achievementListAdapter);
    }

    private final ListAdapter achievementListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return AchievementManager.getCount();
        }

        @Override
        public Object getItem(int position) {
            return AchievementManager.getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            AchievementItemBinding itemBinding;
            if(view == null){
                itemBinding = AchievementItemBinding.inflate(getLayoutInflater());
                view = itemBinding.getRoot();
                view.setTag(itemBinding);
            } else {
                itemBinding = (AchievementItemBinding) view.getTag();
            }

            Achievement achievement = AchievementManager.getItem(position);
            TextView name = itemBinding.achievementName;
            TextView description = itemBinding.achievementDescription;
            if(achievement.isAchieved()) {
                itemBinding.achievementName.setText(achievement.getName());
                itemBinding.achievementDescription.setText(achievement.getDescription());
                name.setTextColor(getColor(R.color.white));
                description.setTextColor(getColor(R.color.white));
            } else {
                itemBinding.achievementName.setText("???");
                itemBinding.achievementDescription.setText("???");
                name.setTextColor(getColor(R.color.black));
                description.setTextColor(getColor(R.color.black));
            }
            return view;
        }
    };

    public void onBackClick(View v){
        finish();
    }
}
