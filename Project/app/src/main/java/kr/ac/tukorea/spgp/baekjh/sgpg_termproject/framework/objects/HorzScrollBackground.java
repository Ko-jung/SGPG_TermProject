package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class HorzScrollBackground extends HorzMoveObject {
    private final float height;
    public HorzScrollBackground(int bitmapResId, float speed) {
        super(bitmapResId, speed);

        this.height = bitmap.getHeight() * Metrics.width / bitmap.getWidth();
        setPosition(Metrics.width / 2, Metrics.height / 2, Metrics.width, height);
        this.speed = speed;
    }

    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas);
        float curr = x % width;
        if (curr > 0) curr -= width;
        while (curr < Metrics.width) {
            dstRect.set(curr, 0, curr + width, Metrics.height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += width;
        }
    }
}
