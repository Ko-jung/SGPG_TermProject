package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene;

import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Convayor;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Filler;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Robot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IGameObject;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.VertScrollBackground;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.scene.Scene;

public class Fillbot extends Scene {
    private enum Layer {
        bg, filler, conveyor, robot, COUNT
    }

    public Fillbot() {
        initLayers(Fillbot.Layer.COUNT);
        add(Layer.bg, new VertScrollBackground(R.mipmap.background, 1.f));

        add(Layer.filler, new Filler(R.mipmap.fillbotsfillersprite));

        add(Layer.conveyor, new Convayor(R.mipmap.fillbotsconveyorsprite, 1.f));

        add(Layer.robot, new Robot(R.mipmap.fillbotsbotsprite, 1.f));
    }
    @Override
    public boolean onTouch(MotionEvent event)  {
        Log.v("test", "onTouch in Fillbot");
        ArrayList<IGameObject> object = layers.get(Fillbot.Layer.filler.ordinal());
        for (int i = 0; i < object.size(); ++i)
        {
            
            object[i];
        }
        return true;
    }
}
