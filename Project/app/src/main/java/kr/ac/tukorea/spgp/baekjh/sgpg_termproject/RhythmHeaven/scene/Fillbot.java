package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene;

import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.scene.Scene;

public class Fillbot extends Scene {
    private enum Layer {
        bg, pump, conveyor, robot, COUNT
    }

    public Fillbot() {
        initLayers(MainScene.Layer.COUNT);


    }
    @Override
    public boolean onTouch(MotionEvent event)  {
        Log.v("test", "onTouch in Fillbot");
        return true;
    }
}
