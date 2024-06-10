package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.SheetSprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.Sprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class Robot extends SheetSprite {
    private float speed;

    private Sprite fluidColor;
    //protected static Rect[][] srcRectsArray;
    protected static Rect[] srcRectsArray = {
            new Rect(1,1,64,112),
            new Rect(67,1,130,112),
            new Rect(133,1,196,112),
    };
    public Robot(int mipmapId, float speed){
        super(mipmapId, 3);
        this.speed = speed;
        srcRects = srcRectsArray;
        setPosition(0.f,11.5f,4.f,4.f);

        fluidColor = new Sprite(R.mipmap.fluidcolor);
        fluidColor.setPosition(0.f,13.5f,4.f,2.f);
    }

    private float timer = 0.f;
    @Override
    public void update(float elapsedSeconds) {
        this.x += speed * elapsedSeconds; // x 값을 스크롤된 양으로 사용한다
        dstRect.set(x, y, x + width, y + height);

        // TODO: 그냥 움직이는 Object를 관리할 클래스 만들기
        if(timer < 4.f)
            timer += elapsedSeconds;
        fluidColor.setUseRectPosition(dstRect.left,dstRect.bottom - (dstRect.bottom - dstRect.top) * (timer / 4.f),dstRect.right,dstRect.bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        fluidColor.draw(canvas);
        super.draw(canvas);
    }
}

