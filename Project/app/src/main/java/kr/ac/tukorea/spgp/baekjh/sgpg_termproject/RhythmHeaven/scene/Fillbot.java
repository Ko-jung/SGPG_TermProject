package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.app.RhythmActivity;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.main.CollisionChecker;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Convayor;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Filler;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Robot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IGameObject;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.VertScrollBackground;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.scene.Scene;

public class Fillbot extends Scene {
    public enum Layer {
        bg, filler, conveyor, robot, controller, COUNT
    }
    private Context context;

    public Fillbot(Context context) {
        this.context = context;

        initLayers(Fillbot.Layer.COUNT);
        add(Layer.bg, new VertScrollBackground(R.mipmap.white_back_ground, 1.f));

        add(Layer.filler, new Filler(R.mipmap.fillbotsfillersprite));

        add(Layer.conveyor, new Convayor(R.mipmap.fillbotsconveyorsprite, 1.f));

        add(Layer.robot, new Robot(R.mipmap.fillbotsbotsprite, 1.f));

        add(Layer.controller, new CollisionChecker(this));
    }
    @Override
    public boolean onTouch(MotionEvent event)  {
        Log.v("test", "onTouch in Fillbot");
        ArrayList<IGameObject> object = layers.get(Fillbot.Layer.filler.ordinal());
        for (IGameObject o : object) {
            if(o instanceof Filler) {
                return ((Filler) o).onTouchEvent(event);
            }
        }

        return false;
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

        if(Robot.deleteCount >= 2){
            //setContentView(gameView);
            pop();

            Intent intent = new Intent(context, RhythmActivity.class);
            intent.putExtra("FillBot_Score", Score);

            context.startActivity(intent);
            //new Fillbot().push();
        }
    }

    public void AddScore(int CollisionLevel) {
        if (CollisionLevel == 0) {
            Score += 1;
        } else if (CollisionLevel == 1) {
            Score += 3;
        } else if (CollisionLevel == 2){
            Score += 7;
        }
        Score = 140;
    }
}
