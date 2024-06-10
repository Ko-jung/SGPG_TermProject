package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.main;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Filler;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.Fillbot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.MainScene;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IGameObject;

public class CollisionChecker implements IGameObject {
    private final MainScene scene;
    private final Filler filler;

    public CollisionChecker(MainScene scene, Filler filler) {
        this.scene = scene;
        this.filler = filler;
    }

    @Override
    public void update(float elapsedSeconds) {
        ArrayList<IGameObject> fillers = scene.objectsAt(Fillbot.Layer.filler);
        ArrayList<IGameObject> robots = scene.objectsAt(Fillbot.Layer.robot);

        for (IGameObject f : fillers) {
            for (IGameObject r : robots) {

            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
