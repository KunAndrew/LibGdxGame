package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class Bonus extends Sprite {
    private static final int AMOUNT = 20;
    protected Vector2 v;
    protected Rect worldBounds;
    private Vector2 descentV = new Vector2(0, -0.15f);

    public Bonus(Rect worldBounds) {
        this.worldBounds = worldBounds;
        this.v = descentV;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(TextureRegion[] regions, float bonusHeight) {
        this.regions = regions;
        setHeightProportion(bonusHeight);
    }

    public int getAmountBonus() {
        return AMOUNT;
    }
}
