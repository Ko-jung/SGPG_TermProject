package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.MainScene;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.activity.GameActivity;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class RhythmActivity extends GameActivity {
    private int gameList[];
    private int currentSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentSelect = 0;

        //Scene.drawsDebugInfo = BuildConfig.DEBUG;
        Metrics.setGameSize(16, 9);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_game);
        // Scene.drawsDebugInfo 변경 시점에 주의한다.
        // new GameView() 가 호출되는 super.onCreate() 보다 이전에 해야 한다.
        new MainScene().push();
    }

    public void onBtnOption(View view) {
        //open option window
    }

    public void onBtnLeft(View view) {
        if(currentSelect > 0) {
            currentSelect -= 1;
            ImageView leftImage = (ImageView)findViewById(R.id.leftAngleBracket);
            ImageView rightImage = (ImageView)findViewById(R.id.rightAngleBracket);
            if(currentSelect == 0) {
                leftImage.setVisibility(View.INVISIBLE);
            }
            rightImage.setVisibility(View.VISIBLE);
        }
        Log.v("test", "onBtnLeft in RhythmActivity");
    }

    public void onBtnRight(View view) {
        if(currentSelect < 2)
        {
            currentSelect += 1;
            ImageView leftImage = (ImageView)findViewById(R.id.leftAngleBracket);
            ImageView rightImage = (ImageView)findViewById(R.id.rightAngleBracket);
            if(currentSelect == 2) {
                rightImage.setVisibility(View.INVISIBLE);
            }
            leftImage.setVisibility(View.VISIBLE);
        }
        Log.v("test", "onBtnRight in RhythmActivity");
    }
}
