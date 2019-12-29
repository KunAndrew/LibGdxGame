package ru.mygame.pool;


import ru.mygame.base.SpritesPool;
import ru.mygame.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    public Bullet newObject() {
        return new Bullet();
    }
}
