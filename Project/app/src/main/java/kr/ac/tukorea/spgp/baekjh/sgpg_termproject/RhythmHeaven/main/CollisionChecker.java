package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.main;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Convayor;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Filler;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Robot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.Fillbot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.MainScene;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IGameObject;

public class CollisionChecker implements IGameObject {
    private final Fillbot scene;

    public CollisionChecker(Fillbot scene) {
        this.scene = scene;
    }

    @Override
    public void update(float elapsedSeconds) {
        ArrayList<IGameObject> fillers = scene.objectsAt(Fillbot.Layer.filler);
        ArrayList<IGameObject> robots = scene.objectsAt(Fillbot.Layer.robot);
        ArrayList<IGameObject> convayors = scene.objectsAt(Fillbot.Layer.conveyor);

        for (IGameObject f : fillers) {
            if(f instanceof Filler) {
                Filler castFiller = (Filler)f;
                for (IGameObject r : robots) {
                    Robot bot = (Robot)r;

                    if (collides(castFiller.getCollisionRect(), bot.getCollisionRect()))
                    {
                        Convayor convayor = ((Convayor)convayors.get(0));
                        convayor.SetSpeed(0.f);

                        bot.Collide(castFiller);
                        castFiller.Collide(bot);
                    }
                }
            }
        }
    }

    public static boolean collides(RectF r1, RectF r2) {
        if (r1.left > r2.right) return false;
        if (r1.top > r2.bottom) return false;
        if (r1.right < r2.left) return false;
        if (r1.bottom < r2.top) return false;

        return true;
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
