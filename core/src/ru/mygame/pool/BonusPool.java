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
    public Bonus obtain() {
        dispose();
        return super.obtain();
    }

    @Override
    public Bonus newObject() {
        float type = (float) Math.random();
        if (type < 0.5f) {
            System.out.println("HP");
            return new Bonus(worldBounds, Bonus.BonusType.HP);
        } else {
            System.out.println("SHP");
            return new Bonus(worldBounds, Bonus.BonusType.SP);
        }

    }
}

