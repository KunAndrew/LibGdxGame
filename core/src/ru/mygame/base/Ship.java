package ru.mygame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.math.Rect;
import ru.mygame.pool.BulletPool;
import ru.mygame.pool.ExplosionPool;
import ru.mygame.sprite.Bullet;
import ru.mygame.sprite.Explosion;

public class Ship extends Sprite {

    protected Vector2 v;
    protected Vector2 v0;

    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected TextureRegion bulletRegion;
    protected float bulletHeight;
    protected Vector2 bulletV;
    protected int damage;

    protected Sound shootSound;

    protected Rect worldBounds;

    protected float reloadInterval;
    protected float reloadTimer;

    protected float damageAnimateInterval = 0.1f;
    protected float damageAnimateTimer = damageAnimateInterval;

    protected int hp;

    public Ship() {
        super();
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= damageAnimateInterval) {
            frame = 0;
        }
    }

    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            destroy();
            hp = 0;
        }
        frame = 1;
        damageAnimateTimer = 0f;
    }

    public int getDamage() {
        return damage;
    }

    public Vector2 getV() {
        return v;
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
        shootSound.play();
    }

    protected void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), this.pos);
    }
}
