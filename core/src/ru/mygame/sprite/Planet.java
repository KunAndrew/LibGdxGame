package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class Planet extends Sprite {
    public Rect worldBounds;
    private Vector2 descentV = new Vector2(0, -0.3f);
    public Planet(Rect worldBounds) {
        this.worldBounds=worldBounds;
    }

    public void set(TextureRegion[] region,float bonusHeight){
        this.regions = region;
        setHeightProportion(bonusHeight);
}
//    @Override
//    public void resize(Rect worldBounds) {
//        super.resize(worldBounds);
//        setHeightProportion(0.8f);
//        setRight(worldBounds.getRight()+getHalfWidth()*0.75f);
//    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(descentV, delta);
        if (getTop() < worldBounds.getBottom()) {
            destroy();
        }
    }
}
