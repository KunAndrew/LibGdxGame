package ru.mygame.pool;

import ru.mygame.base.SpritesPool;
import ru.mygame.math.Rect;
import ru.mygame.sprite.Bonus;

public class BonusPool extends SpritesPool<Bonus> {
    private Rect worldBounds;

    public BonusPool(Rect worldBounds) {
        this.worldBounds = worldBounds;

    }

    @Override
    public Bonus newObject() {
        return new Bonus(worldBounds);
    }
}
