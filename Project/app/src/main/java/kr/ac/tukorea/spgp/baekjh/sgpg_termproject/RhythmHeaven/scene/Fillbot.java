package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene;

import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.scene.Scene;

public class Fillbot extends Scene {
    private enum Layer {
        bg, pump, conveyor, robot, COUNT
    }

    public Fillbot() {
        initLayers(MainScene.Layer.COUNT);
        add(Layer.bg, new HorzScrollBackground(R.mipmap.background, 1.0f));

        add(Layer.bg, new HorzScrollBackground(R.mipmap.background, 1.0f));

    }
    @Override
    public boolean onTouch(MotionEvent event)  {
        Log.v("test", "onTouch in Fillbot");
        return true;
    }
}
