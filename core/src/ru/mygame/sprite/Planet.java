package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class Planet extends Sprite {

    private Vector2 trackingV;
    private Vector2 sumV;
    public Rect worldBounds;
    private Vector2 descentV = new Vector2(0, -0.08f);
    public Planet(Rect worldBounds, Vector2 trackingV) {
        this.worldBounds=worldBounds;
        this.trackingV = trackingV;
        this.sumV = new Vector2();
    }

    public void set(TextureRegion[] region,float bonusHeight){
        this.regions = region;
        setHeightProportion(bonusHeight);
}

    @Override
    public void update(float delta) {
        sumV.setZero().mulAdd(trackingV, 0.1f).rotate(180).add(descentV);
        pos.mulAdd(sumV, delta);
        if (getTop() < worldBounds.getBottom()) {
            destroy();
        }
    }
}
