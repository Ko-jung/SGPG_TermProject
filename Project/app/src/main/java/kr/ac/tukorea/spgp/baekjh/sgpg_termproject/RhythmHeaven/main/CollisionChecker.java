package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.main;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Convayor;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Filler;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects.Robot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.Fillbot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.MainScene;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IGameObject;

public class CollisionChecker implements IGameObject {
    private final Fillbot scene;
    private boolean IsOverlapped = false;

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


                    for (int i = 2; i >= 0; --i) {
                        if (collides(castFiller.getCollisionRect(), bot.getCollisionRects()[i]))
                        {
                            if(IsOverlapped) break;

                            scene.AddScore(i);

                            Log.d("CollisionChecker", "Overlapping At Index" + Integer.toString(i));
                            Convayor convayor = ((Convayor)convayors.get(0));
                            convayor.SetStopMoving(true);

                            bot.Collide(castFiller);
                            castFiller.Collide(bot);
                            IsOverlapped = true;
                        }
                        else if (IsOverlapped) {
                            Log.d("CollisionChecker", "End Overlap");
                            Convayor convayor = ((Convayor)convayors.get(0));
                            convayor.SetStopMoving(false);

                            bot.EndOverlap(castFiller);
                            castFiller.EndOverlap(bot);

                            scene.AddScore(bot.GetLevel());

                            IsOverlapped = false;
                        }
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
