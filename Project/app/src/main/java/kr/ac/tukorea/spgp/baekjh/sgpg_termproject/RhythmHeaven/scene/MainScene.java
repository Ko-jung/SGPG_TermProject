package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene;

import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.scene.Scene;

public class MainScene extends Scene {

    public enum Layer {
        bg, platform, player, COUNT
    }
    //private final Player player;
    public MainScene() {
        initLayers(Layer.COUNT);


    }

    @Override
    public boolean onTouch(MotionEvent event)  {
        Log.v("test", "onTouch in MainScene");
        return true;
    }
}
