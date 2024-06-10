package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface IGameObject {
    public void update(float elapsedSeconds);
    public void draw(Canvas canvas);
}
