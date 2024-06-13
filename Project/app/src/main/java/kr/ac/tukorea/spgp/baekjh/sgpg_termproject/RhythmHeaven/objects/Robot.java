package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.Random;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.Fillbot;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.scene.MainScene;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IGameObject;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.SheetSprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.Sprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.scene.Scene;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class Robot extends SheetSprite implements IBoxCollidable {
    private float speed;

    public RectF[] CollisionBoxs;
    public float[] CollisionBoxWidths = {0.5f, 0.1f, 0.05f};
    private boolean StopMoving = false;

    private Sprite fluidColor;
    //protected static Rect[][] srcRectsArray;
     public enum State {
            idle, filling, COUNT
        }
    protected Robot.State state = Robot.State.idle;
    protected static Rect[][] srcRectsArray = {
            makeRects(0),
            makeRects(0,1,2),
    };
    protected static Rect[] makeRects(int... indices) {
        Rect[] rects = new Rect[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            int l = 0 + (idx) * 66;
            int t = 0;
            rects[i] = new Rect(l + 1, t + 1, l + 64, t + 112);
        }
        return rects;
    }

    public Robot(int mipmapId, float speed){
        super(mipmapId, 3);
        this.speed = speed;
        srcRects = srcRectsArray[state.ordinal()];
        setPosition(-4.f,11.5f,4.f,4.f);

        fluidColor = new Sprite(R.mipmap.fluidcolor);
        fluidColor.setPosition(-4.f,13.5f,4.f,2.f);

        CollisionBoxs = new RectF[3];
        for (int i = 0; i < 3; i++) {
            CollisionBoxs[i] = new RectF((x + x + width) / 2 - CollisionBoxWidths[i], y - 0.5f,
                    (x + x + width) / 2 + CollisionBoxWidths[i], y + 0.5f);
        }
    }

    private void SyncRects() {
        for (int i = 0; i < 3; i++) {
            CollisionBoxs[i].set(  (x + x + width) / 2 - CollisionBoxWidths[i], y - 0.5f,
                    (x + x + width) / 2 + CollisionBoxWidths[i], y + 0.5f);
        }
        fluidColor.setUseRectPosition(dstRect.left,dstRect.bottom - (dstRect.bottom - dstRect.top) * (timer / 4.f),dstRect.right,dstRect.bottom);
    }

    private float timer = 0.f;
    private float scoreTimer = 0.f;
    public static int deleteCount = 0;
    @Override
    public void update(float elapsedSeconds) {
        if(!StopMoving)
        {
            this.x += speed * elapsedSeconds; // x 값을 스크롤된 양으로 사용한다
            dstRect.set(x, y, x + width, y + height);

            if(x >= 9.f){
                Scene.top().remove(Fillbot.Layer.robot, this);
                Scene.top().remove(Fillbot.Layer.conveyor);

                float newSpeed = (float)(Math.random() * 3.f + 1.f);
                Scene.top().add(Fillbot.Layer.robot, new Robot(R.mipmap.fillbotsbotsprite, newSpeed));
                Scene.top().add(Fillbot.Layer.conveyor, new Convayor(R.mipmap.fillbotsconveyorsprite, newSpeed));
                deleteCount += 1;
            }
        }
        else {
            // TODO: 그냥 움직이는 Object를 관리할 클래스 만들기
            scoreTimer += elapsedSeconds;
            timer += elapsedSeconds;
            if(timer > 4.f)
                timer = 4.f;
        }

        SyncRects();
    }

    public void Collide(IGameObject CollideTarget) {
        if (CollideTarget instanceof Filler)
        {
            StopMoving = true;

            this.x = 4.75f - width / 2;
            dstRect.set(x, y, x + width, y + height);

            state = State.filling;
            srcRects = srcRectsArray[state.ordinal()];

            SyncRects();
        }
    }

    public void EndOverlap(IGameObject CollideTarget) {
        if (CollideTarget instanceof Filler)
        {
            StopMoving = false;

            state = State.idle;
            srcRects = srcRectsArray[state.ordinal()];
        }
    }

    public int GetLevel(){
        if (-0.1f < (scoreTimer - 4.f) && (scoreTimer - 4.f) < 0.1f){
            return 2;
        } else if (-0.25f < (scoreTimer - 4.f) && (scoreTimer - 4.f) < 0.25f) {
            return 1;
        } else if (-0.5f < (scoreTimer - 4.f) && (scoreTimer - 4.f) < 0.5f) {
            return 0;
        } else {
            return -1;
        }
    }


    @Override
    public void draw(Canvas canvas) {
        fluidColor.draw(canvas);
        super.draw(canvas);
    }

    @Override
    public RectF getCollisionRect(){
        return CollisionBoxs[2];
    }
    public RectF[] getCollisionRects(){
        return CollisionBoxs;
    }
}

