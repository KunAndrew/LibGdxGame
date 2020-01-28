package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class Bonus extends Sprite {
    public enum BonusType {HP, SP}

    ;
    private BonusType type;
    private static final int HP_AMOUNT = 20;
    private static final int SHP_AMOUNT = 10;
    protected Vector2 v;
    protected Rect worldBounds;
    private Vector2 descentV = new Vector2(0, -0.15f);

    public Bonus(Rect worldBounds, BonusType type) {
        this.worldBounds = worldBounds;
        this.v = descentV;
        this.type = type;
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
        if (type == BonusType.HP) {
            return HP_AMOUNT;
        } else {
            return SHP_AMOUNT;
        }
    }

    public BonusType getType() {
        return type;
    }
}
