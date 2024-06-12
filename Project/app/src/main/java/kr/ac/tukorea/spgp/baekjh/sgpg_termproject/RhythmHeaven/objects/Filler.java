package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IGameObject;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.SheetSprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class Filler extends SheetSprite implements IBoxCollidable {


    public enum State {
        idle, touchDown, holding, touchUp, filling, COUNT
    }
    protected State state = State.idle;
    protected static Rect[][] srcRectsArray = {
            makeRects(28),      // State.idle
            makeRects(25,19,13,7,1,29,29,36,36,36,36,36), // State.TouchDown
            makeRects(36), // State.holding
            makeRects(29,1,7,13,19,25), // State.touchUp
            makeRects(1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,2,2,2,2,2,1,1,1,1,1) // State.Filling.

    };

    private RectF CollisionBox = new RectF();

    public boolean IsCanCollision = false;

    public Filler(int mipmapId) {
        super(mipmapId, 30);

        this.height = bitmap.getHeight() * Metrics.width / bitmap.getWidth();
        //setPosition(0, 0, Metrics.width, height);

        setPosition(4.5f, 11.0f, 9.f, 16.0f);
        srcRects = srcRectsArray[state.ordinal()];

        CollisionBox.set(4.75f - 0.5f, 11.5f - 0.5f, 4.75f + 0.5f, 11.5f + 0.5f);
    }
    protected static Rect[] makeRects(int... indices) {
        Rect[] rects = new Rect[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            int l = 0 + (idx % 10) * 258;
            int t = 0 + (idx / 10) * 258;
            rects[i] = new Rect(l + 1, t + 1, l + 256, t + 256);
        }
        return rects;
    }

    public void update(float elapsedSeconds){
        super.update(elapsedSeconds);

        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        int index = Math.round(time * fps);

        if(state == State.touchDown && index >= srcRectsArray[state.ordinal()].length - 1) {
            createdOn = System.currentTimeMillis();
            state = State.holding;
            srcRects = srcRectsArray[state.ordinal()];
            IsCanCollision = false;
        } else if (state == State.touchUp && index >= srcRectsArray[state.ordinal()].length - 1) {
            createdOn = System.currentTimeMillis();
            state = State.idle;
            srcRects = srcRectsArray[state.ordinal()];
        }
    }

    public boolean onTouchEvent(MotionEvent e){
        if(e.getAction() == MotionEvent.ACTION_DOWN)
        {
            Log.d("Filler", "MotionEvent.ACTION_DOWN");
            state = State.touchDown;
            srcRects = srcRectsArray[state.ordinal()];
            createdOn = System.currentTimeMillis();
            IsCanCollision = true;
            return true;
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            Log.d("Filler", "MotionEvent.ACTION_UP");
            state = State.touchUp;
            srcRects = srcRectsArray[state.ordinal()];
            createdOn = System.currentTimeMillis();
            IsCanCollision = false;
            return true;
        }

        return false;
    }

    @Override
    public RectF getCollisionRect(){
        if(IsCanCollision) {
            return CollisionBox;
        }
        return new RectF();
    }

    public void Collide(IGameObject CollideTarget) {
        if (CollideTarget instanceof Robot){
            state = State.filling;
            srcRects = srcRectsArray[state.ordinal()];
        }
    }
    public void EndOverlap(IGameObject CollideTarget) {
        if (CollideTarget instanceof Robot){
        }
    }
}

