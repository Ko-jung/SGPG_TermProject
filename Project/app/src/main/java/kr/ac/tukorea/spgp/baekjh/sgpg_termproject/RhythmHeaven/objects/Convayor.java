package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.Sprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class Convayor extends Sprite {
    private float speed;
    public Convayor(int mipmapId, float speed) {
        super(mipmapId);
        setPosition(4.5f, 15.5f, 9.0f, 1.0f);
        this.speed = speed;
    }

    @Override
    public void update(float elapsedSeconds) {
        this.x += speed * elapsedSeconds; // x 값을 스크롤된 양으로 사용한다
    }

    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas);
        float curr = x % width;
        if (curr > 0) curr -= width;
        while (curr < Metrics.width) {
            float posY = dstRect.top;
            dstRect.set(curr, y, curr + width, y + height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += width;
        }
    }

}

