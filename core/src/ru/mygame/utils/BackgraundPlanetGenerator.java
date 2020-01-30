package ru.mygame.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import ru.mygame.math.Rect;
import ru.mygame.pool.PlanetPool;
import ru.mygame.sprite.Planet;

public class BackgraundPlanetGenerator {
    private static final float PLANET_HEIGHT = 0.8f;
    // private final TextureRegion[] planets=new TextureRegion[20];
    private PlanetPool planetPool;
    public TextureAtlas.AtlasRegion planet1;
    private TextureRegion[] planet;
    private Rect worldBounds;
    public ArrayList<TextureRegion[]> textureRegions;
    private TextureAtlas atlas;

    public BackgraundPlanetGenerator(TextureAtlas atlas, PlanetPool planetPool, Rect worldBounds) {
        this.planetPool = planetPool;
        float type = (float) Math.random();
        int i = (int) (type * 10);
        if (i == 8 || i == 9 || i == 0) {
            i = 11;
        }                             //временно. нужно поправить pack-и

        this.planet = Regions.split(atlas.findRegion("planet" + i), 1, 1, 1);
        this.worldBounds = worldBounds;
        this.atlas = atlas;
    }

    public void generate() {
        Planet currentPlanet = planetPool.obtain();
        float type = (float) Math.random();
        int i = (int) (type * 10);
        if (i == 8 || i == 9 || i == 0) {
            i = 11;
        }                             //временно. нужно поправить pack-и

        currentPlanet.set(Regions.split(atlas.findRegion("planet" + i), 1, 1, 1)
                , PLANET_HEIGHT);
        currentPlanet.pos.x = worldBounds.getLeft() + currentPlanet.getHalfWidth();
        //currentPlanet.setBottom(worldBounds.getTop() - currentPlanet.getHalfHeight());
        currentPlanet.setBottom(worldBounds.getTop() + 0f);
        currentPlanet.setRight(worldBounds.getRight() + currentPlanet.getHalfWidth() * 0.75f);
        System.out.println("PLANET GENERATED " + currentPlanet.pos);
    }
}
