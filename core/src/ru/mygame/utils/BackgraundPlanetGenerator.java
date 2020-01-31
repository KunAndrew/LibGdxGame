package ru.mygame.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.mygame.math.Rect;
import ru.mygame.math.Rnd;
import ru.mygame.pool.PlanetPool;
import ru.mygame.sprite.Planet;

public class BackgraundPlanetGenerator {
    private static final float PLANET_HEIGHT = 0.8f;
    private PlanetPool planetPool;
    private Rect worldBounds;
    private TextureAtlas atlas;

    public BackgraundPlanetGenerator(TextureAtlas atlas, PlanetPool planetPool, Rect worldBounds) {
        this.planetPool = planetPool;
        this.worldBounds = worldBounds;
        this.atlas = atlas;
    }

    public void generate() {
        Planet currentPlanet = planetPool.obtain();
        float type = (float) Math.random();
        int i = (int) (type * 18);
        if (i > 18 || i < 1) {
            i = 18;
        }
        currentPlanet.set(Regions.split(atlas.findRegion("planet" + i), 1, 1, 1)
                , PLANET_HEIGHT * Rnd.nextFloat(0.5f, 1.5f));
        currentPlanet.setBottom(worldBounds.getTop() + Rnd.nextFloat(0f, 3f));
        currentPlanet.setRight(Rnd.nextFloat(worldBounds.getLeft() + currentPlanet.getHalfWidth(),
                worldBounds.getRight() + currentPlanet.getHalfWidth() * 0.8f));
        System.out.println("PLANET GENERATED " + currentPlanet.pos);
    }
}
