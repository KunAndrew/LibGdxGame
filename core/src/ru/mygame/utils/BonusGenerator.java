package ru.mygame.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.mygame.math.Rect;
import ru.mygame.math.Rnd;
import ru.mygame.pool.BonusPool;
import ru.mygame.sprite.Bonus;

public class BonusGenerator {
    private static final float BONUS_HEIGHT = 0.065f;

    private final TextureRegion[] bonusRegion;
    private final TextureRegion[] bonusRegion2;

    private float generateInterval = 16f;
    private float generateTimer = generateInterval;

    private BonusPool bonusPool;
    private Rect worldBounds;

    public BonusGenerator(TextureAtlas atlas, BonusPool bonusPool, Rect worldBounds) {
        TextureRegion bonus = atlas.findRegion("HP_Bonus");
        TextureRegion bonus2 = atlas.findRegion("Shields_Bonus");
        this.bonusRegion = Regions.split(bonus, 1, 1, 1);
        this.bonusRegion2 = Regions.split(bonus2, 1, 1, 1);
        this.bonusPool = bonusPool;
        this.worldBounds = worldBounds;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer > generateInterval) {
            generateTimer = 0f;
            Bonus bonus = bonusPool.obtain();
            if (bonus.getType() == Bonus.BonusType.HP) {
                bonus.set(bonusRegion, BONUS_HEIGHT);
            } else {
                bonus.set(bonusRegion2, BONUS_HEIGHT);
            }
            bonus.pos.x = Rnd.nextFloat(
                    worldBounds.getLeft() + bonus.getHalfWidth(),
                    worldBounds.getRight() - bonus.getHalfWidth()
            );
            System.out.println("BONUS" + bonus.pos.x + " :" + bonus.pos.y);

            bonus.setBottom(worldBounds.getTop());
        }
    }
}
