package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.R;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.databinding.UpgradeActivityBinding;

public class UpgradeActivity extends AppCompatActivity {

    private UpgradeActivityBinding biding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biding = UpgradeActivityBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());
    }
}
