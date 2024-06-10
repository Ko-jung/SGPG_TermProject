package kr.ac.tukorea.spgp.baekjh.sgpg_termproject.RhythmHeaven.objects;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.R;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.interfaces.IGameObject;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.SheetSprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.objects.Sprite;
import kr.ac.tukorea.spgp.baekjh.sgpg_termproject.framework.view.Metrics;

public class Robot extends SheetSprite implements IBoxCollidable {
    private float speed;

    public RectF CollisionBox = new RectF();
    private boolean StopMoving = false;

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

    private void SyncRects() {
        CollisionBox.set(  (x + x + width) / 2 - 0.5f, y - 0.5f,
                (x + x + width) / 2 + 0.5f, y + 0.5f);
        fluidColor.setUseRectPosition(dstRect.left,dstRect.bottom - (dstRect.bottom - dstRect.top) * (timer / 4.f),dstRect.right,dstRect.bottom);
    }

    private float timer = 0.f;
    @Override
    public void update(float elapsedSeconds) {
        if(!StopMoving)
        {
            this.x += speed * elapsedSeconds; // x 값을 스크롤된 양으로 사용한다
            dstRect.set(x, y, x + width, y + height);
        }
        else {
            // TODO: 그냥 움직이는 Object를 관리할 클래스 만들기
            if(timer < 4.f)
                timer += elapsedSeconds;
        }

        SyncRects();
    }

    public void Collide(IGameObject CollideTarget) {
        if (CollideTarget instanceof Filler)
        {
            StopMoving = true;
            dstRect.set(4.75f - width / 2, 11.5f, 4.75f + width / 2, 11.5f + height);

            SyncRects();
        }
    }


    @Override
    public void draw(Canvas canvas) {
        fluidColor.draw(canvas);
        super.draw(canvas);
    }

    @Override
    public RectF getCollisionRect(){
        return CollisionBox;
    }
}

