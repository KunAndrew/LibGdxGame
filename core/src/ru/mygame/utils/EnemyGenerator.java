package ru.mygame.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.math.Rect;
import ru.mygame.math.Rnd;
import ru.mygame.pool.EnemyPool;
import ru.mygame.sprite.EnemyShip;

public class EnemyGenerator {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.15f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.25f;
    private static final int ENEMY_MEDIUM_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 4f;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_BIG_HP = 10;

    private final TextureRegion[] enemySmallRegions;
    private final TextureRegion[] enemyMediumRegions;
    private final TextureRegion[] enemyBigRegions;
    private final TextureRegion bulletRegion;

    private final Vector2 enemySmallV = new Vector2(0f, -0.2f);
    private final Vector2 enemyMediumV = new Vector2(0f, -0.03f);
    private final Vector2 enemyBigV = new Vector2(0f, -0.005f);

    private float generateInterval = 4f;
    private float generateTimer = generateInterval;

    private EnemyPool enemyPool;
    private Rect worldBounds;

    private int level = 1;

    public EnemyGenerator(TextureAtlas atlas, EnemyPool enemyPool, Rect worldBounds) {
        TextureRegion enemy0 = atlas.findRegion("enemy0");
        this.enemySmallRegions = Regions.split(enemy0, 1, 2, 2);
        TextureRegion enemy1 = atlas.findRegion("enemy1");
        this.enemyMediumRegions = Regions.split(enemy1, 1, 2, 2);
        TextureRegion enemy2 = atlas.findRegion("enemy2");
        this.enemyBigRegions = Regions.split(enemy2, 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
    }

    public void generate(float delta, int frags) {
        level = frags / 15 + 1;
        generateTimer += delta;
        if (generateTimer > generateInterval) {
            generateTimer = 0f;
            EnemyShip enemyShip = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                enemyShip.set(
                        enemySmallRegions,
                        enemySmallV,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_DAMAGE * level,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HP,
                        ENEMY_SMALL_HEIGHT
                );
            } else if (type < 0.8f) {
                enemyShip.set(
                        enemyMediumRegions,
                        enemyMediumV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_DAMAGE * level,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HP,
                        ENEMY_MEDIUM_HEIGHT
                );
            } else {
                enemyShip.set(
                        enemyBigRegions,
                        enemyBigV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_DAMAGE * level,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HP,
                        ENEMY_BIG_HEIGHT
                );
            }
            enemyShip.pos.x = Rnd.nextFloat(
                    worldBounds.getLeft() + enemyShip.getHalfWidth(),
                    worldBounds.getRight() - enemyShip.getHalfWidth()
            );
            enemyShip.setBottom(worldBounds.getTop());
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}