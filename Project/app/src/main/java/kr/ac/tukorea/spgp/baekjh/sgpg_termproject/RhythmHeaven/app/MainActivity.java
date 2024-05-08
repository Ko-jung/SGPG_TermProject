package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_main);
//        if (BuildConfig.DEBUG) {
//            startActivity(new Intent(this, CookieRunActivity.class));
//        }
    }

    public void onClickInTitle(View view) {
        startActivity(new Intent(this, RhythmActivity.class));
    }
}