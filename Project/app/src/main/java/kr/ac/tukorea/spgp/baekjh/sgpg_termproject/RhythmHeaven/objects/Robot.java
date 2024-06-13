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

    public RectF[] CollisionBoxs;
    public float[] CollisionBoxWidths = {0.5f, 0.1f, 0.05f};
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
    @Override
    public void update(float elapsedSeconds) {
        if(!StopMoving)
        {
            this.x += speed * elapsedSeconds; // x 값을 스크롤된 양으로 사용한다
            dstRect.set(x, y, x + width, y + height);
        }
        else {
            // TODO: 그냥 움직이는 Object를 관리할 클래스 만들기
            scoreTimer += elapsedSeconds;
            if(timer < 4.f)
                timer += elapsedSeconds;
        }

        SyncRects();
    }

    public void Collide(IGameObject CollideTarget) {
        if (CollideTarget instanceof Filler)
        {
            StopMoving = true;

            this.x = 4.75f - width / 2; // x 값을 스크롤된 양으로 사용한다
            dstRect.set(x, y, x + width, y + height);

            SyncRects();
        }
    }

    public void EndOverlap(IGameObject CollideTarget) {
        if (CollideTarget instanceof Filler)
        {
            StopMoving = false;
        }
    }

    public int GetScore(){
        if (-0.1f < (scoreTimer - 4.f) && (scoreTimer - 4.f) < 0.1f){
            return 7;
        } else if (-0.25f < (scoreTimer - 4.f) && (scoreTimer - 4.f) < 0.25f) {
            return 3;
        } else if (-0.5f < (scoreTimer - 4.f) && (scoreTimer - 4.f) < 0.5f) {
            return 1;
        } else {
            return 0;
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

