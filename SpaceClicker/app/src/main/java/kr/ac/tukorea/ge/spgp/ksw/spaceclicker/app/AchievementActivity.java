package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.databinding.AchievementLayoutBinding;

public class AchievementActivity extends AppCompatActivity {
    private AchievementLayoutBinding biding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biding = AchievementLayoutBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());
    }

    public void onBackClick(View v){
        finish();
    }
}
