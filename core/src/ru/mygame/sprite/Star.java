package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;
import ru.mygame.math.Rnd;

public class Star extends Sprite {

    private Vector2 v;
    private Rect worldBounds;

    private float height;

    private float starAnimateInterval;
    private float starAnimateTimer;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        float vx = Rnd.nextFloat(-0.005f, 0.005f);
        float vy = Rnd.nextFloat(-0.2f, -0.03f);
        v = new Vector2(vx, vy);
        starAnimateInterval = 1f;
        starAnimateTimer = Rnd.nextFloat(0, 1f);
        height = 0.01f;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        float posx = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posy = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posx, posy);
        setHeightProportion(height);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkBounds();
        height += 0.00005f;
        setHeightProportion(height);
        starAnimateTimer += delta;
        if (starAnimateTimer > starAnimateInterval) {
            starAnimateTimer = 0f;
            height = 0.01f;
        }
    }

    private void checkBounds() {
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
     }
}
