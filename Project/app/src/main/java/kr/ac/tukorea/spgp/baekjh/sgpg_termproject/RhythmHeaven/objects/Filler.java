package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects;

import android.graphics.Rect;
import android.view.MotionEvent;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.SheetSprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class Filler extends SheetSprite {
    public enum State {
        idle, touchDown, filling, COUNT
    }
    protected State state = State.idle;
    protected static Rect[][] srcRectsArray = {
            makeRects(28),      // State.idle
            makeRects(28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0,29), // State.TouchDown
            makeRects(1,2,3,2,1) // State.Filling.

    };

    public Filler(int mipmapId) {
        super(mipmapId, 30);

        this.height = bitmap.getHeight() * Metrics.width / bitmap.getWidth();
        //setPosition(0, 0, Metrics.width, height);

        setPosition(4.5f, 8.0f, 9.f, 16.0f);
        srcRects = srcRectsArray[state.ordinal()];
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
        int index = Math.round(time * fps) % srcRects.length;

        if(state == State.touchDown && index >= srcRectsArray[state.ordinal()].length - 1) {
            state = State.idle;
            srcRects = srcRectsArray[state.ordinal()];
        }
    }

    public boolean onTouchEvent(MotionEvent e){
        state = State.touchDown;
        srcRects = srcRectsArray[state.ordinal()];
        createdOn = System.currentTimeMillis();
        return true;
    }
}

