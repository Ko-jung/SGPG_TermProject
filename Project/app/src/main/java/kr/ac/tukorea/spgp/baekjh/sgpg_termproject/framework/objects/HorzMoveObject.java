package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class HorzMoveObject extends Sprite {
    protected float speed;
    public HorzMoveObject(int bitmapResId, float speed) {
        super(bitmapResId);
        this.height = bitmap.getHeight() * Metrics.width / bitmap.getWidth();
        setPosition(0,0,0,0);
        this.speed = speed;
    }

    @Override
    public void update(float elapsedSeconds) {
        this.x -= speed * elapsedSeconds; // x 값을 스크롤된 양으로 사용한다
    }
}
