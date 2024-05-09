package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.app;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.Fillbot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.MainScene;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.activity.GameActivity;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class RhythmActivity extends GameActivity {

    private MainScene mainScene;

    private enum rank{ IRON, BRONZE, SILVER, GOLD };
    private String gameList[] = {new String("로봇 공장"), new String("팬클럽"), new String("슈팅")};
    private int gameImgIds[] = {R.mipmap.fillbotsroundtitleimage, R.mipmap.fanclubroundtitleimage, R.mipmap.shootingroundtitleimage};
    private int gameRankIds[] = {R.mipmap.ironcover, R.mipmap.bronzecover, R.mipmap.silvercover, R.mipmap.goldcover};
    private int gameRank[] = {0, 0, 0};
    private int currentSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentSelect = 0;

        //Scene.drawsDebugInfo = BuildConfig.DEBUG;
        //Metrics.setGameSize(16, 9);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_game);
        // Scene.drawsDebugInfo 변경 시점에 주의한다.
        // new GameView() 가 호출되는 super.onCreate() 보다 이전에 해야 한다.
        new MainScene().push();

        TextView gameText = (TextView)findViewById(R.id.selectGameText);
        gameText.setText(gameList[0]);
    }

    public void onBtnOption(View view) {
        //open option window
    }

    public void onCLickGameTitle(View view) {
        switch (currentSelect){
            case 0:
                setContentView(gameView);
                new Fillbot().push();
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    private void processSelect() {
        ImageView leftImage = (ImageView)findViewById(R.id.leftAngleBracket);
        ImageView rightImage = (ImageView)findViewById(R.id.rightAngleBracket);
        TextView gameText = (TextView)findViewById(R.id.selectGameText);
        ImageView gameTileView = findViewById(R.id.titleGameImg);
        ImageView gameRankView = findViewById(R.id.rankAngleBracket);

        if(currentSelect == 0) {
            leftImage.setVisibility(View.INVISIBLE);
        }
        else if (currentSelect == 2) {
            rightImage.setVisibility(View.INVISIBLE);
        }
        else {
            leftImage.setVisibility(View.VISIBLE);
            rightImage.setVisibility(View.VISIBLE);
        }

        gameText.setText(gameList[currentSelect]);
        gameTileView.setImageResource(gameImgIds[currentSelect]);
        gameRankView.setImageResource(gameRankIds[gameRank[currentSelect]]);
    }

    public void onBtnLeft(View view) {
        if(currentSelect > 0) {
            currentSelect -= 1;
        }
        processSelect();
    }

    public void onBtnRight(View view) {
        if(currentSelect < 2)
        {
            currentSelect += 1;
        }
        processSelect();
    }
}
