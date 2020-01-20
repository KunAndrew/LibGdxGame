package ru.mygame.pool;

import com.badlogic.gdx.audio.Sound;

import ru.mygame.base.SpritesPool;
import ru.mygame.math.Rect;
import ru.mygame.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private BulletPool bulletPool;
    private Sound sound;
    private Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, Sound sound, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.sound = sound;
        this.worldBounds = worldBounds;
    }

    @Override
    public EnemyShip newObject() {
        return new EnemyShip(bulletPool, sound, worldBounds);
    }
}
