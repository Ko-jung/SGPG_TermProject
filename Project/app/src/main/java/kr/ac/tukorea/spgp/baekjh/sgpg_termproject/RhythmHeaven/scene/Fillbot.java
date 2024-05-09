package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Convayor;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Robot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.Sprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.VertScrollBackground;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.scene.Scene;

public class Fillbot extends Scene {
    private enum Layer {
        bg, pump, conveyor, robot, COUNT
    }

    public Fillbot() {
        initLayers(Fillbot.Layer.COUNT);
        add(Layer.bg, new VertScrollBackground(R.mipmap.background, 1.f));

        add(Layer.conveyor, new Convayor(R.mipmap.fillbotsconveyorsprite, 1.f));

        add(Layer.robot, new Robot(R.mipmap.fillbotsbotsprite, 1.f));

    }
    @Override
    public boolean onTouch(MotionEvent event)  {
        Log.v("test", "onTouch in Fillbot");
        return true;
    }
}
