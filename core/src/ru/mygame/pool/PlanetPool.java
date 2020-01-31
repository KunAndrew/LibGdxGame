package ru.mygame.pool;

import ru.mygame.base.SpritesPool;
import ru.mygame.math.Rect;
import ru.mygame.sprite.MainShip;
import ru.mygame.sprite.Planet;

public class PlanetPool extends SpritesPool<Planet> {

    private final Rect worldBounds;
    private final MainShip mainShip;

    public PlanetPool(Rect worldBounds, MainShip mainShip) {
        this.worldBounds = worldBounds;
        this.mainShip = mainShip;
    }

    @Override
    public Planet obtain() {
        dispose();
        return super.obtain();
    }

    @Override
    public Planet newObject() {
        return new Planet(worldBounds, mainShip.getV());
    }
}
