package ru.mygame.pool;

import ru.mygame.base.SpritesPool;
import ru.mygame.math.Rect;
import ru.mygame.sprite.Planet;

public class PlanetPool extends SpritesPool<Planet> {

    private final Rect worldBounds;

    public PlanetPool(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }
    @Override
    public Planet obtain() {
        dispose();
        return super.obtain();
    }
    @Override
    public Planet newObject() {
        return new Planet(worldBounds);
    }
}
