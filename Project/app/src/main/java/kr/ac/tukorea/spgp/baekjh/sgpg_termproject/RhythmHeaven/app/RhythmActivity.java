package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.app;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kotlin.collections.IntIterator;
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
    private int gameRankIds[] = {R.mipmap.ironcover, R.mipmap.bronzecover, R.mipmap.silvercover, R.mipmap.goldcover, R.mipmap.goldcover};
    private int gameRank[] = {0,0,0};
    private int currentSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentSelect = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_game);
        new MainScene().push();

        TextView gameText = (TextView)findViewById(R.id.selectGameText);
        gameText.setText(gameList[0]);

        Intent intent = getIntent();
        int score = intent.getIntExtra("FillBot_Score", 0);
        gameRank[0] = score;

        processSelect();

    }

    public void onBtnOption(View view) {
        //open option window
    }

    public void onCLickGameTitle(View view) {
        switch (currentSelect){
            case 0:
                setContentView(gameView);
                new Fillbot(this).push();
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

        TextView rankTextView = (TextView)findViewById(R.id.rankText);
        TextView scoreTextView = (TextView)findViewById(R.id.scoreText);

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
        scoreTextView.setText(Integer.toString(gameRank[currentSelect]));

        String rankString;
        int rankId;
        if(gameRank[currentSelect] == 0) {
            rankString = "NonRank";
            rankId = 0;
        }        else if(gameRank[currentSelect] < 100) {
            rankString = "Bronze";
            rankId = 1;
        }        else if(gameRank[currentSelect] < 120) {
            rankString = "Sliver";
            rankId = 2;
        }        else if(gameRank[currentSelect] <= 130) {
            rankString = "Gold";
            rankId = 3;
        }        else {
            rankString = "Perfect";
            rankId = 4;
        }

        gameRankView.setImageResource(gameRankIds[rankId]);
        rankTextView.setText(rankString);
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
